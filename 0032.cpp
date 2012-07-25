#include <iostream>
#include <vector>
#include <functional>
#include <set>

void fn0() {
  std::set<int> products;
  for (int i0 = 0; i0 < 10; i0++) {
    for (int i1 = 0; i1 < 10 ; i1++) {
      if ((i0 != 0 && i1 == 0) || (i1 != 0 && i0 == i1)) {
        continue;
      }
      for (int i2 = 0; i2 < 10 ; i2++) {
        if (((i0 != 0 || i1 != 0) && i2 == 0) || (i2 != 0 && (i0 == i2 || i1 == i2))) {
          continue;
        }
        for (int i3 = 0; i3 < 10 ; i3++) {
          if (i3 == 0 || i0 == i3 || i1 == i3 || i2 == i3) {
            continue;
          }          
          int i = (i0 * 1000) + (i1 * 100) + (i2 * 10) + i3;
          //std::cout << "i: " << i << std::endl;
          for (int j0 = i0; j0 < 10; j0++) {
            if (j0 != 0 && (j0 == i0 || j0 == i1 || j0 == i2 || j0 == i3)) {
              continue;
            }
            for (int j1 = (j0 == i0 ? i1 : 0); j1 < 10 ; j1++) {
              if ((j0 != 0 && j1 == 0) || (j1 != 0 && (j1 == i0 || j1 == i1 || j1 == i2 || j1 == i3 || j0 == j1))) {
                continue;
              }
              for (int j2 = (j1 == i1 ? j2 : 0); j2 < 10 ; j2++) {
                if (((j0 != 0 || j1 != 0) && j2 == 0) || 
                    (j2 != 0 && (j2 == i0 || j2 == i1 || j2 == i2 || j2 == i3 || j0 == j2 || j1 == j2))) {
                  continue;
                }
                for (int j3 = (j2 == i2 ? i3 : 1); j3 < 10 ; j3++) {
                  if (j3 == 0 || j3 == i0 || j3 == i1 || j3 == i2 || j3 == i3 || j0 == j3 || j1 == j3 || j2 == j3) {
                    continue;
                  }
                  int j = (j0 * 1000) + (j1 * 100) + (j2 * 10) + j3;
                  //std::cout << "j: " << j << std::endl;
                  int s = i * j;
                  //std::cout << i << " x " << j << " = " << s << std::endl;
                  int sx = s;
                  int s3 = s % 10;
                  sx /= 10;
                  int s2 = sx % 10;
                  sx /= 10;
                  int s1 = sx % 10;
                  sx /= 10;
                  int s0 = sx % 10;
                  sx /= 10;
                  if (sx > 0) {
                    continue;
                  }
                  if (s0 != 0 && (s0 == i0 || s0 == i1 || s0 == i2 || s0 == i3 || 
                                  s0 == j0 || s0 == j1 || s0 == j2 || s0 == j3 )) {
                    continue;
                  }
                  if ((s0 != 0 && s1 == 0) || ((s1 != 0) &&
                                               (s1 == i0 || s1 == i1 || s1 == i2 || s1 == i3 || 
                                                s1 == j0 || s1 == j1 || s1 == j2 || s1 == j3 ||
                                                s1 == s0 ))) {
                    continue;
                  }
                  if (((s0 != 0 || s1 != 0) && s2 == 0) || ((s2 != 0) &&
                                                            (s2 == i0 || s2 == i1 || s2 == i2 || s2 == i3 || 
                                                             s2 == j0 || s2 == j1 || s2 == j2 || s2 == j3 ||
                                                             s2 == s1 || s2 == s0 ))) {
                    continue;
                  }
                  if (s3 == 0 || s3 == i0 || s3 == i1 || s3 == i2 || s3 == i3 || 
                      s3 == j0 || s3 == j1 || s3 == j2 || s3 == j3 ||
                      s3 == s1 || s3 == s0 || s3 == s2) {
                    continue;
                  }

                  // count the non-zeros
                  int nums = 0;
                  if (i0 != 0) {
                    nums += 4;
                  } else if (i1 != 0) {
                    nums += 3;
                  } else if (i2 != 0) {
                    nums += 2;
                  } else {
                    nums += 1;
                  }
                  if (j0 != 0) {
                    nums += 4;
                  } else if (j1 != 0) {
                    nums += 3;
                  } else if (j2 != 0) {
                    nums += 2;
                  } else {
                    nums += 1;
                  }
                  if (s0 != 0) {
                    nums += 4;
                  } else if (s1 != 0) {
                    nums += 3;
                  } else if (s2 != 0) {
                    nums += 2;
                  } else {
                    nums += 1;
                  }
                  if (nums == 9) {
                    std::cout << i << " x " << j << " = " << s << std::endl;
                    products.insert(s);
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  int s = 0;
  for (int i: products) {
    s += i;
  }
  std::cout << s << std::endl;
}

std::vector<std::function<void()>> progs = { fn0 };
