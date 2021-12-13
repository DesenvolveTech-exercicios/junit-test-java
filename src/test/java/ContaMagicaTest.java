import entities.ContaMagica;
import enums.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaMagicaTest {
    private ContaMagica c1;

    @BeforeEach
    public void setUp() {
        c1 = new ContaMagica("Edi", BigDecimal.ZERO, Categoria.SILVER);
    }

    @Test
    void deveAtualizarCategoriaParaGold() {
        c1.deposita(new BigDecimal("50010"));
        assertEquals(Categoria.GOLD, c1.getCategoria());

    }

    @Test
    void deveAtualizarCategoriaParaPlatinium() {
        c1.deposita(new BigDecimal("200000"));
        assertEquals(Categoria.PLATINIUM, c1.getCategoria());

    }

    @Test
    void deveRetirarValor() {
        c1.setSaldo(new BigDecimal("20000"));
        c1.retirada(new BigDecimal("10000"));
        assertEquals(new BigDecimal("10000"), c1.getSaldo());
    }

    @Test
    void deveAtualizarDePlatinumParaGold() {
        c1.deposita(new BigDecimal("200000"));
        c1.retirada(new BigDecimal("110000"));
        assertEquals(Categoria.GOLD, c1.getCategoria());
    }

    @Test
    void deveAtualizarDeGoldParaSilver() {
        c1.deposita(new BigDecimal("50000"));
        c1.retirada(new BigDecimal("40000"));
        assertEquals(Categoria.SILVER,c1.getCategoria());
    }

    @Test
    void naoDeveAtualizarDePlatinumParaSilver() {
        c1.deposita(new BigDecimal("200000"));
        c1.retirada(new BigDecimal("198000"));
        assertEquals(Categoria.GOLD, c1.getCategoria());
    }

    @Test
    void naoDeveRetirarValorMaiorQueSaldo() {
        c1.setSaldo(new BigDecimal("50000"));
        c1.retirada(new BigDecimal("60000"));
        assertEquals(new BigDecimal("50000"),c1.getSaldo());
    }
}
