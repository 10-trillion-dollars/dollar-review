package org.example.dollarreview;

import org.assertj.core.api.Assertions;
import org.example.dollarreview.domain.order.Order;
import org.example.dollarreview.domain.order.OrderDetail;
import org.example.dollarreview.domain.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.List;


public class FeignTest {

    //실행하기 전에 FeignClient에 url이 local인지 확인
    //각 서버가 실행이 되고 있는지 확인
    @Test
    @DisplayName("Order 통신 테스트")
    public void OrderFeigntest2() {
        RestTemplate restTemplate = new RestTemplate();
        String UserId = "506" ;
        String ProductId = "2";
        ResponseEntity<List<OrderDetail>> response =
                restTemplate.exchange(
                        "http://localhost:8084/external/users/"+UserId+"/products/"+ProductId+"/orders",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<OrderDetail>>() {} );
        List<OrderDetail> orderDetails = response.getBody();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(orderDetails).isNotNull();
        for(OrderDetail orderDetail:orderDetails){
            System.out.println(orderDetail.getProductName());
        }
    }

    @Test
    @DisplayName("Product 통신 테스트")
    public void ProductFeigntest() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Product> response = restTemplate
                .getForEntity("http://localhost:8083/external/products/1", Product.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody().getName());
    }

}
