;; Do Euler Number 1 & 2 with Loop Recursion

;;;;;;;;;;;;;;;;;;; Number 1 ;;;;;;;;;;;;;;;;;;;

; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. 
;The sum of these multiples is 23.
; Find the sum of all the multiples of 3 or 5 below 1000.

(defn multiples
  [coll]
  (loop [[x & xs] coll res 0]
    (if x (if (or (= 0 (rem x 3)) (= 0 (rem x 5))) 
            (recur xs (+ res x))
            (recur xs (+ res 0)))
      res)))

;;;;;;;;;;;;;;;;;;; Number 2 ;;;;;;;;;;;;;;;;;;;
; Each new term in the Fibonacci sequence is generated by adding the previous two terms. 
; By starting with 1 and 2, the first 10 terms will be: (1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...)
; By considering the terms in the Fibonacci sequence whose values do not exceed four million, 
; find the sum of the even-valued terms.


(defn fibbo
  [num]
  (loop [n num a 1 b 2 res [1 2]]
    (if (= n 2) res
      (recur (dec n) b (+ a b) (conj res (+ a b))))))

(defn max-value
  [n]
  (if (>= (last (fibbo n)) 4000000)
    (dec n)
    (max-value (inc n))))

(apply + (filter even? (fibbo (max-value 2))))


;;;;;;;;;;;;;;;;;;; Number 16 ;;;;;;;;;;;;;;;;;;;

; 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

; What is the sum of the digits of the number 2^1000?

(defn expo
  [angka pangkat]
  (loop [a angka p pangkat res 1]
    (if (= p 0)
      res
      (recur a (dec p) (*' res a)))))

(defn divisor-by-digit
  [num]
  (expo 10 (dec (count (str num)))))

(defn listing-digit
  [a b]
  (if (<= b 10)
    [(quot a b) (rem a b)]
    (cons (quot a b) (listing-digit (rem a b) (/ b 10)))))  

(apply + (listing-digit (expo 2 15) (divisor-by-digit (expo 2 15))))

;;;;;;;;;;;;;;;;;;; Number 6 ;;;;;;;;;;;;;;;;;;;

; The sum of the squares of the first ten natural numbers is,
; 1^2 + 2^2 + ... + 10^2 = 385
; The square of the sum of the first ten natural numbers is,
; (1 + 2 + ... + 10)^2 = 55^2 = 3025
; Hence the difference between the sum of the squares of the first ten natural numbers 
; and the square of the sum is 3025 − 385 = 2640.
; Find the difference between the sum of the squares of the first one hundred natural numbers 
; and the square of the sum.

(defn sum
  [[x & xs]]
  (if x
    (+ x (sum xs))
    0))

(defn sq
  [num]
  (* num num))

(defn sqr-o-sum
  [coll]
  (sq (sum coll)))

(defn sum-o-sqr
  [coll]
  (loop [[x & xs] coll res 0]
    (if x
      (recur xs (+ res (sq x)))
      res)))

(defn diff
  [coll]
  (- (sqr-o-sum coll) (sum-o-sqr coll)))

(diff (range 1 101))


  
