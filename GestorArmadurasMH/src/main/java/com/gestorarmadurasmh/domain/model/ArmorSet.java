package com.gestorarmadurasmh.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ArmorSet {
    private final int id;
    private final Integer gameId;
    private final String name;
    private final List<ArmorSetBonus> bonuses;
    private final List<ArmorPiece> pieces;

    public ArmorSet(int id, Integer gameId, String name, List<ArmorSetBonus> bonuses, List<ArmorPiece> pieces) {
        this.id = id;
        this.gameId = gameId;
        this.name = name;
        this.bonuses = bonuses == null ? new ArrayList<>() : new ArrayList<>(bonuses);
        this.pieces = pieces == null ? new ArrayList<>() : new ArrayList<>(pieces);
    }

    public int getId() {
        return id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }

    public List<ArmorSetBonus> getBonuses() {
        return Collections.unmodifiableList(bonuses);
    }

    public List<ArmorPiece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArmorSet armorSet)) {
            return false;
        }
        return id == armorSet.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
