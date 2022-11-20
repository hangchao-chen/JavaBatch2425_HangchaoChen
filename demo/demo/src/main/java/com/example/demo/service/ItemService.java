package com.example.demo.service;

import com.example.demo.vo.Item;

import java.util.List;

public interface ItemService {

    Item findById(long id);

    List<Item> findAllItems();

    Item updateItem(Item item);

    void deleteItemById(long id);

    Item saveItem(Item item);
}
