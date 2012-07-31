def fibgen(n):
    n0 = n1 = 1
    while n1 <= n:
        yield n1
        tmp = n1
        n1 = n0 + n1
        n0 = tmp

def fn0():
    return sum(i for i in fibgen(4000000) if i % 2 == 0)

solutions = [fn0]
        
