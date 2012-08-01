#include <iostream>
#include <array>
#include <algorithm>
#include <vector>
#include <functional>

template <int size>
void print_grid(std::array<std::array<long int, size>, size> grid) {
  for (int y = 0; y < size; y++) {
    for (int x = 0; x < size; x++) {
      std::cout << grid[x][y] << " ";
    }
    std::cout << std::endl;
  }
  std::cout << std::endl;
}

template <int grid_size, int size=grid_size + 1>
void run() {
  std::array<std::array<long int, size>, size> grid { { 0 } };
  grid[size-1][size-2] = 1;
  grid[size-2][size-1] = 1;
  for (int x = size-1; x >= 0; --x) {
    for (int y = size-1; y >= 0; --y) {
      long int cnt = 0;
      if (x+1 < size) {
        cnt += grid[x+1][y];
      }
      if (y+1 < size) {
        cnt += grid[x][y+1];
      }
      grid[x][y] += cnt;
      //print_grid<size>(grid);
    }
  }
  std::cout << grid[0][0] << std::endl;
}

std::vector<std::function<void()>> progs = { run<20> };
