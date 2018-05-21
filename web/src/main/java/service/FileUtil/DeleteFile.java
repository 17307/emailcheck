package service.FileUtil;

import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by XMKZ on 2018/1/2.
 */
@Service("deleteFile")
public class DeleteFile {
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public void delete(){
        File user_folder = new File("F:\\Users\\lunwen\\web\\web\\WEB-INF\\html\\"+username);
        if(user_folder.exists()){
            //原内容删除
            File[] contentFile = user_folder.listFiles();
            for(int i=0;i<contentFile.length;i++){
                System.out.println(contentFile[i].getName());
                if(!contentFile[i].delete()){
                    System.out.println(contentFile[i]+"删除失败！");
                }
            }
        }else {
            if(user_folder.mkdir()){
                System.out.println("新建成功！");
            }else {
                System.out.println("新建失败");
            }
        }
    }
}
