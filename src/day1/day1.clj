(ns day1.day1)

(def init-state {:facing \N :x 0 :y 0 :visited [{:x 0 :y 0}]})

(def turn-direction
  {\N {\R \E \L \W}
   \E {\R \S \L \N}
   \S {\R \W \L \E}
   \W {\R \N \L \S}})

(defn update-direction
  [state turn]
  (let [old-dir (state :facing)]
  (merge state {:facing ((turn-direction old-dir) turn)})))

(defn- get-direction
  [{:keys [facing x y] :as state} distance]
  (case facing
             \N {:y (+ y distance)}
             \E {:x (+ x distance)}
             \S {:y (- y distance)}
             \W {:x (- x distance)}))

(defn update-coordinates
  [state distance]
  (merge state (get-direction state distance)))

(defn get-coords
  [state [turn & dis]]
  (let [distance (->> dis (apply str) read-string)]
    (-> state
      (update-direction turn)
      (update-coordinates distance))))

(defn calc-distance
  [{:keys [x y]}]
  (let [pos #(if (neg? %) (- %) %)]
    (+ (pos x) (pos y))))

(defn day1
  [list]
  (->> list
    (reduce get-coords init-state)
    calc-distance))

; Part 2
(defn visited?
  [visited coord]
  (->> visited
    (filter #(= % coord))
    seq
    seq?))

(defn returns-visited 
  [state result]
    (if (vector? result)
        (conj state {:visited result})
        (reduced result)))

(defn- get-range
  [{:keys [facing x y visited] :as state} distance]
  (case facing
        \N [:y (range (inc (- y distance)) (inc y))]
        \E [:x (range (inc (- x distance)) (inc x))]
        \S [:y (range y (+ y distance))]
        \W [:x (range x (+ x distance))]))

(defn update-visited
  [{:keys [facing x y visited] :as state} distance]
  (let [ axis-range (get-range state distance)
         new-visited (last axis-range)]
    (->> new-visited
         (mapv #(merge {:x x :y y} {(first axis-range) %}))
         (reduce #(if (visited? % %2) (reduced (merge state %2)) (conj % %2)) visited)
         (returns-visited state))))

(defn update-state
  [state [turn & dis]]
  (let [distance (->> dis (apply str) read-string)]
    (-> state
      (update-direction turn)
      (update-coordinates distance)
      (update-visited distance))))

(defn day-1-b
  [list]
  (->> list
    (reduce update-state init-state)
    calc-distance))