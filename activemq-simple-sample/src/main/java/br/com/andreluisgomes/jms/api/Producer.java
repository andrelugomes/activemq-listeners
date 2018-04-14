package br.com.andreluisgomes.jms.api;


import javax.jms.*;
import javax.naming.InitialContext;

public class Producer {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {

        //Lendo o jndi.properties da pasta resources
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("financeiro");
        MessageProducer producer = session.createProducer(fila);
        Message message = session.createTextMessage("<pedido><id>123</id></pedido>");

        producer.send(message);


        session.close();
        connection.close();
        context.close();
    }
}
