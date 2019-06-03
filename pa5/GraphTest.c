// test file for Graph.c
#include "Graph.h"
#include "List.h"

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  // make some grafs
  Graph Zeppelin = newGraph(5);
  Graph Spee = newGraph(5);
  Graph Deutschland = newGraph(4);
  Graph Scheer = newGraph(5);

  printf("beginning tests\n");
  // change this for different tests
  int x = 1;
  switch (x) {
    // test some transpose stuff
  case 1: {
    addArc(Zeppelin, 1, 2);
    addArc(Zeppelin, 2, 3);
    addArc(Zeppelin, 3, 4);
    addArc(Zeppelin, 4, 5);
    addArc(Zeppelin, 5, 1);
    printGraph(stdout, Zeppelin);
    fprintf(stdout, "transposing graph!\n");
    Scheer = transpose(Zeppelin);
    printGraph(stdout, Scheer);

    break;
  }

  // no pls
  default: {
    fprintf(stdout, "y u do dis");
    break;
  }
  }
}
