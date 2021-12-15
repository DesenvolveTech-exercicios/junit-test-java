package models;

import enums.Categoria;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaMagica {
    private String nome;
    private BigDecimal saldo;
    private Categoria categoria;

//    public int recebe(BigDecimal valor){
//        return 0;
//    }

    public void deposita(BigDecimal valor) {
        setSaldo(saldo.add(valor));

        int gold = saldo.compareTo(BigDecimal.valueOf(50000));
        int platinium = saldo.compareTo(BigDecimal.valueOf(200000));

        if (gold >= 0 ) {
            setCategoria(Categoria.GOLD);
            setSaldo( saldo.add(valor.multiply(new BigDecimal("0.01"))));
        }
        if ((platinium > 0) || (platinium == 0)) {
            setCategoria(Categoria.PLATINIUM);
            setSaldo(saldo.add(valor.multiply(new BigDecimal("0.025"))));
        }
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
