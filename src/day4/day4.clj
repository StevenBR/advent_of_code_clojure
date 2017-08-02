(ns day4.day4
  (:require [clojure.string :refer :all]
            [clojure.tools.trace :refer [trace]]))

(defn get-sum
  [line]
  (-> line (split #"\[|]") last))

  (defn get-hash
  [input]
  (->> input
      frequencies
      sort
      (sort-by val >)
      (take 5)
      keys
      (apply str)))

(defn parse-line
  [input]
  (let [contents (clojure.string/split input #"\[|\]|-")
        checksum (last contents)
        id (read-string (nth contents (- (count contents) 2)))
        hash (get-hash (apply str (drop-last 2 contents)))
        name (apply str (drop-last 2 contents))]
        {:name name :hash hash :id id :checksum checksum}))

(defn valid-room?
  [{:keys [hash checksum]}]
  (= hash checksum))

(defn sector-id-total
  [lines]
  (->> lines
      (map parse-line)
      (filter valid-room?)
      (map :id)
      (apply +)))


;######### part 2


