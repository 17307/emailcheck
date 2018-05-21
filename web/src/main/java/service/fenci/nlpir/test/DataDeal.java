package service.fenci.nlpir.test;

import org.springframework.stereotype.Service;
import service.fenci.nlpir.demo.NlpirMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 第一次编写时 用于处理训练数据 分词
 * 目前用于进行邮件分词---非训练数据
 */
@Service
public class DataDeal {

    String data_path;
    String temp_path;
    String path;
    Pattern pattern;
    List<String> al2;

    //与处理邮件，分词后写入到文件中--训练过程
    /*public DataDeal() throws IOException {
        al2 = new ArrayList<String>();
        data_path = "C:\\Users\\XMKZ\\Desktop\\trec06c\\trec06c\\data";
        pattern = Pattern.compile("([\\u0391-\\uFFE5]+)/(\\w+)"); //[\u0391-\uFFE5]+
        File file = new File(data_path);
        String[] paths = file.list();
        *//*for (int j=0;j<paths.length;j++){
            System.out.println(paths[j]);
        }*//*
        for (String p : paths) {
            //System.out.println(p);
            temp_path = data_path + "\\" + p;
            File temp_file = new File(temp_path);
            for (String p2 : temp_file.list()) {
                path = temp_path + "\\" + p2; //最终路径
                //System.out.println(path);
                if (!path.equals("C:\\Users\\XMKZ\\Desktop\\trec06c\\trec06c\\data\\001\\055")) {
                    InputStreamReader br = new InputStreamReader(new FileInputStream(path), "gbk");
                    char[] buffs = new char[1024];
                    int s;
                    StringBuffer sb = new StringBuffer();
                    while ((s = br.read()) != -1) {
                        if (s > 255) {
                            sb.append((char) s);
                        }
                    }
                    String result = NlpirMethod.NLPIR_ParagraphProcess(sb.toString(), 1);
                    //al2.addAll(getVocabulary(result));
                    String temp_path1 = "F:\\Users\\email\\" + p;
                    File temp_file_1 = new File(temp_path1);
                    if (!temp_file_1.exists()) {
                        temp_file_1.mkdir();
                    }
                    String fenci_result = getVocabulary(result).toString().replace(",", " ");
                    OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(temp_path1 + "\\" + p2), "utf8");
                    osw.write(fenci_result.substring(1, fenci_result.length() - 1));
                    osw.flush();
                    osw.close();
                }
            }
        }
        *//*OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("result2.txt"),"utf8");
        osw.write(al2.toString().replace(","," "));
        osw.flush();
        osw.close();*//*
        System.out.println(11);
    }*/
    public DataDeal(){}
    public List<String> getFenci(String s) throws UnsupportedEncodingException {
        String result = NlpirMethod.NLPIR_ParagraphProcess(s, 1);
        //System.out.println(getVocabulary(result));
        return getVocabulary(result);
    }
    //一次使用--检测过程
    public DataDeal(String s) throws UnsupportedEncodingException {
        String result = NlpirMethod.NLPIR_ParagraphProcess(s, 1);
        System.out.println(getVocabulary(result));
    }

    public List<String> getVocabulary(String s) throws UnsupportedEncodingException {
        /**
         * 获取去掉词性后的分词列表*/
        pattern = Pattern.compile("([\\u0391-\\uFFE5]+)/(\\w+)"); //[\u0391-\uFFE5]+
        List<String> arrayList = new ArrayList<String>();
        byte[] b = s.getBytes();
        String s2 = new String(b, "utf-8");
        //System.out.println(s2);
        Matcher m = pattern.matcher(s2);
        while (m.find()) {
            //arrayList.add(m.group(1));
            if (!(m.group(2).contains("w") || m.group(2).contains("e") || m.group(2).contains("vshi")
                    || m.group(2).contains("xm") || m.group(2).contains("o") || m.group(2).contains("u") || m.group(2).contains("p")
                    || m.group(2).contains("y") || m.group(2).contains("vyou") || m.group(2).contains("c") || m.group(2).contains("f")
                    || m.group(2).contains("h") || m.group(2).contains("k") || m.group(2).contains("m"))) {
                /*System.out.print(m.group(1));
                System.out.print("   ");
                System.out.println(m.group(2));*/
                arrayList.add(m.group(1));
            }
        }
        return arrayList;
    }

    public static void main(String[] args) throws IOException {

        String t = "你好,100元滴滴打车券已到账，请尽快查收，谢谢";
        //String result = NlpirMethod.NLPIR_ParagraphProcess(t, 1);
        //System.out.println(result);
        new DataDeal(t);
    }
}

