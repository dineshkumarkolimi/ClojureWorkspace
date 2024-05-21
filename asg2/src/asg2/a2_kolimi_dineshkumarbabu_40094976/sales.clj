(ns asg2.sales)

;converts string to Integer types
(defn parse-int [s]
   (Integer. (re-find  #"\d+" s )))

;converts string to float/double type
(defn parse-float [s]
   (Double/parseDouble s))

;Calculates the sale of a customer by iterating through sales list
(defn findCost [custName Keys prodData salesData]
  (def valMap (get salesData (first Keys)))
  (def isNil (compare (first Keys) "nil"))
  (cond 
    (= isNil 0) (+ 0 0)
    (= (compare (get valMap "custName") custName) 0) (+ (* (parse-int (get valMap "itemCount")) (parse-float (get (get prodData (get valMap "prodID")) "cost"))) (findCost custName (rest Keys) prodData salesData))
    :else (findCost custName (rest Keys) prodData salesData)))

;Calculates total cost of a customer
(defn totalSales [prodData salesData]
  (println "Please enter the customer name:")
  (def custName (read-line))
  (println "")
  (def cost (findCost custName (keys salesData) prodData salesData))
  (str custName ": $" cost "\n"))

;returns number items corresponding to a product Id
(defn getItemCount [prodId Keys salesData]
  (def valMap (get salesData (first Keys)))
  (def isNil (compare (first Keys) "nil"))
  (cond 
    (= isNil 0) (+ 0 0)
    (= (compare (get valMap "prodID") prodId) 0) (+ (parse-int(get valMap "itemCount")) (getItemCount prodId (rest Keys) salesData))
    :else (getItemCount prodId (rest Keys) salesData)))
    
;calculates total number of products sold by a product
(defn total [prodName Keys prodData salesData]
  (def valMap (get prodData (first Keys)))
  (def isNil (compare (first Keys) "nil"))
  (cond
    (= isNil 0) (+ 0 0)
    (= (compare (get valMap "item") prodName) 0) (getItemCount (first Keys) (keys salesData) salesData)
    :else (total prodName (rest Keys) prodData salesData)))

;return totoal number of items sold per product requested by a customer
(defn totalCount [prodData salesData]
  (println "Please enter the product name:")
  (def prodName (read-line))
  (println "")
  (def sum (total prodName (keys prodData) prodData salesData))
  (str prodName ": " sum "\n"))

;prints customerData/productData/salesData as per the option chosen by customer
(defn printData [dataMap Keys option prodData]
  (def k (first Keys))
  (def valMap (get dataMap k))
  (def isNil (compare k "nil"))
  (cond 
    (= isNil 0) (str "<---- Completed printing ---->" "\n")
    (= option 1)(str k ": [\"" (get valMap "name") "\" \"" (get valMap "address") "\" \"" (get valMap "phone") "\"]\n" (printData dataMap (rest Keys) option prodData))
    (= option 2) (str k ": [\"" (get valMap "item") "\" \"" (get valMap "cost") "\"]\n" (printData dataMap (rest Keys) option prodData))
    :else (str k ": [\"" (get valMap "custName") "\" \"" (get (get prodData (get valMap "prodID")) "item") "\" \"" (get valMap "itemCount") "\"]\n" (printData dataMap (rest Keys) option prodData))))

;method splits a line based on '|' and adds the data in to a hash map
(defn processData [dataList custData option]
  (def detailsList (clojure.string/split (first dataList) #"\|"))
  (cond 
    (= option 1) (def tempMap (sorted-map (first detailsList) (hash-map "name" (nth detailsList 1), "address" (nth detailsList 2), "phone" (nth detailsList 3))))
    (= option 2) (def tempMap (sorted-map (first detailsList) (hash-map "item" (nth detailsList 1), "cost" (nth detailsList 2))))
    :else (def tempMap (sorted-map (first detailsList) (hash-map "custName" (get (get custData (nth detailsList 1)) "name"), "prodID" (nth detailsList 2), "itemCount" (nth detailsList 3)))))
  (def isNil (count dataList))
  (cond
    (= isNil 1) (merge tempMap (sorted-map "nil" nil))
    :else (merge tempMap (processData (rest dataList)  custData option))))

;prints all the possible options for a customer/user
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

;Everything happens from here taking inputs, calling corresponding services and exiting
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
                   (println "Good Bye!\n")
                   (System/exit 0))
    :else (println "choose one of the option from below menu \n"))
  (optForCust custData prodData salesData))
 
;main method - initial point and loads data and other required stuff
(defn mainMethod []
  ;definitions of file names
  (def custFile "cust.txt")
  (def prodFile "prod.txt")
  (def salesFile "sales.txt")
  
  ;reads data from various sales files as a string
  (def custData (sort (clojure.string/split-lines (slurp custFile))))
  (def prodData (sort (clojure.string/split-lines (slurp prodFile))))
  (def salesData (sort (clojure.string/split-lines (slurp salesFile))))
  (def custProcessedData (processData custData {} 1))
  (def prodProcessedData (processData prodData {} 2))
  (def salesProcessedData (processData salesData custProcessedData 3))
  (optForCust custProcessedData prodProcessedData salesProcessedData))

(mainMethod)
  
