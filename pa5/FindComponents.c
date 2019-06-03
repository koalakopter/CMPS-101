/*
JULIAN TO (jcto)
pa5: CMPS 101
Professor Tantalo
Spring 2019
*/

#include "Graph.h"
#include "List.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARBITRARY_LENGTH 1000

// the MAIN attraction
int main(int argc, char *argv[]) {
  // read in a file
  FILE *in, *out;
  char line[ARBITRARY_LENGTH];
  // char tokenlist[MAX_LEN];
  // char *token;

  // check command line for correct number of arguments
  if (argc != 3) {
    printf("Usage: %s <input file> <output file>\n", argv[0]);
    exit(1);
  }

  // open files for reading and writing
  in = fopen(argv[1], "r");
  out = fopen(argv[2], "w");
  if (in == NULL) {
    printf("Unable to open file %s for reading\n", argv[1]);
    exit(1);
  }
  if (out == NULL) {
    printf("Unable to open file %s for writing\n", argv[2]);
    exit(1);
  }

  // again, first read in size of graph
  // first line is the size of the Graph
  int size = 0;
  fgets(line, ARBITRARY_LENGTH, in);
  size = atoi(line);
  // make new Graph
  Graph G = newGraph(size);
  // add arcs, copied from pa4
  // then construct the edges
  char *token;
  int num1, num2;
  while (fgets(line, ARBITRARY_LENGTH, in) != NULL) {
    // add adjacency relations from file
    // split strings
    token = strtok(line, " ");
    num1 = atoi(token);
    token = strtok(NULL, " ");
    num2 = atoi(token);
    // do this to ignore the dummy line at the end
    if (num1 == 0 && num2 == 0) {
      break;
    }
    // printf("%d and %d\n", num1, num2);
    // add edges (order doesnt matter since its not directed)
    addArc(G, num1, num2);
  }
  // print the graph!
  fprintf(out, "Adjacency list representation of G:\n");
  printGraph(out, G);
  // close the input file, not necessary anymore
  fclose(in);

  // PART 2: FIND STRONGLY CONNECTED COMPONENTS
  int components = 0;
  // construct transpose
  Graph Gt = transpose(G);
  // do DFS() on original graph G, in standard order
  //(by increasing vertices)
  List order = newList();
  for (int i = 1; length(order) < size; i++) {
    append(order, i);
  }
  // perform a DFS with List order as the guide
  DFS(G, order);
  // now order is sorted in terms of discovery time, greatest to least
  // do DFS again, but on the transpose and with the new list

  DFS(Gt, order);
  // now order consists of a forest of strongly connected components
  List redro = newList(); // Im out of creative variable names

  // to get number of strongly connected components, we count NIL parents in G^T
  moveFront(order);
  while (index(order) != -1) {
    if (getParent(Gt, get(order)) == NIL) {
      components++;
    }
    moveNext(order);
  }
  fprintf(out, "\nG contains %d strongly connected components:", components);

  // print the trees in the forest
  // to extract the forest, traverse list backwards until NIL parent is found
  moveBack(order);
  for (int x = 1; index(order) != -1; x) {
    prepend(redro, get(order));
    if (getParent(Gt, get(order)) == NIL) {
      fprintf(out, "\n");
      fprintf(out, "Component %d: ", x);
      printList(out, redro);
      clear(redro);
      x++;
    }
    movePrev(order);
  }
  fprintf(out, "\n"); // for formatting
  // free memory
  fclose(out);
  freeGraph(&G);
  freeGraph(&Gt);
  freeList(&order);
  freeList(&redro);
}
