package org.example.dollarreview.domain.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dollarreview.domain.user.User;
import org.example.dollarreview.global.TimeStamped;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends TimeStamped {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(value = EnumType.STRING)
    private OrderState state;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private Address address;
//
//    public Order(User user,OrderState state,Address address){
//        this.user = user;
//        this.state = state;
//        this.address = address;
//    }

//    public void changeState(OrderState state){
//        this.state = state;
//    }

}
