package repository;

import models.Mail;

import javax.inject.Singleton;
import java.util.List;

/**
 * Created by archie on 16/06/16.
 */
@Singleton
public class MailRepository {
    public void save(Mail mail) {
        mail.save();
    }

    public List<Mail> getMailsOrderedByCreateTime() {
        return Mail
                .find
                .order()
                .desc("createTime")
                .findList();
    }

    public List<Mail> getMailsByCategroy(String category){
        return Mail
                .find
                .where()
                .eq("category", category)
                .order()
                .desc("createTime")
                .findList();
    }
}
