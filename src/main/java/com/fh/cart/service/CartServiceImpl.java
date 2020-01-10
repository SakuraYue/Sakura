package com.fh.cart.service;

import com.fh.cart.model.Cart;
import com.fh.cart.model.CartItem;
import com.fh.common.ResponseEnum;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import com.fh.util.SystemConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ProductMapper productMapper;


    @Override
    public ServerResponse buyCart(Integer productId, Member member) {
        Integer id = member.getId();
        if (productId==null) {
            return ServerResponse.error(ResponseEnum.PRODUCT_ID_IS_NOT_EXIST);
        }
        Product product = productMapper.selectById(productId);
        if (product==null) {
            return ServerResponse.error(ResponseEnum.PRODUCT_IS_NOT_EXIST);
        }
        if(product.getStatus() == 2){
            return ServerResponse.error(ResponseEnum.PRODUCT_IS_DOWN);
        }

        if(!redisTemplate.opsForHash().hasKey(SystemConstant.CART_KEY+id, productId+"")){
            CartItem cartItem = new CartItem();
            cartItem.setProductId(product.getId());
            cartItem.setProductName(product.getName());
            cartItem.setPrice(product.getPrice());
            cartItem.setCount(1L);
            cartItem.setImage(product.getMainImage());
            cartItem.setSubtotalPrice(product.getPrice());
            cartItem.setChecked(true);
            redisTemplate.opsForHash().put(SystemConstant.CART_KEY+id,productId+"",cartItem);
        }else{
            //如果存在则将购物车中该商品的数量加1
            CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(SystemConstant.CART_KEY+id, productId+"");
            cartItem.setCount(cartItem.getCount()+1);
            cartItem.setSubtotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
            redisTemplate.opsForHash().put(SystemConstant.CART_KEY+id,productId+"",cartItem);
        }

        //统计一下当前登录的会员您的购物车中的商品总数量
        List<CartItem> cartItemList = redisTemplate.opsForHash().values(SystemConstant.CART_KEY+id);
        Long cartTotalNumber = 0L;
        for (CartItem cartItem : cartItemList) {
            cartTotalNumber += cartItem.getCount();
        }
        return ServerResponse.success(cartTotalNumber);
    }

    @Override
    public ServerResponse getCartTotalCount(Member member) {
        Integer id = member.getId();
        String cartKey = SystemConstant.CART_KEY+ id;

        //判断用户的购物车是否存在
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        //统计一下当前登录的会员您的购物车中的商品总数量
        List<CartItem> cartItemList = redisTemplate.opsForHash().values(cartKey);
        Long cartTotalNumber = 0L;
        for (CartItem cartItem : cartItemList) {
            cartTotalNumber += cartItem.getCount();
        }
        return ServerResponse.success(cartTotalNumber);
    }

    @Override
    public ServerResponse queryCheckedCart(Member member) {
        Integer id = member.getId();
        String cartKey = SystemConstant.CART_KEY+id;
        //判断当前登录会员是否拥有购物车
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        //取出当前登录会员购物车中的商品K
        List<CartItem> cartItemList = redisTemplate.opsForHash().values(cartKey);

        List<CartItem> checkedCartItemList = new ArrayList<>();

        //遍历购物车中的商品集合，计算总价格和总个数
        Long totalCount = 0L;
        BigDecimal totalPrice = new BigDecimal("0");
        for (CartItem cartItem : cartItemList) {
            //如果当前遍历的商品被选中
            if(cartItem.getChecked()){
                checkedCartItemList.add(cartItem);
                totalCount += cartItem.getCount();
                totalPrice = totalPrice.add(cartItem.getSubtotalPrice());
            }
        }
        Cart cart = new Cart();
        cart.setTotalCount(totalCount);
        cart.setTotalPrice(totalPrice);
        cart.setCartItemList(checkedCartItemList);
        return ServerResponse.success(cart);
    }

    @Override
    public ServerResponse queryCart(Member member) {
        
        Integer id = member.getId();
        String cartKey = SystemConstant.CART_KEY+id;
        //判断当前登录会员是否拥有购物车
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        //取出当前登录会员购物车中的商品K
        List<CartItem> cartItemList = redisTemplate.opsForHash().values(cartKey);

        //遍历购物车中的商品集合，计算总价格和总个数
        Long totalCount = 0L;
        BigDecimal totalPrice = new BigDecimal("0");
        for (CartItem cartItem : cartItemList) {
            //如果当前遍历的商品被选中
            if(cartItem.getChecked()){
                totalCount += cartItem.getCount();
                totalPrice = totalPrice.add(cartItem.getSubtotalPrice());
            }
        }
        Cart cart = new Cart();
        cart.setTotalCount(totalCount);
        cart.setTotalPrice(totalPrice);
        cart.setCartItemList(cartItemList);
        return ServerResponse.success(cart);
    }

    @Override
    public ServerResponse changeCartItemCheckedStatus(Integer productId,Member member) {
        Integer id = member.getId();
        //先判断当前登录会员有没有购物车
        String cartKey = SystemConstant.CART_KEY+id;
        //判断当前登录会员是否拥有购物车
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        String productKey = productId + "";
        //判断当前登录会员的购物车中是否存在该商品
        if(!redisTemplate.opsForHash().hasKey(cartKey,productKey)){
            return ServerResponse.error(ResponseEnum.PRODUCT_IS_NOT_EXIST);
        }

        CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(cartKey,productKey);
        cartItem.setChecked(!cartItem.getChecked());
        redisTemplate.opsForHash().put(cartKey,productKey,cartItem);

        return ServerResponse.success();
    }

    @Override
    public ServerResponse changeAllCartItemCheckedStatus(Boolean checked,Member member) {
        Integer id = member.getId();
        String cartKey = SystemConstant.CART_KEY+id;
        //判断当前登录会员是否拥有购物车
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        //取出当前登录会员购物车中的商品
        List<CartItem> cartItemList = redisTemplate.opsForHash().values(cartKey);

        //遍历购物车中的商品集合
        for (CartItem cartItem : cartItemList) {
            cartItem.setChecked(checked);
            redisTemplate.opsForHash().put(cartKey,cartItem.getProductId()+"",cartItem);
        }


        return ServerResponse.success();
    }

    @Override
    public ServerResponse changeCartItemCount(Integer productId, Integer count, Member member) {
        Integer id = member.getId();
        //先判断当前登录会员有没有购物车
        String cartKey = SystemConstant.CART_KEY+id;
        //判断当前登录会员是否拥有购物车
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        String productKey = productId + "";
        //判断当前登录会员的购物车中是否存在该商品
        if(!redisTemplate.opsForHash().hasKey(cartKey,productKey)){
            return ServerResponse.error(ResponseEnum.PRODUCT_IS_NOT_EXIST);
        }

        CartItem cartItem = (CartItem) redisTemplate.opsForHash().get(cartKey,productKey);
        cartItem.setCount(count+0L);
        cartItem.setSubtotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        redisTemplate.opsForHash().put(cartKey,productKey,cartItem);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse deleteCartItem(Integer productId,Member member) {
        Integer id = member.getId();
        //先判断当前登录会员有没有购物车
        String cartKey = SystemConstant.CART_KEY+id;
        //判断当前登录会员是否拥有购物车
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        String productKey = productId + "";
        //判断当前登录会员的购物车中是否存在该商品
        if(!redisTemplate.opsForHash().hasKey(cartKey,productKey)){
            return ServerResponse.error(ResponseEnum.PRODUCT_IS_NOT_EXIST);
        }
        redisTemplate.opsForHash().delete(cartKey,productKey);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse batchDeleteCartItem(Member member) {
        Integer id = member.getId();
        String cartKey = SystemConstant.CART_KEY+id;
        //判断当前登录会员是否拥有购物车
        if(!redisTemplate.hasKey(cartKey)){
            return ServerResponse.error(ResponseEnum.CART_IS_NULL);
        }

        //取出当前登录会员购物车中的商品
        List<CartItem> cartItemList = redisTemplate.opsForHash().values(cartKey);

        //遍历购物车中的商品集合
        for (CartItem cartItem : cartItemList) {
            if(cartItem.getChecked()){
                redisTemplate.opsForHash().delete(cartKey,cartItem.getProductId()+"");
            }
        }


        return ServerResponse.success();
    }

}
