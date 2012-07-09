#include <iostream>
#include <vector>
#include <functional>
#include <map>

std::vector<int> calc_cycle(int div) {
  std::map<int, int> rempos;
  int pos = 0;
  std::vector<int> postdot;
  int rem = 10;
  while (rem > 0) {
    auto it = rempos.find(rem);
    if (it != rempos.end()) {
      pos = rempos[rem];
      return std::vector<int>(postdot.begin()+pos, postdot.end());
    }
    int x = rem / div;
    postdot.push_back(x);
    rempos[rem] = pos;
    pos++;
    //std::cout << x;
    rem = (rem * 10) % div;
  }
  return std::vector<int>{ 0 };
}

void fn1() {
  
  int largest = 0;
  int size = 0;
  for (int i = 2; i <= 1000; i++) {
    auto x = calc_cycle(i);
    if (x.size() > size) {
      size = x.size();
      largest = i;
    }
  }
  std::cout << largest << std::endl;
}

void fn2() {
  int maxn;
  int maxlen = 0;
  for (int n = 2; n <= 1000; n++) {
    int rest = 1;
    int r0;
    for (int i=0; i < n; i++) {
      rest = (rest * 10) % n;
      if (rest == 0) continue;
    }
    r0 = rest;
    int len = 0;
    do {
      rest = (rest * 10) % n;
      len++;
    } while (rest != r0);
    if (len > maxlen) {
      maxn = n;
      maxlen = len;
    }
  }
  std::cout << maxn << std::endl;
}

std::vector<std::function<void()>> progs = { fn1, fn2 };
