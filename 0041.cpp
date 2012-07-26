#include <iostream>
#include <vector>
#include <array>
#include <functional>
#include <algorithm>
#include <cmath>
#include "primes.h"
#include "utils.h"

std::vector<int> &dec_pandigital(std::vector<int> &arr) {
  int j = arr.size();
  int i = j - 1;

  while (arr[i-1] <= arr[i]) {
    i = i - 1;
  }
  
  while (arr[j-1] >= arr[i-1]) {
    j = j - 1;
  }
  
  std::swap(arr[i-1], arr[j-1]);

  i++;
  j = arr.size();
  
  while (i < j) {
    std::swap(arr[i-1], arr[j-1]);
    i++;
    j--;
  }
}

int as_int(const std::vector<int> &arr) {
  int s = 0;
  for (int x: arr) {
    s = (s * 10) + x;
  }
  return s;
}

int prod(const std::vector<int> &arr) {
  int s = 1;
  for (int x: arr) {
    s *= x;
  }
  return s;
}

bool is_prime(int n) {
  for (int i = 2; i * i < n; i++) {
    if (n % i == 0) {
      return false;
    }
  }
  return true;
}

void fn0() {
  //std::cout << "made sieve" << std::endl;
  std::vector<int> pan { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
  while (pan.size() > 1) {
    int lim = prod(pan)-1;
    for (int i = 0; i < lim; i++) {
      int p = as_int(pan);
      if (is_prime(p)) {
        std::cout << p << std::endl;
        return;
      }
      dec_pandigital(pan);
    }
    pan.pop_back();
    std::reverse(std::begin(pan), std::end(pan));
  }
}

// seems the prime sieve needs to be too large in this case for my system to handle
void fn1() {
  auto sieve = prime_sieve<7777777>().getsieve();
  std::cout << "made sieve" << std::endl;
  std::vector<int> pan { 7, 6, 5, 4, 3, 2, 1 };
  while (pan.size() > 1) {
    int lim = prod(pan)-1;
    for (int i = 0; i < lim; i++) {
      int p = as_int(pan);
      if (sieve[p]) {
        std::cout << p << std::endl;
        return;
      }
      dec_pandigital(pan);
    }
    pan.pop_back();
    std::reverse(std::begin(pan), std::end(pan));
  }
}

std::vector<std::function<void()>> progs = { fn0, fn1 };
