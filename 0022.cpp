#include <iostream>
#include <vector>
#include <functional>
#include <fstream>
#include <set>
#include <string>

void fn1() {
  std::fstream in("names.txt");
  // std::set is sorted
  std::set<std::vector<int>> names;

  while (!in.eof()) {
    int c = in.get();
    if (c == '"') {
      std::vector<int> name;
      while ((c = in.get()) != '"') {
        name.push_back(c - '@'); // @ is A-1
      }
      names.insert(name);
    }
  }
  int pos = 1;
  int total = 0;
  for (auto name: names) {
    int sum = 0;
    for (int c: name) {
      sum += c;
    }
    total += (sum * pos);
    pos++;
  }
  std::cout << total << std::endl;
}

std::vector<std::function<void()>> progs = { fn1 };
