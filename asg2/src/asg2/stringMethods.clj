(ns asg2.stringMethods)

(defn stringMethods []
  ;general form of a string
  (println "I am dinesh")
  
  ;concatenation of a string by 'str'
  (println (str "I am " "dinesh"))
  
  ;formatting of any string by %s or %t or %06d
  (println (format "I am %s" "dinesh"))
  (println (format "padding with zeroes ahead %06d" 1234))
  
  ;to count size of string
  (println(count "I am dinesh"))
  
  ;to access substring of a string 'subs'
  (println (subs "i am dinesh" 5 10))
  
  ;To compare two strings and returns '+' or '0' or '-' 
  ;if lesser(x<y) or equal(z=y) or greater (x>y)
  (println (compare "dinesh" "Dinesh"))
  
  ;to convert a string to lowercase or uppercase
  (println (clojure.string/lower-case "DINESH Kumar"))
  (println (clojure.string/upper-case "dinesh Kumar"))
  
  ;to join a list or vector or any other collection
  (println (clojure.string/join "," [0 1 2 4]))
  
  ;To split based on a regular expression or based on \n or \rn
  (println (clojure.string/split "I am dinesh" #" "))
  (println (clojure.string/split "I |am |dinesh" #"\|"))
  (println (clojure.string/split-lines "i am \n dinesh" ))
  
  ;to reverse a string
  (println (reverse "dinesh"))
  
  ;to replace a string with another
  (println (clojure.string/replace "i am dinesh" #"dinesh" "kumar"))
  
  ;To trim complete blank spaces or to trim left or right of string
  (println (clojure.string/trim "  i am dinesh "))
  (println (clojure.string/triml " i am dinesh  "))
  (println (clojure.string/trimr "  i am dinesh   ")))

(stringMethods)
  
  
  
  
  
  
  
  
  
 