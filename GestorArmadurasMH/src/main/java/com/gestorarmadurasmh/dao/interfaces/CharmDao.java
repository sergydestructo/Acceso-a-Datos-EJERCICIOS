package com.gestorarmadurasmh.dao.interfaces;

import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.model.Charm;

import java.util.List;
import java.util.Optional;

public interface CharmDao {

    Optional<Charm> findById(int id, Language language);

    List<Charm> findAll(Language language);
}
