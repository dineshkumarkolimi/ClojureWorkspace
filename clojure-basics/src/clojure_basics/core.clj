(ns clojure-basics.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println)
  (println "--------------------------Executing main")
  (def name "GoodBye")
  (def surname "kolimi!")
  (println name surname))

(defn dataTypes []
  (println "--------------------------Executing dataTypes method:")
  (def i 10)
  (def j 1.5)
  (def k "hello")
  (println i j k)
  (str " " i j k))
(dataTypes)

(defn whileLoop []
  (println "--------------------------Executing whileLoop method:")
  (def x (atom 1))
  (while (< @x 5)
    (do
      (println @x)
      (swap! x inc))))
(whileLoop)

(defn doSeq []
  (println "--------------------------Executing doSeq method:")
  (doseq [n [0 1 2]]
    (println n)))
(doSeq)


(defn doTimes []
  (println "--------------------------Executing doTimes method:")
  (dotimes [n 5]
    (println n)))
(doTimes)


(defn Loop []
  (println "--------------------------Executing loop method:")
  (loop [n 10]
    (when (> n 1)
      (println n)
      (recur (- n 2)))))
(Loop)

(defn conditional []
  (println "--------------------------Executing conditional statements:")
  (def n 1)
  (if (= n 1)
    (println "n is" n)
    (println "n is not 1"))
  (if (and (= 2 2) (= 3 3))
    (println "values are equal")
    (println "value is not equal"))

  (if (= n 1)
    (do (println "value is one")
        (println "1 value is positive")))

  (cond
    (> n 0) (println "value is positive")
    (< n 0) (println "value is negative")
    :else (println "value is zero"))

  (case x 5 (println "value is 5")
        10 (println "value is 10")
        (println "value is neither 10 nor 5")))
(conditional)

(defn numbers [x y z s t]
  (println "--------------------------Executing numbers related methods:")
  ;; (def x 1)
  ;; (def y 2.0)
  ;; (def z -2)
  ;; (def s "dinesh")
  ;; (def t 0)
  (println (str "t is zero: " (zero? t)))
  (println (str "y is even: " (even? y)))
  (println (str "x is odd: " (odd? x)))
  (println (str "type of s is: " (type s)))
  (println (str "z is negative: " (neg? z)))
  (println (str "x is positive: " (pos? x)))
  (println (str "s is number: " (number? s)))
  (println (str "x is integer: " (integer? x)))
  (println (str "x is float: " (float? x))))
(numbers 1 2 -2 "dinesh" 0)

(defn readFileAtOnce [filename]
  (println "--------------------------Executing readFileAtOnce:")
  (if (.exists (clojure.java.io/file filename))
    (do (def s (slurp filename))
        (println s))
    (println "file not found!")))
(readFileAtOnce "/Users/dineshkumarkolimi/Documents/GitHub/ClojureGithub/ClojureWorkspace/clojure-basics/resources/slurp.txt")

(defn readFileLine [filename]
  (println "--------------------------Executing readFileLine method:")
  (if (.exists (clojure.java.io/file filename))
    (do (with-open [rdr (clojure.java.io/reader filename)]
          (let [lines (reduce conj [] (line-seq rdr))]
            (println "file read successfully!")
            lines)))
    (do (println "File not found!")
        nil)))
(let [lines (readFileLine "/Users/dineshkumarkolimi/Documents/GitHub/ClojureGithub/ClojureWorkspace/clojure-basics/resources/slurp.txt")]
  (when lines
    (println "File contents: ")
    (doseq [line lines]
      (println line))))

(defn readFromUser []
  (println "--------------------------Executing readFromUser method:")
  (println "enter you name: ")
  (def s (read-line))
  (println (str "Welcome " s " for the show")))
(readFromUser)

(defn strMethods []
  (println "--------------------------Executing String Methods:")
  (println "for concatination use str:")
  (def s1 "Dinesh")
  (def s2 "kumar kolimi")
  (def a1 6993)
  (def a2 5445)
  (println (str s1 s2))
  (println "1. use format to format a str:")
  (println (format "%s:%d" s1 a1))
  (println "2. use count to get len of str:")
  (println (count s1))
  (println "3. to split a string use subs:")
  (println (subs s1 2 5))
  (println "4. use compare to compare two strings:")
  (println (compare s1 s2))
  (println "5. use lower-case and upper-case to convert case:")
  (println (clojure.string/lower-case s1))
  (println (clojure.string/upper-case s2))
  (println "6. use join to join two strings or set of numbers:")
  (println (clojure.string/join ", " [s1, s2]))
  (println "7. use split to split based on a delimiter:")
  (println (clojure.string/split s2 #" "))
  (println "8. use split-lines to split a string:")
  (println (clojure.string/split-lines "dinesh\nkumar"))
  (println "9. use reverse to reverse a string:")
  (println (reverse s1))
  (println "10. use replace to replace a string:")
  (println (clojure.string/replace "dinesh kumar" #"kumar" "kolimi"))
  (println "11. use trim, triml and trimr to trim a string:")
  (println (clojure.string/trim " dinesh kumar "))
  )
(strMethods)

(defn listMethods [l]
  (println "-------------Executing list methods:")
  (println "1. use list* to add elements to the beginning of a list:")
  (println (list* (list 1 2) l))
  (println (list* 1 l))
  (println "2. first for accessing the first element and nth for nth elements:")
  (println (first l))
  (println (nth l 2))
  (println "3. cons for adding elements to the beginning and conj for the end:")
  (println (cons 2 l))
  (println (conj l 6 7))
  (println "4. rest returns the rest of elements in the list except the first:")
  (println (rest l))
  )
(listMethods (list 3 4 5))

