package service.guolv;

import org.python.antlr.ast.Str;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.fenci.nlpir.test.DataDeal;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by XMKZ on 2018/3/24.
 */
@Service
public class PusuBayes {

    @Autowired
    private DataDeal dataDeal;

    public String getSubject(Message[] messages,int content) throws MessagingException, IOException {
        if(content==1){
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream("F:\\Users\\lunwen\\mailout\\cnn_content.mail"),
                    "utf8"
            );
            System.out.println("messages.length");
            System.out.println(messages.length);
            for (int i = 0; i < messages.length; i++) {
                BufferedReader r = new BufferedReader(new FileReader("F:\\Users\\lunwen\\web\\web\\WEB-INF\\html\\lastbright@163.com\\"+i+".html"));
                StringBuffer sb = new StringBuffer();
                String s;
                while ((s = r.readLine()) != null) {
                    s = s.replace("&nbsp;","");
                    s = s.replace("-","");
                    s = s.replace(" ","");
                    sb.append(s);
                }
                s = sb.toString();
                s = s.replaceAll("[a-zA-Z0-9]","");
                s = s.replaceAll("[_:.`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]","");
                s = s.replaceAll("^([\\u0391-\\uFFE5])","");
                //System.out.println("s.length");
                //System.out.println(s.length());
                if(s.length()!=0){
                    osw.write(s.substring(1,s.length()>190?190:s.length()));
                    osw.write("\n");
                }else {
                    osw.write("1\n");
                }

                osw.flush();
            }
            return execPythonCNN();
        }
        else if(content==0){
            System.out.println("count:"+messages);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    new FileOutputStream("F:\\Users\\lunwen\\mailout\\bayes.mail"),"utf8"
            );
            OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(
                    new FileOutputStream("F:\\Users\\lunwen\\mailout\\cnn.mail"),"utf8"
            );

            //对每一个message的subject进行分词
            for(int i=0;i<messages.length;i++){
                //写入到cnn文件
                outputStreamWriter2.write(messages[i].getSubject());
                outputStreamWriter2.write("\n");
                outputStreamWriter2.flush();
                //System.out.println(fenci_subject);
                //将分词后的结果写入到文件中，然后调用python
                //System.out.println(fenci_subject.toString());
                //写入到bayes文件
                List<String> fenci_subject = dataDeal.getFenci(messages[i].getSubject());
                outputStreamWriter.write(fenci_subject.toString());
                outputStreamWriter.write("\n");
                outputStreamWriter.flush();
            }
            String bayes_result = execPython();
            System.out.println("bayes_result"+bayes_result);
            return bayes_result;
        }
        return null;

    }

    public String execPython() throws IOException {
        /**
         * 用于执行bayes.bat程序*/
        String cmd = "F:\\Users\\lunwen\\bayes.bat";

        Process proc=Runtime.getRuntime().exec(cmd); //执行py文件
        InputStreamReader stdin=new InputStreamReader(proc.getInputStream(),"GBK");
        BufferedReader input = new BufferedReader(stdin);

        String line = null;
        while((line=input.readLine())!=null ){
            //System.out.println(line);
            //System.out.println(line.substring(0));
            if(line.contains("[")){
                return line;
            }
        }
        return null;
    }
    public String execPythonCNN() throws IOException {
        /**
         * 用于执行bayes.bat程序*/
        String cmd = "F:\\Users\\lunwen\\cnn.bat";

        Process proc=Runtime.getRuntime().exec(cmd); //执行py文件
        InputStreamReader stdin=new InputStreamReader(proc.getInputStream(),"GBK");
        BufferedReader input = new BufferedReader(stdin);

        String line = null;
        while((line=input.readLine())!=null ){
            //System.out.println(line);
            //System.out.println(line.substring(0));
            if(line.contains("[")){
                return line;
            }
        }
        return null;
    }
}
