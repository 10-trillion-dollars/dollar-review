package org.example.dollarreview.domain.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dollarreview.domain.user.entity.User;
import org.example.dollarreview.global.TimeStamped;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private Long price;
    @Column
    private String description;
    @Column
    private Long stock;
    @Column
    private String photo;
    @Column
    private boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @Builder
    public Product(String name, Long price, String description, Long stock, String photo,
        User user) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
        this.photo = photo;
        this.user = user;
        this.state = true;
    }

}
