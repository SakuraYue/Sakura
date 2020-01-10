package com.fh.product.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.brand.model.Brand;
import com.fh.common.ServerResponse;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ServerResponse queryList() {
        if(redisTemplate.hasKey(SystemConstant.PRODUCT_HOT_LIST)){
            List<Product> categories = (List<Product>) redisTemplate.opsForValue().get(SystemConstant.PRODUCT_HOT_LIST);
            return ServerResponse.success(categories);
        }
        List<Product> brands = productMapper.queryList();
        redisTemplate.opsForValue().set(SystemConstant.PRODUCT_HOT_LIST,brands);
        return ServerResponse.success(brands);
    }
}
