package models;

import enums.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MktBancoTest {
    MktBanco mktBanco;
    ContaMagica cta;
    @BeforeEach
    void setup(){
        cta = Mockito.mock(ContaMagica.class);
        mktBanco = new MktBanco(cta);
    }

    @Test
    void deveDevolverOValorFaltanteParaAProximaCategoriaGOlD(){
        Mockito.when(cta.getCategoria()).thenReturn(Categoria.SILVER);
        Mockito.when(cta.getSaldo()).thenReturn(BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(49900), mktBanco.faltanteProxCategoria());
    }

    @Test
    void deveDevolverOValorFaltanteParaAProximaCategoriaPlatinium(){
        Mockito.when(cta.getCategoria()).thenReturn(Categoria.GOLD);
        Mockito.when(cta.getSaldo()).thenReturn(BigDecimal.valueOf(199000));
        assertEquals(BigDecimal.valueOf(1000), mktBanco.faltanteProxCategoria());
    }

    @Test
    void deveDevolverOValorFaltanteParaAProximaCategoriaQuandoPlatinium(){
        Mockito.when(cta.getCategoria()).thenReturn(Categoria.PLATINIUM);
        Mockito.when(cta.getSaldo()).thenReturn(BigDecimal.valueOf(199000));
        assertEquals(BigDecimal.ZERO, mktBanco.faltanteProxCategoria());
    }

    @Test
    void deveDevolverGoldQuandoACategoriaForSilver(){
        Mockito.when(cta.getCategoria()).thenReturn(Categoria.SILVER);
        assertEquals("GOLD", mktBanco.proxCategoria());
    }

    @Test
    void deveDevolverPlatiniumQuandoACategoriaForGold(){
        Mockito.when(cta.getCategoria()).thenReturn(Categoria.GOLD);
        assertEquals("PLATINIUM", mktBanco.proxCategoria());
    }

    @Test
    void deveDevolverACategorialAtualQuandoPlatinium(){
        Mockito.when(cta.getCategoria()).thenReturn(Categoria.PLATINIUM);
        assertEquals("PLATINIUM", mktBanco.proxCategoria());
    }
}
