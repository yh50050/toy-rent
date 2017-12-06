package com.toy.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {
	/**
	 * 发送163邮件
	 * 
	 * @param sender
	 *            发件人
	 * @param passwd
	 *            授权码
	 * @param receiver
	 *            收件人
	 * @param msg
	 *            邮件消息
	 * @return
	 */
	public static boolean sendBy163Email(String sender, String passwd, String receiver, EmailMessage msg) {
		if (null == sender)
			sender = "yanhu32@163.com";
		if (null == passwd)
			passwd = "YAN591750050";
		Properties prop = new Properties();
		prop.put("mail.host", "smtp.163.com");
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", true);
		// 使用java发送邮件5步骤
		// 1.创建sesssion
		Session session = Session.getInstance(prop);
		// 开启session的调试模式，可以查看当前邮件发送状态
		session.setDebug(true);
		// 2.通过session获取Transport对象（发送邮件的核心API）
		try {
			Transport ts = session.getTransport();
			// 3.通过邮件用户名密码链接
			ts.connect("yanhu32@163.com", passwd);
			// 4.创建邮件
			MimeMessage mm = new MimeMessage(session);
			// 设置发件人
			mm.setFrom(new InternetAddress(receiver));
			// 设置收件人
			mm.setRecipient(Message.RecipientType.TO, new InternetAddress("591750050@qq.com"));
			// 设置抄送人
			// mm.setRecipient(Message.RecipientType.CC, new
			// InternetAddress("用户名@163.com"));
			mm.setSubject(msg.getTitle());
			mm.setContent(msg.getContent(), "text/html;charset=gbk");
			Message msg1 = mm;
			// 5.发送电子邮件
			ts.sendMessage(msg1, msg1.getAllRecipients());
			return true;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 发送QQ邮件
	 * 
	 * @param sender
	 *            发件人
	 * @param passwd
	 *            授权码
	 * @param receiver
	 *            收件人
	 * @param msg
	 *            邮件消息
	 * @return
	 */
	public static boolean sendByQQEmail(String sender, String passwd, String receiver, EmailMessage msg) {
		if (null == sender)
			sender = "591750050@qq.com";
		if (null == passwd)
			passwd = "uqbzdguhckgjbfda";
		// 创建Properties 类用于记录邮箱的一些属性
		final Properties props = new Properties();
		// 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		// 此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.qq.com");
		// 端口号，QQ邮箱给出了两个端口，但是另一个我一直使用不了，所以就给出这一个587
		props.put("mail.smtp.port", "587");
		// 此处填写你的账号
		props.put("mail.user", sender);
		// 此处的密码就是前面说的16位STMP口令
		props.put("mail.password", passwd);
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		try {
			InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
			message.setFrom(form);
			// 设置收件人的邮箱
			InternetAddress to = new InternetAddress(receiver);
			message.setRecipient(RecipientType.TO, to);
			// 设置邮件标题
			message.setSubject(msg.getTitle());
			// 设置邮件的内容体
			message.setContent(msg.getContent(), "text/html;charset=UTF-8");
			// 最后当然就是发送邮件啦
			Transport.send(message);
			return true;
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
