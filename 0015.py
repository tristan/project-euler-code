
class Memoize:
    def __init__(self, f):
        self.f = f
        self.memo = {}
    def __call__(self, *args):
        if not args in self.memo:
            self.memo[args] = self.f(*args)
        return self.memo[args]

def _find_all_paths(x, y, max_x, max_y):
    if not (x == max_x or y == max_y):
        return _find_all_paths(x+1, y, max_x, max_y) + _find_all_paths(x, y+1, max_x, max_y)
    else:
        return 1

_find_all_paths = Memoize(_find_all_paths)

def find_all_paths(max_x, max_y):
    return 2 * _find_all_paths(1, 0, max_x, max_y)

def fn0():
    for i in range(20):
        find_all_paths(i, i)
    return find_all_paths(20,20)

solutions = [fn0]
