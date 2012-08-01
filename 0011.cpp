#include <iostream>
#include <fstream>
#include <array>
#include <functional>
#include <vector>

void fn0() {
  std::array<int, 20*20> grid;
  std::fstream in("0011.txt");
  int i = 0;
  while (!in.eof() && i < 20*20) {
    in >> grid[i++];
  }

  int max = 0;
  for (int x = 0; x < 20; x++) {
    for (int y = 0; y < 20; y++) {
      // left
      if (x < 20 - 3) {
        int p = grid[y*20 + x] * grid[y*20 + x + 1] * grid[y*20 + x + 2] * grid[y*20 + x + 3];
        if (p > max) {
          max = p;
        }
      }
      // down
      if (y < 20 - 3) {
        int p = grid[y*20 + x] * grid[(y+1)*20 + x] * grid[(y+2)*20 + x] * grid[(y+3)*20 + x];
        if (p > max) {
          max = p;
        }
      }
      // diag down left
      if (x < 20 - 3 && y < 20 - 3) {
        int p = grid[y*20 + x] * grid[(y+1)*20 + x + 1] * grid[(y+2)*20 + x + 2] * grid[(y+3)*20 + x + 3];
        if (p > max) {
          max = p;
        }
      }
      // diag up left
      if (x < 20 - 3 && y > 2) {
        int p = grid[y*20 + x] * grid[(y-1)*20 + x + 1] * grid[(y-2)*20 + x + 2] * grid[(y-3)*20 + x + 3];
        if (p > max) {
          max = p;
        }
      }
    }
  }

  std::cout << max << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
