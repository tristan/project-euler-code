
def _find_all_paths(x, y, max_x, max_y):
    if not (x == max_x or y == max_y):
        return _find_all_paths(x+1, y, max_x, max_y) + _find_all_paths(x, y+1, max_x, max_y)
    else:
        return 1

def find_all_paths(max_x, max_y):
    return 2 * _find_all_paths(1, 0, max_x, max_y)

if __name__ == '__main__':
    for i in range(20):
        print 'paths in an %sx%s grid: %s' % (i, i, find_all_paths(i, i))
