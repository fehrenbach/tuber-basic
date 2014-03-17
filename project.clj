(defproject tuber-basic "0.1.0-SNAPSHOT"
  :description "A BASIC on Truffle"
  :url "https://github.com/fehrenbach/tuber-basic"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.oracle/truffle "0.1"]
                 [instaparse "1.3.0"]]
  :repositories {"truffle" "http://lafo.ssw.uni-linz.ac.at/nexus/content/repositories/releases/"}
  :main ^:skip-aot tuber-basic.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
