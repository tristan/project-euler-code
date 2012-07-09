#include <iostream>
#include <array>
#include <functional>
#include <vector>
#include <thread>

void fn1() {

  std::function<int(int)> thirty = [](int year) {
    return 30;
  };
  std::function<int(int)> thirtyone = [](int year) {
    return 31;
  };
  std::function<int(int)> feb = [](int year) {
    return year % 4 == 0 ? 29 : 28;
  };
  std::array<std::function<int(int)>, 12> daysinmonths = { 
    thirtyone, feb, thirtyone, thirty, thirtyone, thirty, thirtyone, thirtyone, thirty, thirtyone, thirty, thirtyone
  };

  // 1 Jan 1901
  int weekday = 1; // 0 indexed
  int count = 0;
  for (int year = 1901; year < 2001; year++) {
    for (int month = 0; month < 12; month++) { // month is 0 indexed
      // an if statement here is probably more efficient than the function call, but playing with lambdas is fun :)
      weekday = (weekday + daysinmonths[month](year)) % 7;
      if (weekday == 6) { // if the first of the month is sunday
        //std::cout << month+1 << " " << year << std::endl;
        count++;
      }
    }
  }
  std::cout << count << std::endl;
}

std::vector<std::function<void()>> progs = { fn1 };
