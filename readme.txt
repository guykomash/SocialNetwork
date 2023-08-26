1.
mvn compile

1.1


SERVER:

TPC :
mvn exec:java -Dexec.mainClass="bgu.spl.net.impl.BGSServer.TPCMain" -Dexec.args="7777"

Reactor:
mvn exec:java -Dexec.mainClass="bgu.spl.net.impl.BGSServer.ReactorMain" -Dexec.args="7777 3"

(3 = num of threads)


CLIENT:
make

./bin/BGSclient

(DEFUALT HOST =127.0.0.1 PORT=7777 , NO NEED FOR ARGUMENTS)

1.2

ALL COMMANDS SHOULD BE IN CAPTIAL LETTERS.

Birthday format:  DD-MM-YYY


REGISTER Guy 123 05-03-1996

LOGIN Guy 123 1

LOGOUT

FOLLOW 0 Evgeni

POST HELLO WORLD! @Evgeni

PM Evgeni HEY EVGENI

LOGSTAT

STAT <username1>|<username2>|<username3>.......


2.

the set of filtered words is created in TPCMain/ReactorMain.



















