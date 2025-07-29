package com.paikcafe.pos.backend.demo.exception;

public class ItemTypeNotFoundException extends ApiException {
    public ItemTypeNotFoundException() {
        super("Item type not found", "ITEM_TYPE_NOT_FOUND");
    }
}
