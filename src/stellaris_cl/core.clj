(ns stellaris-cl.core
  (:require [instaparse.core :as insta])
  (:use
    [clojure.pprint]
    [clojure.string :as string])
  )





;(def grammar (clojure.java.io/resource "grammar/stellaris-commentless.bnf"))
(def grammar (clojure.java.io/resource "grammar/stellaris.bnf"))
(def jsgrammar (clojure.java.io/resource "grammar/json.bnf"))

(def example-tiny (slurp(clojure.java.io/resource "t/example-tiny")))
;(def example-1  (slurp(clojure.java.io/resource "t/00_buildings.txt")))
(def example-1  (slurp(clojure.java.io/resource "t/example2")))
;(def example-1  (slurp(clojure.java.io/resource "t/00_event_buildings.txt")))
(defn strip-comment [x] (apply str (take-while #(not (#{\# \;} %)) x)))
(def stellar-parse
   (insta/parser grammar
     :output-format :enlive))

(print "\n-----\n")
;(pprint (stellar-parse example-file :total true))
(def data (string/join "\n" (map strip-comment (clojure.string/split-lines example-1))))
(print (stellar-parse data :unhide :all))
(time(pprint(stellar-parse data)))
