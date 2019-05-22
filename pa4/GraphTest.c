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
    // make some edges
    addEdge(Zeppelin, 1, 2);
    addEdge(Zeppelin, 2, 4);
    addEdge(Zeppelin, 3, 1);
    addEdge(Zeppelin, 4, 5);
    printGraph(stdout, Zeppelin);
    // make null tests
    printf("making the Graph Null now!\n");
    makeNull(Zeppelin);
    printGraph(stdout, Zeppelin);
    break;
  }
  // try to add bad edges/arcs
  case 2: {
    printf("trying to add an edge that isn't in the graph\n");
    addArc(Spee, 2, 3);
    addArc(Spee, 5, 7);
  }
  default: {
    // pls no
    printf("bruh why\n");
    break;
  }
    printf("jobs done\n");
  }
}
