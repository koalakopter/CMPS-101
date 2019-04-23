/*
JULIAN TO (jcto)
pa2: CMPS 101
Professor Tantalo
Spring 2019
*/
// Based upon the provided FILE.IO
// and logic in Lex.java

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "List.h"

#define MAX_LEN 160

int main(int argc, char *argv[]) {

  int n, count = 0;
  FILE *in, *out;
  char line[MAX_LEN];
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

  // first count num of lines in the files
  while (fgets(line, MAX_LEN, in) != NULL) {
    count++;
  }
  // holds all of the lines of file
  // 2d array: 1st entry is line num, 2nd is length of word
  char Word[count][MAX_LEN];

  // go back to the top of the FILE
  rewind(in);
  // copies inputs of file into Word array
  while (fgets(line, MAX_LEN, in) != NULL) {
    strcpy(Word[n], line);
    n++;
  }
  fclose(in);

  // make a new LIST
  List listing = newList();
  // list starts at zero, obviously
  append(listing, 0);

  // same logic as Lex.java really
  int i = 0;
  for (i = 1; i < count; i++) {
    moveFront(listing);

    while (index(listing) >= 0) {
      char *temp = Word[i];

      // move back until you reach a word lexicographically less
      if (strncmp(Word[get(listing)], temp, MAX_LEN) > 0) {
        insertBefore(listing, i);
        break;
      }
      moveNext(listing);
    }
    // if you reach the front of the list, stop there
    if (index(listing) < 0) {
      append(listing, i);
    }
  }

  moveFront(listing);
  // print the list
  // for testing
  int track = 0;
  printf("value of count is %d\n", count);
  for (track = 0; track < count; track++) {
    printf("%s on itration: %d\n", Word[get(listing)], track);
    fprintf(out, "%s", Word[get(listing)]);
    moveNext(listing);
    // track = track + 1;
  }

  printf("leave the loop?");
  /* close files */
  fclose(in);
  fclose(out);
  // free list
  freeList(&listing);
  printf("free the list?");

  return (0);
}
