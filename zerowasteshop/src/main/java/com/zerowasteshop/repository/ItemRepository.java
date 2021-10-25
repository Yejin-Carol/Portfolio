package com.zerowasteshop.repository;

import com.zerowasteshop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

//JpaRepository<entity type class, PK type> 상속받음.
public interface ItemRepository extends JpaRepository<Item, Long>,
        QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    List<Item> findByItemName(String itemName);

    //Query ItemName or Item Detail
    List<Item> findByItemNameOrItemDetail(String itemName, String itemDetail);

    //Query for Price Less Than
    List<Item> findByPriceLessThan(Integer price);

    //Order By Descending
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    //@Query-1. JPQL
    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    //@Query-2. nativeQuery
    @Query(value="select * from item i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);


}
