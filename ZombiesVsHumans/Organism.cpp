// Organism.cpp is implementation for Organism class


#include "Organism.h"

void Organism::setPosition(int x, int y)
{
    // assigns position of organism
    this->x = x;
    this->y = y;
}

void Organism::setMoved(bool condition)
{
    // updates flag for Organism
    this->moved = condition;
}

int Organism::getRecruitment()
{
    // returns recruitment counter
    return recruitCount;
}

void Organism::resetRecruitment()
{
    // resets Organism's recruitment counter
    this->recruitCount = 0;
}

char Organism::getSpecies()
{
    // returns Organism
    return species;
}
