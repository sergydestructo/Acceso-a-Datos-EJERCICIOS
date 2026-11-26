CREATE TABLE IF NOT EXISTS skill (
    id INTEGER PRIMARY KEY,
    game_id INTEGER UNIQUE,
    kind TEXT NOT NULL,
    icon_id INTEGER,
    icon_kind TEXT,
    CHECK (kind IN ('armor', 'weapon', 'set', 'group'))
);

CREATE TABLE IF NOT EXISTS skill_i18n (
    skill_id INTEGER NOT NULL,
    lang TEXT NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    PRIMARY KEY (skill_id, lang),
    FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (lang IN ('es', 'en'))
);

CREATE TABLE IF NOT EXISTS skill_rank (
    id INTEGER PRIMARY KEY,
    skill_id INTEGER NOT NULL,
    level INTEGER NOT NULL,
    set_pieces_required INTEGER,
    FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (level > 0),
    CHECK (set_pieces_required IS NULL OR set_pieces_required > 0),
    UNIQUE (skill_id, level, set_pieces_required)
);

CREATE TABLE IF NOT EXISTS skill_rank_i18n (
    skill_rank_id INTEGER NOT NULL,
    lang TEXT NOT NULL,
    name TEXT,
    description TEXT NOT NULL,
    PRIMARY KEY (skill_rank_id, lang),
    FOREIGN KEY (skill_rank_id) REFERENCES skill_rank(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (lang IN ('es', 'en'))
);

CREATE TABLE IF NOT EXISTS item (
    id INTEGER PRIMARY KEY,
    game_id INTEGER UNIQUE,
    rarity INTEGER NOT NULL,
    value INTEGER NOT NULL DEFAULT 0,
    carry_limit INTEGER NOT NULL DEFAULT 0,
    icon_id INTEGER,
    icon_kind TEXT,
    icon_color_id INTEGER,
    icon_color TEXT,
    CHECK (rarity >= 0),
    CHECK (value >= 0),
    CHECK (carry_limit >= 0)
);

CREATE TABLE IF NOT EXISTS item_i18n (
    item_id INTEGER NOT NULL,
    lang TEXT NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    PRIMARY KEY (item_id, lang),
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (lang IN ('es', 'en'))
);

CREATE TABLE IF NOT EXISTS armor_set (
    id INTEGER PRIMARY KEY,
    game_id INTEGER UNIQUE
);

CREATE TABLE IF NOT EXISTS armor_set_i18n (
    armor_set_id INTEGER NOT NULL,
    lang TEXT NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (armor_set_id, lang),
    FOREIGN KEY (armor_set_id) REFERENCES armor_set(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (lang IN ('es', 'en'))
);

CREATE TABLE IF NOT EXISTS armor_set_bonus (
    armor_set_id INTEGER NOT NULL,
    bonus_kind TEXT NOT NULL,
    required_pieces INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,
    skill_rank_id INTEGER NOT NULL,
    PRIMARY KEY (armor_set_id, bonus_kind, required_pieces),
    FOREIGN KEY (armor_set_id) REFERENCES armor_set(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES skill(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (skill_rank_id) REFERENCES skill_rank(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CHECK (bonus_kind IN ('set', 'group')),
    CHECK (required_pieces > 0)
);

CREATE TABLE IF NOT EXISTS armor_piece (
    id INTEGER PRIMARY KEY,
    armor_set_id INTEGER,
    kind TEXT NOT NULL,
    rank TEXT NOT NULL,
    rarity INTEGER NOT NULL,
    defense_base INTEGER NOT NULL,
    defense_max INTEGER NOT NULL,
    resist_fire INTEGER NOT NULL,
    resist_water INTEGER NOT NULL,
    resist_ice INTEGER NOT NULL,
    resist_thunder INTEGER NOT NULL,
    resist_dragon INTEGER NOT NULL,
    crafting_zenny INTEGER,
    FOREIGN KEY (armor_set_id) REFERENCES armor_set(id) ON DELETE SET NULL ON UPDATE CASCADE,
    CHECK (kind IN ('head', 'chest', 'arms', 'waist', 'legs')),
    CHECK (rank IN ('low', 'high', 'master')),
    CHECK (rarity >= 0),
    CHECK (defense_base >= 0),
    CHECK (defense_max >= 0),
    CHECK (crafting_zenny IS NULL OR crafting_zenny >= 0)
);

CREATE TABLE IF NOT EXISTS armor_piece_i18n (
    armor_piece_id INTEGER NOT NULL,
    lang TEXT NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    PRIMARY KEY (armor_piece_id, lang),
    FOREIGN KEY (armor_piece_id) REFERENCES armor_piece(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (lang IN ('es', 'en'))
);

CREATE TABLE IF NOT EXISTS armor_piece_slot (
    armor_piece_id INTEGER NOT NULL,
    slot_index INTEGER NOT NULL,
    slot_level INTEGER NOT NULL,
    PRIMARY KEY (armor_piece_id, slot_index),
    FOREIGN KEY (armor_piece_id) REFERENCES armor_piece(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (slot_index >= 0),
    CHECK (slot_level > 0)
);

CREATE TABLE IF NOT EXISTS armor_piece_skill (
    armor_piece_id INTEGER NOT NULL,
    skill_rank_id INTEGER NOT NULL,
    PRIMARY KEY (armor_piece_id, skill_rank_id),
    FOREIGN KEY (armor_piece_id) REFERENCES armor_piece(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (skill_rank_id) REFERENCES skill_rank(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS armor_crafting_material (
    armor_piece_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (armor_piece_id, item_id),
    FOREIGN KEY (armor_piece_id) REFERENCES armor_piece(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CHECK (quantity > 0)
);

CREATE TABLE IF NOT EXISTS charm (
    id INTEGER PRIMARY KEY,
    game_id INTEGER UNIQUE,
    randomized INTEGER NOT NULL DEFAULT 0,
    CHECK (randomized IN (0, 1))
);

CREATE TABLE IF NOT EXISTS charm_rank (
    id INTEGER PRIMARY KEY,
    charm_id INTEGER NOT NULL,
    level INTEGER NOT NULL,
    rarity INTEGER NOT NULL,
    craftable INTEGER NOT NULL DEFAULT 0,
    crafting_zenny INTEGER,
    FOREIGN KEY (charm_id) REFERENCES charm(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (level > 0),
    CHECK (rarity >= 0),
    CHECK (craftable IN (0, 1)),
    CHECK (crafting_zenny IS NULL OR crafting_zenny >= 0),
    UNIQUE (charm_id, level)
);

CREATE TABLE IF NOT EXISTS charm_rank_i18n (
    charm_rank_id INTEGER NOT NULL,
    lang TEXT NOT NULL,
    name TEXT NOT NULL,
    description TEXT,
    PRIMARY KEY (charm_rank_id, lang),
    FOREIGN KEY (charm_rank_id) REFERENCES charm_rank(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CHECK (lang IN ('es', 'en'))
);

CREATE TABLE IF NOT EXISTS charm_rank_skill (
    charm_rank_id INTEGER NOT NULL,
    skill_rank_id INTEGER NOT NULL,
    PRIMARY KEY (charm_rank_id, skill_rank_id),
    FOREIGN KEY (charm_rank_id) REFERENCES charm_rank(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (skill_rank_id) REFERENCES skill_rank(id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS charm_rank_crafting_material (
    charm_rank_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (charm_rank_id, item_id),
    FOREIGN KEY (charm_rank_id) REFERENCES charm_rank(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (item_id) REFERENCES item(id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CHECK (quantity > 0)
);

CREATE TABLE IF NOT EXISTS sync_metadata (
    endpoint TEXT NOT NULL,
    lang TEXT NOT NULL,
    last_sync_at TEXT,
    status TEXT NOT NULL,
    source_version TEXT,
    message TEXT,
    PRIMARY KEY (endpoint, lang),
    CHECK (lang IN ('es', 'en')),
    CHECK (status IN ('pending', 'running', 'success', 'failed'))
);

CREATE INDEX IF NOT EXISTS idx_skill_i18n_lang_name ON skill_i18n(lang, name);
CREATE INDEX IF NOT EXISTS idx_skill_rank_skill_id ON skill_rank(skill_id);
CREATE INDEX IF NOT EXISTS idx_item_i18n_lang_name ON item_i18n(lang, name);
CREATE INDEX IF NOT EXISTS idx_armor_set_i18n_lang_name ON armor_set_i18n(lang, name);
CREATE INDEX IF NOT EXISTS idx_armor_set_bonus_skill_id ON armor_set_bonus(skill_id);
CREATE INDEX IF NOT EXISTS idx_armor_set_bonus_skill_rank_id ON armor_set_bonus(skill_rank_id);
CREATE INDEX IF NOT EXISTS idx_armor_piece_armor_set_id ON armor_piece(armor_set_id);
CREATE INDEX IF NOT EXISTS idx_armor_piece_kind_rank_rarity ON armor_piece(kind, rank, rarity);
CREATE INDEX IF NOT EXISTS idx_armor_piece_i18n_lang_name ON armor_piece_i18n(lang, name);
CREATE INDEX IF NOT EXISTS idx_armor_piece_skill_skill_rank_id ON armor_piece_skill(skill_rank_id);
CREATE INDEX IF NOT EXISTS idx_armor_crafting_material_item_id ON armor_crafting_material(item_id);
CREATE INDEX IF NOT EXISTS idx_charm_rank_charm_id ON charm_rank(charm_id);
CREATE INDEX IF NOT EXISTS idx_charm_rank_i18n_lang_name ON charm_rank_i18n(lang, name);
CREATE INDEX IF NOT EXISTS idx_charm_rank_skill_skill_rank_id ON charm_rank_skill(skill_rank_id);
CREATE INDEX IF NOT EXISTS idx_charm_rank_crafting_material_item_id ON charm_rank_crafting_material(item_id);
