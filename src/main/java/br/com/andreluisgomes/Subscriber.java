package br.com.andreluisgomes;

import br.com.andreluisgomes.factory.MessageConsumerFactory;
import br.com.andreluisgomes.resolver.ArgumentsResolver;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;

import javax.jms.JMSException;


public class Subscriber {

    private static final Logger logger = Logger.getLogger(Subscriber.class);

	public static void main(String[] args) throws JMSException {
        ArgumentsResolver ar = new ArgumentsResolver(args);
         try {
             ActiveMQTopic topic = new ActiveMQTopic(ar.getArgumentName());
             MessageConsumerFactory.consume(topic);
             System.in.read();
        } catch (Exception e) {
            logger.error("Caught:" + e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
