#include <iostream>
#include <vector>
#include <functional>
#include <fstream>
#include <array>
#include <utility>

#define N 10

std::ostream &operator<<(std::ostream &os, std::array<int, N> arr) {
  os << "(";
  for (int i = 0; i < N; i++) {
    os << arr[i];
    if (i < N-1) {
      os << " ";
    }
  }
  os << ")";
  return os;
}

void fn2() {
  std::array<int, 10> arr = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
  std::function<void()> permutate = [&arr] () {
    int i = 9;
    while (arr[i-1] >= arr[i]) {
      i = i - 1;
    }

    int j = 10;
    while (arr[j-1] <= arr[i-1]) {
      j = j - 1;
    }

    std::swap(arr[i-1], arr[j-1]);

    i++;
    j = 10;

    while (i < j) {
      std::swap(arr[i-1], arr[j-1]);
      i++;
      j--;
    }
  };

  // the initial permutation counts
  for (int i = 1; i < 1000000; i++) {
    permutate();
    //std::cout << arr << std::endl;
  }
  std::cout << arr << std::endl;
}

std::vector<std::function<void()>> progs = { fn2 };
