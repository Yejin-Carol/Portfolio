package com.zerowasteshop.entity;

import com.zerowasteshop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//.AUTO: Default, .IDENTITY: MySQL
    private Long id; //item code

    @Column(nullable = false, length = 50)
    private String itemName;

    @Column(name="price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber; //재고

    //@Lob annotation can store Large Object
    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)//constant
    private ItemSellStatus itemSellStatus; //상품 재고현황(판매 상태)

//    private LocalDateTime regTime; //Time of item registration, BaseEntity 상속 - 삭제
//
//    private LocalDateTime updateTime; //Time of item update

}
