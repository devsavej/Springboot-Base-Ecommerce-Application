package com.savej.controller;

import com.savej.model.Category;
import com.savej.model.Product;
import com.savej.repository.CategoryDao;
import com.savej.repository.ProductDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductCategoryController {

    @Autowired
    private CategoryDao cdao;


    @Autowired
    private ProductDao pdao;

    @PostMapping("/category")
    public Category saveCategoryHandler(@RequestBody Category category){
        return cdao.save(category);
    }

    @GetMapping("/allCategory")
    public List<Category> getAllCategoriesHandler(){
       return cdao.findAll();
    }

    @PostMapping("/product/{categoryid}")
    public Product saveProduct(@Valid @RequestBody Product product,
                               @PathVariable("categoryid") Long categoryid){
        Category category=cdao.findById(categoryid).orElseThrow(()->new RuntimeException("Category Not found with id:"+categoryid));

        product.setCategory(category);

        return pdao.save(product);

    }



    @GetMapping("/category/{categoryid}/products")
    public List<Product> getProductByCategoryIdHandler(@PathVariable("categoryid") Long categoryid){

        List<Product> products= pdao.findByCategoryCategoryId(categoryid);

        if (products.size()>0){
            return products;
        }else {
            throw new RuntimeException("Product Not Found With CategoryId:"+categoryid);
        }
    }

    @PutMapping("/updateProduct/{productId}")
    public Product updateProductNameHandler(@Valid @PathVariable Long productId,
                                     @RequestParam String name){
        Product product=pdao.findByProductId(productId);

        if (product!=null){
            product.setProductName(name);
            return pdao.save(product);
        }else {
            throw new RuntimeException("Product Not Found With Id:"+productId);
        }
    }


}
