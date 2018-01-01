package cz.cvut.fel.jee.service;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * @author Vaclav Rechtberger
 */
@ApplicationScoped
public class OrderQueueService {
    @Inject
    JMSContext context;

    @Resource(mappedName="java:/jms/queue/OrderQueue")
    Queue queue;

    public void sendMessage(String message) {
        context.createProducer().send(queue, message);
    }
}
