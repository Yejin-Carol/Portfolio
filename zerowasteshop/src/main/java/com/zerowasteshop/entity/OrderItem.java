package com.zerowasteshop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)//하나의 item은 여러 order.
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)//한번의 order에 여러 item
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice;//주문 가격
    private int count;//수량
//    private LocalDateTime regTime; //BaseEntity 상속 - 삭제
//    private LocalDateTime updateTime;

}
