package com.gestorarmadurasmh.dao.interfaces;

import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDao {

    Optional<Item> findById(int id, Language language);

    List<Item> findAll(Language language);
}
