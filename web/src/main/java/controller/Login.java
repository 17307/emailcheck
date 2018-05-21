package controller;

import beans.Email;
import beans.UserLogin;
import dao.EmailDao;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.ContentDeal;
import service.DealJson;
import service.GetMessage;
import service.guolv.PusuBayes;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Created by XMKZ on 2018/3/3.
 * 此页面用于登陆邮箱代理
 */
@Controller
public class Login {
    //记录登陆信息
    @Autowired
    private UserLogin userLogin;
    //登陆邮箱代理
    @Autowired
    @Qualifier("getMessage")
    private GetMessage getMessage;
    //处理返回的json
    @Autowired
    private DealJson dealJson;
    @Autowired
    private PusuBayes pusuBayes;
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private Email email;

    //处理登陆
    @RequestMapping("login.do")
    public ModelAndView loginEmail(@RequestParam String username,
                                   @RequestParam String password,
                                   @RequestParam String mail_pop3_host,
                                   HttpSession httpSession) throws Exception {
        /**
         * @parameter:
         * @return: 返回到 result.jsp */

        //返回到result.jsp
        ModelAndView mav = new ModelAndView("result");
        //获取form中的参数
        userLogin.setUsername(username);
        userLogin.setPassword(password);
        userLogin.setMail_pop3_host(mail_pop3_host);
        //将登陆信息加到userLogin
        mav.addObject("userLogin", userLogin);
        //登陆邮箱 获取邮件
        Message[] messages = getMessage.login_email(userLogin);
        httpSession.setAttribute("messages", messages);
        httpSession.setAttribute("username", username);
        //mav.addObject("messages",messages);
        return mav;
    }

    //处理查看具体邮件
    @RequestMapping("read")
    public String readEmail(@RequestParam String id, @RequestParam String username) {

        return username + "/" + id;
    }

    //产看最后结果
    @RequestMapping("getresult")
    public String test(HttpServletRequest httpServletRequest) {

        return "pageread2";
    }

    //json处理邮件信息
    @RequestMapping("getdata")
    public void listResult(HttpServletResponse response,
                           HttpSession httpSession,
                           @RequestParam int page,
                           @RequestParam int limit) throws IOException, MessagingException, InterruptedException {
        response.setCharacterEncoding("utf8");
        /*返回的json输出流*/
        PrintWriter out = response.getWriter();
        /*message*/
        Message[] messages = (Message[]) httpSession.getAttribute("messages");
        int length = messages.length;
        /*用于查看的username*/
        String username = (String) httpSession.getAttribute("username");
        /*获取返回的message-json格式*/
        JSONObject[] datajsons = dealJson.get_result(messages, page, limit, username, null);
        //返回数据
        out.print("{\"code\":0,\"msg\":\"\",\"count\":" + length + ",\"data\":" + Arrays.toString(datajsons) + "}");
        //测试
        out.flush();
    }

    @RequestMapping("maliguolv")
    public String mailguolv() {

         return "maliguolv2";
    }

    @RequestMapping("guolv")
    public void guolv(HttpServletResponse response,
                      HttpSession httpSession,
                      @RequestParam int page,
                      @RequestParam int limit) throws IOException, MessagingException {
        response.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        Message[] messages = (Message[]) httpSession.getAttribute("messages");

        String bayes_result = pusuBayes.getSubject(messages, 0);
        httpSession.setAttribute("email_flag", dealJson.split_bayes(bayes_result));
        if (bayes_result != null) {
            //out.print(bayes_result);
        } else {
            //out.print("error!");
        }
        response.setCharacterEncoding("utf8");
        /*返回的json输出流*/
        /*message*/
        int length = messages.length;
        /*用于查看的username*/
        String username = (String) httpSession.getAttribute("username");
        /*获取返回的message-json格式*/
        JSONObject[] datajsons = dealJson.get_result(messages, page, limit, username, bayes_result);
        //返回数据
        out.print("{\"code\":0,\"msg\":\"\",\"count\":" + length + ",\"data\":" + Arrays.toString(datajsons) + "}");

    }

    @RequestMapping("guolvbycontent")
    public String guolvbycontentview() {
        return "mailguolvcontent2";
    }

    @RequestMapping("guolvbycontent_getresult")
    public void guolvbycontent(HttpServletResponse response,
                               HttpSession httpSession,
                               @RequestParam int page,
                               @RequestParam int limit) throws IOException, MessagingException {

        response.setCharacterEncoding("utf8");
        PrintWriter out = response.getWriter();
        Message[] messages = (Message[]) httpSession.getAttribute("messages");

        /*文件内容写入*/
        for (int i = 0; i < messages.length; i++) {
            ContentDeal contentDeal = new ContentDeal((String) httpSession.getAttribute("username"), i, messages[i]);
            contentDeal.run();
        }
        /*html去除*/
        String cnn_result = pusuBayes.getSubject(messages, 1);
        System.out.println("cnn_result"+cnn_result);
        httpSession.setAttribute("email_flag", dealJson.split_bayes(cnn_result));
        if (cnn_result != null) {
            //out.print(bayes_result);
        } else {
            //out.print("error!");
        }
        response.setCharacterEncoding("utf8");
        /*返回的json输出流*/
        /*message*/
        int length = messages.length;
        /*用于查看的username*/
        String username = (String) httpSession.getAttribute("username");
        /*获取返回的message-json格式*/
        JSONObject[] datajsons = dealJson.get_result(messages, page, limit, username, cnn_result);
        //返回数据
        out.print("{\"code\":0,\"msg\":\"\",\"count\":" + length + ",\"data\":" + Arrays.toString(datajsons) + "}");

    }

    @RequestMapping("info")
    public String get_info(HttpSession httpSession) {
        //用于查看相关信息
        List<String> email_flag = (List<String>) httpSession.getAttribute("email_flag");
        Message[] messages = (Message[]) httpSession.getAttribute("messages");
        //将这些信息添加到数据库
        String username = (String) httpSession.getAttribute("username");

        email.setUsername(username);
        for (int i = 0; i < messages.length; i++) {
            email.setEmail_id(messages[i].getMessageNumber());
            email.setFlag(email_flag.get(i));
            emailDao.addflag(email);
        }

        if (email_flag == null) {
            return "maliguolv";
        } else {
            List<Email> emails = emailDao.getSpamEmail();
            httpSession.setAttribute("spam_email",emails);
            return "info";
        }
    }
}
