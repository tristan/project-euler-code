
def problem31(m):
    count = 0
    for a in range(m, -1, -200):
        for b in range(a, -1, -100):
            for c in range(b, -1, -50):
                for d in range(c, -1, -20):
                    for e in range(d, -1, -10):
                        for f in range(e, -1, -5):
                            for g in range(f, -1, -2):
                                count += 1
    return count

if __name__ == '__main__':
    print problem31(1);
    print problem31(2);
    print problem31(5);
    print problem31(10);
    print problem31(20);
    print problem31(50);
    print problem31(100);
    print problem31(200),
    print '=', 73682, '?'
