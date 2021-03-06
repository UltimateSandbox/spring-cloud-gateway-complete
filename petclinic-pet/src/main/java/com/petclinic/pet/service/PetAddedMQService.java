package com.petclinic.pet.service;

import com.petclinic.pet.models.Pet;
import com.petclinic.pet.models.PetWithOwner;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;
import javax.jms.*;

@Service
public class PetAddedMQService {

        public boolean sendPetAddition(Pet pet) {

            try {
                // Create a ConnectionFactory
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://104.196.227.222:61616?jms.useAsyncSend=true");

                // Create a Connection
                Connection connection = connectionFactory.createConnection();
                connection.start();

                // Create a Session
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                // Create the destination (Topic or Queue)
                Destination destination = session.createQueue("Pet.Added");

                // Create a MessageProducer from the Session to the Topic or Queue
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

                // Create a messages
                String text = pet.toString()+ Thread.currentThread().getName() + " : " + this.hashCode();
                TextMessage message = session.createTextMessage(text);

                // Tell the producer to send the message
                System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
                producer.send(message);

                // Clean up
                session.close();
                connection.close();
                return true;
            } catch (Exception e) {
                System.out.println("Caught: " + e);
                e.printStackTrace();
                return false;
            }
        }
}
