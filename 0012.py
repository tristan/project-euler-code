import math

def number_of_factors(number):
    found = 0
    for i in range(2, int(math.sqrt(number))):
        if number % i == 0:
            found += 2
    return found

def problem_twelve():
    s = 0
    n = 1
    while True:
        s += n
        if number_of_factors(s) > 500:
            break
        n += 1
    return s

solutions = [problem_twelve]
