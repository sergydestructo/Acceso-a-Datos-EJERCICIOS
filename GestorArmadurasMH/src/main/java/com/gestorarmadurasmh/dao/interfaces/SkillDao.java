package com.gestorarmadurasmh.dao.interfaces;

import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.model.Skill;

import java.util.List;
import java.util.Optional;

public interface SkillDao {

    Optional<Skill> findById(int id, Language language);

    List<Skill> findAll(Language language);
}
