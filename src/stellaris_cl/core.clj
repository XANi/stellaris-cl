(ns stellaris-cl.core
  (:require [instaparse.core :as insta])
  (:use
    [clojure.pprint]
    [clojure.string :as string])
  )
(def example-tiny "
############################
")

(def example-file "
############################
#
# Pretender Events
#
# Written by Henrik Eklund
#
############################

namespace = pretender

### Ruler Dies Without Heir
country_event = {
        id = pretender.1
        title = \"pretender.1.name\"
        picture = GFX_evt_throne_room
        show_sound = event_conversation

        is_triggered_only = yes
        hide_window = yes

        trigger = {
                from = {
                        is_same_value = root.leader
                }
                NOT = { exists = heir }
                has_authority = auth_imperial
                #OR = {
                #       has_government = despotic_empire
                #       has_government = star_empire
                #       has_government = divine_mandate
                #       has_government = transcendent_empire
                #       has_government = despotic_hegemony
                #       has_government = ai_overlordship
                #       has_government = enlightened_monarchy
                #       has_government = irenic_monarchy
                #       has_government = stagnated_ascendancy
                #       has_government = awakened_ascendancy
                #       has_government = military_order
                #       has_government = machine_consciousness
                #       has_government = curator_government
                #       has_government = trader_government
                #       has_government = artist_government
                #}
        }

        immediate = {
                # Install a new leader
                create_leader = {
                        type = ruler
                        species = owner_main_species
                        name = random
                        traits = {}
                }
                assign_leader = last_created_leader
        }
        #option = {
        #       name = \"UNFORTUNATE\"
        #       custom_tooltip = pretender.1.tooltip
        #}
}
")
(def example-file-commentless "

namespace = pretender

country_event = {
        id = pretender.1
        title = \"pretender.1.name\"
        picture = GFX_evt_throne_room
        show_sound = event_conversation

        is_triggered_only = yes
        hide_window = yes

        trigger = {
                from = {
                        is_same_value = root.leader
                }
                NOT = { exists = heir }
                has_authority = auth_imperial
        }

        immediate = {
                create_leader = {
                        type = ruler
                        species = owner_main_species
                        name = random
                        traits = {}
                }
                assign_leader = last_created_leader
        }
}
")


;(def grammar (clojure.java.io/resource "stellaris-commentless.bnf"))
(def grammar (clojure.java.io/resource "stellaris.bnf"))
(def jsgrammar (clojure.java.io/resource "json.bnf"))

(def example-tiny "
# test
# other test
# a
####test
#### test
#
 testkey = testvalue
testkey2 = \"testvalue2\"
testhash = { testhashk1 = testhashk2 }
testhash2 = {
   testhashk2 = testhashv2
}
testhash3 = {
   testhashk31 = testhashv31
    testhashk32 = testhashv32
}
testhash4 = {
   testhashk41 = {
      testhashk42 = \"testhashv42\"
   }
}
")
(defn strip-comment [x] (apply str (take-while #(not (#{\# \;} %)) x)))
(def stellar-parse
   (insta/parser grammar
     :output-format :enlive))

(def json-parse (insta/parser jsgrammar :output-format :enlive))
;(print (json-parse "{\"asd\":123,\"dsa\":\"xcz\"}"))
(print "\n-----\n")
(pprint (stellar-parse example-tiny :total true))
(print (stellar-parse example-tiny))
(print "\n")
(time (stellar-parse example-tiny))
(print "\n-----\n")
;(pprint (stellar-parse example-file :total true))
;(def commentless (apply str (take-while #(not (#{\# \;} %)) example-file))
;(time(pprint (stellar-parse commentless)))
(def data (string/join "\n" (map strip-comment (clojure.string/split-lines example-file))))
(time(pprint (stellar-parse data :unhide :all)))
(time(stellar-parse data))
