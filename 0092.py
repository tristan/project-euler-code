
def next_in_chain(n):
    return sum(map(lambda x: x*x, map(int, str(n))))

CACHE = {}
def update_cache(n):
    i = 0
    while n[i] != n[-1]:
        CACHE[n[i]] = n[-1]
        i += 1

def build_chain(n):
    if CACHE.has_key(n[-1]):
        n.extend([CACHE[n[-1]]])
        update_cache(n)
        return n
    else:
        n.append(next_in_chain(n[-1]))
        if n[-1] == 89 or n[-1] == 1:
            update_cache(n)
            return n
        else:
            return build_chain(n)

if __name__ == '__main__':
    n = 1
    cnt = 0
    while n < 10000000:
        c = build_chain([n])
        if c[-1] == 89:
            cnt += 1
        n += 1
    print "found:", cnt
