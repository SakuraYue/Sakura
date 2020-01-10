package com.fh.category.controller;

import com.fh.common.Ignore;
import com.fh.common.ServerResponse;
import com.fh.category.model.Category;
import com.fh.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Ignore
    @RequestMapping("/queryCategoryList")
    public ServerResponse queryCategoryList(){
            List<Category> categoryList = categoryService.queryCategoryList();
        return ServerResponse.success(categoryList);
    }

}
