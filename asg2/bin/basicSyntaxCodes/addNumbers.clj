(ns asg2.addNumbers)

(defn addNum [] 
   (def x 10)
   (def y 20)
   (def sum (+ x y))
   (println x)
   (println y)
   (println sum)
   (println (str "the sum of ", x, " & ", y, " is ", sum)))
(addNum)