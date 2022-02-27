// City.h - header file for City class

#ifndef A4_ECOSIM_CITY_H
#define A4_ECOSIM_CITY_H

#include <iostream>
#include "Organism.h"
#include "GameSpecs.h"

class Organism; // city is of type Organism

const int GRID_WIDTH = GRIDSIZE;
const int GRID_HEIGHT = GRIDSIZE;

class City
{
protected:
    int generation;
    Organism *grid[GRID_HEIGHT][GRID_WIDTH]{}; // an array of Organisms

public:
    City(); // default constructor
    ~City(){}; // destructor

    Organism *getOrganism( int x, int y ); // method to call when walking through board.
    void setOrganism( Organism *organism, int x, int y ); // used at start to place Organisms on board

    void move(); // this isn't "move" in the same way the creatures have. it's actually meant to step through time.
    // the tick will call City's move method (this) and this will walk through the board. at the end, it will call
    // the print method (below) to display updated board to show the completed turn. this will also detect if there
    // has been an ELI. I think this will also search for and call the move methods of whatever organisms are found
    // while iterating through city

    bool hasDiversity(); // checks if both Z and H exist in grid

    void reset(); // reset flags for organisms

    int countType(int orgType); // count types of organisms in grid

    int getGeneration() const; // getter for generation

    friend ostream& operator<<( ostream &output, City& ); // prints formatted grid
};

#endif //A4_ECOSIM_CITY_H
