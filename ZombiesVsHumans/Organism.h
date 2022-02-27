// Organism.h - header file for Organism class

#ifndef A4_ECOSIM_ORGANISM_H
#define A4_ECOSIM_ORGANISM_H

#include <iostream>

using namespace std;

class City;

class Organism
{
protected:
    int x; // location x
    int y; // location y
    int width; // max x value
    int height; // max y value
    char species; // will hold char for each Organism subclass
    int recruitCount = 0; // will keep track of Organism's eligibility for recruiting
    bool moved; // flag if organism has moved this turn
    City *city; // access to the city without having to pass it around as a parameter

public:
    Organism(){}; // constructor
    Organism( City *city, int width, int height ){};
    virtual ~Organism(){}; // destructor (virtual to make sure it'll match correct subclass destructor)

    // pure virtual methods
    virtual void move() = 0;
    virtual void movementAttempt() = 0;
    virtual void recruitmentAttempt() = 0;
    virtual char getSpecies() = 0;

    void setPosition( int x, int y ); // will set an organism's position
    void setMoved(bool); // conditionally change Organism's moved status
    int getRecruitment(); // returns Organism's recruitmentCount
    void resetRecruitment(); // resets Organism's recruitment counter

    friend ostream& operator<<( ostream &output, Organism *organism );
};

#endif //A4_ECOSIM_ORGANISM_H
