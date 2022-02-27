// Zombie.cpp is implementation for Zombie class
// learned implementation for multi-pair vectors here: https://www.geeksforgeeks.org/store-data-triplet-vector-c/

#include <vector>
#include <chrono>
#include <random>
#include <algorithm>
#include "Human.h"
#include "Zombie.h"
#include "GameSpecs.h"
#include "City.h"
#include "Vacant.h"

Zombie::Zombie(){} // default constructor
Zombie::~Zombie(){} // destructor

Zombie::Zombie(City *city, int width, int height)
{
    this->width = width; // city width
    this->height = height; // city height
    this->city = city;
    species = ZOMBIE_CH;
    lastEaten = 0; // counter to evaluate zombie starving
}

void Zombie::move(){
    if (!moved) { // only doing these actions if Zombie has not already moved
        setMoved(true); // updating moved flag
        recruitCount++; // increment recruitCount
        movementAttempt(); // attempts to move
        if (getRecruitment() >= ZOMBIE_BREED) { // checks for recruitment / breed eligibility
            recruitmentAttempt(); // attempts to recruit
        }
        if(lastEaten == ZOMBIE_STARVE){ // checks for zombie starving
            starve(); // replaces starved zombie with new human
        }
    }
}

void Zombie::movementAttempt() {
    // Zombie will prioritize eating human and moving to that location. If no human within Zombie reach,
    // move, if possible

    // validate and store locations - add to this vector
    // will store like "Z, 4, 5" (org type, x location, y location)
    vector<pair<char, pair<int, int>>> possibleMovements;

    // storing original x and y values to give to new Vacant object after Zombie moves
    int originalX = x;
    int originalY = y;

    if (x != 0) { // checking if Zombie is in top row
        possibleMovements.emplace_back(make_pair(city->getOrganism(x-1, y)->getSpecies(), make_pair(x-1, y))); // N position
        if(y != 0){ // checking if Zombie in first column (for NW)
            possibleMovements.emplace_back(make_pair(city->getOrganism(x-1, y-1)->getSpecies(), make_pair(x-1, y-1))); // NW position
        }
    }
    if (x != GRIDSIZE - 1) { // checking if Zombie is in bottom row
        possibleMovements.emplace_back(make_pair(city->getOrganism(x+1, y)->getSpecies(), make_pair(x+1, y))); // S position
        if(y != GRIDSIZE - 1){ // checking if Zombie in last column (for SE)
            possibleMovements.emplace_back(make_pair(city->getOrganism(x+1, y+1)->getSpecies(), make_pair(x+1, y+1))); // SE position
        }
    }
    if (y != GRIDSIZE - 1) { // checking if Zombie is in last column
        possibleMovements.emplace_back(make_pair(city->getOrganism(x, y+1)->getSpecies(), make_pair(x, y+1))); // E position
        if(x != 0){ // checking if Zombie in top row (for NE)
            possibleMovements.emplace_back(make_pair(city->getOrganism(x-1, y+1)->getSpecies(), make_pair(x-1, y+1))); // NE position
        }
    }
    if (y != 0) { // check if Zombie is in first column
        possibleMovements.emplace_back(make_pair(city->getOrganism(x, y-1)->getSpecies(), make_pair(x, y-1))); // W position
        if(x != GRIDSIZE - 1){ // checking if Zombie in bottom row (for SW)
            possibleMovements.emplace_back(make_pair(city->getOrganism(x+1, y-1)->getSpecies(), make_pair(x+1, y-1))); // SW position
        }
    }

    // removing Zombies from possible movement vector - NOT REMOVING ZOMBIES
    for(int i = 0; i < possibleMovements.size(); i++){
        if(possibleMovements[i].first == ZOMBIE_CH){
            possibleMovements.erase(possibleMovements.begin() + i);
            i--; // decrementing counter to account for the chance that a zombie is behind a removed zombie in the list
            // if first zombie is removed at i = 2, the loop then looks for i = 3, which skips the new i = 2
        }
    }

    // create new vector for human locations
    if(!possibleMovements.empty()) {
        vector<pair<int, int>> humanLocations;
        for (int i = 0; i < possibleMovements.size(); i++) {
            if (possibleMovements[i].first == HUMAN_CH) {
                humanLocations.emplace_back(possibleMovements[i].second.first, possibleMovements[i].second.second);
            }
        }
        // move Zombie to space occupied by Human, destroying Human. fill old Zombie space with Vacant
        if (!humanLocations.empty()) {
            unsigned seed = chrono::system_clock::now().time_since_epoch().count(); // create random seed using sys clock
            shuffle(humanLocations.begin(), humanLocations.end(), default_random_engine(seed));
            city->setOrganism(this, humanLocations[0].first, humanLocations[0].second);
//            cout << "Zombie at " << originalX << ", " << originalY << " kill human, move --> " << humanLocations[0].first << ", " << humanLocations[0].second << endl;
            Organism *vacant = new Vacant();
            city->setOrganism(vacant, originalX, originalY);
            lastEaten = 0; // resetting lastEaten flag
        }
        // if no humans adjacent to Zombie, move to random unoccupied space
        else {
            unsigned seed = chrono::system_clock::now().time_since_epoch().count(); // create random seed using sys clock
            shuffle(possibleMovements.begin(), possibleMovements.end(), default_random_engine(seed));
            city->setOrganism(this, possibleMovements[0].second.first, possibleMovements[0].second.second);
            Organism *vacant = new Vacant();
            city->setOrganism(vacant, originalX, originalY);
            lastEaten++; // increment lastEaten
        }
    }
    // possible movements list was empty
    else{
        lastEaten++; // increment lastEaten
//        cout << "Zombie at " << originalX << ", " << originalY << " is unable to move" << endl;
    }

}

void Zombie::recruitmentAttempt() {
    // Zombie will try to recruit, and do so if possible. If unsuccessful, the counter will not reset

    // get and validate recruitment locations - add all eligible locations to vector
    // before adding, validate locations: must be adjacent human (including diagonal)
    vector<pair<int, int>> recruitmentLocations;

    // demonstrating a slightly different routine for validating locations

    if(x != 0) { // checking if Zombie is in top row
        if(city->getOrganism(x-1, y)->getSpecies() == HUMAN_CH){
            recruitmentLocations.emplace_back(x-1, y); // N position
        }
        if(y != 0){ // checking if Zombie in first column (for NW)
            if(city->getOrganism(x-1, y-1)->getSpecies() == HUMAN_CH){
                recruitmentLocations.emplace_back(x-1, y-1); // NW position
            }
        }
        if(y != GRIDSIZE - 1){ // checking if Zombie is in last column (for NE)
            if(city->getOrganism(x-1, y+1)->getSpecies() == HUMAN_CH){
                recruitmentLocations.emplace_back(x-1, y+1); // NE position
            }
        }
    }
    if(x != GRIDSIZE - 1) { // checking if Zombie is in bottom row
        if(city->getOrganism(x+1, y)->getSpecies() == HUMAN_CH){
            recruitmentLocations.emplace_back(x+1, y); // S position
        }
        if(y != GRIDSIZE - 1){ // checking if Zombie is in last column (for SE)
            if(city->getOrganism(x+1, y+1)->getSpecies() == HUMAN_CH){
                recruitmentLocations.emplace_back(x+1, y+1); // SE position
            }
        }
        if(y != 0){ // checking if Zombie is in first column (for SW)
            if(city->getOrganism(x+1, y-1)->getSpecies() == HUMAN_CH){
                recruitmentLocations.emplace_back(x+1, y-1); // SW position
            }
        }
    }
    if(y != GRIDSIZE -1){ // checking if Zombie is in last column
        if(city->getOrganism(x, y+1)->getSpecies() == HUMAN_CH){
            recruitmentLocations.emplace_back(x, y+1); // E position
        }
    }
    if(y != 0){
        if(city->getOrganism(x, y-1)->getSpecies() == HUMAN_CH){
            recruitmentLocations.emplace_back(x, y-1); // W position
        }
    }

    if(!recruitmentLocations.empty()){
        // if here, recruitment will be successful
        resetRecruitment(); // reset recruitment counter
        unsigned seed = chrono::system_clock::now().time_since_epoch().count(); // create random seed using sys clock
        shuffle(recruitmentLocations.begin(), recruitmentLocations.end(), default_random_engine(seed));
        // create new Zombie and assign to selected position
        Organism *zombie = new Zombie(city, GRIDSIZE, GRIDSIZE);
        city->setOrganism(zombie, recruitmentLocations[0].first, recruitmentLocations[0].second);
//        cout << "new Zombie! X: " << recruitmentLocations[0].first << ", Y: " << recruitmentLocations[0].second << endl;
    }
}

void Zombie::starve()
{
    // if a zombie has not eaten any humans within three steps, at the end of the step, the zombie will be
    // converted into a new human.
//    cout << "Zombie at " << x << ", " << y << " starved and converted to a human" << endl;
    Organism *human = new Human(city, GRIDSIZE, GRIDSIZE);
    city->setOrganism(human, x, y);
}

char Zombie::getSpecies() {
    return species;
}