# Examples

Here some examples how to use a slugger.

A slugger is threadsafe.  That's the reason why you always  build a new slugger,
if you want  to change the options  it uses. For more detailed  examples see the
unit tests in `src/main/tests`.

## Defaults

Generate a default slug:

    final Slug slugger = Slug.Builder.newBuiler().create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !");
    // Output: schoener-titel-laesst-gruessen-bel-ete
    System.out.println(slug);

## Custom Separator

Asterisk as separator:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .separator("*")
                                     .create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !");
    // Output: schoener*titel*laesst*gruessen*bel*ete
    System.out.println(slug);

Underscore  as separator:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .separator("_")
                                     .create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !");
    // Output: schoener_titel_laesst_gruessen_bel_ete
    System.out.println(slug);

## URI Characters

Allow URI characters with slash:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .uric(true)
                                     .create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !");
    // Output: schoener-titel-laesst-gruessen?-bel-ete
    System.out.println(slug);

Allow URI characters without slash:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .uricNoSlash(true)
                                     .create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !");
    // Output: schoener-titel-laesst-gruessen?-bel-ete
    System.out.println(slug);

Allow mark characters:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .mark(true)
                                     .create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !");
    // Output: schoener-titel-laesst-gruessen!-bel-ete-!
    System.out.println(slug);

    final Slug slugger = Slug.Builder.newBuiler().create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !", {
        truncate: 20
    });
    System.out.println(slug); // Output: schoener-titel

## Maintain Case

    final Slug slugger = Slug.Builder.newBuiler()
                                     .maintainCase(true)
                                     .create();
    final String slug = slugger.get("Schöner Titel läßt grüßen!? Bel été !");
    System.out.println(slug); // Output: Schoener-Titel-laesst-gruessen-Bel-ete

## Replace Symbols

German symbols:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .lang(Language.GERMAN)
                                     .create();
    final String slug = slugger.get("Äpfel ♥ Birnen!");
    System.out.println(slug); // Output: aepfel-liebe-birnen

English symbols (is default, just to clarify):

    final Slug slugger = Slug.Builder.newBuiler()
                                     .lang(Language.ENGLISH)
                                     .create();
    final String slug = slugger.get("Apple ♥ Pear!");
    System.out.println(slug); // Output: apple-love-pear

Do not replace any symbols by language:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .lang(Language.NONE)
                                     .create();
    final String slug = slugger.get("Apple ♥ Pear!");
    System.out.println(slug); // Output: apple-pear

Custom symbol replacement:

    final Map<String, String> custom = new HashMap<>();
    custom.put("♥", " doo ");
    final Slug slugger = Slug.Builder.newBuiler()
                                     .custom(custom)
                                     .create();
    final String slug = slugger.get("Foo ♥ Bar * Baz");
    // Output: foo-doo-bar-baz
    System.out.println(slug);

Allow mark characters:

    final Map<String, String> custom = new HashMap<>();
    custom.put("*", "Boo");
    final Slug slugger = Slug.Builder.newBuiler()
                                     .mark(true)
                                     .custom(custom)
                                     .create();
    final String slug = slugger.get("Foo & Bar | (Baz) * Doo");
    // Output: foo-and-bar-or-(baz)-boo-doo
    System.out.println(slug);

Multiple words:

    final Map<String, String> custom = new HashMap<>();
    custom.put("and", "und");
    custom.put("or", "");
    final Slug slugger = Slug.Builder.newBuiler()
                                     .custom(custom)
                                     .create();
    final String slug = slugger.get("Foo and Bar or Baz";
    // Output: foo-und-bar-baz
    System.out.println(slug);

Replace currency symbols:

    final Slug slugger = Slug.Builder.newBuiler().create();
    final String slug = slugger.get("NEXUS4 only $299");
    // Output: nexus-4-only-usd299
    System.out.println(slug);

    final Slug slugger = Slug.Builder.newBuiler()
                                     .maintin(case)
                                     .create();
    final String slug = slugger.get("NEXUS4 only €299";
    // Output: NEXUS-4-only-EUR299
    System.out.println(slug);

## Titles and Case

Uppercase title words:

    final Slug slugger = Slug.Builder.newBuiler()
                             .titleCase(true)
                             .create();
    final String slug = slugger.get("Don't drink and drive");
    // Output: Don-t-Drink-And-Drive
    System.out.println(slug);

Exclude words:

    final Set<String> titleCaseExclude = new HashSet<>();
    titleCaseExclude.add("and");
    final Slug slugger = Slug.Builder.newBuiler()
                                     .titleCaseExclude(titleCaseExclude)
                                     .create();
    final String slug = slugger.get("Don\"t drink and drive");
    // Output: Don-t-Drink-and-Drive
    System.out.println(slug);
