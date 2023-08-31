package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;
    @Scheduled(cron = "0 * * * * ?")//
    //@Scheduled(cron = "0/5 * * * * ?")
    public void processTimeoutOrder(){
        log.info("定时处理超时订单：{}", LocalDateTime.now());
        //select * from orders where status = ? and order_time <(当前时间-15分钟)
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(-15);
        List<Orders> ordersList = orderMapper.getByStatusandOrderTime(Orders.PENDING_PAYMENT, localDateTime);
        if (ordersList !=null && ordersList.size()>0){
            for (Orders orders:ordersList
                 ) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("订单超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);


            }
        }


    }
    /*
    处理一直派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")//每天凌晨一点触发一次
    //@Scheduled(cron = "0/5 * * * * ?")
    public  void processDeliveryOrder(){
        log.info("定时处理处于派送中的订单：{}",LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(-60);

        List<Orders> ordersList = orderMapper.getByStatusandOrderTime(Orders.DELIVERY_IN_PROGRESS, localDateTime);

        if (ordersList !=null && ordersList.size()>0){
            for (Orders orders:ordersList
            ) {
                orders.setStatus(Orders.COMPLETED);

                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);


            }
        }


    }
}
