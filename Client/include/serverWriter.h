//
// Created by komashg@wincs.cs.bgu.ac.il on 06/01/2022.
//

#include "connectionHandler.h"

#ifndef ASS3_NEW2_SERVERWRITER_H
#define ASS3_NEW2_SERVERWRITER_H

#endif //ASS3_NEW2_SERVERWRITER_H

#include <boost/algorithm/string.hpp>
#include <boost/asio.hpp>

class serverWriter{

public:
    std::string getDateAndTimeString();
    std::string processLine(std::string line);
    void run(ConnectionHandler *connectionHandler);
};