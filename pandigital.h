#include <vector>

class pandigital {
  std::vector<int> arr;
 public:
  pandigital(int start, int end) {
    for (int i = start; i <= end; i++) {
      arr.push_back(i);
    }
  }

  void increase() {
    int j = arr.size();
    int i = j - 1;
    while (arr[i-1] >= arr[i]) {
      i = i - 1;
    }
    
    while (arr[j-1] <= arr[i-1]) {
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

  long int join(int start, int end) {
    long int s = 0;
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

  int operator[](int x) {
    return arr[x];
  }

};
