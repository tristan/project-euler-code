import math

def sieveoferatosthenes(prime=6, smax=-1):
    if smax < 0:
        smax = prime ** 3
    while True:
        try:
            sieve = [True]*smax
            break
        except:
            smax /= 2
            print smax
    #print sieve
    primes = [2]
    i = 2
    while i < smax-1:
        #print 'i=', i
        j = i+i
        while j < smax-1:
            #print 'j=', j
            sieve[j] = False
            j += i
        while i < smax-1:
            i += 1
            if sieve[i] == True:
                break
        #print 'got prime:', i
        primes.append(i)
        #if len(primes) % 100 == 0:
        #    print 'found', len(primes), 'primes so far'
    print 'found', len(primes), 'primes'
    if len(primes) < prime:
        return -1
    return primes[prime-1]

if __name__ == "__main__":
    #if sieveoferatosthenes() != 13:
    #    raise Exception("sieve function broken")
    print sieveoferatosthenes(10001, (10 ** 6))
