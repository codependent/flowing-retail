package io.flowing.retail.order.flow.camunda;

import org.camunda.bpm.engine.impl.pvm.delegate.ActivityExecution;

import io.flowing.retail.order.application.OrderEventProducer;
import io.flowing.retail.order.domain.Order;

public class ReserveGoodsAdapter extends CommandPubEventSubAdapter {

  @Override
  public void execute(ActivityExecution execution) throws Exception {
    OrderEventProducer eventProducer = new OrderEventProducer();

    Order order = orderRepository.getOrder((String)execution.getVariable("orderId")); 
    String transactionId = (String)execution.getVariable("transactionId");

    addMessageSubscription(execution, "GoodsReserved");    

    eventProducer.publishCommandReserveGoods(transactionId, order);    
  }

}
