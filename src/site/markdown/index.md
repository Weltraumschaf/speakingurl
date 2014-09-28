# Speaking URL

Generate a slug with a lot of  options; create of so called 'static' or 'Clean
URL' or 'Pretty  URL' or 'nice-looking URL' or 'Speaking  URL' or 'user-friendly
URL' or 'SEO-friendly URL' from a  string. This module aims to transliterate the
input string.

For use in Maven projects add this dependnecy:

    <dependnecy>
      <groupId>de.weltraumschaf</groupId>
      <artifactId>speakingurl</artifactId>
      <version>1.0.0-SNAPSHOT</version>
    </dependnecy>

## Version

First I considered to use the  same version numbers like the original JavaScript
version of  [Speaking URL][original]. But  it does  not fit completely  into the
concept  of [semantic  versioning][versioning]. Semantic  versioning suggest  to
start with version  1.0.0 for a stable  non development version. Also  it is not
obvious if  in the  minor and  patch levels of  the [original][origin]  were API
changes done which  violates the rules of  [semantic versioning][versioning]. So
I decided to start  with 1.0.0 which maps to the features  of version v0.12.0 of
the [original][original]. The [changelog][changelog]  will document the "maping"
to Pid's versions.

## Credits

- [@pid](https://github.com/pid/speakingurl)
- [@dypsilon](https://github.com/dypsilon)
- [@simov](https://github.com/simov/slugify)
- [@henrikjoreteg](https://github.com/henrikjoreteg/slugger)

[original]:   https://github.com/pid/speakingurl
[license]:    https://raw.github.com/Weltraumschaf/speakingurl/master/LICENSE
[versioning]: http://semver.org/
[changelog]:  https://github.com/Weltraumschaf/speakingurl/blob/master/CHANGELOG.md
