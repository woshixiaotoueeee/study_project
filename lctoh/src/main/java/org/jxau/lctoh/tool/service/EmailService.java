package org.jxau.lctoh.tool.service;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jxau.lctoh.tool.config.EmailConfing;
import org.jxau.lctoh.tool.config.EncodingConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * @author qdt_PC
 */
@Service("EmailService")
public class EmailService {
	@Autowired
	Properties props;
	
	public void putprops() throws GeneralSecurityException{
		props.setProperty("mail.transport.protocol", EmailConfing.mailTransportProtocol);   // 使用的协议（JavaMail规范要求）
	    props.setProperty("mail.smtp.host", EmailConfing.myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
	    props.setProperty("mail.smtp.auth", EmailConfing.mailSmtpAuth);  // 需要请求认证 
	    props.setProperty("mail.auth", "true");
	    
	    MailSSLSocketFactory sf = new MailSSLSocketFactory();
	    sf.setTrustAllHosts(true);
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.socketFactory", sf); 

	}
	/**
	 * receiveMailAccount对象发送一封邮件
	 * @param receiveMailAccount
	 * @param popTile 收件人简称
     * @param sendMSGTile 邮件标题
     * @param sendMSGInfo 邮件内容
	 * @throws Exception 
	 */
	public void sendEmail(String receiveMailAccount,String popTile,String sendMSGTile,String sendMSGInfo) throws Exception{
		putprops();
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
	    Session session = Session.getInstance(props);
	    session.setDebug(EmailConfing.debug); // 设置为debug模式, 可以查看详细的发送 log
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, EmailConfing.myEmailAccount, receiveMailAccount, popTile, sendMSGTile, sendMSGInfo);
        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

        transport.connect(EmailConfing.myEmailAccount, EmailConfing.myEmailPassword);
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        // 7. 关闭连接
        transport.close();      
	}
	
    /**
     * 创建一封只包含文本的简单邮件
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @param popTile 收件人简称
     * @param sendMSGTile 邮件标题
     * @param sendMSGInfo 邮件内容
     * @return MimeMessage
     * @throws Exception
     */
    private static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String popTile,String sendMSGTile,String sendMSGInfo) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, EmailConfing.sendTile, EncodingConfig.characterEncoding));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, popTile, EncodingConfig.characterEncoding));
        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(sendMSGTile, EncodingConfig.characterEncoding);
        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(sendMSGInfo, EncodingConfig.produces);
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }
}
