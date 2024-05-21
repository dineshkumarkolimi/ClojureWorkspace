(ns asg2.mapsMethods)

(defn mapMethods []
   ;Two types of maps one is hash-map & sorted-map
   ;sorted map is sorted based on keys
   (def demo (hash-map "z" "1" "b" "2" "a" "3"))
   (println (hash-map "z" "1" "b" "2" "a" "3"))
   (println (sorted-map "z" "1" "b" "2" "a" "3"))
   (println (count demo))
   
   ;to get a value corresponding to a key use 'get'
   (println (get demo "b"))
   
   ;to check if a key is presetn use 'contians'
   (println (contains? demo "c"))
   (println (contains? demo "z"))
   
   ;returns the key value pair
   (println (find demo "z"))
   
   ;returns list of values in the map
   (println (vals demo))
   
   ;returns the list of keys
   (println (keys demo))
   
   ;to remove a key value pair use 'dissoc'
   (println (dissoc demo "b"))
   
   ;to merge two hashmaps
   (def demo2 (hash-map "k" "0" "d" "9"))
   (def demo3 (hash-map "" ""))
   (println (merge demo demo2))
   (println (merge-with + demo demo2))
   (println (assoc demo (first (keys demo2)) (first (vals demo2))))
   
   ;to invert the map - keys becomes values and values becomes keys
   ;(println (clojure.set/map-invert demo))
   
   ;returns a map containing the keys given
   (println (select-keys demo ["a" "b"]))
   
   ;to rename keys with different keys
   ;(println (clojure.set/rename-keys demo {"a" "demo1" "b" "demo2"}))
   
   ;to merge with
   (println (merge-with + demo demo2))
   (demo (merge-with + demo demo2))
   (println demo)
   (println demo2))

(mapMethods)