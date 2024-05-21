(ns asg2.looping)
;;This file is about different kind of loopings possible in clojure

;;Example of while loop
(defn whileExmple [] 
  (def x (atom 1))
  (while (< @x 5)
    (do
      (println @x)
      (swap! x inc))))

;; calling method
(whileExmple)

;;Example of doseq looping
(defn doSeqExmpl []
  (doseq [n [0 1 2 3 4]]
    (println n)))
(doSeqExmpl)

;;example of dotimes looping
(defn doTimesExmpl []
  (dotimes [n 5]
    (println n)))
(doTimesExmpl)

;;example of loop 
(defn loopExmpl []
  (loop [n 10]
    (when (> n 1)
      (println n)
      (recur (- n 2)))))
(loopExmpl)


    
    
  