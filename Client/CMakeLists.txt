cmake_minimum_required(VERSION 3.20)
project(ass3_new2)

set(CMAKE_CXX_STANDARD 11)

add_executable(ass3_new2 src/echoClient.cpp src/connectionHandler.cpp src/serverWriter.cpp)

set(BOOST_ROOT "C:Boost")
set(Boost_USE_STATIC_LIBS OFF)
set(Boost_USE_MULTITHREADED ON)
set(Boost_USE_STATIC_RUNTIME off)
find_package(Boost REQUIRED COMPONENTS system)
target_include_directories(ass3_new2 PUBLIC ${Boost_INCLUDE_DIRD})
target_link_libraries(ass3_new2 ${Boost_LIBRARIES})
SET(LDFLAGS "-lboost_system -lboost_thread -pthread")
SET(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${LDFLAGS}")