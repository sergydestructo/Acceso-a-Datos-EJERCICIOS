package com.gestorarmadurasmh.domain.model;

import com.gestorarmadurasmh.domain.enums.SkillKind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Skill {
    private final int id;
    private final Integer gameId;
    private final SkillKind kind;
    private final Integer iconId;
    private final String iconKind;
    private final String name;
    private final String description;
    private final List<SkillRank> ranks;

    public Skill(int id, Integer gameId, SkillKind kind, Integer iconId, String iconKind, String name,
                 String description, List<SkillRank> ranks) {
        this.id = id;
        this.gameId = gameId;
        this.kind = kind;
        this.iconId = iconId;
        this.iconKind = iconKind;
        this.name = name;
        this.description = description;
        this.ranks = ranks == null ? new ArrayList<>() : new ArrayList<>(ranks);
    }

    public int getId() {
        return id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public SkillKind getKind() {
        return kind;
    }

    public Integer getIconId() {
        return iconId;
    }

    public String getIconKind() {
        return iconKind;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<SkillRank> getRanks() {
        return Collections.unmodifiableList(ranks);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Skill skill)) {
            return false;
        }
        return id == skill.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
