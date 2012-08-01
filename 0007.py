import math
import itertools

def primes():
    mem = [2,3,5,7] # memory
    wheel = itertools.cycle([2, 4, 2, 4, 6, 2, 6, 4, 2, 4, 6, 6, 2, 6, 4, 2, 6, 4, 6, 8, 4, 2, 4, 2, 4, 8, 6, 4, 6, 2, 4, 6, 2, 6, 6, 4, 2, 4, 6, 2, 6, 4, 2, 4, 2, 10, 2, 10])
    test = 11
    for p in mem:
        yield p
    while True:
        if not any(test % p == 0 for p in 
                   itertools.takewhile(lambda x: x * x <= test, mem)):
            yield test
            mem.append(test)
        test += wheel.next()

def fn0():
    for i,v in enumerate(primes()):
        if i == 10000:
            return v

solutions = [fn0]
