package com.paikcafe.pos.backend.demo.exception;

public class ItemTypeAlreadyExistsException extends ApiException {
    public ItemTypeAlreadyExistsException() {
        super("Item type with this name already exists", "ITEM_TYPE_EXISTS");
    }
}
