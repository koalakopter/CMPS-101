/*
JULIAN TO (jcto)
pa3: CMPS 101
Professor Tantalo
Spring 2019
*/
#include "Graph.h"
#include "List.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ARBITRARY_LENGTH 40

// the MAIN attraction
int main(int argc, char *argv[]) {
  // read in a file
  int n, count = 0;
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

  // first line is the size of the Graph
  int size = 0;
  fscanf(in, "%d", &size);
  // make new Graph
  Graph G = newGraph(size);
  // then construct the edges
  char *token;
  int num1, num2;
  // skip the first line?
  fgets(line, ARBITRARY_LENGTH, in);
  // printf("now read in the inputs, size is %d:\n", size);
  while (fgets(line, ARBITRARY_LENGTH, in) != NULL) {
    // add adjacency relations from file
    // split strings
    token = strtok(line, " ");
    num1 = atoi(token);
    token = strtok(NULL, " ");
    num2 = atoi(token);
    // do this to move on from adding edges to performing BFS
    if (num1 == 0 && num2 == 0) {
      break;
    }
    printf("%d and %d\n", num1, num2);
    // add edges (order doesnt matter since its not directed)
    addEdge(G, num1, num2);
  }
  // print the graph
  printGraph(out, G);

  // close the files
  fclose(in);
  fclose(out);
}
