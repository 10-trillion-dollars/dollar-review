package org.example.dollarreview.feign;

import java.util.List;
import org.example.dollarreview.domain.order.OrderDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "dollar-order", url = "http://localhost:8084/external")
public interface OrderFeignClient {

    @GetMapping("/users/{userId}/products/{productId}")
    Long countByUserIdAndProductId(@PathVariable Long userId, @PathVariable Long productId);
    @GetMapping("/order/users/{userId}/products/{productId}")
    String checkOrderState(@PathVariable Long userId,@PathVariable Long productId);

    @GetMapping("/users/{userId}/products/{productId}/orders")
    List<OrderDetail> getOrderDetails(@PathVariable Long userId, @PathVariable Long productId);

    @PostMapping("/orders/orderDetail/reviewState")
    void saveOrderDetailReviewedState(OrderDetail orderDetail);
}
