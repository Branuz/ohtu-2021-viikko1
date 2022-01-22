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
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
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
    public void käyttökelvoton(){
        varasto = new Varasto(0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiTyhjaVarasto(){
        varasto = new Varasto(10, 4);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yliMaarainenSaldo(){
        varasto = new Varasto(10, 4);
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisataanAlleNolla(){
        varasto = new Varasto(10, 4);
        varasto.lisaaVarastoon(-100);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuus(){
        varasto = new Varasto(-10, 4);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void netatiivinenSaldo(){
        varasto = new Varasto(10, -4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaNegatiivinenMaara(){
        varasto = new Varasto(10, 4);
        varasto.otaVarastosta(-20);
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaKaikkiMitaVoidaan(){
        varasto = new Varasto(10, 4);
        varasto.otaVarastosta(8);
        assertEquals(100000, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkiJonoTesti(){
        varasto = new Varasto(10, 4);
        assertNotEquals("saldo = 4  vielä tilaa 6", varasto.toString());
    }

}