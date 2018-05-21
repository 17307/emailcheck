package service;

import beans.Email;
import beans.UserLogin;
import dao.EmailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.FileUtil.DeleteFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

/**
 * Created by XMKZ on 2018/3/3.
 * 获取邮件内容
 */
@Service("getMessage")
public class GetMessage {
    private Store store;
    private Properties properties;
    private Folder folder;
    Message[] messages;
    //   mail_pop3_host--接受邮件服务器地址
    String mail_pop3_host;
    String username;
    String password;
    //数据库
    @Autowired
    EmailDao emailDao;
    //文件相关
    @Autowired
    DeleteFile deleteFile;

    @Autowired
    Email email;

    public Message[] login_email(UserLogin userLogin) throws Exception {
        getMessageConnect(userLogin);
        //storeMessage(username,messages);
        return messages;
    }
    public void getMessageConnect(UserLogin userLogin){
        //邮件的配置信息，返回邮箱的连接对象
        mail_pop3_host = userLogin.getMail_pop3_host();
        username = userLogin.getUsername();
        password = userLogin.getPassword();

        properties = new Properties();
        properties.setProperty("mail.store.protocol","pop3");
        properties.setProperty("mail.pop3.host",mail_pop3_host);
        Session session = Session.getInstance(properties);
        try {
            store = session.getStore("pop3");
        } catch (NoSuchProviderException e) {
            System.out.println("getStore Error!");
        }
        try {
            store.connect(mail_pop3_host,username,password);
        } catch (MessagingException e) {
            System.out.println("Store.connect Error!");
        }
        try {
            //获取邮件服务器的收件箱
            folder = store.getFolder("INBOX");
        } catch (MessagingException e) {
            System.out.println("getFolder Error!");
        }
        //以只读权限打开收件箱
        try {
            folder.open(Folder.READ_ONLY);
        } catch (MessagingException e) {
            System.out.println("folder.open Error!");
        }
        //获取收件箱中的邮件，也可以使用getMessage(int 邮件的编号)来获取具体某一封邮件
        try {
            this.messages = folder.getMessages();
        } catch (MessagingException e) {
            System.out.println("getMessage Error!");
        }
    }
    @Transactional
    public void storeMessage(String username, Message[] messages) throws Exception {

        //清空数据库
        emailDao.deleteEmailByUsername(username);
        //删除文件
        //deleteFile.setUsername(username);
        //deleteFile.delete();
        //创建线程
        Thread thread = null;
        ContentDeal cd = null;
        for (int i = 0; i < messages.length; i++) {

            email.setUsername(username);
            Address[] froms = messages[i].getFrom();
            String subject = messages[i].getSubject();
            String type = messages[i].getContentType();
            int message_num = messages[i].getMessageNumber();

            // System.out.print("<h1>编号</h1>" + message_num + "<br>");
            email.setEmail_id(message_num);
            //System.out.print("<h2>地址</h2><br>");
            if (froms != null) {
                InternetAddress addr = (InternetAddress) froms[0];
                // 发件人地址
                email.setReceive_address(addr.getAddress());

            }
            // 邮件主题
            System.out.println(subject);
            email.setSubject(subject);

            //文件读写
            /*cd = new ContentDeal(username,message_num,messages[i]);
            thread = new Thread(cd);
            thread.run();*/

            //纯文本直接输出-----以下是content内容---存储到文件中
            /*Object o = messages[i].getContent();
            if(o instanceof Multipart) {
                Multipart multipart = (Multipart) o ;
                reMultipart(multipart);
            } else if (o instanceof Part){
                Part part = (Part) o;
                rePart(part);
            } else {
                //System.out.println("类型" + messages[i].getContentType());
                System.out.println("内容" + messages[i].getContent());
            }*/
            //存储邮件
            emailDao.addEmail(email);
            //thread.join();
        }
    }
}
