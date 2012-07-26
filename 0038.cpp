#include <iostream>
#include <vector>
#include <array>
#include <functional>
#include "utils.h"

std::array<int, 9> &inc_pandigital(std::array<int, 9> &arr) {
  int i = 8;
  while (arr[i-1] >= arr[i]) {
    i = i - 1;
  }
  
  int j = 9;
  while (arr[j-1] <= arr[i-1]) {
    j = j - 1;
  }
  
  std::swap(arr[i-1], arr[j-1]);
  
  i++;
  j = 9;
  
  while (i < j) {
    std::swap(arr[i-1], arr[j-1]);
    i++;
    j--;
  }
}

std::array<int, 9> &dec_pandigital(std::array<int, 9> &arr) {
  int i = 8;
  while (arr[i-1] <= arr[i]) {
    i = i - 1;
  }
  
  int j = 9;
  while (arr[j-1] >= arr[i-1]) {
    j = j - 1;
  }
  
  std::swap(arr[i-1], arr[j-1]);

  i++;
  j = 9;
  
  while (i < j) {
    std::swap(arr[i-1], arr[j-1]);
    i++;
    j--;
  }
}

int join(const std::array<int, 9> &arr, int start, int end) {
  int s = 0;
  for (int i = start; ;) {
    s += arr[i];
    i++;
    if (i < end+1) {
      s *= 10;
    } else {
      break;
    }
  }
  return s;
}

bool fn0_(const std::array<int, 9> &pan, int base, int start, int x) {
  int target = base * x;
  for (int j = start; j < 9 ; j++) {
    int bt = join(pan, start, j);
    if (bt == target) {
      if (j+1 >= 9) {
        return true;
      } 
      return fn0_(pan, base, j+1, x+1);
    } else if (bt > target) {
      return false;
    }
  }
  return false;
}

void fn0() {
  std::array<int, 9> pan { 9,8,7,6,5,4,3,2,1 };
  while (true) {
    for (int i = 0;i < 8;i++) {
      int a = join(pan, 0, i);
      if (fn0_(pan, a, i+1, 2)) {
        std::cout << join(pan, 0, 8) << std::endl;
        return;
      }
    }
    dec_pandigital(pan);
  }
  std::cout << "NOT FOUND" << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
