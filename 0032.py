def fact(k, n):
    f = []
    for j in range(1, n + 1):
        f.append((k % j) + 1)
        k /= j
    return f[::-1]

def get_permutation(k,n):
    tmp = map(lambda x: x+1, fact(k,n))
    for i in range(len(tmp) - 1, -1, -1):
        for j in range(i + 1, len(tmp)):
            if (tmp[j] >= tmp[i]):
                tmp[j] += 1
    return map(lambda x: x-1, tmp)

def list_to_number(list):
    mult = len(list)-1
    s = 0
    for i in list:
        s += i * (10 ** mult)
        mult -= 1
    return s
    

if __name__ == '__main__':
    products = set()
    for i in range(9*8*7*6*5*4*3*2):
        p = get_permutation(i, 9)
        for x in range(1, len(p)-2):
            p1 = list_to_number(p[:x])
            for y in range(x+1, len(p)-1):
                p2 = list_to_number(p[x:y])
                p3 = list_to_number(p[y:])
                if p1 * p2 == p3:
                    print p1, "*", p2, "==", p3
                    products.add(p3)
    print "sum:", sum(products)
            
