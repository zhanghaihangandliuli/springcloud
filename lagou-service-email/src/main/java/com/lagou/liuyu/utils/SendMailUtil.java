package com.lagou.liuyu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * 发送邮件工具类
 *
 * @author LiuYu
 * @date 2022/5/14 14:27
 */
@Component
public class SendMailUtil{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromUserName;

    /**
     * 发送邮件工具类(普通的文本文件)
     *
     * @param toEmail 收件人邮箱(1********23@qq.com)
     * @param title 邮件的标题
     * @param msg 发送的内容
     */
    public void sendSimpleMail(String toEmail,String title, String msg){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 发件人
        simpleMailMessage.setFrom(fromUserName);
        // 收件人
        simpleMailMessage.setTo(toEmail);
        // 邮件标题
        simpleMailMessage.setSubject(title);
        // 邮件内容
        simpleMailMessage.setText(msg);

        javaMailSender.send(simpleMailMessage);
    }

    /**
     * 发送邮件工具类(富文本邮件---带图片的邮件，支持多张图片)
     * 发送富文本邮件需要使用MimeMessageHelper类，MimeMessageHelper支持发送复杂邮件模板，支持文本、附件、HTML、图片等。
     * 如果需要发送多张图片，可以改变传参方式，使用集合添加多个<img src='cid:rscId'>和 helper.addInline(rscId, res);即可实现'
     *
     * @param toEmail 收件人
     * @param title 邮件标题
     * @param msg 邮件内容
     * @param recPathMap 图片信息 : 图片路径
     */
    public void sendInlineResourceMail(String toEmail, String title, String msg, Map<String, String> recPathMap) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(fromUserName);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(msg, true);

        if (!recPathMap.isEmpty()){
            recPathMap.keySet().forEach(rsc -> {
                FileSystemResource res = new FileSystemResource(new File(recPathMap.get(rsc)));
                try {
                    mimeMessageHelper.addInline(rsc, res);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        }

        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送邮件工具类（发送HTML邮件）
     *
     * @param toEmail 收件人
     * @param title 邮件标题
     * @param msg 邮件内容
     */
    public void sendHtmlMail(String toEmail,String title, String msg) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(fromUserName);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(msg, true);

        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送邮件工具类（发送带附件的邮件）
     * 如果有多个附件，同样可以改变传参方式，使用集合多次调用helper.addAttachment(fileName, file);如多个图片的实现方式
     *
     * @param toEmail 收件人
     * @param title 标题
     * @param msg 内容
     * @param filePath 附件路径
     */
    public void sendAttachmentsMail(String toEmail,String title, String msg, String filePath) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom(fromUserName);
        mimeMessageHelper.setTo(toEmail);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(msg, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        mimeMessageHelper.addAttachment(file.getFilename(), file);

        javaMailSender.send(mimeMessage);
    }

}
