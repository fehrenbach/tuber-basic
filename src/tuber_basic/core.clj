(ns tuber-basic.core
  (:require [clojure.java.io]
            [clojure.edn]
            [instaparse.core :as insta])
  (:import [com.oracle.truffle.api Truffle]
           [com.oracle.truffle.api.nodes Node]))

(println (.getName (Truffle/getRuntime)))

(def parser (insta/parser (clojure.java.io/resource "tuber-basic.bnf")))

(parser "decl:
LET a = 5
PRINT  \"57\"
PRINT 5
use-site:
PRINT a
END
")




(def transformation
  {:numberliteral
   (fn [s]
     (let [n (clojure.edn/read-string s)]
       (proxy [Node] []
         (execute)
         )))})

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
