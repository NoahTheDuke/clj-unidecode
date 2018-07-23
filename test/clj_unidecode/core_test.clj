(ns clj-unidecode.core-test
  (:require [clj-unidecode.core :refer :all]
            [clojure.test :refer :all]))

(deftest test-specific
  (doseq [[t e]
          [["" ""]
           ["Hello, World!" "Hello, World!"]
           ["'\"\r\n" "'\"\r\n"]
           ["Answer is 42\n" "Answer is 42\n"]
           ["ČŽŠčžš" "CZSczs"]
           ["ア" "a"]
           ["α" "a"]
           ["а" "a"]
           ["ch\u00e2teau" "chateau"]
           ["vi\u00f1edos" "vinedos"]
           ["\u5317\u4EB0" "Bei Jing "]
           ["Efﬁcient" "Efficient"]
           ;; https://github.com/iki/unidecode/commit/4a1d4e0a7b5a11796dc701099556876e7a520065
           ["příliš žluťoučký kůň pěl ďábelské ódy"
            "prilis zlutoucky kun pel dabelske ody"]
           ["PŘÍLIŠ ŽLUŤOUČKÝ KŮŇ PĚL ĎÁBELSKÉ ÓDY"
            "PRILIS ZLUTOUCKY KUN PEL DABELSKE ODY"]
           ["\u00c6neid" "AEneid"]
           ["\u00e9tude" "etude"]
           ["\u1515\u14c7\u14c7" "shanana"]
           ["\u13d4\u13b5\u13c6" "taliqua"]
           ["\u0726\u071b\u073d\u0710\u073a" "ptu'i"]
           ["\u0905\u092d\u093f\u091c\u0940\u0924" "abhijiit"]
           ["\u0985\u09ad\u09bf\u099c\u09c0\u09a4" "abhijiit"]
           ["\u0d05\u0d2d\u0d3f\u0d1c\u0d40\u0d24" "abhijiit"]
           ["\u0d2e\u0d32\u0d2f\u0d3e\u0d32\u0d2e\u0d4d" "mlyaalm"]
           ["\u3052\u3093\u307e\u3044\u8336" "genmaiCha "]]]
    (is (= e (unidecode t)))
    (is (instance? String (unidecode t)))))

(deftest test-lower
  (doseq [e
          [;; Fullwidth
           "\uff54\uff48\uff45 \uff51\uff55\uff49\uff43\uff4b \uff42\uff52\uff4f\uff57\uff4e \uff46\uff4f\uff58 \uff4a\uff55\uff4d\uff50\uff53 \uff4f\uff56\uff45\uff52 \uff54\uff48\uff45 \uff4c\uff41\uff5a\uff59 \uff44\uff4f\uff47 \uff11\uff12\uff13\uff14\uff15\uff16\uff17\uff18\uff19\uff10"]]
    (let [sentence "the quick brown fox jumps over the lazy dog 1234567890"]
      (is (= sentence (unidecode e))))))

(deftest test-upper
  (doseq [e
          [;; Fullwidth
           "\uff34\uff28\uff25 \uff31\uff35\uff29\uff23\uff2b \uff22\uff32\uff2f\uff37\uff2e \uff26\uff2f\uff38 \uff2a\uff35\uff2d\uff30\uff33 \uff2f\uff36\uff25\uff32 \uff34\uff28\uff25 \uff2c\uff21\uff3a\uff39 \uff24\uff2f\uff27 \uff11\uff12\uff13\uff14\uff15\uff16\uff17\uff18\uff19\uff10"]]
    (let [sentence "THE QUICK BROWN FOX JUMPS OVER THE LAZY DOG 1234567890"]
      (is (= sentence (unidecode e))))))
