package com.example.demo.controller;

import com.example.demo.exception.ItemNotFound;
import com.example.demo.service.ItemService;
import com.example.demo.util.Constants;
import com.example.demo.vo.ErrorResponse;
import com.example.demo.vo.Item;
import com.example.demo.vo.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
@Api(value = "Item", description = "REST API for items",tags = {"Item"})
public class ItemRestController {
    private static Logger logger = LoggerFactory.getLogger(ItemRestController.class);

    ItemService itemService;

    Constants messages;


    @Autowired
    public ItemRestController(ItemService itemService, Constants messages){
        this.itemService = itemService;
        this.messages = messages;
    }

    @ApiOperation(value = "get a single item")
    @RequestMapping(value = "/item/{uid}",method = RequestMethod.GET)
    public ResponseEntity<?> getItem(@PathVariable("uid") long id) throws Exception{
        Item item = itemService.findById(id);
        if (item == null) {
            throw new ItemNotFound(messages.getMessage("ITEM_NOT_FOUND"));
        }
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }

    @ApiOperation(value = "create a item")
    @RequestMapping(value = "/item", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<ResponseMessage> createItem(@Validated @RequestBody Item item, UriComponentsBuilder ucBuilder) {
        Item savedItem = itemService.saveItem(item);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/item/{id}").buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("ITEM_CREATED"), savedItem), headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update a item")
    @RequestMapping(value = "/item/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @RequestBody Item item){
        Item currentItem = itemService.findById(id);

        if (currentItem == null) {
            throw new ItemNotFound(messages.getMessage("ITEM_NOT_FOUND"));
        }

        currentItem.setName(item.getName());
        currentItem.setPrice(item.getPrice());
        currentItem.setCategory(item.getCategory());

        itemService.updateItem(currentItem);
        return new ResponseEntity<Item>(currentItem, HttpStatus.OK);
    }

    @ApiOperation(value = "delete a item")
    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseMessage> deleteItem(@PathVariable("id") long id) {

        Item item = itemService.findById(id);
        if (item == null) {
            throw new ItemNotFound(messages.getMessage("ITEM_NOT_FOUND"));
        }
        itemService.deleteItemById(id);
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("ITEM_DELETED"), item), HttpStatus.OK);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setMessage(ex.getMessage());
        logger.error("Controller Error",ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemNotFound.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerUserNotFound(Exception ex) {
        logger.error("Cannot find item");
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
