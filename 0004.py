def ispalindromic(n):
    r = str(n)
    if len(r) % 2:
        return False
    lim = len(r) / 2
    for i in range(lim):
        if r[i] != r[-(i+1)]:
            return False
    return True

def fn0():
    largestp = 0
    for i in xrange(100, 1000):
        for j in xrange(100, 1000):
            r = i * j
            if ispalindromic(r) and r > largestp:
                #print r
                largestp = r
    return largestp

solutions = [fn0]
