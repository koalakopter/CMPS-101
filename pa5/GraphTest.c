// test file for Graph.c
#include "Graph.h"
#include "List.h"

#include <setjmp.h>
#include <signal.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]) {
  // make some grafs
  Graph Zeppelin = newGraph(5);
  Graph Spee = newGraph(5);
  Graph Deutschland = newGraph(4);
  Graph Scheer = newGraph(5);

  printf("beginning tests\n");
  // change this for different tests
  int x = 2;
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
  case 2: {
    //  uint8_t runTest(Graph * pA, List * pL, int test) {
    Graph A = newGraph(100);
    List L = newList();
    printf("start tests\n");
    for (int i = 1; i <= 100; i++) {
      if (getDiscover(A, i) != UNDEF) {
        printf("fail 1");
        exit(1);
      }
    }
    addArc(A, 64, 4);
    addArc(A, 64, 3);
    addArc(A, 42, 2);
    addArc(A, 2, 64);
    addArc(A, 4, 2);
    addArc(A, 3, 42);
    for (uint8_t i = 1; i <= 100; i++) {
      prepend(L, i);
    }
    fprintf(stdout, "initial list:");
    printList(stdout, L);
    printf("\n");
    DFS(A, L);
    if (getDiscover(A, 100) != 1) {
      printf("fail 2");
      exit(1);
    }
    if (getDiscover(A, 64) != 73) {
      printf("fail 3");
      exit(1);
    }
    if (getDiscover(A, 4) != 80) {
      printf("fail 4");
      exit(1);
    }
    printList(stdout, L);
    DFS(A, L);
    fprintf(stdout, "second list is:");
    printList(stdout, L);
    if (getDiscover(A, 4) != 126) {
      printf("fail 5");
      exit(1);
    }
    if (getDiscover(A, 63) != 117) {
      printf("fail 6");
      exit(1);
    }
    fprintf(stdout, "%d size of List, %d size of Graph", length(L),
            getOrder(A));
    // printList(stdout, L);
    DFS(A, L);
    if (getDiscover(A, 65) != 71) {
      printf("fail 7");
      exit(1);
    }
    if (getDiscover(A, 1) != 199) {
      printf("fail 8");
      exit(1);
    }
    printf("pass all");
    break;
  }

  // no pls
  default: {
    fprintf(stdout, "y u do dis");
    break;
  }
  }
}
