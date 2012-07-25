#include <iostream>
#include <vector>
#include <functional>

void fn0() {
  int prodi = 1;
  int prodj = 1;
  for (int i0 = 1; i0 < 10; i0++) {
    for (int i1 = 1; i1 < 10; i1++) {
      for (int j0 = i0; j0 < 10; j0++) {
        for (int j1 = (j0 == i0 ? i1+1 : 1); j1 < 10; j1++) {
          int ci, cj;
          if (i0 != 0 && i1 != 0) {
            if (i0 == j0) {
              ci = i1;
              cj = j1;
            } else if (i0 == j1) {
              ci = i1;
              cj = j0;
            } else if (i1 == j0) {
              ci = i0;
              cj = j1;
            } else if (i1 == j1) {
              ci = i0;
              cj = j0;
            } else {
              // nothing to remove
              continue;
            }
            int i = i0 * 10 + i1;
            int j = j0 * 10 + j1;
            if (i * cj == j * ci) {
              std::cout << i << "/" << j << " == " << ci << "/" << cj << std::endl;
              prodi *= ci;
              prodj *= cj;
            }
          }
        }
      }
    }
  }
  int x = 1;
  while (prodi >= ++x) {
    if (prodi % x == 0 && prodj % x == 0) {
      prodi /= x;
      prodj /= x;
    }
  }
  std::cout << "result: " << prodi << "/" << prodj << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
