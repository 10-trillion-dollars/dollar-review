package org.example.dollarreview.feign;

import feign.FeignException.FeignClientException;
import java.util.List;
import org.example.dollarreview.domain.order.OrderDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "dollar-order", url = "https://order.10-trillon-dollars.com/external")
//@FeignClient(name = "dollar-order", url = "http://localhost:8084/external")
public interface OrderFeignClient {

    @GetMapping("/users/{userId}/products/{productId}")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, maxDelay = 5000)
        , noRetryFor = {FeignClientException.class}
    )
    Long countByUserIdAndProductId(@PathVariable Long userId, @PathVariable Long productId);
    @GetMapping("/order/users/{userId}/products/{productId}")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, maxDelay = 5000)
        , noRetryFor = {FeignClientException.class}
    )
    String checkOrderState(@PathVariable Long userId,@PathVariable Long productId);

    @GetMapping("/users/{userId}/products/{productId}/orders")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, maxDelay = 5000)
        , noRetryFor = {FeignClientException.class}
    )
    List<OrderDetail> getOrderDetails(@PathVariable Long userId, @PathVariable Long productId);

    @PostMapping("/orders/orderDetail/reviewState")
    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000, maxDelay = 5000)
        , noRetryFor = {FeignClientException.class}
    )
    void saveOrderDetailReviewedState(@RequestBody OrderDetail orderDetail);
}
