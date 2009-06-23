def fibgen(n):
    n0 = n1 = 1
    while n1 <= n:
        yield n1
        tmp = n1
        n1 = n0 + n1
        n0 = tmp

if __name__ == '__main__':
    print sum(i for i in fibgen(4000000) if i % 2 == 0)
        
