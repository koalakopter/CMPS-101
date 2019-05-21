// test file for Graph.c
#include "Graph.h"
#include "List.h"

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  // make some grafs
  Graph Zeppelin = newGraph(5);
  Graph Spee = newGraph(4);
  Graph Deutschland = newGraph(4);
  Graph Scheer = newGraph(7);

  printf("beginning tests\n");
  // change this for different tests
  int x = 1;
  switch (x) {
  case 1: {
    // case 0
    int fun = getOrder(Zeppelin);
    int fun2 = getSize(Zeppelin);
    printf("get order is: %d\n", fun);
    printf("get size is %d\n", fun);
    break;
  }
  default: {
    // pls no
    printf("bruh why\n");
    break;
  }
    printf("jobs done\n");
  }
}
