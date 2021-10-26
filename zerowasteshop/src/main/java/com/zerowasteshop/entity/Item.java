package com.zerowasteshop.entity;

import com.zerowasteshop.constant.ItemSellStatus;
import com.zerowasteshop.dto.ItemFormDto;
import com.zerowasteshop.exception.OutOfStockException;
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

    public void updateItem(ItemFormDto itemFormDto){
        this.itemName = itemFormDto.getItemName();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0) {
            throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량: " + this.stockNumber + ")");
            }
            this.stockNumber = restStock;
        }
    //취소 시 상품 재고 증가 시키는 메소드   
    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }




}


