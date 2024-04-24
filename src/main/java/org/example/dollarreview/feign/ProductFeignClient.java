package org.example.dollarreview.feign;


import org.example.dollarreview.domain.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "dollar-product", url = "https://product.10-trillon-dollars.com/external")
@FeignClient(name = "dollar-product", url = "http://localhost:8083/external")
public interface ProductFeignClient {

    @GetMapping("/products/{productId}")
    Product getProduct(@PathVariable("productId") Long productId);

}

