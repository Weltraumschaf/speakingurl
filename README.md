# Speaking URL

Generate a slug with a lot of  options; create of so called 'static' or 'Clean
URL' or 'Pretty  URL' or 'nice-looking URL' or 'Speaking  URL' or 'user-friendly
URL' or 'SEO-friendly URL' from a  string. This module aims to transliterate the
input string.

This is the Java port of [Speaking URL][original].

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

## [License][license]

> The BSD 3-Clause License (BSD3)
>
> Copyright (c) 2013-2014 Sascha Droste <pid@posteo.net>, Sven Strittmatter <weltraumschaf@googlemail.com>
> All rights reserved.
>
> Redistribution  and   use  in   source  and  binary   forms,  with   or  without
> modification, are permitted provided that the following conditions are met:
>
> * Redistributions of source code must retain the above copyright notice, this
>   list of conditions and the following disclaimer.
> * Redistributions in binary form must reproduce the above copyright notice, this
>   list of conditions and the following disclaimer in the documentation and/or
>   other materials provided with the distribution.
> * Neither the name of the author nor the names of its contributors may be used
>   to endorse or promote products derived from this software without specific
>   prior written permission.
>
> THIS SOFTWARE IS PROVIDED BY THE  COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
> ANY EXPRESS  OR IMPLIED WARRANTIES, INCLUDING,  BUT NOT LIMITED TO,  THE IMPLIED
> WARRANTIES  OF  MERCHANTABILITY  AND  FITNESS   FOR  A  PARTICULAR  PURPOSE  ARE
> DISCLAIMED. IN  NO EVENT SHALL  THE COPYRIGHT  HOLDER OR CONTRIBUTORS  BE LIABLE
> FOR  ANY  DIRECT, INDIRECT,  INCIDENTAL,  SPECIAL,  EXEMPLARY, OR  CONSEQUENTIAL
> DAMAGES  (INCLUDING, BUT  NOT LIMITED  TO,  PROCUREMENT OF  SUBSTITUTE GOODS  OR
> SERVICES;  LOSS OF  USE, DATA,  OR  PROFITS; OR  BUSINESS INTERRUPTION)  HOWEVER
> CAUSED AND  ON ANY THEORY OF  LIABILITY, WHETHER IN CONTRACT,  STRICT LIABILITY,
> OR TORT (INCLUDING  NEGLIGENCE OR OTHERWISE) ARISING  IN ANY WAY OUT  OF THE USE
> OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

[original]:   https://github.com/pid/speakingurl
[license]:    https://raw.github.com/Weltraumschaf/speakingurl/master/LICENSE
[versioning]: http://semver.org/
[changelog]:  https://github.com/Weltraumschaf/speakingurl/blob/master/CHANGELOG.md