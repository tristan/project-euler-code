from euler__ import solutions
from time import time

print "==== PYTHON ===="
for fn in solutions:
    st = time()
    res = fn()
    et = time()
    print res
    print "runtime: %s ms" % ((et - st) * 1000)
