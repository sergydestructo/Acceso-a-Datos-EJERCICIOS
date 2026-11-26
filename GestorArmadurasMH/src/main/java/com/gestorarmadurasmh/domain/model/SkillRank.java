package com.gestorarmadurasmh.domain.model;

import java.util.Objects;

public class SkillRank {
    private final int id;
    private final int skillId;
    private final int level;
    private final Integer setPiecesRequired;
    private final String name;
    private final String description;

    public SkillRank(int id, int skillId, int level, Integer setPiecesRequired, String name, String description) {
        this.id = id;
        this.skillId = skillId;
        this.level = level;
        this.setPiecesRequired = setPiecesRequired;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getSkillId() {
        return skillId;
    }

    public int getLevel() {
        return level;
    }

    public Integer getSetPiecesRequired() {
        return setPiecesRequired;
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
        if (!(o instanceof SkillRank skillRank)) {
            return false;
        }
        return id == skillRank.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
