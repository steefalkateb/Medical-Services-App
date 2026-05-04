package com.example.alwadi;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//https://www.thecodecity.com/2017/07/send-email-from-android-app-directly.html
//https://www.youtube.com/watch?v=ZbosRfH1SnM
public class JavaEmailApi extends AsyncTask<Void, Void, Void> {
    private Context context;
    private Session session;
    private String email, subject, password;

    //    public JavaEmailApi(Context context, Session session, String email, String subject, String message) {
    public JavaEmailApi(Context context, String email, String subject, String password) {

        this.context = context;
//        this.session = session;
        this.email = email;
        this.subject = subject;
        this.password = password;
    }

    @Override
    protected Void doInBackground(Void... voids) {

//        Properties properties = new Properties();
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.host", "mail.massar-pos.com");
//        props.put("mail.smtp.socketFactory.port", "587");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
        props.put("mail.smtp.starttls.enable", "true");


//        session = Session.getInstance(props, new GMailAuthenticator("lolo.steef@gmail.com", "rrlolo90"));

        session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Forget_Password_Utils.EMAIL, Forget_Password_Utils.PASSWORD);
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            Log.e("7777777777777777777777", Forget_Password_Utils.EMAIL);
            mimeMessage.setFrom(new InternetAddress(Forget_Password_Utils.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));

            mimeMessage.setSubject(subject);
            mimeMessage.setText(password);
            Transport.send(mimeMessage);


        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }


}
