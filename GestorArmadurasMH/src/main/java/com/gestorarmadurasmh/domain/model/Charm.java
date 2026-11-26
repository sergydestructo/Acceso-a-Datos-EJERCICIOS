package com.gestorarmadurasmh.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Charm {
    private final int id;
    private final Integer gameId;
    private final boolean randomized;
    private final List<CharmRank> ranks;

    public Charm(int id, Integer gameId, boolean randomized, List<CharmRank> ranks) {
        this.id = id;
        this.gameId = gameId;
        this.randomized = randomized;
        this.ranks = ranks == null ? new ArrayList<>() : new ArrayList<>(ranks);
    }

    public int getId() {
        return id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public boolean isRandomized() {
        return randomized;
    }

    public List<CharmRank> getRanks() {
        return Collections.unmodifiableList(ranks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Charm charm)) {
            return false;
        }
        return id == charm.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
