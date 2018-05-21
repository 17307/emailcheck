package service.fenci.nlpir.test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XMKZ on 2018/1/4.
 */
public class TestHtml2 {
    public static void main(String[] args) {
        String str = "<div style='text-align:center;'> 整治“四风”   清弊除垢<br/><span style='font-size:14px;'> </span><span style='font-size:18px;'>公司召开党的群众路线教育实践活动动员大会。</span><br/></div>";
        String str2 = "\n" +
                "<meta charset=\"UTF-8\" /><table align=\"center\" style=\"font-family:Microsoft YaHei,Simsun;width:700px;table-layout:fixed;\"  bgcolor=\"#ffffff\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><td style=\"display:none;\">网易自营生活类电商，来自一线大牌制造商！</td></tr> <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:1px;font-family:Microsoft YaHei,Simsun;background:#aae6e7;\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116927041833.jpeg\" style=\"display:block;border:0;\"/></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:101px;font-family:Microsoft YaHei,Simsun;background:#ace8e9;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/8hizf5.do?product=edm_60557519&domain=email&uid=&area=1\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116929001834.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:331px;font-family:Microsoft YaHei,Simsun;background:#bae3e9;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/cxC7fm.do?product=edm_60557519&domain=email&uid=&area=2\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116931261835.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:311px;font-family:Microsoft YaHei,Simsun;background:#cfe9ea;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/4kfjAC.do?product=edm_60557519&domain=email&uid=&area=3\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116934181836.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:248px;font-family:Microsoft YaHei,Simsun;background:#cbe7e8;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/9BjHzO.do?product=edm_60557519&domain=email&uid=&area=4\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116936821837.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:272px;font-family:Microsoft YaHei,Simsun;background:#c8e6e6;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/zSAfds.do?product=edm_60557519&domain=email&uid=&area=5\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116939081838.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:302px;font-family:Microsoft YaHei,Simsun;background:#c8e6e6;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/fdwnkh.do?product=edm_60557519&domain=email&uid=&area=6\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116941331839.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:406px;font-family:Microsoft YaHei,Simsun;background:#efffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/cxC7fm.do?product=edm_60557519&domain=email&uid=&area=7\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116943971840.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:345px;height:443px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/mmxf5j.do?product=edm_60557519&domain=email&uid=&area=8\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116946541841.jpeg\" style=\"display:block;border:0;\"/></a></td><td style=\"width:355px;height:443px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/97sA4s.do?product=edm_60557519&domain=email&uid=&area=9\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116949151842.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:348px;height:436px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/cjA8W5.do?product=edm_60557519&domain=email&uid=&area=10\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116951761843.jpeg\" style=\"display:block;border:0;\"/></a></td><td style=\"width:352px;height:436px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/B9PhrC.do?product=edm_60557519&domain=email&uid=&area=11\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116954351844.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:351px;height:441px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/W7C9oj.do?product=edm_60557519&domain=email&uid=&area=12\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116956961845.jpeg\" style=\"display:block;border:0;\"/></a></td><td style=\"width:349px;height:441px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/n8fm7q.do?product=edm_60557519&domain=email&uid=&area=13\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116959301846.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:353px;height:453px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/o7Or8q.do?product=edm_60557519&domain=email&uid=&area=14\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116961911847.jpeg\" style=\"display:block;border:0;\"/></a></td><td style=\"width:347px;height:453px;font-family:Microsoft YaHei,Simsun;background:#edffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/r4qyy9.do?product=edm_60557519&domain=email&uid=&area=15\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116964501848.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:197px;font-family:Microsoft YaHei,Simsun;background:#ffffff;\"><a style=\"display: block;\" href=\"http://count.mail.163.com/statistics/8hizf5.do?product=edm_60557519&domain=email&uid=&area=16\" target=\"_blank\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116967111849.jpeg\" style=\"display:block;border:0;\"/></a></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:700px;height:278px;font-family:Microsoft YaHei,Simsun;background:#ffffff;\"><img src=\"http://mimg.127.net/hz/uploader/20180108/15154116969361850.jpeg\" style=\"display:block;border:0;\"/></td></tr></table></td></tr>  <tr><td><table style=\"width:700px;\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"><tr></tr></table></td></tr> </tbody></table><IMG SRC=\"http://count.mail.163.com/beacon/edm.gif?no=60557519&domain=email&date=20180124&uid=\" style=\"display:none\"><div class=\"dm-unsub-div\"><div style=\"text-align:center;padding-top:15px;font-size:10px;color:#777\">如果你不想再收到该产品的推荐邮件，请点击 <a style=\"font-size:10px\" href=\"http://dm.mail.163.com/anonymous/adsubscribe/unsubscribe?depId=1&proId=11301207&mitId=60557519&sec=fDRJppjl7tbUsAAQQtKGYtuRcJ6vsADz\" hidefocus=\"true\">这里退订</a></div>\n" +
                "</div>\n";
        String t = TestHtml2.Html2Text(str2);
        System.out.println("t = " + t);

    }
    public static String Html2Text(String inputString) {
        String htmlStr = inputString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        Matcher m_script;
        Pattern p_style;
        Matcher m_style;
        Pattern p_html;
        Matcher m_html;

        Pattern p_html1;
        Matcher m_html1;

        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签

            textStr = htmlStr;

        } catch (Exception e) {
            System.err.println("Html2Text: " + e.getMessage());
        }

        return textStr;// 返回文本字符串
    }
}
