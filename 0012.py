import math

def number_of_factors(number, limit=0):
    found = 0
    for i in range(2, math.sqrt(number)):
        if number % i == 0:
            found += 1
        if limit != 0 and found * 2 >= limit:
            return True
    if limit != 0:
        return False
    return found * 2

def problem_twelve():
    n = 1
    while True:
        tri = (n * (n + 1)) / 2
        if number_of_factors(tri, 500):
            break
        n += 1
    print tri

if __name__ == '__main__':
    problem_twelve()
