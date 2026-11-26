package com.gestorarmadurasmh.domain.model;

import java.util.Objects;

public class Item {
    private final int id;
    private final Integer gameId;
    private final int rarity;
    private final int value;
    private final int carryLimit;
    private final Integer iconId;
    private final String iconKind;
    private final Integer iconColorId;
    private final String iconColor;
    private final String name;
    private final String description;

    public Item(int id, Integer gameId, int rarity, int value, int carryLimit, Integer iconId, String iconKind,
                Integer iconColorId, String iconColor, String name, String description) {
        this.id = id;
        this.gameId = gameId;
        this.rarity = rarity;
        this.value = value;
        this.carryLimit = carryLimit;
        this.iconId = iconId;
        this.iconKind = iconKind;
        this.iconColorId = iconColorId;
        this.iconColor = iconColor;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public int getRarity() {
        return rarity;
    }

    public int getValue() {
        return value;
    }

    public int getCarryLimit() {
        return carryLimit;
    }

    public Integer getIconId() {
        return iconId;
    }

    public String getIconKind() {
        return iconKind;
    }

    public Integer getIconColorId() {
        return iconColorId;
    }

    public String getIconColor() {
        return iconColor;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item item)) {
            return false;
        }
        return id == item.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
