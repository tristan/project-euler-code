#include <iostream>
#include <vector>
#include <functional>
#include <array>

void fn0() {
  std::vector<int> coins = { 1, 2, 5, 10, 20, 50, 100, 200 };
  int amount = 200;
  std::vector<int> ways = { 1 };
  ways.resize(amount+1);
  for (int coin: coins) {
    for (int j = coin; j <= amount; j++) {
      ways[j] = ways[j] + ways[j - coin];
    }
  }
  std::cout << ways[amount] << std::endl;
}

void fn1() {
  int count = 0;
  for (int a = 200; a > -1; a -= 200) {
    for (int b = a; b > -1; b -= 100) {
      for (int c = b; c > -1; c -= 50) {
        for (int d = c; d > -1; d -= 20) {
          for (int e = d; e > -1; e -= 10) {
            for (int f = e; f > -1; f -= 5) {
              for (int g = f; g > -1; g -= 2) {
                count++;
              }
            }
          }
        }
      }
    }
  }
  std::cout << count << std::endl;
}

void fn2() {
  std::vector<int> coins = { 200, 100, 50, 20, 10, 5, 2, 1 };
  std::function<int(int, int)> calc = [&calc, &coins] (int c, int x) {
    if (coins[c] == 1) {
      return 1;
    }
    int count = 0;
    for (int i = x; i > -1; i -= coins[c]) {
      count += calc(c+1, i);
    }
    return count;
  };
  std::cout << calc(0, 200) << std::endl;
}

 std::vector<std::function<void()>> progs = { fn0, fn1, fn2 };
