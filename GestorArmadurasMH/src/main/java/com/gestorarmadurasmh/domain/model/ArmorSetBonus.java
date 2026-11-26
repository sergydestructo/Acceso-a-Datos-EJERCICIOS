package com.gestorarmadurasmh.domain.model;

import com.gestorarmadurasmh.domain.enums.BonusKind;

import java.util.Objects;

public class ArmorSetBonus {
    private final int armorSetId;
    private final BonusKind bonusKind;
    private final int requiredPieces;
    private final Skill skill;
    private final SkillRank skillRank;

    public ArmorSetBonus(int armorSetId, BonusKind bonusKind, int requiredPieces, Skill skill, SkillRank skillRank) {
        this.armorSetId = armorSetId;
        this.bonusKind = bonusKind;
        this.requiredPieces = requiredPieces;
        this.skill = skill;
        this.skillRank = skillRank;
    }

    public int getArmorSetId() {
        return armorSetId;
    }

    public BonusKind getBonusKind() {
        return bonusKind;
    }

    public int getRequiredPieces() {
        return requiredPieces;
    }

    public Skill getSkill() {
        return skill;
    }

    public SkillRank getSkillRank() {
        return skillRank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ArmorSetBonus that)) {
            return false;
        }
        return armorSetId == that.armorSetId && requiredPieces == that.requiredPieces && bonusKind == that.bonusKind;
    }

    @Override
    public int hashCode() {
        return Objects.hash(armorSetId, bonusKind, requiredPieces);
    }
}
