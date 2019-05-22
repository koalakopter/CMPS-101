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

#define INF -2
#define NIL -1

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
  // Space Created
  G->parent = malloc(sizeof(int) * (n + 1));
  G->distance = malloc(sizeof(int) * (n + 1));
  G->colour = malloc(sizeof(int) * (n + 1));
  G->adjacent = malloc(sizeof(List) * (n + 1));

  // set all parameters  to default setting
  for (int i = 0; i <= n; i++) {
    G->adjacent[i] = newList();
    G->parent[i] = NIL;
    G->distance[i] = INF;
    G->colour[i] = WHITE;
  }
  return G;
}

// free memory
void freeGraph(Graph *pG) {
  // do stuff
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

// returns the most recently used vertex from BFS
int getSource(Graph G) {
  // check for valid graph
  if (G == NULL) {
    printf("function getSource() called on invalid Graph");
    exit(1);
  }
  return G->source;
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

// returns the distance from the most recent BFS source to vertex u
int getDist(Graph G, int u) {
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
  // also if BFS hasn't been called, return NIL
  if (getSource(G) == NIL) {
    return INF;
  }
  return G->distance[u];
}

// finds the shortest path from vertex G to source u
// or NIL if BFS hasn't been called before
// OR L if no path exists between the two vertices
void getPath(List L, Graph G, int u) {
  // error case 1: bad u value
  if (u < 1 || u > getOrder(G)) {
    printf("function getPath() called on invalid value of u");
    exit(1);
  }
  // error case 2: BFS hasn't been called yet
  if (getSource(G) == NIL) {
    printf("function getPath() error! BFS not called on source graph G");
    exit(1);
  }
  // path found
  if (u == G->source) {
    prepend(L, u);
  }
  // no path found
  else if (G->parent[u] == NIL) {
    append(L, u);
  } else {
    prepend(L, u);
    // recursive function, finds path until u is the source or no path is found
    getPath(L, G, G->parent[u]);
  }
}
/**MANIPULATION PROCEDURES**/
// makes the graph null (wow)
// deletes all edges
void makeNull(Graph G) {
  // placeholder
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
// BREADTH-FIRST-SEARCH
// follow algorithim from GraphTheory handout
void BFS(Graph G, int s) {
  // set up algorithim
  int u;
  int v;
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
    printList(out, G->adjacent[i]);
    fprintf(out, "\n");
  }
}
