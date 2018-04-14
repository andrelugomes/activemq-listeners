package br.com.andreluisgomes;

import br.com.andreluisgomes.factory.MessageConsumerFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;

import javax.jms.JMSException;


public class DurableSubscriber {

    private static final Logger logger = Logger.getLogger(DurableSubscriber.class);

	public static void main(String[] args) throws JMSException {
         try {
             String topicName = args[0];
             String subscriberName = args[1];
             ActiveMQTopic topic = new ActiveMQTopic(topicName);
             MessageConsumerFactory.durable(topic, subscriberName);
             System.in.read();
        } catch (Exception e) {
            logger.error("Caught:" + e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
