(ns stellaris-cl.core
  (:require [instaparse.core :as insta])
  (:use
    [clojure.pprint]
    [stellaris-cl.parse :as parse]
    [net.cgrand.enlive-html :as html]
    [clojure.string :as string])
  )


(def example-tiny (slurp(clojure.java.io/resource "t/example-tiny")))
(def example-1  (slurp(clojure.java.io/resource "t/00_buildings.txt")))
;(def example-1  (slurp(clojure.java.io/resource "t/example2")))
(def example-1  (slurp(clojure.java.io/resource "t/00_event_buildings.txt")))
;(def example-1  (slurp(clojure.java.io/resource "t/00_diplo_phrases.txt")))
;(defn strip-comment [x] (apply str (take-while #(not (#{\# \;} %)) x)))
(def data example-1)

(print "\n-----\n")
(defn run-bench [times]
    (dotimes [n times]
      (print "Run " n " ")
      (time (parse/stellar-parse data))
      (print "\n")
      ))
;(run-bench 5)
(print "\n-----\n")
;(pprint (stellar-parse example-file :total true))
;(def data (string/join "\n" (map strip-comment (clojure.string/split-lines example-1))))
;(print (stellar-parse data :unhide :all :total true))
;(print (stellar-parse data :unhide :all ))
(def d (parse/stellar-parse data))
;(pprint d)
(defn travel [x depth]
  (print (join ""(repeat depth "─")) "┬"  (first x) "\n")
   (for [v (rest x)]
       (cond
         (vector? v) (doall
                       (travel v (+ depth 1))
                    )
      ;   (keyword? v) (print "--" v ": ")
      ;   (string? v) (print v "\n")
         :else (print (join "" (repeat (- depth 0) " ")) "└" (type v) ": " v "\n")
         )
       ;(travel v (+ depth 1))
       )
     )

(doall (travel d 0))
