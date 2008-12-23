
if __name__ == '__main__':
    i = 2
    s = 0
    while len(str(i)) < 7: # cause 7*9^5 is less than 9999999
        if sum(map(lambda x: x**5, map(int, (str(i))))) == i:
            print i
            s += i
        i += 1
    print "Sum:", s

    print sum(filter(lambda i: sum(map(lambda x: x**5, map(int, (str(i))))) == i, range(2, 1000000)))
