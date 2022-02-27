// Vacant.cpp is implementation for Vacant class

#include "Vacant.h"
#include "GameSpecs.h"

Vacant::Vacant()
{
    species = VACANT_CH;
}
Vacant::~Vacant(){}
void Vacant::move(){}
void Vacant::recruitmentAttempt(){}
void Vacant::movementAttempt(){}

char Vacant::getSpecies() {
    return species;
}
