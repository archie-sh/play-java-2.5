package controllers;

import models.Category;
import models.Mail;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.MailService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by archie on 15/06/16.
 */
@Singleton
public class MailController extends Controller {
    //"Random category"
    volatile int random = 0;

    @Inject
    private MailService mailService;

    @Inject
    FormFactory formFactory;

    public Result receiveMail() {
        Form<Mail> mailForm = formFactory.form(Mail.class);
        Mail mail = mailForm.bindFromRequest().get();

        //call mailService with some complex logic to determine category
        mail.setCategory(random % 2 == 0 ? Category.NOT_SOCIAL : Category.SOCIAL);
        random++;

        mailService.createMail(mail);
        Logger.info("Saved Mail successfully");
        return ok();
    }

    public Result getMail() {
        return ok(Json.toJson(mailService.getMailsOrderedByCreateTime()));
    }

    public Result deleteMail(Long id) {
        Mail.find.byId(id).delete();
        return ok();
    }

    public Result getMailByCategory(String category) {
        return ok(Json.toJson(mailService.getMailsByCategroy(category)));
    }
}
