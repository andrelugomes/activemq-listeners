package br.com.andreluisgomes.factory;

import org.apache.activemq.command.ActiveMQTopic;

import javax.jms.*;

/**
 * Created queue andre on 15/05/17.
 */
public class MessageConsumerFactory {
    public static MessageConsumer consume(Destination destination) throws JMSException {
        try {
            Session session = ActiveMQSession.factory(false, Session.AUTO_ACKNOWLEDGE);

            System.out.println("Session create with : " + destination);

            MessageConsumer consumer = session.createConsumer(destination);
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
        }catch (Exception e){
            System.out.println("Erro ocorreu ao tentar consumir a mensagem. Error=" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static MessageConsumer error(Destination destination) throws JMSException {
        try {
            Session session = ActiveMQSession.factory(false, Session.AUTO_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(destination);
            MessageListener listener = new MessageListener() {
                public void onMessage(Message message) {
                    String error = "Simulação de Erro...";
                    System.out.println(error);
                    throw new RuntimeException(error);
                }
            };
            consumer.setMessageListener(listener);
            return consumer;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static MessageConsumer durable(ActiveMQTopic destination, String name) throws JMSException {
        try {
            Session session = ActiveMQSession.factory(false, Session.AUTO_ACKNOWLEDGE);

            System.out.println("Durable session create with : " + destination);

            MessageConsumer consumer = session.createDurableSubscriber(destination, name);
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
        }catch (Exception e){
            System.out.println("Erro ocorreu ao tentar consumir a mensagem. Error=" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
