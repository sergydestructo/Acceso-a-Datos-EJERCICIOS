package com.gestorarmadurasmh.domain.model;

import java.util.Objects;

public class CraftingMaterial {
    private final Item item;
    private final int quantity;

    public CraftingMaterial(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CraftingMaterial that)) {
            return false;
        }
        return quantity == that.quantity && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, quantity);
    }
}
