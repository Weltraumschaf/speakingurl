# Speaking URL

> Generate a slug with a lot of  options; create of so called 'static' or 'Clean
> URL' or 'Pretty  URL' or 'nice-looking URL' or 'Speaking  URL' or 'user-friendly
> URL' or 'SEO-friendly URL' from a  string. This module aims to transliterate the
> input string. (from the [original][original])

Read [examples][examples] to see how it works.

## Installation

For use in [Maven][mvn] projects add this dependnecy:

    <dependnecy>
      <groupId>de.weltraumschaf</groupId>
      <artifactId>speakingurl</artifactId>
      <version>1.0.0</version>
    </dependnecy>

For manual instalation clone this repository and run Maven. You need at least
[Maven][mvn] 3.0.3 or above installed:

    $> git clone https://github.com/Weltraumschaf/speakingurl.git
    $> cd speakingurl
    $> mvn clean install

Or you can download the jar-file directly from the [Maven Central Repository][mvn-repo].

## Dependencies

This module has no dependencies except some test libraries (scope test) or
some Maven plugins.

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

## License

For license information see [here][license].

[original]:     https://github.com/pid/speakingurl
[versioning]:   http://semver.org/
[mvn]:          http://maven.apache.org/
[changelog]:    https://github.com/Weltraumschaf/speakingurl/blob/master/CHANGELOG.md
[mvn-repo]:     http://search.maven.org/#search|gav|1|g:%22de.weltraumschaf%22%20AND%20a:%22speakingurl%22
[license]:      license.html
[examples]:     examples.html