(ns day4.day4
  (:require [clojure.string :refer :all]
            [clojure.tools.trace :refer [trace]]))

(defn get-sum
  [line]
  (-> line (split #"\[|]") last))

(defn- get-hash
  [input]
  (->> input
      frequencies
      sort
      (sort-by val >)
      (take 5)
      keys
      (apply str)))

(defn- parse-line
  [input]
  (let [contents (clojure.string/split input #"\[|\]|-")
        checksum (last contents)
        id (read-string (nth contents (- (count contents) 2)))
        hash (apply str (drop-last 2 contents))]
        {:hash hash :id id :checksum checksum}))

(defn valid-room?
  [{:keys [hash checksum]}]
  (= (get-hash hash) checksum))

(defn sector-id-total
  [lines]
  (->> lines
      (map parse-line)
      (filter valid-room?)
      (map :id)
      (apply +)))

(defn shift-letter [n letter]
  (-> letter
      int
      (- 97)
      (+ n)
      (mod 26)
      (+ 97)
      char))

(defn decrypt [{:keys [hash id] :as room}]
  (assoc room :decrypted
         (apply str (map (partial shift-letter id) hash))))

(defn north-pole?
  [contents]
  (re-matches #".*north.*" (:decrypted contents)))

(defn get-secret-id
  [lines]
  (->> lines
    (map parse-line)
    (filter valid-room?)
    (map decrypt)
    (filter north-pole?)
    first
    :id))
