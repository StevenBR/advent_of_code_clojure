(ns day4-tests
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :refer [split-lines]]
            [day4.day4 :refer :all]))

(def input (-> "test/day4_input.txt" slurp split-lines))

(def test-input-1 "aaaaa-bbb-z-y-x-123[abxyz]")
(def test-input-2 "totally-real-room-200[decoy]")

(def correct-map {:name "aaaaabbbzyx" :hash "abxyz" :id 123 :checksum "abxyz"})
(def wrong-map {:name "totallyrealroom" :hash "loart" :id 200 :checksum "decoy"})

(println "\"Tests\"")

(deftest day4-tests
  (testing "sector-id-total"
    (is (= 278221 (sector-id-total input))))
  (testing "parse-line"
    (is (= correct-map (day4.day4/parse-line test-input-1))))
  (testing "parse-line"
    (is (= correct-map (day4.day4/parse-line test-input-1))))
  (testing "parse-line"
    (is (= wrong-map (day4.day4/parse-line test-input-2))))
  (testing "check if valid room"
    (is (valid-room? correct-map)))
  (testing "check if valid room"
    (is (not (valid-room? wrong-map))))
  ()
    )