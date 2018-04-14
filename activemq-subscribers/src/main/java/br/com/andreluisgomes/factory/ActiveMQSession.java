package br.com.andreluisgomes.factory;

import br.com.andreluisgomes.constants.Broker;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by andre on 17/05/17.
 */
public class ActiveMQSession {
    public static Session factory(boolean transacted, int autoAcknowledge, String clientID) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Broker.URL);
        Connection connection = connectionFactory.createConnection();
        if(clientID != null){
            connection.setClientID(clientID);
        }
        connection.start();
        return connection.createSession(transacted, autoAcknowledge);
    }
}
