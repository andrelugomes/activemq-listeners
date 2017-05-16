package br.com.andreluisgomes;

import br.com.andreluisgomes.factory.MessageConsumerFactory;
import br.com.andreluisgomes.resolver.ArgumentsResolver;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;

import javax.jms.JMSException;


public class Receiver {

    private static final Logger logger = Logger.getLogger(Receiver.class);

	public static void main(String[] args) throws JMSException {
        try {
            ArgumentsResolver ar = new ArgumentsResolver(args);
            ActiveMQQueue queue = new ActiveMQQueue(ar.getArgumentName());
            MessageConsumerFactory.consume(queue);
            System.in.read();
        } catch (Exception e) {
            logger.error("Caught:" + e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
