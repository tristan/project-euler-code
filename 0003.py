def isprime(n):
    i = 2
    while i < n:
        if n % i == 0:
            return False
        i += 1
    return True

def genprimefactors(n):
    i = 2
    while i < n:
        if n % i == 0:
            if isprime(i):
                yield i
        i += 1

if __name__ == '__main__':
    """test isprime"""
    if not isprime(3) or isprime(4) or not isprime(29) or isprime(30):
        raise Exception('isprime broken')

    """test genprimefactors"""
    primefactors = [i for i in genprimefactors(13195)]
    if [5, 7, 13, 29] != primefactors:
        print primefactors
        raise Exception('genprimefactors did not work')

    print 'All tests passed'

    largestprime = 1
    for i in genprimefactors(600851475143):
        print i
        largestprime = i
    print largestprime
