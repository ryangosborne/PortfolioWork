// Human.cpp is implementation for Human class

#include <vector>
#include <chrono>
#include <algorithm>
#include <random>
#include "Human.h"
#include "GameSpecs.h"
#include "City.h"
#include "Vacant.h"
using namespace std;

Human::Human(){} // default constructor
Human::~Human(){} // destructor


Human::Human(City *city, int width, int height)
{
    this->width = width;
    this->height = height;
    species = HUMAN_CH;
    this->city = city;
}

void Human::move() {
    if (!moved) { // only doing these actions if Human has not already moved
        setMoved(true); // updating moved flag
        recruitCount++; // increment recruitCount
        movementAttempt(); // attempts to move
        if (getRecruitment() == HUMAN_BREED) { // checks for recruitment eligibility
            recruitmentAttempt(); // attempts to recruit
        }
    }
}

void Human::movementAttempt(){
    // Human will attempt to move, and do so if possible

    // get and validate locations - add all eligible movement locations to vector
    // before adding, check against grid boundaries and Vacant
    vector<pair<int, int>> movementLocations;
    // storing original x and y values to give to new Vacant object after Human moves
    int originalX = x;
    int originalY = y;

    if (x != 0) { // checking if Human is in top row
        if (city->getOrganism(x - 1, y)->getSpecies() == VACANT_CH) { // then check if location is vacant
            movementLocations.emplace_back(x - 1, y); // storing north location
        }
    }
    if (x != GRIDSIZE - 1) { // checking if Human is in bottom row
        if (city->getOrganism(x + 1, y)->getSpecies() == VACANT_CH) { // check if vacant
            movementLocations.emplace_back(x + 1, y); // storing south location
        }
    }
    if (y != GRIDSIZE - 1) { // checking if in last column
        if (city->getOrganism(x, y + 1)->getSpecies() == VACANT_CH) { // check if vacant
            movementLocations.emplace_back(x, y + 1); // storing east location
        }
    }
    if (y != 0) { // check if in first column
        if (city->getOrganism(x, y - 1)->getSpecies() == VACANT_CH) { // check if vacant
            movementLocations.emplace_back(x, y - 1); // storing west location
        }
    }

    // Move to new location: if vector has entries, shuffle them, pick the first and move Human to that location
    if (!movementLocations.empty()) {
        unsigned seed = chrono::system_clock::now().time_since_epoch().count(); // create random seed using sys clock
        shuffle(movementLocations.begin(), movementLocations.end(), default_random_engine(seed));

        // move Human to selected position and fill old position with Vacant
        city->setOrganism(this, movementLocations[0].first, movementLocations[0].second);
        Organism *vacant = new Vacant();
        city->setOrganism(vacant, originalX, originalY);
    }
}

void Human::recruitmentAttempt() {
    // Human will attempt to recruit, and do so if possible. the counter will reset either way

    resetRecruitment(); // resetting recruitment counter

    // get and validate recruitment locations - add all eligible locations to vector
    // before adding, check against grid boundaries and Vacant
    vector<pair<int, int>> recruitmentLocations;

    if (x != 0) { // checking if Human is in top row
        if (city->getOrganism(x - 1, y)->getSpecies() == VACANT_CH) { // then check if location is vacant
            recruitmentLocations.emplace_back(x - 1, y); // storing north location
        }
    }
    if (x != GRIDSIZE - 1) { // checking if Human is in bottom row
        if (city->getOrganism(x + 1, y)->getSpecies() == VACANT_CH) { // check if vacant
            recruitmentLocations.emplace_back(x + 1, y); // storing south location
        }
    }
    if (y != GRIDSIZE - 1) { // checking if in last column
        if (city->getOrganism(x, y + 1)->getSpecies() == VACANT_CH) { // check if vacant
            recruitmentLocations.emplace_back(x, y + 1); // storing east location
        }
    }
    if (y != 0) { // check if in first column
        if (city->getOrganism(x, y - 1)->getSpecies() == VACANT_CH) { // check if vacant
            recruitmentLocations.emplace_back(x, y - 1); // storing west location
        }
    }

    if (!recruitmentLocations.empty()) {
        unsigned seed = chrono::system_clock::now().time_since_epoch().count(); // create random seed using sys clock
        shuffle(recruitmentLocations.begin(), recruitmentLocations.end(), default_random_engine(seed));

        // create new Human and assign selected position
        Organism *human = new Human(city, GRIDSIZE, GRIDSIZE);
        city->setOrganism(human, recruitmentLocations[0].first, recruitmentLocations[0].second);
    }
}

char Human::getSpecies() {
    return species;
}
