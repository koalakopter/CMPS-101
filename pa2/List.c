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

  //other data points
 // int index;
  //int length;

} ListObject;

// makes a new Nodes
NodeItem newNode(int data) {
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
  //index defaults to -1, length is
  //new_List->index = -1;
  //new_List->length = 0;
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
  }if(L == NULL){
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

// returns the data point at the front of the LIST
int data(List L) {
    int listLength = length(L);
  // if length is zero, throw error
  if (listLength == 0) {
    printf("\nERROR: LIST LENGTH IS ZERO\n");
    exit(1);
  }
  return L->head->data;
}
