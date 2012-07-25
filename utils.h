#include <iostream>
#include <vector>

std::ostream & operator<<(std::ostream& o, const std::vector<int> &v) {
  for (int x: v) {
    o << x << " ";
  }
  return o;
}
