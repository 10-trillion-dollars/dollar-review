package org.example.dollarreview.feign;


import org.example.dollarreview.domain.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dollar-product", url = "http://localhost:8083")
public interface ProductFeignClient {

    @GetMapping("/external/products/{productId}")
    Product getProduct(@PathVariable("productId") Long productId);

}

