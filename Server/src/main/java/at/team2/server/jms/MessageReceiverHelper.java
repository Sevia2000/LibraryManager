package at.team2.server.jms;

import javax.jms.*;

public class MessageReceiverHelper {
    public static String getMessage(ConnectionFactory connectionFactory, Destination destination) throws JMSException {
        Connection connection;
        MessageConsumer messageConsumer;
        TextMessage textMessage;

        connection = connectionFactory.createConnection();
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);
        messageConsumer = session.createConsumer(destination);
        connection.start();

        textMessage = (TextMessage) messageConsumer.receive();

        messageConsumer.close();
        session.close();
        connection.close();
        return textMessage.getText();
    }
}
