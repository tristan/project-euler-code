y = 0
(1..999).each { |x| y = y + x if (x % 3 == 0) || (x % 5 == 0) }
puts y
