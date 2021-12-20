package models;

import enums.Categoria;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import util.MapCategories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MktBanco {
    @NonNull
    private final ContaMagica cta;
    private final Map<Categoria, List<String>> categories = MapCategories.categories;

    public BigDecimal faltanteProxCategoria(){
        Categoria categoryCont = cta.getCategoria();
        return (categoryCont.equals(Categoria.PLATINIUM))?
                BigDecimal.ZERO:
                new BigDecimal(categories.get(categoryCont).get(0)).subtract(cta.getSaldo());
    }

    public String proxCategoria(){
        Categoria categoryCont = cta.getCategoria();
        return (categoryCont.equals(Categoria.PLATINIUM))?
                "PLATINIUM":
                categories.get(categoryCont).get(1);
    }
}