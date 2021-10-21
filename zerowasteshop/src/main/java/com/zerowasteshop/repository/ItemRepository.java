package com.zerowasteshop.repository;

import com.zerowasteshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository<entity type class, PK type> 상속받음.
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByItemName(String itemName);

    //Query ItemName or Item Detail
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    //Query for Price Less Than
    List<Item> findByPriceLessThan(Integer price);

    //Order By Descending
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
}
