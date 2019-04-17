/*
JULIAN TO (jcto)
pa2: CMPS 101
Professor Tantalo
Spring 2019
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "List.h"
// STRUCTS

// defines the Node which holds all data
typedef struct NodeItem {
  struct NodeItem *nextItem;
  struct NodeItem *prevItem;
  int data;
} NodeItem;

// save some typing
typedef NodeItem *Node;

// defines the List which holds all the Nodes
typedef struct ListObject {
  Node head;   // defines the head of the list
  Node tail;   // defines the tail of the list
  Node cursor; // defines the cursor object

  // other data points
  // int index;
  // int length;

} ListObject;

// makes a new Nodes
Node newNode(int data) {
  NodeItem *x = malloc(sizeof(NodeItem));
  x->data = data;
  x->nextItem = NULL;
  x->prevItem = NULL;
}

// frees a Node from memory
void freeNode(Node *n) {
  if (*n != NULL && n != NULL) {
    free(*n);
    *n = NULL;
  }
}

// makes a new list, returning the LIst
List newList(void) {
  List L;
  L = malloc(sizeof(ListObject));
  // set head, tail, and cursor all to null
  L->head = NULL;
  L->tail = NULL;
  L->cursor = NULL;
  // index defaults to -1, length is
  // new_List->index = -1;
  // new_List->length = 0;
  return L;
}

// MOST FUNCTIONS TAKE IN THE LIST AS AN ARGUMENT FOR THE FUNCTION

// returns the length of the list, returns zero if empty
int length(List L) {
  Node pointer = L->head;
  int length = 0;
  // if head is null, list is probably empty (barring weird circumstance)
  if (pointer == NULL) {
    return length;
  }
  length++;
  while (pointer->nextItem != NULL) {
    pointer = pointer->nextItem;
    length++;
  }
  return length;
}

// returns the index (position) of the cursor element
int index(List L) {
  int cursorTracker = -1;
  // if cursor undefined, return -1
  Node cursorRadar = NULL;
  cursorRadar = L->head;
  if (cursorRadar == NULL) {
    return cursorTracker;
  }
  if (L == NULL) {
    printf("\nERROR: Calling function on undefined List\n");
    exit(1);
  }

  // otherwise, traverse the list until cursor is located
  NodeItem *pointer = L->head;
  while (pointer->nextItem != NULL) {
    if (pointer == cursorRadar) {
      break;
    }
    pointer = pointer->nextItem;
    cursorTracker++;
  }
  cursorTracker++;
  return cursorTracker;
}

// like the above function, but returns the value of the cursor instead
int get(List L) {
  // for undefined list
  if (L == NULL) {
    printf("\nERROR: Calling function on undefined List\n");
    exit(1);
  }
  // for lists with no cursor
  if (L->cursor == NULL) {
    printf("\nERROR: cursor not defined");
    exit(1);
  }
  NodeItem *pointer = L->head;
  while (pointer->nextItem != NULL) {
    if (pointer == L->cursor) {
      break;
    }
    pointer = pointer->nextItem;
  }
  return pointer->data;
}

// returns the data point at the front of the LIST
int front(List L) {
  int listLength = length(L);
  // if length is zero, throw error
  if (listLength == 0) {
    printf("\nERROR: LIST LENGTH IS ZERO\n");
    exit(1);
  }
  // if list is not defined, throw error as well
  if (L == NULL) {
    printf("\nERROR: LIST UNDEFINED\n");
    exit(1);
  }
  return L->head->data;
}

// returns the data point at the end of the LIST
int back(List L) {
  int listLength = length(L);
  // if length is zero, throw error
  if (listLength == 0) {
    printf("\nERROR: LIST LENGTH IS ZERO\n");
    exit(1);
  }
  // if list is not defined, throw error as well
  if (L == NULL) {
    printf("\nERROR: LIST UNDEFINED\n");
    exit(1);
  }
  return L->tail->data;
}

// check if two lists are equal/the same
int equals(List A, List B) {
  // idiot-proofing
  if (A == NULL || B == NULL) {
    return 0;
  }
  // traverse-rs
  Node start = A->head;
  Node start2 = B->head;
  // two lists of different lengths can't be equal
  if (length(A) != length(B)) {
    return 0;
  }
  // traverse and check each nodeItem
  while (start->nextItem != NULL && start2->nextItem != NULL) {
    if (start->data != start2->data) {
      return 0;
    }
    start = start->nextItem;
    start2 = start2->nextItem;
  }
  // check the last item on each list
  if (start->data != start2->data) {
    return 0;
  }
  return 1;
}

// manipulation FUNCTIONS
void clear(List L) {
  L->head = NULL;
  L->tail = NULL;
  L->cursor = NULL;
}

// put cursor under front elements
void moveFront(List L) {
  if (length(L) <= 0) {
    return; // do nothing
  } else {
    L->cursor = L->head;
  }
}

// put cursor under front elements
void moveBack(List L) {
  if (length(L) <= 0) {
    return; // do nothing
  } else {
    L->cursor = L->tail;
  }
}

// move cursor one back from the current point
void movePrev(List L) {
  // if cursor at the front of the list, make cursor UNDEFINED
  if (L->cursor == L->head) {
    L->cursor = NULL;
  }
  // if cursor is undefined, do nothing
  if (L->cursor == NULL) {
    return;
  } else {
    L->cursor = L->cursor->prevItem;
  }
}

// move cursor one forward from the current point
void moveNext(List L) {
  // if cursor at the front of the list, make cursor UNDEFINED
  if (L->cursor == L->head) {
    L->cursor = NULL;
  }
  // if cursor is undefined, do nothing
  if (L->cursor == NULL) {
    return;
  } else {
    L->cursor = L->cursor->nextItem;
  }
}

// adds a node to the front of the list
void prepend(List L, int data) {
  // make a new Node
  Node freshNode = newNode(data);
  // if list is empty, make this the first node
  if (length(L) == 0) {
    L->head = freshNode;
    L->tail = freshNode;
  }
  // otherwise make this the new head
  else {
    freshNode->nextItem = L->head;
    L->head->prevItem = freshNode;
    L->head = freshNode;
  }
}

// adds a node to the end of the list
void append(List L, int data) {
  // make a new Node
  Node freshNode = newNode(data);
  // if list is empty, make this the first node
  if (length(L) == 0) {
    L->head = freshNode;
    L->tail = freshNode;
  }
  // otherwise make this the new tail
  else {
    freshNode->prevItem = L->tail;
    L->tail->nextItem = freshNode;
    L->tail = freshNode;
  }
}
