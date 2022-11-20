package com.example.demo.service;

import com.example.demo.dao.ItemRepository;
import com.example.demo.entity.ItemEntity;
import com.example.demo.util.ItemEntityConverter;
import com.example.demo.vo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepo;


    public Item findById(long id) {
        ItemEntity itemEntity = itemRepo.findById(id).orElse(null);
        return ItemEntityConverter.convertEntityToItem(itemEntity);
    }


    public List<Item> findAllItems() {
        List<ItemEntity> items = itemRepo.findAll();
        return items.stream().map(e -> new Item(e.getId(), e.getName(), e.getPrice(), e.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public Item updateItem(Item item) {
        ItemEntity itemEntity = itemRepo.saveAndFlush(new ItemEntity(item.getId(), item.getName(), item.getPrice(), item.getCategory()));
        return ItemEntityConverter.convertEntityToItem(itemEntity);
    }

    @Override
    public void deleteItemById(long id) {
        itemRepo.deleteById(id);
    }

    @Override
    public Item saveItem(Item item) {
        ItemEntity itemEntity = itemRepo.save(new ItemEntity(item.getId(), item.getName(), item.getPrice(), item.getCategory()));
        return ItemEntityConverter.convertEntityToItem(itemEntity);
    }
}
