# clj-unidecode

A Clojure implementation of the Junidecode library.
All credit with the exception of bugs goes to Giuseppe Cardone, the author
of Junidecode, and by extension Sean M. Burke, author of Text::Unidecode
perl module.  All bugs, errors, or tragic programming practices are my
sole responsibility.  Speaking of which should you find any of them please
submit an issue on github, pull requests are also welcome.

## Usage

```clojure
(ns my-super-awesome-project
  (:require [clj-unidecode.core :refer (unidecode)]))

(defn is-bei-jing? []
  (= (unidecode "\u5317\u4eb0") "Bei Jing "))
```
See doc/intro.md for resetting the cache.

## More information

For a more in-depth discussion of the problem see Giuseppe Cardone's Junidecode site
http://www.ippatsuman.com/projects/junidecode/index.html

Also this is my first attempt at releasing any clojure code so any _constructive_
criticism is appreciated.

## License

Copyright © 2012 Michael Grubb.
All Rights Reserved.

Released under the BSD license see LICENSE file for exact details.
