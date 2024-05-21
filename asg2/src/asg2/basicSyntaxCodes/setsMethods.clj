(ns asg2.setsMethods
  (:require [clojure.set :as set]))

(defn setMethods []
  ;general set creation and set removes duplicates 
  (def set1 (set '(1 2 3 4 1)))
  (println (count set1))
  
  ;sorting sets is very easy 'sorted-sets'
  (println (sorted-set 1 1 3 2 7 6 5 5))
  
  ;to return a element at a specific index 
  (println (get (set '(1 1 2 3 4 5)) 2))
  
  ;to find if a element is present we use 'contains ?'
  (println (contains? (set '(1 2 3 4 5))3))
  
  ;to append an element to the set use 'conj'
  (println (conj (set '(1 2 3 4)) 5 6))
  
  ;to remove an element from a set use 'disj'
  (println (disj (set '(1 2 3 4 5 6)) 6))
  
  ;to add two sets use 'union'
  (println (clojure.set/union #{1 2} #{3 4}))
  
  ;to return a set from first set which is different form second set 'difference'
  (println (clojure.set/difference #{1 2} #{2 3}))
  
  ;to return intersection of two sets use 'intersect'
  (println (clojure.set/intersection #{1 2} #{2 3}))
  
  ;to get subset or super set
;  (println (clojure.set/subset #{1 2} #{1 2 3}))
;  (println (clojure.set/superset #{1 2 3} #{1 2}))
  )

(setMethods)
 
  
  
           