package service;

import service.FileUtil.FileIO;
import service.fenci.nlpir.test.TestHtml2;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by XMKZ on 2018/1/2.
 */
public class ContentDeal implements Runnable {


    private Message message;
    FileIO fileIO = null;

    public ContentDeal(String username, int email_id, Message message) {

        this.message = message;
        fileIO = new FileIO(username, email_id);
    }

    public ContentDeal(Message message) {
        this.message = message;
    }

    /**
     * @param part 解析内容
     * @throws Exception
     */
    private void rePart(Part part) throws MessagingException,
            UnsupportedEncodingException, IOException, FileNotFoundException {
        if (part.getDisposition() != null) {
            //System.out.println("发现附件: " +  MimeUtility.decodeText(part.getFileName()));
        } else {
            if (part.getContentType().startsWith("text/plain")) {
                //System.out.println("文本内容：" + part.getContent());
                if(fileIO!=null){

                    fileIO.setContent(TestHtml2.Html2Text((String) part.getContent()));
                    fileIO.run();
                }else {
                    System.out.println("文本内容：" + part.getContent());
                }

            } else {
                // System.out.println("HTML内容：" + part.getContent());
                if(fileIO!=null){
                    fileIO.setContent(TestHtml2.Html2Text((String) part.getContent()));
                    fileIO.run();
                }else {
                    System.out.println("文本内容：" + part.getContent());
                }

            }
        }
    }

    /**
     * @param multipart // 接卸包裹（含所有邮件内容(包裹+正文+附件)）
     * @throws Exception
     */
    private void reMultipart(Multipart multipart) throws Exception {
        //System.out.println("邮件共有" + multipart.getCount() + "部分组成");
        // 依次处理各个部分
        for (int j = 0, n = multipart.getCount(); j < n; j++) {
            //System.out.println("处理第" + j + "部分");
            Part part = multipart.getBodyPart(j);//解包, 取出 MultiPart的各个部分, 每部分可能是邮件内容,
            // 也可能是另一个小包裹(MultipPart)
            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
            if (part.getContent() instanceof Multipart) {
                Multipart p = (Multipart) part.getContent();// 转成小包裹
                //递归迭代
                reMultipart(p);
            } else {
                rePart(part);
            }
        }
    }

    public void run() {
        Object o = null;
        try {
            o = this.message.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if (o instanceof Multipart) {
            Multipart multipart = (Multipart) o;
            try {
                reMultipart(multipart);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (o instanceof Part) {
            Part part = (Part) o;
            try {
                rePart(part);
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //System.out.println("类型" + messages[i].getContentType());
            try {
                //System.out.println("内容" + this.message.getContent());
                if(fileIO!=null){
                    fileIO.setContent(TestHtml2.Html2Text((String) message.getContent()));
                    fileIO.run();
                }else {
                    System.out.println((String) message.getContent());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
