t = 12
f1 = 89
f2 = 144
while len(str(f2)) < 1000:
    fx = f1 + f2
    t += 1
    f1 = f2
    f2 = fx
print t
print f2
