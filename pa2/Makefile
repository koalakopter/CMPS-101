#------------------------------------------------------------------------------
# Makefile for CMPS 101 Programming Assignment 2
#
#       make                   makes Lex
#       make ListClient        makes ListClient
#------------------------------------------------------------------------------

Lex : Lex.o List.o
	gcc -o -g Lex Lex.o List.o

Lex.o : Lex.c List.h
	gcc -c -g -std=c99 -Wall Lex.c

ListClient: ListClient.o List.o
	gcc -o -g ListClient ListClient.o List.o

ListClient.o : ListClient.c List.h
	gcc -c -g -std=c99 -Wall ListClient.c
	
Test: Test.o List.o
	gcc -o -g Test Test.o List.o
	
Test.o : Test.c List.h
	gcc -c -g -std=c99 -Wall Test.c

List.o : List.c List.h
	gcc -c -g -std=c99 -Wall List.c

clean :
	rm -f Lex ListClient Test Lex.o ListClient.o List.o Test.o
 