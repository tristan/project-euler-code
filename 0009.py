import math

def brute_force():
    a = 100
    b = 100
    while True:
        if b > a:
            b = 100
            a += 1
        c = math.sqrt((a ** 2) + (b ** 2))
        if a + b + c == 1000:
            print a, '*', b, '*', c
            return a * b * c
        b += 1


def fn0():
    '''

    a + b + c = 1000
    a ** 2 + b ** 2 = c ** 2


    '''

    return brute_force()


solutions = [brute_force]
