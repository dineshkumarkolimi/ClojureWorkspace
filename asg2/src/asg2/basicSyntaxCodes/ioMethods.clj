(ns asg2.ioMethods)

;read a complete file as a string
(defn readFile []
  (def s  (slurp "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/dummy.txt"))
  (println s))
(readFile)

;read a file line by line
(defn readFileLine []
  (with-open [r (clojure.java.io/reader "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/dummy.txt")]
    (reduce conj [] (line-seq r))))
(readFileLine)

;write a string in to a file
(defn writeString []
  (spit "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/dummy.txt" 
        "4|Kolimi Dineshkumar|1800 Montreal|5149675445"))
(writeString)

;;write a string in to a file
(defn writeStringByLine []
  (with-open [w (clojure.java.io/writer "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/dummy.txt" :append true)]
    (.write w (str "\n5|Kolimi Dinesh|1800 Montreal|5149675445"))))
(writeStringByLine)

;check whether a file exists or not
(defn checkFile []
  (println (.exists (clojure.java.io/file "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/dummy.txt"))))
(checkFile)

;to read input from user or console
(defn userInput []
  (println (read-line))
  (println "printed output"))
(userInput)