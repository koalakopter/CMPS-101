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

  // first line is the size of the Graph
  int size = 0;
  fgets(line, ARBITRARY_LENGTH, in);
  size = atoi(line);
  // make new Graph
  Graph G = newGraph(size);
  // then construct the edges
  char *token;
  int num1, num2;
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
    // printf("%d and %d\n", num1, num2);
    // add edges (order doesnt matter since its not directed)
    addEdge(G, num1, num2);
  }
  // print the graph
  printGraph(out, G);
  // fprintf(out, "\n"); // formatting

  int distance;
  // used to store a found path
  List path = newList();
  // next, find shortest path between two points from BFS
  while (fgets(line, ARBITRARY_LENGTH, in) != NULL) {
    // tokenize
    token = strtok(line, " ");
    num1 = atoi(token);
    token = strtok(NULL, " ");
    num2 = atoi(token);
    // do this to exit loop if last dummy line is reached
    if (num1 == 0 && num2 == 0) {
      break;
    } else {
      fprintf(out, "\n"); // more formatting
    }
    // printf("loop! %d and %d\n", num1, num2);
    // perform BFS from vertex s
    BFS(G, num1);
    // otherwise find the path
    // if path doesn't exist
    distance = getDist(G, num2);
    // printf("distance is: %d\n", distance);
    if (distance < 0) {
      fprintf(out, "The distance from %d to %d is infinity\n", num1, num2);
      fprintf(out, "No %d-%d path exists\n", num1, num2);
      // printf("The distance from %d to %d is infinity\n", num1, num2);
      // printf("No %d-%d path exists\n", num1, num2);
    }
    // otherwise, a path does exist
    else {
      getPath(path, G, num2);
      fprintf(out, "The distance from %d to %d is %d\n", num1, num2, distance);
      fprintf(out, "A shortest %d-%d path is: ", num1, num2);
      printList(out, path);
      // For printing to console
      /*
      printf("The distance from %d to %d is %d\n", num1, num2, distance);
      printf("A shortest %d-%d path is: ", num1, num2);
      printList(stdout, path);
      */
      fprintf(out, "\n");
      // make G null so we can find a new path on the next loop
      clear(path);
    }
  }

  // close the files
  fclose(in);
  fclose(out);

  // done with everything, free all the memory
  freeList(&path);
  freeGraph(&G);
}
