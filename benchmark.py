from euler__ import solutions
from time import time

for fn in solutions:
    st = time()
    res = fn()
    et = time()
    print res
    print "runtime:", (et - st)
