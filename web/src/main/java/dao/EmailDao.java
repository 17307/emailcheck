package dao;

import beans.Email;

import java.util.List;

/**
 * Created by XMKZ on 2018/3/3.
 */
public interface EmailDao {
    public void addEmail(Email email);
    public void updateEmail(Email email);
    public List<Email> getEmail(Email email);
    public void deleteEmailByUsername(String username);
    public void addflag(Email email);
    public List<Email> getSpamEmail();
}
