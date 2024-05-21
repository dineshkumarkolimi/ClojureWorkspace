(ns asg2.projSwetha)

;method splits a line based on '|' and adds the data in to the list
(defn parse_string [common_list]
  (def first_line (first common_list))
  (def split_list (clojure.string/split first_line #"\|"))
  (def size_of_list (count common_list))
  (cond 
    (= size_of_list 1) (list* split_list "")
    :else (list* split_list (parse_string (rest common_list)))))

(defn print_data [common_list choice]
  (def data (first common_list))
  (def size_of_list (count common_list))
  (cond 
    (= size_of_list 0) (str "" "\n")
    (= choice 1) (str (first data) ":[\"" (nth data 1) "\" \"" (nth data 2) "\" \"" (nth data 3) "\"]\n" (print_data (rest common_list) choice))
    :else (str (first data) ":[\"" (nth data 1) "\" \"" (nth data 2) "\"]\n" (print_data (rest common_list) choice))))

(defn get_cust_name [id cust_list]
  (def cust_data (first cust_list))
  (def size_of_list (count cust_list))
  (cond 
    (= size_of_list 0) (str "" "")
    (= (compare id (first cust_data)) 0) (str (nth cust_data 1) "")
    :else (get_cust_name id (rest cust_list))))

(defn get_prod_name [id prod_list]
  (def prod_data (first prod_list))
  (def size_of_list (count prod_list))
  (cond 
    (= size_of_list 0) (str "" "")
    (= (compare id (first prod_data)) 0) (str (nth prod_data 1) "")
    :else (get_prod_name id (rest prod_list))))

(defn print_sales_data [customer_list product_list sales_list]
  (def data (first sales_list))
  (def cust_name (get_cust_name (nth data 1) customer_list))
  (def prod_name (get_prod_name (nth data 2) product_list))
  (def size_of_list (count sales_list))
  (cond
    (= size_of_list 0) (str "" "\n")
    :else (str (first data) ":[\"" cust_name "\" \"" prod_name "\" \"" (nth data 3) "\"]\n" (print_sales_data customer_list product_list (rest sales_list)))))

(defn get_cust_id [cust_name cust_list]
  (def cust_data (first cust_list))
  (def size_of_list (count cust_list))
  (cond 
    (= size_of_list 0) (str "" "")
    (= (compare cust_name (nth cust_data 1)) 0) (str (first cust_data) "")
    :else (get_cust_id cust_name (rest cust_list))))


(defn get_prod_val [id prod_list]
  (def prod_data (first prod_list))
  (def prod_id (first prod_data))
  (def size_of_list (count prod_list))
  (cond 
    (= size_of_list 0) (* 0 0)
    (= id prod_id) (Double/parseDouble (nth prod_data 2))
    :else (get_prod_val id (rest prod_list))))

(defn cal_total [id prod_list sale_list]
  (def sale_data (first sale_list))
  (def cust_id (nth sale_data 1))
  (def size_of_list (count sale_list))
  (cond
    (= size_of_list 0) (* 0 0)
    (= id cust_id)(+ (* (Integer. (nth sale_data 3)) (get_prod_val (nth sale_data 2) prod_list)) (cal_total id prod_list (rest sale_list)))
    :else (cal_total id prod_list (rest sale_list))))

(defn get_prod_id [name prod_list]
  (def prod_data (first prod_list))
  (def prod_name (nth prod_data 1))
  (def size_of_list (count prod_list))
  (cond 
    (= size_of_list 0) (* 0 0)
    (= name prod_name) (first prod_data)
    :else (get_prod_id name (rest prod_list))))

(defn get_item_total [id sale_list]
  (def sale_data (first sale_list))
  (def prod_id (nth sale_data 2))
  (def size_of_list (count sale_list))
  (cond 
    (= size_of_list 0) (* 0 0)
    (= id prod_id) (+ (Integer. (nth sale_data 3)) (get_item_total id (rest sale_list)))
    :else (get_item_total id (rest sale_list))))


(defn count_sale [prod_list sale_list]
  (println "enter the name of product: ")
  (println "")
  (def prod_name (read-line))
  (def prod_id (get_prod_id prod_name prod_list))
  (str prod_name ": " (get_item_total prod_id sale_list) "\n"))


(defn total_sale_val [customer_list product_list sales_list]
  (println "enter the name of customer: ")
  (println "")
  (def cust_name (read-line))
  (def cust_id (get_cust_id cust_name customer_list))
  (str cust_name ": $" (cal_total cust_id product_list sales_list) "\n"))

(defn loop_options [customer_list product_list sales_list]
  (println (str "    Sales Menu    \n" 
               "------------------\n" 
               "1. Display Customer Table\n" 
               "2. Display Product Table\n" 
               "3. Display Sales Table\n" 
               "4. Total Sales for Customer\n" 
               "5. Total Count for Product\n" 
               "6. Exit\n" 
               "Enter an option?\n"))
  (def choice (Integer. (read-line)))
  (cond
    (= choice 1) (println (print_data customer_list choice))
    (= choice 2) (println (print_data product_list choice))
    (= choice 3) (println (print_sales_data customer_list product_list sales_list))
    (= choice 4) (println (total_sale_val customer_list product_list sales_list))
    (= choice 5) (println (count_sale product_list sales_list))
    (= choice 6) (
                   (println "Good Bye!")
                   (System/exit 0))
    :else (println "select the correct option \n"))
  (loop_options customer_list product_list sales_list))
  
(defn mainMethod []
  ;reads data from various sales files as a string
  (def customer_string (slurp "cust.txt"))
  (def product_string (slurp "prod.txt"))
  (def sales_string (slurp "sales.txt"))
  (def customer_list (parse_string (sort (clojure.string/split-lines customer_string))))
  (def product_list (parse_string (sort (clojure.string/split-lines product_string))))
  (def sales_list (parse_string (sort (clojure.string/split-lines sales_string))))
  (loop_options customer_list product_list sales_list))

(mainMethod)









  