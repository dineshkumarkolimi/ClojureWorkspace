(ns asg2.conditionalExamples)

;if example
(defn ifExmpl []
  (if (= 2 2)
    (println "these are equal")
    (println "these are not equal")))
(ifExmpl)

;if do else do kind of example
(def ifDoExmpl []
  (if ((= 2 2) and (= 3 3))
    (
      (println "these are equal")
      (println "this is true"))
    (
      (println "not equal")
      (println "false"))))
(ifDoExmpl)

;kind of if - case example
(defn caseExmpl []
  (def n 5)
  (case n 5 (println "n is 5")
    10 (println "n is 10")
    (println "neither 5 nor 10")))
(caseExmpl)

;kind of if - cond example
(defn condExmpl []
  (def x 5)
  (cond
    (= x 5) (println "this is 5")
    (= x 10) (println "this is 10")
    :else (println "this is not 5 nor 10")))
(condExmpl)




  
    