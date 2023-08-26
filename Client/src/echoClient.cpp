#include <stdlib.h>
#include "../include/connectionHandler.h"
#include "thread"
#include "../include/serverWriter.h"

/**
* This code assumes that the server repllsies the exact text the client sent it (as opposed to the practical session example)
*/
int main(int argc, char *argv[]) {
//    if (argc < 3) {
//        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
//        return -1;
//    }
//    std::string host = argv[1];
//    short port = atoi(argv[2]);

    short port = 7777;
    std::string host = "127.0.0.1";
    ConnectionHandler *connectionHandler;
    connectionHandler=new ConnectionHandler(host, port);
//    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler->connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }

    serverWriter *task = new serverWriter();
    std::thread writer(&serverWriter::run, task, connectionHandler);
    while (1) {
        std::string answer;
        // Get back an answer: by using the expected number of bytes (len bytes + newline delimiter)
        // We could also use: connectionHandler.getline(answer) and then get the answer without the newline char at the end
        if (!connectionHandler->getLine(answer)) {
            std::cout << "Disconnected. Exiting...\n" << std::endl;
            break;
        }
        if (answer[0] == '\n')
            answer = answer.substr(1, answer.length() - 1);
        std::string receivedOpcode = answer.substr(0, 2);

        //ACK
        if (receivedOpcode == "10") {
            std::string sentOpcode = answer.substr(2, 2);
            if (sentOpcode == "01") { // ACK for register

                std::cout << "ACK 1" << std::endl;
            }
            if (sentOpcode == "02") { // ACK for login
                std::cout << "ACK 2" << std::endl;
            }
            if (sentOpcode == "03") { // ACK for logout

                std::cout << "ACK 3" << std::endl;
                writer.detach(); //Check it
                break;
            }
            if (sentOpcode == "04") { // ACK for follow/unfollow
                std::string followcode = answer.substr(4, 1);
                std::string otherusername = answer.substr(5, answer.length() - 6);
                std::cout << "ACK 4 " + followcode + " " + otherusername << std::endl;
            }
            if (sentOpcode == "05") { // ACK for post
                std::cout << "ACK 5" << std::endl;
            }
            if (sentOpcode == "06") { // ACK for pm
                std::cout << "ACK 6" << std::endl;
            }
            if (sentOpcode == "07") { // ACK for logstat
                answer = answer.substr(4, answer.length() - 5);
                std::vector<std::string> parameters;
                boost::split(parameters, answer, boost::is_any_of(" "));
                std::string age = parameters.at(0);
                std::string numPost = parameters.at(1);
                std::string numFollowers = parameters.at(2);
                std::string numFollowing = parameters.at(3);
                std::cout << "ACK 7 age: " + age + " " + " numPost: " + numPost + " " + "numFollowers: " + numFollowers +
                           " " + "numFollowing: " + numFollowing<<std::endl;
            }
            if (sentOpcode == "08") { // ACK for stat
                answer = answer.substr(4, answer.length() - 5);
                std::vector<std::string> parameters;
                boost::split(parameters, answer, boost::is_any_of(" "));
                std::string age = parameters.at(0);
                std::string numPost = parameters.at(1);
                std::string numFollowers = parameters.at(2);
                std::string numFollowing = parameters.at(3);
                std::cout << "ACK 8 age: " + age + " " + " numPost: " + numPost + " " + "numFollowers: " + numFollowers +
                           " " + "numFollowing: " + numFollowing<<std::endl;
            }
            if (sentOpcode == "12") {
                std::cout << "ACK 12" << std::endl;
            }
            //ERROR

        } else if (receivedOpcode == "11") {
            std::string sentOpcode = answer.substr(2, 2);
            std::cout << "ERROR " + sentOpcode << std::endl;
        } else {  //Notification?
            std::string notificationType = answer.substr(2, 1);
            if (notificationType == "1") {
                std::cout << "NOTIFICATION Public " + answer.substr(3, answer.length() - 4) << std::endl;
            } else {
                std::string unsplitted = answer.substr(3, answer.length() - 4);
                std::string content = "";
                std::string dateAndTime = "";
                bool next = false;
                for (unsigned int i = 0; i < unsplitted.length(); i++) {
                    if (unsplitted.at(i) == '\0') {
                        next = true;
                        i++;
                    }
                    if (!next)
                        content = content + unsplitted.at(i);
                    if (next)
                        dateAndTime = dateAndTime + unsplitted.at(i);
                }
                std::cout << "NOTIFICATION PM " + content + " " + dateAndTime << std::endl;
            }
        }
//        int len = answer.length();
//        // A C string must end with a 0 char delimiter.  When we filled the answer buffer from the socket
//        // we filled up to the \n char - we must make sure now that a 0 char is also present. So we truncate last character.
//        answer.resize(len - 1);
//        std::cout << "Reply: " << answer << " " << len << " bytes " << std::endl << std::endl;
//        if (answer == "bye") {
//            std::cout << "Exiting...\n" << std::endl;
//            break;

        //From here we will see the rest of the ehco client implementation:

        // We can use one of three options to read data from the server:
        // 1. Read a fixed number of characters
        // 2. Read a line (up to the newline character using the getline() buffered reader
        // 3. Read up to the null character

    }
    delete task;
    delete connectionHandler;
    task= nullptr;
    connectionHandler= nullptr;
//    delete connectionHandler;
    return 0;
}