package org.example.dollarreview.domain.order.repository;


import java.util.List;
import org.example.dollarreview.domain.order.entity.Order;
import org.example.dollarreview.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByUser(User user);
}
