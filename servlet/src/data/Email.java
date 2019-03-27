package data;

import com.chilkatsoft.*;

public class Email {

    static {
        try {
            System.loadLibrary("chilkat");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Native code library failed to load.\n" + e);
            System.exit(1);
        }
    }

    public static void main(String argv[])
    {
        CkMailMan mailman = new CkMailMan();

        //  Any string argument automatically begins the 30-day trial.
        boolean success = mailman.UnlockComponent("30-day trial");
        if (success != true) {
            System.out.println("Component unlock failed");
            return;
        }

        String recipient = "tim.huhndorf@sportplatz-media.com";

        //  Do a DNS MX lookup for the recipient's mail server.
        String smtpHostname;
        smtpHostname = mailman.mxLookup(recipient);
        if (mailman.get_LastMethodSuccess() != true) {
            System.out.println(mailman.lastErrorText());
            return;
        }

        System.out.println(smtpHostname);

        //  Set the SMTP server.
        mailman.put_SmtpHost(smtpHostname);

        //  Create a new email object
        CkEmail email = new CkEmail();

        email.put_Subject("This is a test");
        email.put_Body("This is a test");
        email.put_From("My Name <myname@mydomain.com>");
        email.AddTo("",recipient);

        success = mailman.SendEmail(email);
        if (success != true) {
            System.out.println(mailman.lastErrorText());
        }
        else {
            System.out.println("Mail Sent!");
        }


    }
}