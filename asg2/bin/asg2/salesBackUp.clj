(ns asg2.salesBackUp)

;method to read data from a file
(defn readDataFromFile [fileName]
     (def s (slurp fileName))
     (str "" s))

;method splits a line based on '|' and adds the data in to a hash map
(defn processLine [item]
  (def detailsList (clojure.string/split item #"\|"))
  (hash-map (first detailsList) (hash-map "name" (nth detailsList 1), "address" (nth detailsList 2), "phone" (nth detailsList 3))))

(defn processCustData [data]
  (def dataList (sort (clojure.string/split-lines data)))
  (def final-map (atom {})) 
  (doseq [item dataList]
    (def tempMap (processLine item))
    (swap! final-map assoc (first (keys tempMap )) (first (vals tempMap))))
  (swap! final-map assoc "nil" nil))

(defn printCustData [custMap]
  (def Keys (keys custMap))
  (doseq [k Keys]
    (def valMap (get custMap k))
    (def isNil (compare k "nil"))
    (if (= isNil 0)
      (println "completed printing")
      (println (str k ": [\"" (get valMap "name") "\" \"" (get valMap "address") "\" \"" (get valMap "phone") "\"]")))))

(defn mainMethod []
  ;definitions of file names
  (def custFile "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/cust.txt")
  (def prodFile "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/prod.txt")
  (def salesFile "/Users/dineshkumars/Desktop/eclipse/clojure_workspace/asg2/src/asg2/sales.txt")
  
  ;reads data from various sales files as a string
  (def custData (slurp custFile))
  (def prodData (slurp prodFile))
  (def salesData (slurp salesFile))
  (def custProcessedData (processCustData custData))
  (printCustData custProcessedData))

(mainMethod)
  