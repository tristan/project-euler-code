(defn char-to-number [c]
  (if (= c \.)
    c
    (if (or (< (int c) 48) (> (int c) 57))
      nil
      (- (int c) 48))))
