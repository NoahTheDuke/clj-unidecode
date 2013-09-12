(defproject clj-unidecode "1.0.1"
  :description "Clojure implementation of Junidecode"
  :url "http://github.com/mgrubb/clj-unidecode"
  :license {:name "BSD"
            :url "http://www.debian.org/misc/bsd.license"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :profiles {:dev {:dependencies [[midje "1.6-SNAPSHOT"]]}}
  :min-lein-version "2.0.0"
  :pom-addition [:developers [:developer
                              [:name "Michael Grubb"]
                              [:id "mgrubb"]
                              [:url "http://github.com/mgrubb"]]])
