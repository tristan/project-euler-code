#include <iostream>
#include <vector>
#include <functional>
#include <algorithm>

std::ostream &operator<<(std::ostream &out, const std::vector<int> &v) {
  for (auto x = v.rbegin(); x != v.rend(); x++) {
    out << *x;
  }
  return out;
}

std::vector<int> &inc(std::vector<int> &n) {
  for (int i = 0; i < n.size(); i++) {
    if (n[i] < 9) {
      n[i]++;
      return n;
    } else {
      n[i] = 0;
    }
  }
  n.push_back(1);
  return n;
}

std::vector<int> operator*(const std::vector<int> &lhs, int rhs) {
  std::vector<int> res { 0 };
  int i = 0;
  for (; i < lhs.size(); i++) {
    int x = res[i] + (lhs[i] * rhs);
    res[i] = x % 10;
    res.push_back(x / 10);
  }
  while (res[i] >= 10) {
    int x = res[i];
    res[i] = x % 10;
    res.push_back(x / 10);
    i++;
  }
  if (res[i] == 0) {
    res.pop_back();
  }
  return res;
}

std::vector<int> operator+(const std::vector<int> &lhs, const std::vector<int> &rhs) {
  std::vector<int> res;
  int rem = 0;
  for (int i = 0 ; i < lhs.size() || i < rhs.size() ; i++ ) {
    int l = i < lhs.size() ? lhs[i] : 0;
    int r = i < rhs.size() ? rhs[i] : 0;
    int x = l + r + rem;
    res.push_back( x % 10 );
    rem = x / 10;
  }
  while (rem >= 10) {
    res.push_back( rem % 10 );
    rem /= 10;
  }
  return res;
}

std::vector<int> operator*(const std::vector<int> &lhs, const std::vector<int> &rhs) {
  std::vector<int> res { 0 };
  for (int x: rhs) {
    std::vector<int> tmp = lhs * x;
    res = res + tmp;
  }
  return res;
}

std::vector<int> pow(int n) {
  //std::cout << "===== " << n << " =====" << std::endl;
  std::vector<int> res { n };
  for (int i = 1; i < n; i++) {
    res = res * n;
  }
  //std::cout << "(" << res << ")" << std::endl;
  return res;
}

void fn0() {
  std::vector<int> res { 0 };
  for (int i = 1; i < 1001; i++) {
    res = res + pow(i);
    //std::cout << "|||| sum: " << res << " ||||" << std::endl;
  }
  std::vector<int> ans(res.begin(), res.begin()+10);
  std::cout << ans << std::endl;
}

void fn1() {
  unsigned long int sum = 0;
  for (unsigned long int i = 1; i < 1001; i++) {
    unsigned long int pow = i;
    for (unsigned long int j = 1; j < i; j++) {
      pow = (pow * i) % 10000000000L;
    }
    sum = (sum + pow) % 10000000000L;
  }
  std::cout << sum << std::endl;
}

std::vector<std::function<void()>> progs = { fn0, fn1 };
