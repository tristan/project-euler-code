import math
INITIAL_PRIMES = [2, 3, 5, 7]

def find_all_primes_less_than(limit):
    primes = INITIAL_PRIMES
    if limit <= primes[-1]:
        return [i for i in primes if i <= limit]
    cur = primes[-1] + 2
    while cur <= limit:
        sqrtcur = math.sqrt(cur)
        i = 1
        possiblyprime = True
        while primes[i] <= sqrtcur and possiblyprime:
            if cur % primes[i] == 0:
                possiblyprime = False
            else:
                i += 1
        if possiblyprime:
            primes.append(cur)
        cur += 2
    return primes

def unit_test_faplt():
    if (find_all_primes_less_than(2000000) != 142913828922):
        print "test failed"
    else:
        print "test passed"
