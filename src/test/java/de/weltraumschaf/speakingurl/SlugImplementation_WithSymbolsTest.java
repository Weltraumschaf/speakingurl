package de.weltraumschaf.speakingurl;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Tests for {@link SlugImplementation}.
 *
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class SlugImplementation_WithSymbolsTest {

    @Test
    public void shouldConvertSymbols() {
        Slug sut = Slug.Builder.newBuiler().create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-and-bar-or-baz")));

        sut = Slug.Builder.newBuiler().lang(Language.ENGLISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-and-bar-or-baz")));

        sut = Slug.Builder.newBuiler().lang(Language.GERMAN).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-und-bar-oder-baz")));

        sut = Slug.Builder.newBuiler().lang(Language.FRENCH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-et-bar-ou-baz")));

        sut = Slug.Builder.newBuiler().lang(Language.SPANISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-y-bar-u-baz")));

        sut = Slug.Builder.newBuiler().lang(Language.RUSSIAN).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-i-bar-ili-baz")));

        sut = Slug.Builder.newBuiler().lang(Language.CZECH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-a-bar-nebo-baz")));

        sut = Slug.Builder.newBuiler().lang(Language.SWEDISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-a-bar-alebo-baz")));
    }

    @Test
    public void shouldNotConvertSymbolsWithUricFlagTrue() {
        Slug sut = Slug.Builder.newBuiler().uric(true).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-or-baz")));

        sut = Slug.Builder.newBuiler().uric(true).lang(Language.ENGLISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-or-baz")));

        sut = Slug.Builder.newBuiler().uric(true).lang(Language.GERMAN).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-oder-baz")));

        sut = Slug.Builder.newBuiler().uric(true).lang(Language.FRENCH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-ou-baz")));

        sut = Slug.Builder.newBuiler().uric(true).lang(Language.SPANISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-u-baz")));

        sut = Slug.Builder.newBuiler().uric(true).lang(Language.RUSSIAN).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-ili-baz")));

        sut = Slug.Builder.newBuiler().uric(true).lang(Language.CZECH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-nebo-baz")));

        sut = Slug.Builder.newBuiler().uric(true).lang(Language.SWEDISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-alebo-baz")));
    }

    @Test
    public void shouldNotConvertSymbolsWithUricNoSlashFlagTrue() {
        Slug sut = Slug.Builder.newBuiler().uricNoSlash(true).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-or-baz")));

        sut = Slug.Builder.newBuiler().uricNoSlash(true).lang(Language.ENGLISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-or-baz")));

        sut = Slug.Builder.newBuiler().uricNoSlash(true).lang(Language.GERMAN).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-oder-baz")));

        sut = Slug.Builder.newBuiler().uricNoSlash(true).lang(Language.FRENCH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-ou-baz")));

        sut = Slug.Builder.newBuiler().uricNoSlash(true).lang(Language.SPANISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-u-baz")));

        sut = Slug.Builder.newBuiler().uricNoSlash(true).lang(Language.RUSSIAN).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-ili-baz")));

        sut = Slug.Builder.newBuiler().uricNoSlash(true).lang(Language.CZECH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-nebo-baz")));

        sut = Slug.Builder.newBuiler().uricNoSlash(true).lang(Language.SWEDISH).create();
        assertThat(sut.get("Foo & Bar | Baz"), is(equalTo("foo-&-bar-alebo-baz")));
    }

    @Test
    public void shouldNotConvertSymbolsWithMarkFlagTrue() {
        Slug sut = Slug.Builder.newBuiler().mark(true).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));

        sut = Slug.Builder.newBuiler().mark(true).lang(Language.ENGLISH).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));

        sut = Slug.Builder.newBuiler().mark(true).lang(Language.GERMAN).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));

        sut = Slug.Builder.newBuiler().mark(true).lang(Language.FRENCH).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));

        sut = Slug.Builder.newBuiler().mark(true).lang(Language.SPANISH).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));

        sut = Slug.Builder.newBuiler().mark(true).lang(Language.RUSSIAN).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));

        sut = Slug.Builder.newBuiler().mark(true).lang(Language.CZECH).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));

        sut = Slug.Builder.newBuiler().mark(true).lang(Language.SWEDISH).create();
        assertThat(sut.get("Foo (Bar) . Baz"), is(equalTo("foo-(bar)-.-baz")));
    }

    @Test
    public void shouldConvertSymbolsWithFlagsTrue() {
        Slug sut = Slug.Builder.newBuiler().uric(true).uricNoSlash(true).mark(true).lang(Language.ENGLISH).create();
        assertThat(sut.get("Foo (♥) ; Baz=Bar"), is(equalTo("foo-(love)-;-baz=bar")));

        sut = Slug.Builder.newBuiler().uric(true).uricNoSlash(true).mark(true).lang(Language.GERMAN).create();
        assertThat(sut.get("Foo (♥) ; Baz=Bar"), is(equalTo("foo-(liebe)-;-baz=bar")));

        sut = Slug.Builder.newBuiler().uric(true).uricNoSlash(true).mark(true).lang(Language.FRENCH).create();
        assertThat(sut.get("Foo (♥) ; Baz=Bar"), is(equalTo("foo-(amour)-;-baz=bar")));

        sut = Slug.Builder.newBuiler().uric(true).uricNoSlash(true).mark(true).lang(Language.SPANISH).create();
        assertThat(sut.get("Foo (♥) ; Baz=Bar"), is(equalTo("foo-(amor)-;-baz=bar")));

        sut = Slug.Builder.newBuiler().uric(true).uricNoSlash(true).mark(true).lang(Language.RUSSIAN).create();
        assertThat(sut.get("Foo (♥) ; Baz=Bar"), is(equalTo("foo-(lubov)-;-baz=bar")));

        sut = Slug.Builder.newBuiler().uric(true).uricNoSlash(true).mark(true).lang(Language.CZECH).create();
        assertThat(sut.get("Foo (♥) ; Baz=Bar"), is(equalTo("foo-(laska)-;-baz=bar")));

        sut = Slug.Builder.newBuiler().uric(true).uricNoSlash(true).mark(true).lang(Language.SWEDISH).create();
        assertThat(sut.get("Foo (♥) ; Baz=Bar"), is(equalTo("foo-(laska)-;-baz=bar")));

        sut = Slug.Builder.newBuiler().uric(true)
            .uricNoSlash(true)
            .mark(true)
            .maintainCase(true)
            .lang(Language.ENGLISH)
            .create();
        assertThat(sut.get(" Sch(* )ner (♥)Ti♥tel ♥läßt grüßen!? Bel♥♥ été !"),
            is(equalTo("Sch(*-)ner-(love)Ti-love-tel-love-laesst-gruessen!?-Bel-love-love-ete-!")));
    }

    @Test
    public void shouldReplaceSymbolsForDe() {
        Slug sut = Slug.Builder.newBuiler().lang(Language.GERMAN).create();
        assertThat(sut.get("Äpfel & Birnen"), is(equalTo("aepfel-und-birnen")));

        sut = Slug.Builder.newBuiler().lang(Language.GERMAN).maintainCase(true).create();
        assertThat(sut.get("ÄÖÜäöüß"), is(equalTo("AeOeUeaeoeuess")));
    }

    @Test
    public void shouldReplaceCharsByCzechLanguageStandards() {
        Slug sut = Slug.Builder.newBuiler().lang(Language.CZECH).create();
        assertThat(sut.get("AaÁáBbCcČčDdĎďEeÉéĚěFfGgHhChchIiÍíJjKkLlMmNnŇňOoÓóPpQqRrŘřSsŠšTtŤťUuÚúŮůVvWwXxYyÝýZzŽž"),
                is(equalTo("aaaabbccccddddeeeeeeffgghhchchiiiijjkkllmmnnnnooooppqqrrrrssssttttuuuuuuvvwwxxyyyyzzzz")));

        sut = Slug.Builder.newBuiler().lang(Language.CZECH).maintainCase(true).create();
        assertThat(sut.get("AaÁáBbCcČčDdĎďEeÉéĚěFfGgHhChchIiÍíJjKkLlMmNnŇňOoÓóPpQqRrŘřSsŠšTtŤťUuÚúŮůVvWwXxYyÝýZzŽž"),
                is(equalTo("AaAaBbCcCcDdDdEeEeEeFfGgHhChchIiIiJjKkLlMmNnNnOoOoPpQqRrRrSsSsTtTtUuUuUuVvWwXxYyYyZzZz")));
    }

    @Test
    public void shouldReplaceCharsBySwedishLanguageStandards() {
        Slug sut = Slug.Builder.newBuiler().lang(Language.SWEDISH).create();

        assertThat(sut.get("AaÁaÄäBbCcČčDdĎďDzdzDždžEeÉéFfGgHhChchIiÍíJjKkLlĹĺĽľMmNnŇňOoÓóÔôPpQqRrŔŕSsŠšTtŤťUuÚúVvWwXx"
                + "YyÝýZzŽž"),
                is(equalTo("aaaaaabbccccdddddzdzdzdzeeeeffgghhchchiiiijjkkllllllmmnnnnooooooppqqrrrrssssttttuuuuvvwwxx"
                        + "yyyyzzzz")));

        sut = Slug.Builder.newBuiler().lang(Language.SWEDISH).maintainCase(true).create();
        assertThat(sut.get("AaÁaÄäBbCcČčDdĎďDzdzDždžEeÉéFfGgHhChchIiÍíJjKkLlĹĺĽľMmNnŇňOoÓóÔôPpQqRrŔŕSsŠšTtŤťUuÚúVvWwXx"
                + "YyÝýZzŽž"),
                is(equalTo("AaAaAaBbCcCcDdDdDzdzDzdzEeEeFfGgHhChchIiIiJjKkLlLlLlMmNnNnOoOoOoPpQqRrRrSsSsTtTtUuUuVvWwXx"
                        + "YyYyZzZz")));
    }

    @Test
    public void shouldIgnoreNotAvailableLanguageParam() {
        final Slug sut = Slug.Builder.newBuiler().lang(Language.NONE).create();

        assertThat(sut.get("Äpfel & Birnen"), is(equalTo("aepfel-birnen")));
    }

    @Test
    public void shouldConvertCurrencySymbolsToLowercase() {
        final Slug sut = Slug.Builder.newBuiler().create();

        assertThat(sut.get("NEXUS4 only €199!"), is(equalTo("nexus4-only-eur199")));
        assertThat(sut.get("NEXUS4 only €299.93"), is(equalTo("nexus4-only-eur299-93")));
        assertThat(sut.get("NEXUS4 only 円399.73"), is(equalTo("nexus4-only-yen399-73")));
    }

    @Test
    public void shouldConvertCurrencySymbolsToUppercase() {
        final Slug sut = Slug.Builder.newBuiler().maintainCase(true).create();

        assertThat(sut.get("NEXUS4 only €199!"), is(equalTo("NEXUS4-only-EUR199")));
        assertThat(sut.get("NEXUS4 only €299.93"), is(equalTo("NEXUS4-only-EUR299-93")));
        assertThat(sut.get("NEXUS4 only 円399.73"), is(equalTo("NEXUS4-only-YEN399-73")));
    }
}
