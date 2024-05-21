(ns asg2.sales)

;method to read data from a file
(defn readDataFromFile [fileName]
     (def s (slurp fileName))
     (str "" s))

;method splits a line based on '|' and adds the data in to a hash map
(defn processData [dataList custData option]
  (def detailsList (clojure.string/split (first dataList) #"\|"))
  (println detailsList)
  (cond 
    (= option 1) (def tempMap (sorted-map (first detailsList) (hash-map "name" (nth detailsList 1), "address" (nth detailsList 2), "phone" (nth detailsList 3))))
    (= option 2) (def tempMap (sorted-map (first detailsList) (hash-map "item" (nth detailsList 1), "cost" (nth detailsList 2))))
    :else (def tempMap (sorted-map (first detailsList) (hash-map "custName" (get (get custData (nth detailsList 1)) "name"), "prodID" (nth detailsList 2), "itemCount" (nth detailsList 3)))))
  (def isNil (count dataList))
  (cond
    (= isNil 1) (merge tempMap (sorted-map "nil" nil))
    :else (merge tempMap (processData (rest dataList)  custData option))))

(defn printData [dataMap Keys option prodData]
  (def k (first Keys))
  (def valMap (get dataMap k))
  (def isNil (compare k "nil"))
  (cond 
    (= isNil 0) (str "<---- Completed printing ---->" "\n")
    (= option 1)(str k ": [\"" (get valMap "name") "\" \"" (get valMap "address") "\" \"" (get valMap "phone") "\"]\n" (printData dataMap (rest Keys) option prodData))
    (= option 2) (str k ": [\"" (get valMap "item") "\" \"" (get valMap "cost") "\"]\n" (printData dataMap (rest Keys) option prodData))
    :else (str k ": [\"" (get valMap "custName") "\" \"" (get (get prodData (get valMap "prodID")) "item") "\" \"" (get valMap "itemCount") "\"]\n" (printData dataMap (rest Keys) option prodData))))

(defn printOptions []
  (println (str "*** Sales Menu ***\n" 
                "------------------\n" 
                "1. Display Customer Table\n" 
                "2. Display Product Table\n" 
                "3. Display Sales Table\n" 
                "4. Total Sales for Customer\n" 
                "5. Total Count for Product\n" 
                "6. Exit\n" 
                "Enter an option?\n")))

(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

(defn parse-float [s]
   (Double/parseDouble s))



(defn findCost [custName Keys prodData salesData]
  (def valMap (get salesData (first Keys)))
  (def isNil (compare (first Keys) "nil"))
  (cond 
    (= isNil 0) (+ 0 0)
    (= (compare (get valMap "custName") custName) 0) (+ (* (parse-int (get valMap "itemCount")) (parse-float (get (get prodData (get valMap "prodID")) "cost"))) (findCost custName (rest Keys) prodData salesData))
    :else (findCost custName (rest Keys) prodData salesData)))

(defn totalSales [prodData salesData]
  (println "Please enter the customer name:")
  (def custName (read-line))
  (println "")
  (def cost (findCost custName (keys salesData) prodData salesData))
  (str custName ": $" cost "\n"))

(defn getItemCount [prodId Keys salesData]
  (def valMap (get salesData (first Keys)))
  (def isNil (compare (first Keys) "nil"))
  (cond 
    (= isNil 0) (+ 0 0)
    (= (compare (get valMap "prodID") prodId) 0) (+ (parse-int(get valMap "itemCount")) (getItemCount prodId (rest Keys) salesData))
    :else (getItemCount prodId (rest Keys) salesData)))
    

(defn total [prodName Keys prodData salesData]
  (def valMap (get prodData (first Keys)))
  (def isNil (compare (first Keys) "nil"))
  (cond
    (= isNil 0) (+ 0 0)
    (= (compare (get valMap "item") prodName) 0) (getItemCount (first Keys) (keys salesData) salesData)
    :else (total prodName (rest Keys) prodData salesData)))
(defn totalCount [prodData salesData]
  (println "Please enter the product name:")
  (def prodName (read-line))
  (println "")
  (def sum (total prodName (keys prodData) prodData salesData))
  (str prodName ": " sum "\n"))

(defn optForCust [custData prodData salesData]
  (printOptions)
  (def option (parse-int (read-line)))
  (println "")
  (cond
    (= option 1) (println (printData custData (keys custData) 1 {}))
    (= option 2) (println (printData prodData (keys prodData) 2 {}))
    (= option 3) (println (printData salesData (keys salesData) 3 prodData))
    (= option 4) (println (totalSales prodData salesData))
    (= option 5) (println (totalCount prodData salesData))
    (= option 6) (
                   (println "Good Bye!")
                   (System/exit 0))
    :else (println "choose one of the option from below menu \n"))
  (optForCust custData prodData salesData))
  


(defn mainMethod []
  ;definitions of file names
  (def custFile "cust.txt")
  (def prodFile "prod.txt")
  (def salesFile "sales.txt")
  
  ;reads data from various sales files as a string
  (def custData (slurp custFile))
  (def prodData (slurp prodFile))
  (def salesData (slurp salesFile))
  (def custProcessedData (processData custData {} 1))
  (def prodProcessedData (processData prodData {} 2))
  (def salesProcessedData (processData salesData custProcessedData 3))
  
  (optForCust custProcessedData prodProcessedData salesProcessedData))

(mainMethod)
  
