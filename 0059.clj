(load-file "math.clj")


; space = 32
; A = 65
; Z = 90
; a = 97
; z = 122

; iterates the current passphrase 
(defn iterate-passphrase [passphrase]
  (let [a (first passphrase) b (second passphrase) c (first (rest (rest passphrase)))]
       (if (= c 122)
	   (if (= b 122)
	       (if (= a 122)
		   nil
		 (list (inc a) 97 97))
	     (list a (inc b) 97))
	 (list a b (inc c)))))

(defn decrypt 
  ([cipher passphrase] (reverse (decrypt cipher passphrase 0 '())))
  ([cipher passphrase it result]
	   (if (nil? cipher)
	       result
	     (recur (rest cipher) passphrase (rem (inc it) (count passphrase))
		    (cons (bit-xor (first cipher) (nth passphrase it)) result)))))

(defn to-ascii 
  ([cipher] (apply str (reverse (to-ascii cipher '()))))
  ([cipher result]
	   (if (nil? cipher)
	       result
	     (recur
	      (rest cipher)
	      (cons (char (first cipher)) result)))))

(let [cipher (map #(math/parse-integer (last %)) (re-seq #"([0-9]+)" (slurp "cipher1.txt")))]
     (loop [passphrase '(97 97 97)]
	   (if (nil? passphrase)
	       nil
	     (let [decoded (decrypt cipher passphrase)]
		  ; makes a guess, if most of the characters are in the range a-zA-Z or a space, then we have the right value
		  ; we could make this more effective by adding in punctuation and numbers to the filter
		  (if (< (- (count decoded) (/ (count decoded) 10)) (count (filter #(or (= 32 %) (and (> % 64) (< % 91)) (and (> % 96) (< % 123))) decoded)))
		      (do
			  (println passphrase ":" (to-ascii decoded))
			  (println (math/sum decoded))
			nil)
		    (recur (iterate-passphrase passphrase)))))))