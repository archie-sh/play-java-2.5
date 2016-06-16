package services;

import models.Category;
import models.Mail;
import repository.MailRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by archie on 15/06/16.
 */
@Singleton
public class MailService {
    @Inject
    MailRepository mailRepository;

    public void createMail(Mail mail){
        mailRepository.save(mail);
    }

    public List<Mail> getMailsOrderedByCreateTime() {
        return mailRepository.getMailsOrderedByCreateTime();
    }

    public List<Mail> getMailsByCategroy(String category) {
        return mailRepository.getMailsByCategroy(category);
    }

}
