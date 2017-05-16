package br.com.andreluisgomes;

import br.com.andreluisgomes.constants.Broker;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;


public class Subscriber {

    private static final Logger logger = Logger.getLogger(Subscriber.class);

	public static void main(String[] args) throws JMSException {

        String topicName;
        try {
            topicName = args[0];
            System.out.println("Preparing for listening topic : " + topicName);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Erro ao recuperar o nome do Tópico");
            throw new RuntimeException(e.getMessage());
        }
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Broker.URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(topicName);
        System.out.println("Session create with : " + topic.getTopicName());

        MessageConsumer consumer = session.createConsumer(topic);
        MessageListener listener = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received message >>> " + textMessage.getText());
                    }
                } catch (Exception e) {
                    logger.error("Caught:" + e);
                    throw new RuntimeException(e.getMessage());
                }
            }
        };
        consumer.setMessageListener(listener);

        try {
            System.in.read();
        } catch (Exception e) {
            logger.error("Caught:" + e);
            throw new RuntimeException(e.getMessage());
        }
        connection.close();
    }
}
