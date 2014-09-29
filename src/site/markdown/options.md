# Options

The  [slugger][Slug] itself  has no  options.  The reason  for that  is to  only
provide immutable objects  as public API. This will  prevent multithread related
problems.

The  only "runtime"  option you  can  pass to  the [slugger][Slug]  is the  used
separator:

    final Slug slugger = ...;
    // Default separator:
    final String url1 = slugger.get("...");
    // Custom separator:
    final String url2 = slugger.get("...", "*");

Keep  in mind  that the  latter call  will overwrite  the separator  set to  the
builder options.

## How to Configure a Slug

The basic  principle how to configure  and create a [slugger][Slug]  is based on
the  [builder pattern][builder-pattern].  That means  that you  can't create  an
instance of [Slug][Slug] by  yourself. You must use [Slug.Builder][Slug.Builder]
for that.

Create a [slugger][slug] with default options:

    final Slug slugger = Slug.Builder.newBuiler().create();

Create a [slugger][slug] with some options:

    final Slug slugger = Slug.Builder.newBuiler()
                                     .separator("_")
                                     .maintainCase(true)
                                     .uricNoSlash(true)
                                     .create();

Keep in  mind that each call  to `Builder#create()` will create  a new immutable
instance.

If you  change the  options of the  builder, any yet  created sluggers  will not
chnage their behaviour:

    final Slug.Builder creator = Slug.Builder.newBuiler();
    final Slug slugger1 = creator.separator("_").create();
    // Will not affect slugger1!
    final Slug slugger2 = creator.separator("*").create();

### Builder Options

All methods to set an option returns the builder itself for method chaining.

Note:  default only  Base64  characters are  allowed (`/A-Za-z0-9_-/`),  setting
`uric`, `uricNoSlash`  or/and `mark` to `true`  will add the specified  chars to
the allowed characters. The separator character is always allowed.

- `Builder#custom(Map<String,String> custom)` default: empty
    - * Custom map for translation, overwrites all.
- `Builder#lang(Language lang)` default: `Language.ENGLISH`
    - Language for symbol translation (see [Javadoc][Language] for list).
- `Builder#maintainCase(boolean maintainCase)` default: `false`
    - `true`: Maintain case characters.
    - `false`: Convert all characterss to lower case.
- `Builder#mark(boolean mark)` default: `false`
    - `true`: Additionally allow characters: `"-", "_", ".", "!", "~", "*", "'", "(", ")"`.
    - `false`: Only Base64 characters allowed (`/A-Za-z0-9-_/`).
- `Builder#separator(String separator)` default: `"-"`
    - Character that replace the whitespaces.
- `Builder#titleCase(boolean titleCase)` default: `false`
    - `true`: Upper case title words.
    - `false`: Nothing upper cased.
- `Builder#titleCaseExclude(String... titleCaseExclude)` default: empty
    - Converts vararg to set and calls `#titleCaseExclude(Set<String> titleCaseExclude)`.
- `Builder#titleCaseExclude(Set<String> titleCaseExclude)` default: empty
    - Set of words to not uppercase if `titleCase` is `true`.
- `Builder#truncate(int truncate)` default: `0`
    - 0 -> Don't trim length.
    - > 0 -> Trim to max length while not breaking any words.
- `Builder#uric(boolean uric)` default: `false`
    - `true`: Additionally allow characters: `";", "?", ":", "@", "&", "=", "+", "$", ",", "/"`.
    - `false`: Only Base64 characters allowed (`/A-Za-z0-9-_`/).
- `Builder#uricNoSlash(boolean uricNoSlash)` default: `false`
    - `true`: Aadditionally allow characters: `";", "?", ":", "@", "&", "=", "+", "$", ","`.
    - `false`: Only Base64 characters allowed (`/A-Za-z0-9-_/`).

[Slug]:             apidocs/de/weltraumschaf/speakingurl/Slug.html
[Slug.Builder]:     apidocs/de/weltraumschaf/speakingurl/Slug.Builder.html
[Language]:         apidocs/de/weltraumschaf/speakingurl/Language.html
[builder-pattern]:  http://en.wikipedia.org/wiki/Builder_pattern