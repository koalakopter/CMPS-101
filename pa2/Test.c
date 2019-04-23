
#include <stdio.h>
#include <stdlib.h>

#include "List.h"

int main(int argc, char *argv[]) {
  // List L = newList();
  List L = newList();
  printf("dab dab dabs\n");
  List F = newList();
  List F = copyList(L);
  append(L, 5);
  printf("dab dab dab\n");
  append(L, 4);
  moveFront(L);
  printf("wheee %d", get(L));
  // printf("Head is: %s", L->cursor->data);
  printf("\ndone\n");
  // printList2(L);
}
