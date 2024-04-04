package org.example.dollarreview.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dollar-order", url = "http://localhost:8084/external")
public interface OrderFeignClient {

    @GetMapping("/users/{userId}/products/{productId}")
    Long countByUserIdAndProductId(@PathVariable Long userId, @PathVariable Long productId);
}
