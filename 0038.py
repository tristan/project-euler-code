tools = __import__("0032")

def isdistinct(obj):
    if type(obj) in [str, unicode]:
        a = list(set(obj))
        a.sort()
        b = list(obj)
        b.sort()
    elif type(obj) == list:
        a = obj
        b = set(obj)
    else:
        return False
    a.sort()
    b.sort()
    return a == b

def dothing(p):
    keep_looping = True
    x = 1
    while keep_looping:
        a = 0
        b = x
        num = tools.list_to_number(p[a:b])
        cnt = 2
        while True:
            #print 'x:', x, 'a:', a, 'b:', b,
            #print 'num:', num,
            if b == 9:
                print tools.list_to_number(p), ":", x
                keep_looping = False
            t1 = num * cnt
            #print 't1:', t1,
            if tools.list_to_number(p[b:b+len(str(t1))]) == t1:
                #print 'found', p, num, '==', t1
                a = b
                b += len(str(t1))
                #num = t1
                cnt += 1
            else:
                #print 'break'
                break
        x += 1
        if x > 6:
            #print 'done looping'
            keep_looping = False
        #else:
        #    print 'continuing'
    

if __name__ == '__main__':
    #dothing([9,1,8,2,7,3,6,4,5])
    #dothing([1,9,2,3,8,4,5,7,6])
    for i in range((9*8*7*6*5*4*3*2)-1, -1, -1):
        p = tools.get_permutation(i, 9)
        dothing(p)
    
