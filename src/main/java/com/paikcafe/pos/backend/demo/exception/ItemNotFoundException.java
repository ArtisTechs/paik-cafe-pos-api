package com.paikcafe.pos.backend.demo.exception;

public class ItemNotFoundException extends ApiException {
    public ItemNotFoundException() {
        super("Item not found", "ITEM_NOT_FOUND");
    }
}
