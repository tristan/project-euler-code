import primes
import math

if __name__ == '__main__':
    limit = 50000000
    sqrtlim = math.sqrt(limit)
    primes = primes.find_all_primes_less_than(sqrtlim)
    results = set()
    for c in primes:
        for b in primes:
            for a in primes:
                if a ** 2 + b ** 3 + c ** 4 < limit:
                    results.add(a ** 2 + b ** 3 + c ** 4)
                else:
                    break
            if c ** 4 + b ** 3 + primes[0] ** 2 >= limit:
                break
        if c ** 4 + primes[0] ** 3 + primes[0] ** 2 >= limit:
            break
    print len(results)
