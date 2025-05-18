package com.bluefox.Pizzeria.model.food;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum representing pizza sizes.
 */
@Getter
public enum Size {
    SMALL("Pequeno", "P"),
    MEDIUM("Médio", "M"),
    LARGE("Grande", "G"),
    FAMILY("Familia", "GG");

    private final String portuguese;
    private final String symbol;

    private static final Map<String, Size> FROM_PORTUGUESE = new HashMap<>();

    static {
        for (Size size : values()) {
            FROM_PORTUGUESE.put(size.portuguese.toLowerCase(), size);
        }
    }

    Size(String portuguese, String symbol) {
        this.portuguese = portuguese;
        this.symbol = symbol;
    }

    public static Size fromPortuguese(String portuguese) {
        if (portuguese == null) return null;
        return FROM_PORTUGUESE.get(portuguese.toLowerCase());
    }

    public static Size fromSymbol(String symbol) {
        if (symbol == null) return null;
        return FROM_PORTUGUESE.get(symbol.toLowerCase());
    }
}
