package com.fh.brand.controller;

import com.fh.brand.service.BrandService;
import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("brand")
@CrossOrigin
public class BrandController {
    @Autowired
    private BrandService brandService;

    @Ignore
    @RequestMapping("queryList")
    public ServerResponse queryList(){
        return brandService.queryList();
    }

}
