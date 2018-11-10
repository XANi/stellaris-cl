(ns stellaris-cl.core
  (:require [instaparse.core :as insta])
  (:use
    [clojure.pprint]
    [stellaris-cl.parse :as parse]
    [clojure.string :as string])
  )


(def example-tiny (slurp(clojure.java.io/resource "t/example-tiny")))
(def example-1  (slurp(clojure.java.io/resource "t/00_buildings.txt")))
;(def example-1  (slurp(clojure.java.io/resource "t/example2")))
(def example-1  (slurp(clojure.java.io/resource "t/00_event_buildings.txt")))
;(def example-1  (slurp(clojure.java.io/resource "t/00_diplo_phrases.txt")))
;(defn strip-comment [x] (apply str (take-while #(not (#{\# \;} %)) x)))


(print "\n-----\n")
;(pprint (stellar-parse example-file :total true))
;(def data (string/join "\n" (map strip-comment (clojure.string/split-lines example-1))))
(def data example-1)
;(print (stellar-parse data :unhide :all :total true))
;(print (stellar-parse data :unhide :all ))
(pprint (parse/stellar-parse-debug data))
(print "\n-----\n")
;(pprint (stellar-parse data ))
(print "\n-----\n")
(defn run-bench [times]
    (dotimes [n times]
      (print "Run " n " ")
      (time (parse/stellar-parse data))
      (print "\n")
      ))
(run-bench 5)
