memory = {1:1}

def get_number_of_sequences(start):
    n = start
    count = 0
    while True:
        if memory.has_key(n):
            return count + memory.get(n)
        if n % 2 == 0:
            n /= 2
        else:
            n = (3 * n) + 1
        count += 1

def find_largest_sequence_under(limit):
    n = 1
    while n < limit:
        sequences = get_number_of_sequences(n)
        memory[n] = sequences
        n += 1
    maxval = max(memory.values())
    for i in memory.keys():
        if memory.get(i) == maxval:
            return i
    raise Exception('something went horribly wrong!')

def fn0():
    return find_largest_sequence_under(1000000)

solutions = [fn0]
