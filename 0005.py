def gcd(a,b):
    if b == 0:
        return a;
    return gcd(b, a % b)

def lcm(a,b):
    return (a * b) / gcd(a,b)

def fn0():
    return reduce(lcm, range(1,21))

solutions = [fn0]
