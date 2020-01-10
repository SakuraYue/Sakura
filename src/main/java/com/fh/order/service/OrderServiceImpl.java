package com.fh.order.service;

import com.fh.cart.model.CartItem;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.order.mapper.OrderMapper;
import com.fh.order.model.Order;
import com.fh.order.model.OrderItem;
import com.fh.pay.model.PayLog;
import com.fh.pay.service.PayLogService;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import com.fh.util.IdUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private PayLogService payLogService;


    @Override
    public ServerResponse addOrder(Member member) {

        String cartKey = SystemConstant.CART_KEY + member.getId();

        if (!redisTemplate.hasKey(cartKey)) {
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        List<CartItem> cartItemList = redisTemplate.opsForHash().values(cartKey);

        List<CartItem> shortOfStockCartItemList = new ArrayList<>();

        List<OrderItem> orderItemList = new ArrayList<>();

        Long totalCount = 0L;

        BigDecimal totalPrice = new BigDecimal("0");

        String orderId = IdUtil.createId();

        int checkedCartItemCount = 0;

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getChecked()) {
                checkedCartItemCount++;
                Product product = productMapper.selectById(cartItem.getProductId());
                if (product.getStock() >= cartItem.getCount()) {
                    Long rowsCount = productMapper.updateProductStock(cartItem.getProductId(), cartItem.getCount());
                    if (rowsCount > 0) {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setOrderId(orderId);
                        orderItem.setMemberId(member.getId());
                        orderItem.setProductId(cartItem.getProductId());
                        orderItem.setProductName(cartItem.getProductName());
                        orderItem.setPrice(cartItem.getPrice());
                        orderItem.setImage(cartItem.getImage());
                        orderItem.setSubtotalPrice(cartItem.getSubtotalPrice());
                        orderItem.setTotal(cartItem.getCount());
                        //将订单明细放入到订单明细集合中
                        orderItemList.add(orderItem);
                        totalCount += cartItem.getCount();
                        totalPrice = totalPrice.add(cartItem.getSubtotalPrice());
                    }else {
                        shortOfStockCartItemList.add(cartItem);
                    }
                }else {
                    shortOfStockCartItemList.add(cartItem);
                }
            }
        }
        if(shortOfStockCartItemList.size() == checkedCartItemCount){
            return ServerResponse.error(ResponseEnum.CART_ALL_STOCK_SHORT);
        }
        Order order = new Order();
        order.setId(orderId);
        order.setMemberId(member.getId());
        order.setCreateTime(new Date());
        order.setTotalCount(totalCount);
        order.setTotalPrice(totalPrice);
        order.setPayType(1); //1代表在线支付 2代表货到付款
        order.setStatus(1); //1代表未付款 2代表已支付
        orderMapper.addOrder(order);

        PayLog payLog = new PayLog();
        payLog.setOutTradeNo(IdUtil.createId());
        payLog.setPayStatus(1);
        payLog.setMemberId(member.getId());
        payLog.setPayType(1);
        payLog.setPayMoney(totalPrice);
        payLog.setOrderId(orderId);

        //将支付日志保存到支付日志表中
        payLogService.addPayLog(payLog);

        redisTemplate.opsForValue().set("payLog:" + member.getId(),payLog,30, TimeUnit.MINUTES);

        orderMapper.addOrderItemList(orderItemList);



        for (OrderItem orderItem : orderItemList) {
            redisTemplate.opsForHash().delete(cartKey,orderItem.getProductId()+"");
        }
        return ServerResponse.success(shortOfStockCartItemList);
    }

    @Override
    public void updateOrder(Order order) {
        orderMapper.updateById(order);
    }
}
