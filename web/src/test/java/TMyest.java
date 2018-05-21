import beans.Email;
import dao.EmailDao;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.python.antlr.ast.Str;
import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by XMKZ on 2018/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TMyest {
    @Autowired
    private EmailDao emailDao;
    @Autowired
    private Email e;

    @Test
    public void test_database(){

        List<Email> emails = emailDao.getSpamEmail();
        for(Email e:emails){
            System.out.println(e);
        }
    }
    @Test
    public void test1() {
        e.setUsername("firstbright@163.com");
        System.out.println(emailDao.getEmail(e));
    }

    @Test
    public void testjson() {
        JSONObject[] datajsons = new JSONObject[30];
        JSONObject datajson = new JSONObject();

        for (int i = 0; i < 30; i++) {
            datajson.put("id", 10000);
            datajson.put("username", "ff");
            datajson.put("sex", "nv");
            datajson.put("city", "city");
            datajsons[i] = datajson;
        }
        String s = "hello";
        System.out.println(s.substring(1, s.length() - 1));
        System.out.println(Arrays.toString(datajsons));
    }

    @Test
    public void getPython() throws IOException {
        //String path = "D:\\public.bat";
        Runtime run = Runtime.getRuntime();
        Process process = run.exec("F:\\Users\\lunwen\\bayes.bat");
        InputStream inputStream = process.getInputStream();
        while (inputStream.read() != -1) {
            System.out.println(inputStream.read());
        }

    }

    @Test
    public void testpython() {

        PythonInterpreter interpreter = new PythonInterpreter();
        PySystemState sys = Py.getSystemState();
        sys.path.add("F:\\anaconda\\anaconda\\envs\\tensorflow\\Lib\\site-packages");
        interpreter.exec("from sklearn.externals import joblib");
        interpreter.exec("import sklearn");
        interpreter.execfile("F:\\Users\\XMKZ\\PycharmProjects\\tensorflow\\emailCheck\\demo\\test.py");
    }

    @Test
    public void testbat() throws IOException {

        String cmd = "F:\\Users\\lunwen\\bayes.bat";

        Process proc = Runtime.getRuntime().exec(cmd); //执行py文件
        InputStreamReader stdin = new InputStreamReader(proc.getInputStream(), "GBK");
        BufferedReader input = new BufferedReader(stdin);

        String line = null;
        while ((line = input.readLine()) != null) {
            System.out.println(line);//得到输出
        }

    }
    @Test
    public void testbatcnn() throws IOException {

        String cmd = "F:\\Users\\lunwen\\cnn.bat";

        Process proc = Runtime.getRuntime().exec(cmd); //执行py文件
        InputStreamReader stdin = new InputStreamReader(proc.getInputStream(), "GBK");
        BufferedReader input = new BufferedReader(stdin);

        String line = null;
        while ((line = input.readLine()) != null) {
            System.out.println(line);//得到输出
        }

    }
    @Test
    public void teststringsub() {
        String s = "[0, 0, 1, 1, 0, 0]";
        String s2 = s.substring(1, s.length() - 1);
        String[] s3 = s2.split(",");
        for (String t : s3) {
            if (t.contains("0")) {
                System.out.println("0");
            } else if (t.contains("1")) {
                System.out.println("1");
            }
        }
    }

    @Test
    public void fileabout() throws IOException {

        OutputStreamWriter osw = new OutputStreamWriter(
                new FileOutputStream("F:\\Users\\lunwen\\mailout\\cnn_content.mail"),
                "utf8"
        );

        for (int i = 0; i < 8; i++) {

            BufferedReader r = new BufferedReader(new FileReader("F:\\Users\\lunwen\\web\\web\\WEB-INF\\html\\lastbright@163.com\\"+i+".html"));
            StringBuffer sb = new StringBuffer();
            String s;
            while ((s = r.readLine()) != null) {
                s = s.replace("&nbsp;","");
                s = s.replace("-","");
                s = s.replace(" ","");
                sb.append(s);
                //osw.write(sb.toString());
                //System.out.println(sb);
            }
            s = sb.toString();
            s = s.replaceAll("[a-zA-Z0-9]","");
            s = s.replaceAll("[_:.`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]","");
            s = s.replaceAll("^([\\u0391-\\uFFE5])","");
            //System.out.println(s);
            osw.write(s.substring(1,s.length()>190?190:s.length()));
            osw.write("\n");
            osw.flush();
        }


        //System.out.println(Arrays.toString(a));

    }
    @Test
    public void ttt(){
        String s = "转发邮件信息发件人：cpm_zhaopin@mail.zhaopin.cn发送日期：2018032023:14:28收件人：firstbright@163.com主题：【智联推荐】【国有独资】【集团总部】福州地铁集团2018春季校园招聘成都站开始了【国有独资】【集团总部】福州地铁集团2018春季校园招聘成都站开始了【宣讲会现场接收简历并面试】土建/机电/安全工程类等岗位集团招聘，3月22日（周四）下午14:30就在西南交通大学（犀浦校区）二教418，福州落户，起薪10万+，独有公租房等七大福利诚邀你的加入，我们在福州地铁等你！（详情请加招聘组微信1097290173进地铁微信群）网申地址：fzmtr.zhaopin.com如果你不想再收到此类邮件，请点击这里退订【网易自营|30天无忧退货】RalphLauren制造商春装上新，品质卫衣限时仅99元起&gt;&gt;&gt;&gt;\n";
        s = s.replaceAll("[a-z]","");
        s = s.replaceAll("[0-9]","");
        System.out.println(s);
    }
    @Test
    public void ttttt(){
        String s= "[0. 0. 1. 1. 0. 1. 1. 0.]";
        s = s.substring(1,0);
        System.out.println(s);
    }
}
