package service;

import org.json.simple.JSONObject;
import org.python.antlr.ast.Str;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XMKZ on 2018/3/4.
 */
@Service("dealjson")
public class DealJson {
    public JSONObject[] get_result(Message[] messages,int page,int limit,String username,String bayes_result) throws MessagingException {
        List<String> bayes_results = null;
        //如果bayes_result的结果不是null
        if(bayes_result!=null){
            //将bayes_result的结果转成数组形式
            //System.out.println(bayes_result);
            bayes_results = split_bayes(bayes_result);
        }

        /*Json数组 用于返回*/
        JSONObject[] jsonObjects = new JSONObject[limit];
        int count = 0;
        //用于临时获取单个message的内容
        /*
        * 0  0-9; 1  10-19
        * page limit*page-limit*(page+1)-1
        * */
        for (int i = (page-1)*limit; i < limit*(page)&&i<messages.length; i++) {
            JSONObject json_temp = new JSONObject();
            Address[] froms = messages[i].getFrom();
            String subject = messages[i].getSubject();
            int message_num = messages[i].getMessageNumber();
            json_temp.put("id", message_num);
            if (froms != null) {
                //System.out.println("发件人信息:" + froms[0]);
                InternetAddress addr = (InternetAddress) froms[0];
                json_temp.put("address", "发件人地址:" + addr.getAddress() + ",发件人显示名:" + addr.getPersonal());
            }
            //主题
            json_temp.put("theme", subject);
            //超链接
            json_temp.put("look", "<a href=\"/read?id=" + (message_num-1) + "&username="+username+"\">查看</a></td>");

            jsonObjects[count++] = json_temp;
            json_temp.put("spam",bayes_results==null?null:bayes_results.get(i));
        }
        JSONObject json_temp = new JSONObject();
        for(int i=count;i<10;i++){
            json_temp.put("id", "null");
            json_temp.put("address", "null");
            json_temp.put("theme", "null");
            json_temp.put("look", "null");
            json_temp.put("spam",null);

            jsonObjects[i] = json_temp;
        }

        return jsonObjects;
    }

    public List<String> split_bayes(String bayes_result) {
        List<String> bayes_resluts = new ArrayList<String>();
        int i=0;
        String s2 = bayes_result.substring(1,bayes_result.length()-1);
        String[] s3;
        if(s2.contains(",")){
            s3 = s2.split(",");
        }
        else{
            System.out.println(s2);
            s3 = s2.split("\\.");
            System.out.println(s3);
        }
        for(String t:s3){
            if(t.contains("0")){
                bayes_resluts.add("0");
            }else if(t.contains("1")) {
                bayes_resluts.add("1");
            }
        }
        System.out.println(bayes_resluts);

        return bayes_resluts;
    }
}
