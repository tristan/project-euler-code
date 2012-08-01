#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <functional>

void fn0() {

  std::fstream in("0008.txt");
  std::vector<int> nums;

  std::string s;
  while (!in.eof()) {
    in >> s;
    for (char c: s) {
      nums.push_back(c - '0');
    }
  }
  int max = 0;

  auto it = nums.begin();
  while(it+4 != nums.end()) {
    int sum = *it * *(it+1) * *(it+2) * *(it+3) * *(it+4);
    if (sum > max) {
      max = sum;
    }
    it++;
  }

  std::cout << max << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
