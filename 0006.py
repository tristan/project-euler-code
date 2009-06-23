if __name__ == '__main__':
    sumofsqr = 0
    sumof = 0
    for i in xrange(1, 101):
        sumofsqr += (i*i)
        sumof += i
    print (sumof * sumof) - sumofsqr
