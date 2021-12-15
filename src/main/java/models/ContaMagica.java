package models;

import enums.Categoria;
import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class ContaMagica {
    @NonNull
    private String nome;
    @NonNull
    private BigDecimal saldo;
    @NonNull
    private Categoria categoria;
    private Map<Categoria,BigDecimal> multiple = Map.ofEntries(
            Map.entry(Categoria.SILVER, BigDecimal.valueOf(1)),
            Map.entry(Categoria.GOLD, BigDecimal.valueOf(0.01)),
            Map.entry(Categoria.PLATINIUM, BigDecimal.valueOf(0.025)));

    public Categoria update(BigDecimal saldoComparar){
        int gold = saldoComparar.compareTo(BigDecimal.valueOf(50000));
        int platinium = saldoComparar.compareTo(BigDecimal.valueOf(200000));
        if((gold >= 0) && (platinium < 0)){
            return Categoria.GOLD;
        } else if(platinium >= 0){
            return Categoria.PLATINIUM;
        } else{
            return Categoria.SILVER;
        }
    }

    public void deposita(BigDecimal valor) {
        setSaldo(saldo.add(valor));
        setCategoria(update(saldo));
        setSaldo(saldo.add(valor.multiply(multiple.get(categoria))));
    }

    public void retirada(BigDecimal valor) {
        int verificaSaldo = saldo.compareTo(valor);
        if( verificaSaldo >= 0 ){
            setSaldo(saldo.subtract(valor));
            if((getSaldo().compareTo(new BigDecimal("100000")) < 0) && getCategoria() == Categoria.PLATINIUM ){
                setCategoria(Categoria.GOLD);
            } else if((getSaldo().compareTo(new BigDecimal("25000"))< 0) && getCategoria() == Categoria.GOLD) {
                setCategoria(Categoria.SILVER);
            }
        }
    }
}
