(ns day1-tests
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :refer [split]]
            [day1.day1 :refer :all]))

(def test-wrong {:facing \N :x 10 :y 5 :visited [{:x 0 :y 0} {:x 1 :y 1} {:x 10 :y 3} {:x 5 :y 10} {:x 4 :y 6}]})
(def test-wrong-visited {:facing \N :x 10 :y 5 :visited [{:x 0 :y 0} {:x 1 :y 1} {:x 10 :y 3} {:x 5 :y 10} {:x 4 :y 6} {:x 10 :y 5}]})

(def test-match {:facing \N :x 10 :y 5 :visited [{:x 0 :y 0} {:x 1 :y 1} {:x 10 :y 3} {:x 10 :y 5} {:x 4 :y 6}]})
(def visited-y {:facing \N :x 0 :y 3 :visited [{:x 0 :y 0} {:x 0 :y 1} {:x 0 :y 2} {:x 0 :y 3}]})
(def visited-x {:facing \E :x 3 :y 0 :visited [{:x 0 :y 0} {:x 1 :y 0} {:x 2 :y 0} {:x 3 :y 0}]})
(def directions-test ["R5" "L2" "L2" "L3"])
(def test-directions-final {:facing \S :x 3 :y 0 :visited [{:x 0 :y 0} {:x 1 :y 0} {:x 2 :y 0} {:x 3 :y 0}{:x 4 :y 0}{:x 5 :y 0}{:x 5 :y 1}{:x 5 :y 2}{:x 4 :y 2}{:x 3 :y 2}]})

(def directions (-> "test/day1_input.txt" slurp (split  #",\s+")))

(deftest day1-final-results
  (testing "Day-1-A final result"
    (is (= 291 (day1 directions))))
  (testing "Day-1-B final result"
    (is (= 159 (day-1-b directions)))))

(deftest day1-b-functions
  (testing "update-visited y"
    (is (= visited-y (update-visited {:facing \N :x 0 :y 3 :visited [{:x 0 :y 0}]} 3))))
  (testing "update-visited x"
    (is (= visited-x (update-visited {:facing \E :x 3 :y 0 :visited [{:x 0 :y 0}]} 3))))
  (testing "distance"
    (is (= 15 (calc-distance test-match))))
  (testing "distace with directions"
    (is (= 0 (day1 ["R2", "R2", "R2", "R2"]))))
  (testing "distace with directions"
    (is (= 12 (day1 ["R5", "L5", "R5", "R3"]))))
  
  (testing "update coords, facing north"
    (is (= {:facing \N :x 0 :y 10} (update-coordinates {:facing \N :x 0 :y 0} 10))))
  (testing "update coords, facing south"
    (is (= {:facing \S :x 0 :y -10} (update-coordinates {:facing \S :x 0 :y 0} 10))))
  (testing "update coords, facing east"
    (is (= {:facing \E :x 10 :y 0} (update-coordinates {:facing \E :x 0 :y 0} 10))))
  (testing "update coords, facing west"
    (is (= {:facing \W :x -10 :y 0} (update-coordinates {:facing \W :x 0 :y 0} 10))))
  
  (testing "update direction, facing north turn right"
    (is (= {:facing \E :x 0 :y 0} (update-direction {:facing \N :x 0 :y 0} \R))))
  (testing "update direction, facing east turn right"
    (is (= {:facing \S :x 0 :y 0} (update-direction {:facing \E :x 0 :y 0} \R))))
  (testing "update direction, facing south turn right"
    (is (= {:facing \W :x 0 :y 0} (update-direction {:facing \S :x 0 :y 0} \R))))
  (testing "update direction, facing west turn right"
    (is (= {:facing \N :x 0 :y 0} (update-direction {:facing \W :x 0 :y 0} \R))))

  (testing "update direction, facing north turn left"
    (is (= {:facing \W :x 0 :y 0} (update-direction {:facing \N :x 0 :y 0} \L))))
  (testing "update direction, facing west turn left"
    (is (= {:facing \S :x 0 :y 0} (update-direction {:facing \W :x 0 :y 0} \L))))
  (testing "update direction, facing south turn left"
    (is (= {:facing \E :x 0 :y 0} (update-direction {:facing \S :x 0 :y 0} \L))))
  (testing "update direction, facing east turn left"
    (is (= {:facing \N :x 0 :y 0} (update-direction {:facing \E :x 0 :y 0} \L)))))