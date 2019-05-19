/*
JULIAN TO (jcto)
pa3: CMPS 101
Professor Tantalo
Spring 2019
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Graph.h"
#include "List.h"

#define INF -1
#define NIL 0

#define WHITE 0
#define GRAY 1
#define BLACK 2

// structs
typedef struct GraphObj {
  List *adjacent;
  int order;
  int size;
  int source;
  int *parent;
  int *distance;
  int *colour;
} GraphObj;

// constructor thingy
Graph newGraph(int n) {
  // intialization for Graph
  Graph G = malloc(sizeof(GraphObj));
  G->order = n;
  G->size = 0;
  G->source = NIL;
  G->parent = malloc(sizeof(int) * (n + 1));
  G->distance = malloc(sizeof(int) * (n + 1));
  G->colour = malloc(sizeof(int) * (n + 1));
  G->adjacent = malloc(sizeof(List) * (n + 1));

  // set parent,color,distance and adjacent to begginger setting
  for (int i = 0; i <= n; i++) {
    G->adjacent[i] = newList();
    G->parent[i] = NIL;
    G->distance[i] = INF;
    G->colour[i] = WHITE;
  }
  return G;
}

// free memory
void freeGraph(Graph *pG) {}

// returns the order
int getOrder(Graph G) {
  // check for valid graph
  if (G == NULL) {
    printf("function called on invalid Graph");
    exit(1);
  }
  return G->order;
}

// returns the size
int getSize(Graph G) {
  // check for valid graph
  if (G == NULL) {
    printf("function called on invalid Graph");
    exit(1);
  }
  return G->size;
}
