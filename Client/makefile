CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:= -lboost_system -lboost_thread -pthread

all: EchoClient
	g++ -o bin/BGSclient bin/connectionHandler.o bin/echoClient.o bin/serverWriter.o $(LDFLAGS)

EchoClient: bin/connectionHandler.o bin/echoClient.o bin/serverWriter.o
	
bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/echoClient.o: src/echoClient.cpp
	g++ $(CFLAGS) -o bin/echoClient.o src/echoClient.cpp

bin/serverWriter.o: src/serverWriter.cpp
	g++ $(CFLAGS) -o bin/serverWriter.o src/serverWriter.cpp

.PHONY: clean
clean:
	rm -f bin/*
