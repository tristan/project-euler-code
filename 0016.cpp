#include <iostream>
#include <array>
#include "bigint.h"

int main1() {
  std::array<int, 512> num = { 0 };
  int d = 1;
  num[1] = 1;
  for(int i = 0; i<1000; i++) {
    for(int j = d; j>=1; j--) {
      num[j] <<= 1;
      if (num[j] > 9) {
        num[j] -= 10;
        num[j+1]++;
      }
    }
    if (num[d+1] !=0 ) {
      d++;
    }
  }
  int sum = 0;
  for(int c = d; c >= 1; c--) {
    sum+=num[c];
  }
  std::cout << sum << std::endl;
}

// using bigint
int main() {
  bigint x("2");
  for (int i = 0; i < 999; i++) {
    x = x * 2;
  }
  std::string s = x.to_string();
  int sum = 0;
  for (char c: s) {
    sum += (c - 48);
  }
  std::cout << sum << std::endl;
}
