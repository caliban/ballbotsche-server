/*
 * Copyright 2013 caliban.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.fancytools.ballbotsche.push;

import ch.fancytools.ballbotsche.config.ConfigValue;
import ch.fancytools.ballbotsche.config.ConfigurationChangedEvent;
import ch.fancytools.ballbotsche.config.ConstConfig;
import ch.fancytools.ballbotsche.domain.Device;
import ch.fancytools.ballbotsche.domain.Player;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author caliban
 */
@ApplicationScoped
public class Push2Mail implements Push2Mobile {
    
    private Properties mailProperties;
    private String from;
    private String pw;
    private static final String TEMPLATE = "Hallo {0}\n\nDer Match von diesem Dienstag findet statt.\n\nScBallBotsche";
    private static final Logger LOGGER = Logger.getLogger(Push2Mail.class);
    
    //note to myself: host is smtp.gmail.com port for ssl is 465
    @Inject
    public Push2Mail(@ConfigValue(ConstConfig.SMTP_HOST_KEY) String host,
            @ConfigValue(ConstConfig.SMTP_PORT_KEY) String port,
            @ConfigValue(ConstConfig.MAIL_FROM_KEY) String from,
            @ConfigValue(ConstConfig.MAIL_FROM_PW_KEY) String pw) {
        init(null, host, port, from, pw);
    }
    
    public void init(@Observes ConfigurationChangedEvent event, @ConfigValue(ConstConfig.SMTP_HOST_KEY) String host,
            @ConfigValue(ConstConfig.SMTP_PORT_KEY) String port,
            @ConfigValue(ConstConfig.MAIL_FROM_KEY) String from,
            @ConfigValue(ConstConfig.MAIL_FROM_PW_KEY) String pw) {
        LOGGER.debug("handling event, refreshing config");
        this.from = from;
        this.pw = pw;
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        mailProperties = props;
    }
    
    private void send(Player player) {
        
        
        Session session = Session.getDefaultInstance(mailProperties,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, pw);
                    }
                });
        
        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(player.getEmail()));
            message.setSubject("Ballbotsche: Findet statt");
            String mailBody = MessageFormat.format(TEMPLATE, player.getGivenName());
            message.setText(mailBody);
            
            Transport.send(message);
            
            LOGGER.debug("sent mail to " + player.getEmail());
            
        } catch (MessagingException e) {
            LOGGER.error("cannot send email to " + player.getEmail(), e);
        }
    }
    
    @Override
    public void push(List<Player> players) {
        for (Player player : players) {
            send(player);
        }
    }
    
    @Override
    public boolean acceptPushNotficationForType(Device dev) {
        if (dev == null || dev.getKindOfDevice() == null) {
            return false;
        }
        return Device.KindOfDeviceEnum.MAIL == dev.getKindOfDevice();
    }
}
