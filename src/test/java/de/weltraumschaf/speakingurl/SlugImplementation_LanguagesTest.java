package de.weltraumschaf.speakingurl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link SlugImplementation}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SlugImplementation_LanguagesTest {

    @Test
    public void shouldReplaceSymbolsForDefault() {
        final Slug sut = Slug.Builder.newBuiler().create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-infinity-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-love-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-and-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-or-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-less-than-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-greater-than-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-sum-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-currency-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForDefaultMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-infinity-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-love-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-and-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-or-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-less-than-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-greater-than-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-sum-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-currency-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForNone() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.NONE).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForNoneMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.NONE).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForArabic() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.ARABIC).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-la-nihaya-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-hob-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-wa-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-aw-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-aqal-men-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-akbar-men-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-majmou-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-omla-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForArabicMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.ARABIC).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-la-nihaya-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-hob-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-wa-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-aw-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-aqal-men-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-akbar-men-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-majmou-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-omla-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForGerman() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.GERMAN).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-unendlich-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-liebe-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-und-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-oder-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-kleiner-als-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-groesser-als-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-summe-von-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-waehrung-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForGermanMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.GERMAN).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-unendlich-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-Liebe-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-und-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-oder-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-kleiner-als-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-groesser-als-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-Summe-von-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-Waehrung-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForEnglish() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.ENGLISH).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-infinity-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-love-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-and-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-or-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-less-than-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-greater-than-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-sum-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-currency-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForEnglishMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.ENGLISH).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-infinity-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-love-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-and-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-or-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-less-than-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-greater-than-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-sum-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-currency-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForSpain() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.SPANISH).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-infinito-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-amor-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-y-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-u-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-menos-que-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-mas-que-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-suma-de-los-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-moneda-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForSpainMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.SPANISH).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-infinito-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-amor-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-y-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-u-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-menos-que-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-mas-que-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-suma-de-los-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-moneda-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForFrench() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.FRENCH).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-infiniment-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-amour-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-et-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-ou-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-moins-que-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-superieure-a-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-somme-des-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-monnaie-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForFrenchMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.FRENCH).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-infiniment-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-Amour-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-et-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-ou-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-moins-que-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-superieure-a-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-somme-des-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-monnaie-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForPortuguese() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.PORTUGUESE).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-infinito-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-amor-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-e-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-ou-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-menor-que-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-maior-que-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-soma-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-moeda-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForPortugueseMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.PORTUGUESE).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-infinito-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-amor-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-e-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-ou-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-menor-que-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-maior-que-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-soma-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-moeda-Bar")));
    }

    @Test
    public void shouldReplaceSymbolsForRussian() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.RUSSIAN).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("foo-delta-bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("foo-beskonechno-bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("foo-lubov-bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("foo-i-bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("foo-ili-bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("foo-menshe-bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("foo-bolshe-bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("foo-summa-bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("foo-valjuta-bar")));
    }

    @Test
    public void shouldReplaceSymbolsForRussianMaintainCase() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.RUSSIAN).maintainCase(true).create();

        assertThat(sut.get("Foo ∆ Bar"), is(equalTo("Foo-delta-Bar")));
        assertThat(sut.get("Foo ∞ Bar"), is(equalTo("Foo-beskonechno-Bar")));
        assertThat(sut.get("Foo ♥ Bar"), is(equalTo("Foo-lubov-Bar")));
        assertThat(sut.get("Foo & Bar"), is(equalTo("Foo-i-Bar")));
        assertThat(sut.get("Foo | Bar"), is(equalTo("Foo-ili-Bar")));
        assertThat(sut.get("Foo < Bar"), is(equalTo("Foo-menshe-Bar")));
        assertThat(sut.get("Foo > Bar"), is(equalTo("Foo-bolshe-Bar")));
        assertThat(sut.get("Foo ∑ Bar"), is(equalTo("Foo-summa-Bar")));
        assertThat(sut.get("Foo ¤ Bar"), is(equalTo("Foo-valjuta-Bar")));
    }

    @Test
    @Ignore
    public void shouldReplaceSymbolsForCzech() {

    }

    @Test
    @Ignore
    public void shouldReplaceSymbolsForCzechMaintainCase() {

    }

    @Test
    @Ignore
    public void shouldReplaceSymbolsForSwedish() {

    }

    @Test
    @Ignore
    public void shouldReplaceSymbolsForSwedishMaintainCase() {

    }

    @Test
    @Ignore
    public void shouldReplaceSymbolsForVietnamese() {

    }

    @Test
    @Ignore
    public void shouldReplaceSymbolsForVietnameseMaintainCase() {

    }

}
