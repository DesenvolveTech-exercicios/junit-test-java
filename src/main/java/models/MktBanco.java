package models;

import enums.Categoria;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@RequiredArgsConstructor
public class MktBanco {
    @NonNull
    private final ContaMagica cta;
    private static final Map<Categoria, BigDecimal> categories =  Map.ofEntries(
            Map.entry(Categoria.SILVER, BigDecimal.valueOf(50000)),
            Map.entry(Categoria.GOLD, BigDecimal.valueOf(200000)));

    private static final Map<Categoria, Categoria> ProxCategories =  Map.ofEntries(
            Map.entry(Categoria.SILVER, Categoria.GOLD),
            Map.entry(Categoria.GOLD, Categoria.PLATINIUM));

    public BigDecimal faltanteProxCategoria(){
        Categoria categoriaConta = cta.getCategoria();
        return (categoriaConta.equals(Categoria.PLATINIUM))?
                BigDecimal.ZERO:
                categories.get(categoriaConta).subtract(cta.getSaldo());
    }

    public Categoria proxCategoria(){
        Categoria categoriaConta = cta.getCategoria();
        return (categoriaConta.equals(Categoria.PLATINIUM))?
                Categoria.PLATINIUM:
                ProxCategories.get(categoriaConta);
    }

}
