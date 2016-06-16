import com.google.inject.Guice;
import models.Category;
import models.Mail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.ApplicationLoader;
import play.Environment;
import play.db.Database;
import play.db.Databases;
import play.db.evolutions.Evolutions;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.Helpers;
import play.test.WithApplication;
import repository.MailRepository;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by archie on 16/06/16.
 */
public class MailTest {
    @Inject
    Application application;

    @Inject
    MailRepository mailRepository;

    @Before
    public void setup() {
        GuiceApplicationBuilder builder = new GuiceApplicationLoader()
                .builder(new ApplicationLoader.Context(Environment.simple()));
        Guice.createInjector(builder.applicationModule()).injectMembers(this);

        Helpers.start(application);
    }

    @After
    public void teardown() {
        Helpers.stop(application);
    }

    @Test
    public void testMailsOnlyInCategory() {
        Mail mail1 = new Mail();
        mail1.setCategory(Category.NOT_SOCIAL);
        mail1.save();

        Mail mail2 = new Mail();
        mail2.setCategory(Category.SOCIAL);
        mail2.save();

        Mail mail3 = new Mail();
        mail3.setCategory(Category.SOCIAL);
        mail3.save();

        List<Mail> mails = mailRepository.getMailsByCategroy(Category.SOCIAL.name());
        assertEquals(mails.size(), 2);
    }
}
