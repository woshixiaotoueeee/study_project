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


/**
 * 解决部分中文编码问题
 * */
@WebFilter("/*")
public class EncodingFilter implements Filter
{
    private String characterEncoding = "utf-8";//默认编码
   // private String characterEncoding = "iso8859-1";//默认编码
    public void destroy()
    {
    }
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        final HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
//        设置post请求的编码
        req.setCharacterEncoding(characterEncoding);
        
//        设置响应的编码
        resp.setCharacterEncoding(characterEncoding);
        resp.setContentType("text/html;charset="+characterEncoding);
        
//        解决get请求乱码
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
                return new String(value.getBytes("iso8859-1"),characterEncoding);
            }
        }), resp);
    }
    public void init(FilterConfig fConfig) throws ServletException
    {
//        使用web.xml文件中配置的编码
    //    characterEncoding = fConfig.getServletContext().getInitParameter("encoding");
    }
}