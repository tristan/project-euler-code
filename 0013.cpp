#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include "bigint.h"

int main() {
  bigint sum("0");
  std::fstream in("0013.txt");
  std::string s;
  while (1) {
    in >> s;
    if (in.eof()) break;
    sum = sum + bigint(s);
  }
  sum.print();
  return 0;
}
