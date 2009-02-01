


def iterate_expansion(frac):
    numerator = frac[0]
    denominator = frac[1]
    
    numerator += denominator
    return (denominator + numerator, numerator)

if __name__ == '__main__':
    frac = (3, 2)
    counter = 0
    for i in xrange(1000):
        if len(str(frac[0])) > len(str(frac[1])):
            counter += 1
        frac = iterate_expansion(frac)
    print 'result:', counter
