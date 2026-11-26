package com.gestorarmadurasmh.dao.interfaces;

import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.model.ArmorSet;

import java.util.List;
import java.util.Optional;

public interface ArmorSetDao {

    Optional<ArmorSet> findById(int id, Language language);

    List<ArmorSet> findAll(Language language);
}
