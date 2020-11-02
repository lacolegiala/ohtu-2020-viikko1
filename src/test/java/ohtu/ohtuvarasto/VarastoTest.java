package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(9);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(9, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void laitetaanLiikaaTavaraa() {
        varasto.lisaaVarastoon(11);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otetaanLiikaaTavaraa() {
        varasto.lisaaVarastoon(9);

        varasto.otaVarastosta(10);

        String vastaus = varasto.toString();

        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

    @Test
    public void ottaminenPienentääSaldoa() {
        varasto.lisaaVarastoon(9);

        varasto.otaVarastosta(3);

        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusNollautuu() {
        Varasto uusiVarasto = new Varasto(-1);
        Varasto uusiVarastoSaldolla = new Varasto(-1, 0);

        assertEquals(0, uusiVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, uusiVarastoSaldolla.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiLiianSuurtaAlkusaldoa() {
        Varasto uusiVarasto = new Varasto(6, 7);

        assertEquals(6, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiNegatiivistaAlkuSaldoa() {
        Varasto uusiVarasto = new Varasto(6, -1);

        assertEquals(0, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaSaldo() {
        Varasto uusiVarasto = new Varasto(6, 5);

        assertEquals(6, uusiVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivistaSummaaEiLisätä() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivistaSummaaEiPoisteta() {
        varasto.lisaaVarastoon(1);

        varasto.otaVarastosta(-1);

        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

}