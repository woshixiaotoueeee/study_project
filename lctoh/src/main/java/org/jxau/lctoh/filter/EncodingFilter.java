package org.jxau.lctoh.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jxau.lctoh.tool.config.charEncoding.EncodingConfig;

/**
 * 解决部分中文编码问题
 * @author qdt_PC
 */
@WebFilter("/*")
public class EncodingFilter implements Filter
{
    private String characterEncoding;//"utf-8";//默认编码
    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy(){}
    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        final HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //设置post请求的编码
        req.setCharacterEncoding(characterEncoding);
        //设置响应的编码
        resp.setCharacterEncoding(characterEncoding);
        resp.setContentType("text/html;charset=".concat(characterEncoding));
        //解决get请求乱码
        chain.doFilter((ServletRequest) Proxy.newProxyInstance(EncodingFilter.class.getClassLoader(),req.getClass().getInterfaces(),new InvocationHandler()
        {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable
            {
                if(!method.getName().equals("getParameter"))//拦截getParameter方法
                {
                    return method.invoke(req, args);
                }
                if(!req.getMethod().equalsIgnoreCase("get"))//拦截get请求
                {
                    return method.invoke(req, args);
                }
                String value = (String) method.invoke(req, args);
                if(value == null)
                    return null;
                return new String(value.getBytes(EncodingConfig.charEncoding),characterEncoding);
            }
        }), resp);
    }
    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException
    {
    	//使用配置类文件中配置的编码
    	characterEncoding = EncodingConfig.characterEncoding;
    }
}