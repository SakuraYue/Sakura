package com.fh.product.controller;

import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("product/")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Ignore
    @RequestMapping("queryList")
    public ServerResponse queryList(){
        return productService.queryList();
    }

}
