package controller;

import beans.Email;
import dao.EmailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by XMKZ on 2018/3/3.
 */
@Controller
public class MvcTest {

    @Autowired
    private EmailDao emailDao;
    @Autowired
    private Email e;
    @RequestMapping("test")
    public String t(HttpServletRequest request){
        e.setUsername("firstbright@163.com");
        System.out.println(e);
        request.setAttribute("shujuku",emailDao.getEmail(e));
        return "test";
    }

    @RequestMapping("test2")
    public String tt(){
        return "test2";
    }
}
