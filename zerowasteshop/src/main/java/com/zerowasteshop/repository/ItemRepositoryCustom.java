package com.zerowasteshop.repository;

import com.zerowasteshop.dto.ItemSearchDto;
import com.zerowasteshop.dto.MainItemDto;
import com.zerowasteshop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}
