
if __name__ == '__main__':
    r = set()
    for i in range(2, 11):
        for j in range(2, 11):
            r.add(i ** j)
    print len(r)
