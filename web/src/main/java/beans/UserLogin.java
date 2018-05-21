package beans;

import org.springframework.stereotype.Repository;

/**
 * Created by XMKZ on 2018/3/3.
 */
@Repository("userLogin")
public class UserLogin {
    private String username;
    private String password;
    private String mail_pop3_host;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail_pop3_host() {
        return mail_pop3_host;
    }

    public void setMail_pop3_host(String mail_pop3_host) {
        this.mail_pop3_host = mail_pop3_host;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail_pop3_host='" + mail_pop3_host + '\'' +
                '}';
    }
}
