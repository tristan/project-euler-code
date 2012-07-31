def fn0():
    return sum(i for i in range(1000) if not i % 5 or not i % 3)

solutions = [fn0]
