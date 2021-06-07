package com.petclinic.owner.services;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;
import javax.jms.*;


@Service
public class ConsumeNewPetMQService {

    public boolean consumeNewPetMessage(){
        try {
            //Create Connection Factory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://104.196.227.222:61616?jms.useAsyncSend=true");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            //Create a session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //Create Topic / Que
            Destination destination = session.createQueue("Pet.Added");

            //Create Consumer
            MessageConsumer consumer = session.createConsumer(destination);

            //wait for message
            Message message = consumer.receive(1000);

            if(message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: "+text);
            }else{
                System.out.println("Received: "+message);
            }

            consumer.close();
            session.close();
            connection.close();

            return true;
        }catch(Exception e){
            System.out.println("Caught: "+ e);
            return false;
        }
    }
}
