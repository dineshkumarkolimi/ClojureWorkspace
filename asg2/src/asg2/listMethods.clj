(ns asg2.listMethods)

(defn listMethods []
  ;basic list
  (println (list 1 2 3 4))
  
  ;to append one list to another 'list* new_list old_list'
  (def list1 [1 2])
  (def num 3)
  (println (list* [1 2] [3 4]))
  (println (list* 1 [2 3 4]))
  (list* num list1)
  (println list1)
  (println num)
  
  ;to return first item of list
  (println (first (list 1 2 3 4)))
  
  ;to return nth item of list
  (println (nth (list 1 2 3 4) 1))
  
  ;to add element to the begining of list
  (println (cons 0 (list 1 2 3)))
  
  ;to add elements and they are present at the end 
  (println (conj (list 1 2 3) 4 5 ))
  
  ;to return rest of the elements except the first item
  (println (rest (list 1 2 3 4 5)))
  (def data (list 1))
  (if (compare (rest data) ())
    (merge data ())
    (println "didnt work")))

(def item (listMethods))
(println item)
  