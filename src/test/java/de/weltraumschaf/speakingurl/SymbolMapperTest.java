package de.weltraumschaf.speakingurl;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Tests for {@link SymbolMapper}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SymbolMapperTest {

    private final SymbolMapper sut = new SymbolMapper();

    @Test
    public void knowsSymbol() {
        for (final Language lang : Language.values()) {
            assertThat(lang.toString(), sut.knowsSymbol(lang, "∆"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, "∞"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, "♥"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, "&"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, "|"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, "<"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, ">"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, "∑"), is(equalTo(true)));
            assertThat(lang.toString(), sut.knowsSymbol(lang, "¤"), is(equalTo(true)));
        }
    }

    @Test
    public void mapSymbol_none() {
        assertThat(sut.mapSymbol(Language.NONE, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.NONE, "∞"), is(equalTo("infinity")));
        assertThat(sut.mapSymbol(Language.NONE, "♥"), is(equalTo("love")));
        assertThat(sut.mapSymbol(Language.NONE, "&"), is(equalTo("and")));
        assertThat(sut.mapSymbol(Language.NONE, "|"), is(equalTo("or")));
        assertThat(sut.mapSymbol(Language.NONE, "<"), is(equalTo("less than")));
        assertThat(sut.mapSymbol(Language.NONE, ">"), is(equalTo("greater than")));
        assertThat(sut.mapSymbol(Language.NONE, "∑"), is(equalTo("sum")));
        assertThat(sut.mapSymbol(Language.NONE, "¤"), is(equalTo("currency")));
    }

    @Test
    public void mapSymbol_enlgish() {
        assertThat(sut.mapSymbol(Language.ENGLISH, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.ENGLISH, "∞"), is(equalTo("infinity")));
        assertThat(sut.mapSymbol(Language.ENGLISH, "♥"), is(equalTo("love")));
        assertThat(sut.mapSymbol(Language.ENGLISH, "&"), is(equalTo("and")));
        assertThat(sut.mapSymbol(Language.ENGLISH, "|"), is(equalTo("or")));
        assertThat(sut.mapSymbol(Language.ENGLISH, "<"), is(equalTo("less than")));
        assertThat(sut.mapSymbol(Language.ENGLISH, ">"), is(equalTo("greater than")));
        assertThat(sut.mapSymbol(Language.ENGLISH, "∑"), is(equalTo("sum")));
        assertThat(sut.mapSymbol(Language.ENGLISH, "¤"), is(equalTo("currency")));
    }

    @Test
    public void mapSymbol_arabic() {
        assertThat(sut.mapSymbol(Language.ARABIC, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.ARABIC, "∞"), is(equalTo("la-nihaya")));
        assertThat(sut.mapSymbol(Language.ARABIC, "♥"), is(equalTo("hob")));
        assertThat(sut.mapSymbol(Language.ARABIC, "&"), is(equalTo("wa")));
        assertThat(sut.mapSymbol(Language.ARABIC, "|"), is(equalTo("aw")));
        assertThat(sut.mapSymbol(Language.ARABIC, "<"), is(equalTo("aqal-men")));
        assertThat(sut.mapSymbol(Language.ARABIC, ">"), is(equalTo("akbar-men")));
        assertThat(sut.mapSymbol(Language.ARABIC, "∑"), is(equalTo("majmou")));
        assertThat(sut.mapSymbol(Language.ARABIC, "¤"), is(equalTo("omla")));
    }

    @Test
    public void mapSymbol_german() {
        assertThat(sut.mapSymbol(Language.GERMAN, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.GERMAN, "∞"), is(equalTo("unendlich")));
        assertThat(sut.mapSymbol(Language.GERMAN, "♥"), is(equalTo("Liebe")));
        assertThat(sut.mapSymbol(Language.GERMAN, "&"), is(equalTo("und")));
        assertThat(sut.mapSymbol(Language.GERMAN, "|"), is(equalTo("oder")));
        assertThat(sut.mapSymbol(Language.GERMAN, "<"), is(equalTo("kleiner als")));
        assertThat(sut.mapSymbol(Language.GERMAN, ">"), is(equalTo("groesser als")));
        assertThat(sut.mapSymbol(Language.GERMAN, "∑"), is(equalTo("Summe von")));
        assertThat(sut.mapSymbol(Language.GERMAN, "¤"), is(equalTo("Waehrung")));
    }

    @Test
    public void mapSymbol_netherland() {
        assertThat(sut.mapSymbol(Language.NETHERLAND, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, "∞"), is(equalTo("oneindig")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, "♥"), is(equalTo("liefde")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, "&"), is(equalTo("en")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, "|"), is(equalTo("of")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, "<"), is(equalTo("kleiner dan")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, ">"), is(equalTo("groter dan")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, "∑"), is(equalTo("som")));
        assertThat(sut.mapSymbol(Language.NETHERLAND, "¤"), is(equalTo("valuta")));
    }

    @Test
    public void mapSymbol_spanish() {
        assertThat(sut.mapSymbol(Language.SPANISH, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.SPANISH, "∞"), is(equalTo("infinito")));
        assertThat(sut.mapSymbol(Language.SPANISH, "♥"), is(equalTo("amor")));
        assertThat(sut.mapSymbol(Language.SPANISH, "&"), is(equalTo("y")));
        assertThat(sut.mapSymbol(Language.SPANISH, "|"), is(equalTo("u")));
        assertThat(sut.mapSymbol(Language.SPANISH, "<"), is(equalTo("menos que")));
        assertThat(sut.mapSymbol(Language.SPANISH, ">"), is(equalTo("mas que")));
        assertThat(sut.mapSymbol(Language.SPANISH, "∑"), is(equalTo("suma de los")));
        assertThat(sut.mapSymbol(Language.SPANISH, "¤"), is(equalTo("moneda")));
    }

    @Test
    public void mapSymbol_french() {
        assertThat(sut.mapSymbol(Language.FRENCH, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.FRENCH, "∞"), is(equalTo("infiniment")));
        assertThat(sut.mapSymbol(Language.FRENCH, "♥"), is(equalTo("Amour")));
        assertThat(sut.mapSymbol(Language.FRENCH, "&"), is(equalTo("et")));
        assertThat(sut.mapSymbol(Language.FRENCH, "|"), is(equalTo("ou")));
        assertThat(sut.mapSymbol(Language.FRENCH, "<"), is(equalTo("moins que")));
        assertThat(sut.mapSymbol(Language.FRENCH, ">"), is(equalTo("superieure a")));
        assertThat(sut.mapSymbol(Language.FRENCH, "∑"), is(equalTo("somme des")));
        assertThat(sut.mapSymbol(Language.FRENCH, "¤"), is(equalTo("monnaie")));
    }

    @Test
    public void mapSymbol_portuguese() {
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "∞"), is(equalTo("infinito")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "♥"), is(equalTo("amor")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "&"), is(equalTo("e")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "|"), is(equalTo("ou")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "<"), is(equalTo("menor que")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, ">"), is(equalTo("maior que")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "∑"), is(equalTo("soma")));
        assertThat(sut.mapSymbol(Language.PORTUGUESE, "¤"), is(equalTo("moeda")));
    }

    @Test
    public void mapSymbol_russian() {
        assertThat(sut.mapSymbol(Language.RUSSIAN, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, "∞"), is(equalTo("beskonechno")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, "♥"), is(equalTo("lubov")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, "&"), is(equalTo("i")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, "|"), is(equalTo("ili")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, "<"), is(equalTo("menshe")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, ">"), is(equalTo("bolshe")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, "∑"), is(equalTo("summa")));
        assertThat(sut.mapSymbol(Language.RUSSIAN, "¤"), is(equalTo("valjuta")));
    }

    @Test
    public void mapSymbol_czech() {
        assertThat(sut.mapSymbol(Language.CZECH, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.CZECH, "∞"), is(equalTo("nekonecno")));
        assertThat(sut.mapSymbol(Language.CZECH, "♥"), is(equalTo("laska")));
        assertThat(sut.mapSymbol(Language.CZECH, "&"), is(equalTo("a")));
        assertThat(sut.mapSymbol(Language.CZECH, "|"), is(equalTo("nebo")));
        assertThat(sut.mapSymbol(Language.CZECH, "<"), is(equalTo("mene jako")));
        assertThat(sut.mapSymbol(Language.CZECH, ">"), is(equalTo("vice jako")));
        assertThat(sut.mapSymbol(Language.CZECH, "∑"), is(equalTo("soucet")));
        assertThat(sut.mapSymbol(Language.CZECH, "¤"), is(equalTo("mena")));
    }

    @Test
    public void mapSymbol_swedish() {
        assertThat(sut.mapSymbol(Language.SWEDISH, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.SWEDISH, "∞"), is(equalTo("nekonecno")));
        assertThat(sut.mapSymbol(Language.SWEDISH, "♥"), is(equalTo("laska")));
        assertThat(sut.mapSymbol(Language.SWEDISH, "&"), is(equalTo("a")));
        assertThat(sut.mapSymbol(Language.SWEDISH, "|"), is(equalTo("alebo")));
        assertThat(sut.mapSymbol(Language.SWEDISH, "<"), is(equalTo("menej ako")));
        assertThat(sut.mapSymbol(Language.SWEDISH, ">"), is(equalTo("viac ako")));
        assertThat(sut.mapSymbol(Language.SWEDISH, "∑"), is(equalTo("sucet")));
        assertThat(sut.mapSymbol(Language.SWEDISH, "¤"), is(equalTo("mena")));
    }

    @Test
    public void mapSymbol_vietnamese() {
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "∆"), is(equalTo("delta")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "∞"), is(equalTo("vo cuc")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "♥"), is(equalTo("yeu")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "&"), is(equalTo("va")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "|"), is(equalTo("hoac")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "<"), is(equalTo("nho hon")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, ">"), is(equalTo("lon hon")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "∑"), is(equalTo("tong")));
        assertThat(sut.mapSymbol(Language.VIETNAMESE, "¤"), is(equalTo("tien te")));
    }
}
