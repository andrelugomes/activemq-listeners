package br.com.andreluisgomes;

import br.com.andreluisgomes.factory.MessageConsumerFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.log4j.Logger;

import javax.jms.JMSException;


public class RedeliveryListener {

    private static final Logger logger = Logger.getLogger(RedeliveryListener.class);

	public static void main(String[] args) throws JMSException {
        try {
            ActiveMQQueue queue = new ActiveMQQueue(args[0]);
            MessageConsumerFactory.error(queue);
            System.in.read();
        } catch (Exception e) {
            logger.error("Caught:" + e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
