package entities;

import enums.Categoria;

import java.math.BigDecimal;

public class ContaMagica {
    private String nome;
    private BigDecimal saldo;
    private Categoria categoria;

    public ContaMagica() {
    }

    public ContaMagica(String nome, BigDecimal saldo, Categoria categoria) {
        this.nome = nome;
        this.saldo = saldo;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void deposita(BigDecimal valor) {
        setSaldo(saldo.add(valor));
        int gold = saldo.compareTo(new BigDecimal("50000"));
        int platinium = saldo.compareTo(new BigDecimal("200000"));

        if ((gold > 0) || (gold == 0)) {
            setCategoria(Categoria.GOLD);
            System.out.println("Miga gora cê é gold");
           setSaldo( saldo.add(valor.multiply(new BigDecimal("0.01"))));
        }
        if ((platinium > 0) || (platinium == 0)) {
            setCategoria(Categoria.PLATINIUM);
            setSaldo(saldo.add(valor.multiply(new BigDecimal("0.025"))));
            System.out.println("Agora você é platinium");
        }
    }

    public void retirada(BigDecimal valor) {
        int verificaSaldo = saldo.compareTo(valor);
        if( verificaSaldo >= 0 ){
            setSaldo(saldo.subtract(valor));
            if((getSaldo().compareTo(new BigDecimal("100000")) < 0) && getCategoria() == Categoria.PLATINIUM ){
                setCategoria(Categoria.GOLD);
                System.out.println("Migah Cê tá pobre! Desceu pra gold");
            } else if((getSaldo().compareTo(new BigDecimal("25000"))< 0) && getCategoria() == Categoria.GOLD) {
                setCategoria(Categoria.SILVER);
                System.out.println("Migah A falência Chegando na sua porta! Desceu pra Silver");
            }
        }
    }

    public Categoria getStatus() {
        return getCategoria();
    }

}
