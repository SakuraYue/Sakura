package com.fh.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.product.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {
    List<Product> queryList();

    Long updateProductStock(@Param("id") Integer productId, @Param("count") Long count);
}