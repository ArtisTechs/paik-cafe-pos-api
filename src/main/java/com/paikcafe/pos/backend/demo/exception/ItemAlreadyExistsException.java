package com.paikcafe.pos.backend.demo.exception;

public class ItemAlreadyExistsException extends ApiException {
    public ItemAlreadyExistsException() {
        super("Item with this name already exists", "ITEM_ALREADY_EXISTS");
    }
}
