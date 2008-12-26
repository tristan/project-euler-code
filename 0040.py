import math

# 10 - 190 - 2890 - 38900 - 488890
# each increment a for 10^a
# happens at:
def get_nth_tenth_increment(n):
    return ((10 ** n) * n) - sum(map(lambda x: 10 ** x, range(1, n)))

def number_of_fields_per_num(n):
    a = int(math.floor(math.log10(n)))
    b = get_nth_tenth_increment(a)
    if b > n:
        return a
    else:
        return a + 1

def get_number_at(n):
    if (n < 10):
        return n
    a = number_of_fields_per_num(n) - 1
    return (10 ** a) + ((n - get_nth_tenth_increment(a)) / (a+1))

def get_index(n):
    a = number_of_fields_per_num(n)
    return ((n % a) - (a - 2)) % a

def calc_d(n):
    if (n < 10):
        return n
    indx = get_index(n)
    nbr = get_number_at(n)
    return int(str(nbr)[indx])

if __name__ == "__main__":
    print reduce(lambda x,y: x * y, map(lambda x: calc_d(10**x), range(7)))
