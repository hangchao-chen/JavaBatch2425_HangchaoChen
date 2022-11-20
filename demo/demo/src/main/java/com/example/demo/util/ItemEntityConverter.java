package com.example.demo.util;

import com.example.demo.entity.ItemEntity;
import com.example.demo.vo.Item;

public class ItemEntityConverter {

    public static Item convertEntityToItem(ItemEntity itemEntity) {

        if (itemEntity != null) {
            Item item = new Item();
            item.setId(itemEntity.getId());
            item.setName(itemEntity.getName());
            item.setPrice(itemEntity.getPrice());
            item.setCategory(itemEntity.getCategory());
            return item;
        } else {
            return null;
        }
    }
}
