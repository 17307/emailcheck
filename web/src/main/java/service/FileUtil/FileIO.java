package service.FileUtil;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by XMKZ on 2018/1/2.
 */
public class FileIO implements Runnable {

    private String username;
    private int email_id;
    private String content;

    public FileIO(String username, int email_id) {
        this.username = username;
        this.email_id = email_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //将文件内容写到 记事本
    public void run() {
        //D:\论文\EmailCheck\web\WEB-INF\html
        File f = new File("F:\\Users\\lunwen\\web\\web\\WEB-INF\\html\\"+username+"\\"+email_id+".html");
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(content);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
