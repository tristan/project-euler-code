#include <iostream>
#include <string>

int main() {
  int max = 0;
  for (int i = 100; i < 1000; i++) {
    for (int j = 100; j < 1000; j++) {
      std::string s = std::to_string(i * j);
      //std::cout << i << "*" << j << " = " << s << std::endl;
      for (int a = 0, b = s.size()-1; a < b; a++, b--) {
        if (s[a] != s[b]) break;
        if (a == b || a+1 >= b-1) {
          if (i * j > max) {
            max = i * j;
          }
        }
      }
    }
  }
  std::cout << max << std::endl;
  return 0;
}
