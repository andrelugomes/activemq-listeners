package br.com.andreluisgomes;

import br.com.andreluisgomes.factory.MessageConsumerFactory;
import br.com.andreluisgomes.resolver.ArgumentsResolver;

import javax.jms.*;
import java.io.IOException;


public class Receiver {

    public static String queueName;

	public static void main(String[] args) throws JMSException {
        ArgumentsResolver ar = new ArgumentsResolver(args);
        MessageConsumerFactory.queue(ar.getArqumentName());
        try {
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
