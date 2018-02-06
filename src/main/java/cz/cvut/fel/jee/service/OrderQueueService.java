package cz.cvut.fel.jee.service;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.*;
import javax.jms.Queue;
import java.util.*;

/**
 * @author Vaclav Rechtberger
 */
@ApplicationScoped
public class OrderQueueService {
    @Inject
    JMSContext context;

    @Resource(mappedName="java:/jms/queue/OrderQueue")
    //@Resource(mappedName = "java:jboss/exported/jms/queue/OrderQueue")
    Queue queue;

    JMSProducer producer = null;

    JMSConsumer consumer = null;

    QueueBrowser queueBrowser = null;


    @PostConstruct
    private void createProducerAndConsumer(){
        producer = context.createProducer();
        consumer = context.createConsumer(queue);
        queueBrowser = context.createBrowser(queue);

    }

    public void sendMessage(String message) {
         producer.send(queue, message);
    }

    public String receiveMessage() throws JMSException {
        //String message = context.createConsumer(queue).receiveBody(String.class, 1000);
        Message message = consumer.receiveNoWait();
        if(message == null)
            return null;
        return message.getBody(String.class);
    }

    //TODO
    public boolean hasNextMessage() throws JMSException {
        return false;
    }
}
