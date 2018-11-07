(ns stellaris-cl.core
  (:require [instaparse.core :as insta])
  (:use [clojure.pprint])
  )
(def example-tiny "
############################
")

(def example-file "
############################s
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
(def grammar (clojure.java.io/resource "stellaris.bnf"))
(def jsgrammar (clojure.java.io/resource "json.bnf"))

(def example-tiny "
# test
# other test
#
")
(def as-and-bs-enlive
  (insta/parser
    "S = AB*
     AB = A B
     A = 'a'+
     B = 'b'+"
    :output-format :enlive))

(def stellar-parse
   (insta/parser grammar
     :output-format :enlive))

(def json-parse (insta/parser jsgrammar :output-format :enlive))
;(print (json-parse "{\"asd\":123,\"dsa\":\"xcz\"}"))
(print "\n-----\n")
(pprint (stellar-parse example-tiny))

(print "\n-----\n")
(print (stellar-parse example-file))
