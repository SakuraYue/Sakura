package com.fh.brand.service;

import com.fh.brand.mapper.BrandMapper;
import com.fh.brand.model.Brand;
import com.fh.common.ServerResponse;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BrandMapper branMapper;

    @Override
    public ServerResponse queryList() {
        if(redisTemplate.hasKey(SystemConstant.BRANDLIST)){
            List<Brand> categories = (List<Brand>) redisTemplate.opsForValue().get(SystemConstant.BRANDLIST);
            return ServerResponse.success(categories);
        }
        List<Brand> categories = branMapper.queryList();
        redisTemplate.opsForValue().set(SystemConstant.BRANDLIST,categories);
        return ServerResponse.success(categories);
    }
}
