(defn ceil [x]
  (int (. Math (ceil x))))

(defn floor [x]
  (int (. Math (floor x))))

(defn sqrt [x]
  (. Math (sqrt x)))

(defn abs [x]
  (if (neg? x)
    (- x)
    x))