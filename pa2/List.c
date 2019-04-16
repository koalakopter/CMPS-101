/*
JULIAN TO (jcto)
pa2: CMPS 101
Professor Tantalo
Spring 2019
*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "List.h"

//defines the Node which holds all data
typedef struct NodeItem {
    struct NodeItem *nextItem;
    struct NodeItem *prevItem;
    int data;
} NodeItem;

//defines the List which holds all the Nodes
struct List {
    NodeItem *head; //defines the head of the list
    NodeItem *tail; //defines the tail of the list
    NodeItem *cursor; //defines the cursor object
};

//makes a new list
List *newList(void)
{
    List *new_List = malloc(sizeof(List));
    //set head, tail, and cursor all to null
    new_List->head = NULL;
    new_List->tail = NULL;
    new_List->cursor = NULL;
    return new_List;
}
