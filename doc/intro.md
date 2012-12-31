# Introduction to clj-unidecode

clj-unidecode is pretty simple, it only has two functions.

unidecode takes a string and returns a string that is 7-bit clean.

reset-unidecode-cache will reset the cache for unidecode to free up
memory.

```clojure
(ns my-super-awesome-project
  (:require [clj-unidecode.core :refer (unidecode)]))

(defn is-bei-jing? []
  (= (unidecode "\u5317\u4eb0") "Bei Jing "))

(reset-unidecode-cache)
```
