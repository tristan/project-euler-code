import math

def sieveoferatosthenes(smax=100):
    sieve = [True]*smax
    primes = [2]
    i = 2
    while i < smax-1:
        j = i+i
        while j < smax-1:
            sieve[j] = False
            j += i
        while i < smax-1:
            i += 1
            if sieve[i] == True:
                break
        if i >= math.sqrt(smax):
            while i < smax-1:
                if sieve[i] == True:
                    primes.append(i)
                i += 1
        else:
            primes.append(i)
    return primes

if __name__ == '__main__':
    sieveoferatosthenes(1000000)
