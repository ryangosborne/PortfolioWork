cmake_minimum_required(VERSION 3.19)
project(A4_EcoSim)

set(CMAKE_CXX_STANDARD 20)

add_executable(A4_EcoSim main.cpp Organism.h Organism.cpp Organism.h Zombie.cpp Zombie.cpp Zombie.h Human.cpp Human.h City.cpp City.h GameSpecs.cpp GameSpecs.h GameSpecs.cpp Vacant.cpp Vacant.h)
