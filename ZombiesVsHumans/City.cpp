// City.cpp is implementation for City class
// randomizing implementation from https://stackoverflow.com/questions/7560114/random-number-c-in-some-range/7560151

#include "City.h"
#include "GameSpecs.h"
#include "Organism.h"
#include "Zombie.h"
#include "Human.h"
#include "Vacant.h"
#include <array>
#include <random>
#include <iostream>

using namespace std;

City::City()
{
    // (called from main) - default constructor
    // creates grid and runs loop to create initial humans and zombies, randomizing starting co-ordinates
    generation = 0; // counter for turns

    // randomizing initial co-ordinates for Humans and Zombies
    random_device rand;
    mt19937 gen(rand());
    uniform_int_distribution<> dist(0, GRIDSIZE -1);

    // creating required Humans to start the simulation
    for(int i = 0; i < HUMAN_STARTCOUNT; i++){
        Organism *human = new Human(this, GRIDSIZE, GRIDSIZE);
        setOrganism(human, dist(gen), dist(gen));
    }

    // creating required Zombies to start the simulation
    for(int j = 0; j < ZOMBIE_STARTCOUNT; j++){
        Organism *zombie = new Zombie(this, GRIDSIZE, GRIDSIZE);
        setOrganism(zombie, dist(gen), dist(gen));
    }

    // go through city, create Vacant at any location does not have human or zombie
    for(int i = 0; i < GRIDSIZE; i++){
        for(int j = 0; j < GRIDSIZE; j++){
            if(grid[i][j] == nullptr) {
                Organism *vacant = new Vacant();
                setOrganism(vacant, i, j);
            }
        }
    }
}

Organism* City::getOrganism(int x, int y)
{
    // return the object as pointer
    return grid[x][y];
}

void City::setOrganism(Organism *organism, int x, int y)
{
    // this method assigns array location to the object's properties
    // is called to originally create the board, and is called when every new Organism is assigned a grid location
    grid[x][y] = organism;
    organism->setPosition(x, y);
}

void City::move()
{
    // (called from main)
    // this will iterate generation counter and then call the move method for respective organism
    // all actions by an organism are contained within their class' move method
    generation ++;
    for(int i = 0; i < GRIDSIZE; i++){
        for(int j = 0; j < GRIDSIZE; j++){
            grid[i][j]->move();
        }
    }
}

bool City::hasDiversity()
{
    // (called from main)
    // use count method and make sure both have at least one. if both not found, return false.
    if((countType(HUMAN_CH) >= 1) && (countType(ZOMBIE_CH) >= 1)){
        return true;
    }
    else{
        return false;
    }
}

void City::reset()
{
    // (called from main)
    // go through grid, find all Organisms, turn all hasMoved flag to false
    for(int i = 0; i < GRIDSIZE; i++){
        for(int j = 0; j < GRIDSIZE; j++){
            grid[i][j]->setMoved(false);
        }
    }
}

int City::countType(int orgType)
{
    // (called from main)
    // this will go through grid and determine how many of H or Z currently exist
    int orgCount = 0;
    for(int i = 0; i < GRIDSIZE; i++){
        for(int j = 0; j < GRIDSIZE; j++) {
            if(grid[i][j]->getSpecies() == orgType){
                orgCount ++;
            }
        }
    }
    return orgCount;
}

int City::getGeneration() const
{
    // (called from main) this returns current "generation" or iteration
    return generation;
}

ostream& operator<<(ostream& os, City& city)
{
    // (called from main), overloaded << operator. this is what graphically creates the grid
    void Col(int);

    for(int i = 0; i < GRIDSIZE; i++){
        for(int j = 0; j < GRIDSIZE; j++){
            char sp = city.grid[i][j]->getSpecies();
            switch (sp) {
                case ZOMBIE_CH:
                    Col(12);
                    os << " " << sp << " ";
                    break;
                case HUMAN_CH:
                    Col(3);
                    os << " " << sp << " ";
                    break;
                case VACANT_CH:
                    Col(8);
                    os << " " << sp << " ";
                    break;
            }
//                os << " " << city.grid[i][j]->getSpecies() << " ";
        }
        os << "\n";
    }
    Col(0); // turns back to black for titles
    return os;
}

//void Col(int c)
//{
//    HANDLE hConsole;
//    hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
//    SetConsoleTextAttribute(hConsole, c);
//}