package com.gestorarmadurasmh.domain.model;

import com.gestorarmadurasmh.domain.enums.ArmorKind;
import com.gestorarmadurasmh.domain.enums.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArmorPiece {
    private final int id;
    private final Integer armorSetId;
    private final ArmorKind kind;
    private final Rank rank;
    private final int rarity;
    private final int defenseBase;
    private final int defenseMax;
    private final EnumMap<ResistanceType, Integer> resistances;
    private final List<Integer> slots;
    private final List<SkillRank> skills;
    private final Integer craftingZenny;
    private final List<CraftingMaterial> craftingMaterials;
    private final String name;
    private final String description;

    public ArmorPiece(int id, Integer armorSetId, ArmorKind kind, Rank rank, int rarity, int defenseBase,
                      int defenseMax, EnumMap<ResistanceType, Integer> resistances, List<Integer> slots,
                      List<SkillRank> skills, Integer craftingZenny, List<CraftingMaterial> craftingMaterials,
                      String name, String description) {
        this.id = id;
        this.armorSetId = armorSetId;
        this.kind = kind;
        this.rank = rank;
        this.rarity = rarity;
        this.defenseBase = defenseBase;
        this.defenseMax = defenseMax;
        this.resistances = resistances == null ? new EnumMap<>(ResistanceType.class) : new EnumMap<>(resistances);
        this.slots = slots == null ? new ArrayList<>() : new ArrayList<>(slots);
        this.skills = skills == null ? new ArrayList<>() : new ArrayList<>(skills);
        this.craftingZenny = craftingZenny;
        this.craftingMaterials = craftingMaterials == null ? new ArrayList<>() : new ArrayList<>(craftingMaterials);
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Integer getArmorSetId() {
        return armorSetId;
    }

    public ArmorKind getKind() {
        return kind;
    }

    public Rank getRank() {
        return rank;
    }

    public int getRarity() {
        return rarity;
    }

    public int getDefenseBase() {
        return defenseBase;
    }

    public int getDefenseMax() {
        return defenseMax;
    }

    public Map<ResistanceType, Integer> getResistances() {
        return Collections.unmodifiableMap(resistances);
    }

    public List<Integer> getSlots() {
        return Collections.unmodifiableList(slots);
    }

    public List<SkillRank> getSkills() {
        return Collections.unmodifiableList(skills);
    }

    public Integer getCraftingZenny() {
        return craftingZenny;
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
        if (!(o instanceof ArmorPiece that)) {
            return false;
        }
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
