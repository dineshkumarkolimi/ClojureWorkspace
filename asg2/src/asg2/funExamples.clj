(ns asg2.funExamples)

;example of anonymous function
(defn anonymousFun []
  ((fn [x] (* x 2)) 2))
(anonymousFun)

;example of function with multiple arguments
(defn multiArgExample [x y]
  (println (* 2 (+ x y))))
(multiArgExample 2 4)

;example of variadic function which takes multiple arguments but giving
;preference to 1st argument and stores the rest of arguments in others.
(defn variadicFunExmpl [method & others]
  (str method (clojure.string/join " " others)))
(variadicFunExmpl "HI" "am" "Dineshkumar" "Babu")


;example of higher order function (HOF) - functions that take other functions as arguments.
;examples of inbuilt functions - zero? pos? neg? even? odd? number? float? integer?
(filter even? (range 1 10))


;;example of recur function 
(defn loopExmpl []
  (loop [n 10]
    (when (> n 1)
      (println n\)
      (recur (- n 2)))))
(loopExmpl)
    


