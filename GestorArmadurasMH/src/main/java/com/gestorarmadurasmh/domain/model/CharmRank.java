package com.gestorarmadurasmh.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CharmRank {
    private final int id;
    private final int charmId;
    private final int level;
    private final int rarity;
    private final boolean craftable;
    private final Integer craftingZenny;
    private final List<SkillRank> skills;
    private final List<CraftingMaterial> craftingMaterials;
    private final String name;
    private final String description;

    public CharmRank(int id, int charmId, int level, int rarity, boolean craftable, Integer craftingZenny,
                     List<SkillRank> skills, List<CraftingMaterial> craftingMaterials, String name, String description) {
        this.id = id;
        this.charmId = charmId;
        this.level = level;
        this.rarity = rarity;
        this.craftable = craftable;
        this.craftingZenny = craftingZenny;
        this.skills = skills == null ? new ArrayList<>() : new ArrayList<>(skills);
        this.craftingMaterials = craftingMaterials == null ? new ArrayList<>() : new ArrayList<>(craftingMaterials);
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getCharmId() {
        return charmId;
    }

    public int getLevel() {
        return level;
    }

    public int getRarity() {
        return rarity;
    }

    public boolean isCraftable() {
        return craftable;
    }

    public Integer getCraftingZenny() {
        return craftingZenny;
    }

    public List<SkillRank> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public List<CraftingMaterial> getCraftingMaterials() {
        return Collections.unmodifiableList(craftingMaterials);
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
        if (!(o instanceof CharmRank charmRank)) {
            return false;
        }
        return id == charmRank.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
