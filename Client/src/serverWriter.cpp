//
// Created by komashg@wincs.cs.bgu.ac.il on 06/01/2022.
//
#include "../include/connectionHandler.h"
#include "../include/serverWriter.h"
#include <boost/algorithm/string.hpp>

std::string serverWriter::getDateAndTimeString() {
    time_t now = time(0);
    tm *ltm = localtime(&now);
    int year = 1900 + ltm->tm_year;
    int month = 1 + ltm->tm_mon;
    int day = ltm->tm_mday;
    int hour = ltm->tm_hour;
    int minute = ltm->tm_min;
    std::string year_str = std::to_string(year);
    std::string month_str;
    std::string day_str;
    std::string hour_str;
    std::string minute_str;
    if (month < 10) {
        month_str = "0" + std::to_string(month);
    } else month_str = std::to_string(month);
    if (day < 10) {
        day_str = "0" + std::to_string(day);
    } else day_str = std::to_string(day);
    if (hour < 10) {
        hour_str = "0" + std::to_string(hour);
    } else hour_str = std::to_string(hour);
    if (minute < 10) {
        minute_str = "0" + std::to_string(minute);
    } else minute_str = std::to_string(minute);

    return day_str + "-" + month_str + "-" + year_str + " " + hour_str + ":" + minute_str;
}

std::string serverWriter::processLine(std::string line) {
    std::vector<std::string> parameters;
    boost::split(parameters, line, boost::is_any_of(" "));
    std::string command = parameters.at(0);
    if (command == "REGISTER") {
        std::string username = parameters.at(1);
        std::string password = parameters.at(2);
        std::string birthday = parameters.at(3);
        return "01" + username + ' ' + password + ' ' + birthday + ' ' + ";";
    } else if (command == "LOGIN") {
        std::string username = parameters.at(1);
        std::string password = parameters.at(2);
        std::string captcha = parameters.at(3);
        return "02" + username + ' ' + password + ' ' + captcha + ";";
    } else if (command == "LOGOUT") {
        return "03;";
    } else if (command == "FOLLOW" || command == "UNFOLLOW") {
        std::string follow = parameters.at(1);
        std::string otherusername = parameters.at(2);
        return "04" + follow + otherusername + ";";
    } else if (command == "POST") {
        std::string postContent = parameters.at(1);
        for (unsigned int i = 2; i < parameters.size(); i++) {
            postContent = postContent + " " + parameters.at(i);
        }
        return "05" + postContent + ";";
    } else if (command == "PM") {
        std::string otherusername = parameters.at(1);
        std::string postContent = parameters.at(2);
        for (unsigned int i = 3; i < parameters.size(); i++) {
            postContent = postContent + " " + parameters.at(i);
        }
        return "06" + otherusername + " " + postContent + '\0' + getDateAndTimeString() + ";";
    } else if (command == "LOGSTAT") {
        return "07;";
    } else if (command == "STAT") {
        std::string usernames = parameters.at(1);
        return "08" + usernames + ";";
    }
    else if(command == "BLOCK"){
        std::string username = parameters.at(1);
        return "12" +username+";";
    }
    else return "line not valid";
}

void serverWriter::run(ConnectionHandler *connectionHandler) {
    while (1) {
        const short bufsize = 1024;
        char buf[bufsize];
        std::cin.getline(buf, bufsize);
        std::string line(buf);
        std::string toSend = processLine(line);
        if (toSend != "line not valid") {
            if (!connectionHandler->sendLine(toSend)) {
                std::cout << "Disconnected. Exiting...\n" << std::endl;
                break;
            }
        }
    }
}

