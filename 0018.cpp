#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

int main() {
  std::vector<std::vector<int>> triangle;

  // read in the triangle
  std::fstream in("0018.txt");
  for (int j = 1; ;j++) {
    std::vector<int> x;
    for (int i = 0; i < j; i++) {
      int n;
      in >> n;
      if (in.eof()) break;
      x.push_back(n);
      std::cout << n << " ";
    }
    if (in.eof()) break;
    std::cout << std::endl;
    triangle.push_back(x);
  }

  std::vector<int> sum = triangle.back();
  triangle.pop_back();

  for (auto x = triangle.rbegin(); x != triangle.rend(); x++) {
    std::vector<int> tmp;
    for (int i = 0; i < (*x).size(); i++) {
      int m = std::max((*x)[i] + sum[i], (*x)[i] + sum[i+1]);
      std::cout << m << " ";
      tmp.push_back(m);
    }
    std::cout << std::endl;
    sum = std::move(tmp);
  }
  std::cout << sum[0] << std::endl;
}
