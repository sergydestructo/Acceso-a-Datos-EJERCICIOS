package com.gestorarmadurasmh.dao.interfaces;

import com.gestorarmadurasmh.domain.enums.Language;
import com.gestorarmadurasmh.domain.model.ArmorPiece;

import java.util.List;
import java.util.Optional;

public interface ArmorPieceDao {

    Optional<ArmorPiece> findById(int id, Language language);

    List<ArmorPiece> findAll(Language language);

    List<ArmorPiece> findByArmorSetId(int armorSetId, Language language);
}
