;;;
;;; Copyright (c) 2012 Michael Grubb
;;; All rights reserved.
;;; 
;;; Redistribution and use in source and binary forms, with or without
;;; modification, are permitted provided that the following conditions
;;; are met:
;;; 1. Redistributions of source code must retain the above copyright
;;;    notice, this list of conditions and the following disclaimer.
;;; 2. Redistributions in binary form must reproduce the above copyright
;;;    notice, this list of conditions and the following disclaimer in the
;;;    documentation and/or other materials provided with the distribution.
;;; 3. The name of the author may not be used to endorse or promote products
;;;    derived from this software without specific prior written permission.
;;; 
;;; THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
;;; IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
;;; OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
;;; IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
;;; INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
;;; NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
;;; DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
;;; THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
;;; (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
;;; THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
;;;

(ns clj-unidecode.core-test
  (:use midje.sweet
        clj-unidecode.core))

(defn check-7bit [c]
  (not (= (bit-and (.hashCode c) 0x80) 0x00)))

(defn- make-pair [x] [x x])

(def test-pairs-ex [["\u00c6neid" "AEneid"]
                    ["\u00e9tude" "etude"]
                    ;; Chinese
                    ["\u5317\u4eb0" "Bei Jing "]
                    ;; Canadian syllabics
                    ["\u1515\u14c7\u14c7" "shanana"]
                    ;; Cherokee
                    ["\u13d4\u13b5\u13c6" "taliqua"]
                    ;; Syriac
                    ["\u0726\u071b\u073d\u0710\u073a" "ptu'i"]
                    ;; Devangari
                    ["\u0905\u092d\u093f\u091c\u0940\u0924" "abhijiit"]
                    ;; Bengali
                    ["\u0985\u09ad\u09bf\u099c\u09c0\u09a4" "abhijiit"]
                    ;; Malayalaam
                    ["\u0d05\u0d2d\u0d3f\u0d1c\u0d40\u0d24" "abhijiit"]
                    ;; Malayalaam. Correct transliteration is "malayaalam"
                    ;; but unidecode is not context sensitive so this is as
                    ;; good as it gets.
                    ["\u0d2e\u0d32\u0d2f\u0d3e\u0d32\u0d2e\u0d4d" "mlyaalm"]
                    ;; Japanese
                    ["\u3052\u3093\u307e\u3044\u8336" "genmaiCha "]]) 

(def test-pairs [(make-pair "")
                 (make-pair "Answer is 42")
                 (make-pair "\n")
                 (make-pair "Answer is 42\n")])

(facts "ASCII Strings are unchanged"
  (doall
    (for [[t e] test-pairs]
      (unidecode t) => e)))

(facts "Non-ASCII Charsets are changed to ASCII"
  (doall
    (for [[t e] test-pairs-ex]
      (unidecode t) => e)))

(facts "Reset cache results in empty cache"
  (reset-unidecode-cache)
  @*unidecode-cache* => {}
  (unidecode "a test") => "a test"
  (count @*unidecode-cache*) => 1
  (reset-unidecode-cache)
  (count @*unidecode-cache*) => 0
  @*unidecode-cache* => {})
