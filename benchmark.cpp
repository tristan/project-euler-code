#include <cstdlib>
#include <vector>
#include <iostream>
#include <functional>
#include <sys/time.h>

extern std::vector<std::function<void()>> progs;

int main() {
  for (auto p: progs) {
    timeval timer[2];
    gettimeofday(&timer[0], NULL);
    p();
    gettimeofday(&timer[1], NULL);
    int secs(timer[1].tv_sec - timer[0].tv_sec);
    int usecs(timer[1].tv_usec - timer[0].tv_usec);

    if(usecs < 0) {
      --secs;
      usecs += 1000000;
    }
    
    std::cout << "runtime: " << static_cast<int>(secs * 1000 + usecs / 1000.0 + 0.5) << std::endl;
  }
}
