def fn0():
    sumofsqr = 0
    sumof = 0
    for i in xrange(1, 101):
        sumofsqr += (i*i)
        sumof += i
    return (sumof * sumof) - sumofsqr

solutions = [fn0]
