package util;

import enums.Categoria;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MapCategories {
    public static final Map<Categoria, List<String>> categories;

    static {
        categories = Map.ofEntries(
                Map.entry(Categoria.SILVER, Arrays.asList("50000", "GOLD")),
                Map.entry(Categoria.GOLD, Arrays.asList("200000", "PLATINIUM")));
    }
}
