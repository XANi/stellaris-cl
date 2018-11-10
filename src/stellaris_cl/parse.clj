(ns stellaris-cl.parse
  (:require [instaparse.core :as insta])
  (:use
    [clojure.pprint]
    [clojure.string :as string])
  )
;(def grammar (clojure.java.io/resource "grammar/stellaris-commentless.bnf"))
(def grammar (clojure.java.io/resource "grammar/stellaris.bnf"))
(defn strip-comment [x]
  (string/join "\n"
               (map #(clojure.string/replace % #"\#.*$" "")
                           (clojure.string/split-lines x))))
(def stellaris-parser (insta/parser grammar :output-format :hiccup))
(def stellaris-parser-debug (insta/parser grammar :output-format :hiccup :unhide :all :total true))

(defn stellar-parse [data]
  (stellaris-parser (strip-comment data)))

(defn stellar-parse-debug [data]
  (stellaris-parser-debug (strip-comment data)))

