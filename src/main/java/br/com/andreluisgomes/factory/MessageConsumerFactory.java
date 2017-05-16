package br.com.andreluisgomes.factory;

import br.com.andreluisgomes.constants.Broker;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created queue andre on 15/05/17.
 */
public class MessageConsumerFactory {
    public static MessageConsumer queue(String name) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Broker.URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(name);

        System.out.println("Session create with : " + queue.getQueueName());

        MessageConsumer consumer = session.createConsumer(queue);
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received message >>> " + textMessage.getText());
                    }
                } catch (JMSException e) {
                    System.out.println("Caught : " + e);
                    throw new RuntimeException(e.getMessage());
                }
            }
        };
        consumer.setMessageListener(listener);
        return consumer;
    }
}
