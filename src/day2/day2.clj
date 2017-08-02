(ns day2.part1)

(def init-state {:x 1 :y 1 :password []})

(def keypad [[1 2 3] [4 5 6] [7 8 9]])

(defn inbounds?
  [coord]
  (every? (and (<= val 2) (>= val 0)) (vals coord)))

(defn get-keypad
  [{:keys [x y]}]
  (get (get keypad x) y))

(def new-coord 
  [direction {:keys [x y] :as old}]
  (let new [case direction
                  \U {:y (- y 1)}
                  \D {:y (+ y 1)}
                  \L {:x (- x 1)}
                  \R {:x (+ x 1)}]
   (if (inbounds? new)
     new 
     old)))

(defn get-number
  [{:keys [x y password] :as state} directions]
  (->> directions
    (clojure.string/split)
    (reduce #(merge state (new-coord {:x :y} %) state)
    (conj password get-keypad state)
    (merge state ))))
; get new values
; check if values are valid

(defn gimmie-the-digits 
  [directions]
  (reduce get-number init-state directions))
