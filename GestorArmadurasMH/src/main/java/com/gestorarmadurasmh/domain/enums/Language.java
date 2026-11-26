package com.gestorarmadurasmh.domain.enums;

public enum Language {
    ES("es"),
    EN("en");

    private final String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Language fromCode(String code) {
        for (Language language : values()) {
            if (language.code.equalsIgnoreCase(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Idioma no soportado: " + code);
    }
}
