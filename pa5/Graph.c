/*
JULIAN TO (jcto)
pa4: CMPS 101
Professor Tantalo
Spring 2019
*/
#include "Graph.h"
#include "List.h"

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define INF -2
#define NIL -1

#define WHITE 0
#define GREY 1
#define BLACK 2

// struct
typedef struct GraphObj {
  List *adjacent;
  int order;
  int size;
  int *parent;
  int *discover;
  int *finish;
  int *colour;
} GraphObj;

// constructor thingy
Graph newGraph(int n) {
  // intialization for Graph
  Graph G = malloc(sizeof(GraphObj));
  G->order = n;
  G->size = 0;
  // Space Created
  G->parent = malloc(sizeof(int) * (n + 1));
  G->discover = malloc(sizeof(int) * (n + 1));
  G->colour = malloc(sizeof(int) * (n + 1));
  G->finish = malloc(sizeof(int) * (n + 1));
  G->adjacent = malloc(sizeof(List) * (n + 1));

  // set all parameters  to default setting
  for (int i = 0; i <= n; i++) {
    G->adjacent[i] = newList();
    G->parent[i] = NIL;
    G->colour[i] = WHITE;
  }
  return G;
}

// free memory
void freeGraph(Graph *pG) {
  makeNull(*pG);
  // first free all the lists
  for (int x = 0; x < getOrder(*pG); x++) {
    freeList(&(*pG)->adjacent[x]);
  }
  free((*pG)->parent);
  free((*pG)->adjacent);
  free((*pG)->colour);
  free((*pG)->finish);
  free(*pG);
  pG = NULL;
}

// returns the order (or number of vertices)
int getOrder(Graph G) {
  // check for valid graph
  if (G == NULL) {
    printf("function getOrder() called on invalid Graph");
    exit(1);
  }
  return G->order;
}

// returns the size (number of edges)
int getSize(Graph G) {
  // check for valid graph
  if (G == NULL) {
    printf("function getSize() called on invalid Graph");
    exit(1);
  }
  return G->size;
}

// returns the parent of vertex number 'u' most recently used in BFS
int getParent(Graph G, int u) {
  // check for valid graph
  if (G == NULL) {
    printf("function getParent() called on invalid Graph");
    exit(1);
  }
  // check for a valid "u"
  // parent can't be less than one or bigger than the number of vertices
  if (u < 1 || u > getOrder(G)) {
    printf("function getParent() called on invalid value of u");
    exit(1);
  }
  // otherwise just return the parent field with no issue
  return G->parent[u];
}

// returns the discover time of a certain vertex, u
int getDiscover(Graph G, int u) {
  // check for valid graph
  if (G == NULL) {
    printf("function getDiscover() called on invalid Graph");
    exit(1);
  }
  if (u < 1 || u > getOrder(G)) {
    printf("function getDiscover() called on invalid value of u");
    exit(1);
  }
  return G->discover[u];
}

// returns the finish time of a certain vertex, u
int getFinish(Graph G, int u) {
  // check for valid graph
  if (G == NULL) {
    printf("function getFinish() called on invalid Graph");
    exit(1);
  }
  if (u < 1 || u > getOrder(G)) {
    printf("function getFinish() called on invalid value of u");
    exit(1);
  }
  return G->finish[u];
}

/**MANIPULATION PROCEDURES**/
// makes the graph null (wow)
// deletes all edges and clears adjacency lists
void makeNull(Graph G) {
  // revert all edges back to default state
  for (int x = 1; x <= getOrder(G); x++) {
    // delete all adjacency lists
    while (length(G->adjacent[x]) > 0) {
      deleteBack(G->adjacent[x]);
    }
    G->colour[x] = WHITE;
    G->parent[x] = NIL;
  }
  G->size = 0;
}
// adds an edge to the graph
// adds v to adjacency list of u
// precondition: 1 < u && v < getOrder(u & v)
void addEdge(Graph G, int u, int v) {
  if (u >= 1 && u <= getOrder(G) && v >= 1 && v <= getOrder(G)) {
    moveFront(G->adjacent[u]);
    // traveres the list until an adjacent vertex is found
    // that is greater than v
    while (index(G->adjacent[u]) != -1) {
      // if a value is found that is greater than the one in the list,
      // exit loop early
      if (get(G->adjacent[u]) > v) {
        break;
      }
      moveNext(G->adjacent[u]);
    }
    // check the index
    if (index(G->adjacent[u]) != -1) {
      insertBefore(G->adjacent[u], v);
    }
    // cursor is off the list, put it on the end
    else {
      append(G->adjacent[u], v);
    }
    // PART 2
    // DO IT AGAIN BUT SWAP U AND V
    moveFront(G->adjacent[v]);
    while (index(G->adjacent[v]) != -1) {
      // if a value is found that is greater than the one in the list,
      // exit loop early
      if (get(G->adjacent[v]) > u) {
        break;
      }
      moveNext(G->adjacent[v]);
    }
    // check the index
    if (index(G->adjacent[v]) != -1) {
      insertBefore(G->adjacent[v], u);
    }
    // cursor is off the list, put it on the end
    else {
      append(G->adjacent[v], u);
    }
    G->size++;
  }
  // precondition not met
  else {
    printf("invalid value of u or v for function addEdge()");
    exit(1);
  }
}

// like addEdge, but the connection is one way
// i.e. a directed graph
void addArc(Graph G, int u, int v) {
  if (u >= 1 && u <= getOrder(G) && v >= 1 && v <= getOrder(G)) {
    // first move cursor to the front of the adjacency list of the vertex
    moveFront(G->adjacent[u]);
    // traveres the list until an adjacent vertex is found
    // that is greater than v
    while (index(G->adjacent[u]) != -1) {
      // if a value is found that is greater than the one in the list,
      // exit loop early
      if (get(G->adjacent[u]) > v) {
        break;
      }
      moveNext(G->adjacent[u]);
    }
    // check the index
    if (index(G->adjacent[u]) != -1) {
      insertBefore(G->adjacent[u], v);
    }
    // cursor is off the list, put it on the end
    else {
      append(G->adjacent[u], v);
    }
    G->size++;
  }
  // precondition not met
  else {
    printf("invalid value of u or v for function addArc()");
    exit(1);
  }
}

void DFS(Graph G, List S) {
  // do stuff
}

// prints the Graph
void printGraph(FILE *out, Graph G) {
  // error check
  if (out == NULL || G == NULL) {
    printf("ERROR: parameter out or Graph G is NULL in printGraph()");
    exit(1);
  }
  for (int i = 1; i <= getOrder(G); i++) {
    fprintf(out, "%d: ", i);
    // check for empty lists, if empty, print nothing
    if (length(G->adjacent[i]) <= 0) {
      fprintf(out, "\n");
      continue;
    }
    printList(out, G->adjacent[i]);
    fprintf(out, "\n");
  }
}
