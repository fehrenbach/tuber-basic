(ns tuber-basic.core
  (:require [clojure.java.io]
            [instaparse.core :as insta])
  (:import [com.oracle.truffle.api Truffle]))

(println (.getName (Truffle/getRuntime)))

(def parser (insta/parser (clojure.java.io/resource "tuber-basic.bnf")))

(parser "PRINT  \"57\"
PRINT 5
PRINT abc
")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
