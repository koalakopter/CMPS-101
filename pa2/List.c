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

// defines the Node which holds all data
typedef struct NodeItem {
  struct NodeItem *nextItem;
  struct NodeItem *prevItem;
  int data;
} NodeItem;

// defines the List which holds all the Nodes
struct List {
  NodeItem *head;   // defines the head of the list
  NodeItem *tail;   // defines the tail of the list
  NodeItem *cursor; // defines the cursor object
};

// makes a new list
List *newList(void) {
  List *new_List = malloc(sizeof(List));
  // set head, tail, and cursor all to null
  new_List->head = NULL;
  new_List->tail = NULL;
  new_List->cursor = NULL;
  return new_List;
}

// MOST FUNCTIONS TAKE IN THE LIST AS AN ARGUMENT FOR THE FUNCTION

// returns the length of the list, returns zero if empty
int length(List *L) {
  NodeItem *pointer = L->head;
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
int index(List *L) {
  int cursorTracker = -1;
  // if cursor undefined, return -1
  NodeItem *cursorRadar = L->cursor;
  if (cursorRadar == NULL) {
    return cursorTracker;
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
