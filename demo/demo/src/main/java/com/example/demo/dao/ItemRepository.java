package com.example.demo.dao;

import com.example.demo.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    ItemEntity findByPriceBetween(int from, int to);

    ItemEntity findByCategory(String category);
}
