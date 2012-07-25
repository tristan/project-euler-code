#include <vector>
#include <array>

template <int smax>
class prime_sieve {
private:
  std::array<bool, smax> sieve;
  std::vector<int> primes{ 2 };
public:
  prime_sieve() {
     sieve.fill(true);
     sieve[0] = false;
     sieve[1] = false;
     int i = 2;
     while (i < smax - 1) {
       int j = i+i;
       while (j < smax - 1) {
         // mark all the multiples of i in the sieve as not-prime
         sieve[j] = false;
         j += i;
       }
       while (i < smax - 1) {
         // find the next value in the sieve that is still true (meaning it's prime)
         i += 1;
         if (sieve[i] == true) {
           break;
         }
       }
       if (i >= smax / 2) {
         // after we have checked sqrt(smax) numbers, we wont find any more
         // so everything true value left in the sieve will be a prime
         while (i < smax - 1) {
           if (sieve[i] == true) {
             primes.push_back(i);
           }
           i += 1;
         }
       } else {
         primes.push_back(i);
       }
     } 
  }

  std::array<bool, smax> getsieve() {
    return sieve;
  }

  std::vector<int> getprimes() {
    return primes;
  }
};
