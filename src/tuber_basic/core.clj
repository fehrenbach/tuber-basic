(ns tuber-basic.core
  (:import [com.oracle.truffle.api Truffle]))

(println (.getName (Truffle/getRuntime)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
