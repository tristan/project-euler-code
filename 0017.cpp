#include <iostream>
#include <array>

int main() {
  std::array<int, 10> ones = {
    // zero, one, ..., nine
    0, 3, 3, 5, 4, 4, 3, 5, 5, 4
  };
  std::array<int, 10> teens = {
    // 10, 11, 12, 13, ..., 19
    3, 6, 6, 8, 8, 7, 7, 9, 8, 8
  };
  std::array<int, 10> tens = {
    // X, ten, twenty, thirty, ..., ninety
    0, 3, 6, 6, 5, 5, 5, 7, 6, 6
  };
  int hundred = 7;
  int and_ = 3;
  int thousand = 8;

  int sum = 0;
  for (int h = 0; h <= 9; h++) {
    for (int t = 0; t <= 9; t++) {
      for (int o = 0; o <= 9; o++) {
        int s = 0;
        std::cout << h << t << o << ": ";
        if (h > 0) {
          
          s += ones[h] + hundred;
          if (t > 0 || o > 0) {
            s += and_;
          }
        }
        if (t > 1) {
          s += tens[t] + ones[o];
        } else if (t == 1) {
          s += teens[o];
        } else {
          s += ones[o];
        }
        std::cout << s << std::endl;
        sum += s;
      }
    }
  }
  sum += ones[1] + thousand;
  std::cout << sum << std::endl;
}
