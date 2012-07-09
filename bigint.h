#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <iomanip>

class bigint {
public:
  bigint(std::string const &val) {
    // see what's left over after spliting this up into groups of 9 numbers
    int l = val.size() - PARTITION_SIZE;
    while (l >= 0) {
      num.push_back(std::stoi(val.substr(l, PARTITION_SIZE)));
      l -= PARTITION_SIZE;
    }
    if (PARTITION_SIZE + l > 0) {
      num.push_back(std::stoi(val.substr(0, PARTITION_SIZE + l)));
    }
    neg = false; // TODO: handle negatives
  }

  bigint operator+(bigint const &rhs) {
    int overflow = 0;
    std::vector<int> res;
    auto tn = num.begin();
    auto rn = rhs.num.begin();
    while (1) {
      long int r = overflow + (*tn) + (*rn);
      if (r >= LIMIT) {
        overflow = r / LIMIT;
        r -= (LIMIT * overflow);
      } else {
        overflow = 0;
      }
      res.push_back(r);
      tn++;
      rn++;
      if (tn == num.end() && rn != rhs.num.end()) {
        while (rn != rhs.num.end()) {
          res.push_back((*rn)+overflow);
          rn++;
          overflow = 0;
        }
        break;
      } else if (rn == rhs.num.end() && tn != num.end()) {
        while (tn != num.end()) {
          res.push_back((*tn)+overflow);
          tn++;
          overflow = 0;
        }
        break;
      } else if (tn == num.end() && rn == rhs.num.end()) {
        if (overflow > 0) {
          res.push_back(overflow);
        }
        break;
      }
    }
    return bigint(std::move(res), false);
  }

  // TODO: fix this, it's really broken!
  bigint operator*(int rhs) {
    int overflow = 0;
    std::vector<int> res;
    for (int lhs_n: num) {
      long int r = (overflow + lhs_n) * rhs;
      if (r >= LIMIT) {
        overflow = r / LIMIT;
        r -= (LIMIT * overflow);
      } else {
        overflow = 0;
      }
      res.push_back(r);
    }
    if (overflow > 0) {
      res.push_back(overflow);
    }
    return bigint(std::move(res), false);
  }

  void print() {
    std::for_each(num.rbegin(), num.rend(), [](int i) {
      std::cout << i;
      });
    std::cout << std::endl;
  }

  std::string to_string() {
    std::stringstream ss;
    ss << std::setfill('0');
    std::for_each(num.rbegin(), num.rend(), [&ss](int i) {
        ss << std::setw(9) << i;
      });
    std::string r = ss.str();
    int i = 0;
    while (r[i] == '0') i++;
    return r.substr(i);
  }

private:
  std::vector<int> num;
  bool neg;
  static const long int LIMIT = 1000000000;//2147483647;
  static const long int PARTITION_SIZE = 9; // the initial string partition size

  bigint(std::vector<int> &&num, bool neg) : num(num), neg(neg) { 
  };
};
