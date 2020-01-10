package com.fh.category.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fh.category.mapper.CategoryMapper;
import com.fh.category.model.Category;
import com.fh.category.service.CategoryService;
import com.fh.util.RedisUtil;
import com.fh.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> queryCategoryList() {
        if(redisTemplate.hasKey(SystemConstant.CATEGORYLIST)){
            return (List<Category>) redisTemplate.opsForValue().get(SystemConstant.CATEGORYLIST);
        }
        List<Category> categories = categoryMapper.queryCategoryList();
        redisTemplate.opsForValue().set(SystemConstant.CATEGORYLIST,categories);
        return categories;
    }
}
