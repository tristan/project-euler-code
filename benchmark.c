#include <stdlib.h>
#include <stdio.h>
#include <sys/time.h>

extern void (*solutions[])();

void main() {
  printf("==== C ====\n");
  for (int i = 0; solutions[i] != NULL; i++) {
    struct timeval timer[2];
    gettimeofday(&timer[0], NULL);
    solutions[i]();
    gettimeofday(&timer[1], NULL);
    int secs = (timer[1].tv_sec - timer[0].tv_sec);
    int usecs = (timer[1].tv_usec - timer[0].tv_usec);

    if(usecs < 0) {
      --secs;
      usecs += 1000000;
    }
    
    printf("runtime: %d ms\n", (secs * 1000 + usecs / 1000.0 + 0.5));
  }
}
