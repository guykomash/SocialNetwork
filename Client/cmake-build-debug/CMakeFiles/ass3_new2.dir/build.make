# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.20

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /users/studs/bsc/2022/komashg/Downloads/old/CLion-2021.2.3/clion-2021.2.3/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /users/studs/bsc/2022/komashg/Downloads/old/CLion-2021.2.3/clion-2021.2.3/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/ass3_new2.dir/depend.make
# Include the progress variables for this target.
include CMakeFiles/ass3_new2.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/ass3_new2.dir/flags.make

CMakeFiles/ass3_new2.dir/src/echoClient.cpp.o: CMakeFiles/ass3_new2.dir/flags.make
CMakeFiles/ass3_new2.dir/src/echoClient.cpp.o: ../src/echoClient.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/ass3_new2.dir/src/echoClient.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/ass3_new2.dir/src/echoClient.cpp.o -c /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/echoClient.cpp

CMakeFiles/ass3_new2.dir/src/echoClient.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ass3_new2.dir/src/echoClient.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/echoClient.cpp > CMakeFiles/ass3_new2.dir/src/echoClient.cpp.i

CMakeFiles/ass3_new2.dir/src/echoClient.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ass3_new2.dir/src/echoClient.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/echoClient.cpp -o CMakeFiles/ass3_new2.dir/src/echoClient.cpp.s

CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.o: CMakeFiles/ass3_new2.dir/flags.make
CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.o: ../src/connectionHandler.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.o -c /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/connectionHandler.cpp

CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/connectionHandler.cpp > CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.i

CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/connectionHandler.cpp -o CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.s

CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.o: CMakeFiles/ass3_new2.dir/flags.make
CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.o: ../src/serverWriter.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.o"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.o -c /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/serverWriter.cpp

CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/serverWriter.cpp > CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.i

CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/src/serverWriter.cpp -o CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.s

# Object files for target ass3_new2
ass3_new2_OBJECTS = \
"CMakeFiles/ass3_new2.dir/src/echoClient.cpp.o" \
"CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.o" \
"CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.o"

# External object files for target ass3_new2
ass3_new2_EXTERNAL_OBJECTS =

ass3_new2: CMakeFiles/ass3_new2.dir/src/echoClient.cpp.o
ass3_new2: CMakeFiles/ass3_new2.dir/src/connectionHandler.cpp.o
ass3_new2: CMakeFiles/ass3_new2.dir/src/serverWriter.cpp.o
ass3_new2: CMakeFiles/ass3_new2.dir/build.make
ass3_new2: /usr/lib/x86_64-linux-gnu/libboost_system.so
ass3_new2: CMakeFiles/ass3_new2.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Linking CXX executable ass3_new2"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/ass3_new2.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/ass3_new2.dir/build: ass3_new2
.PHONY : CMakeFiles/ass3_new2.dir/build

CMakeFiles/ass3_new2.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/ass3_new2.dir/cmake_clean.cmake
.PHONY : CMakeFiles/ass3_new2.dir/clean

CMakeFiles/ass3_new2.dir/depend:
	cd /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug /users/studs/bsc/2022/komashg/Desktop/316593912_321759417/Client/cmake-build-debug/CMakeFiles/ass3_new2.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/ass3_new2.dir/depend

