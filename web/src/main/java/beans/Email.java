package beans;

import org.springframework.stereotype.Repository;

/**
 * Created by XMKZ on 2018/1/1.
 */
@Repository("email")
public class Email {

    //用户名
    private String username;
    private int email_id;
    //主题
    private String subject;
    //邮件内容，不存放在数据库中
    private String content;
    //发件人地址
    private String receive_address;
    //是否为垃圾邮件 bad or good
    private String flag;

    @Override
    public String toString() {
        return "Email{" +
                "username='" + username + '\'' +
                ", email_id=" + email_id +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", receive_address='" + receive_address + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getEmail_id() {
        return email_id;
    }

    public void setEmail_id(int email_id) {
        this.email_id = email_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceive_address() {
        return receive_address;
    }

    public void setReceive_address(String receive_address) {
        this.receive_address = receive_address;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
