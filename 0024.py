
# dave's solution
def factorial(n):
    return reduce(lambda x,y: x*y, range(1, n+1))
    #r = 1
    #for i in range(1, n + 1):
    #    r *= i
    #return r

def p24(n, num_digits):
    digits = range(num_digits)
    result = []
    for i in range(num_digits - 1, 0, -1):
        f = factorial(i)
        result.append(digits[n / f])
        del digits[n / f]
        n = n % f
    return result
