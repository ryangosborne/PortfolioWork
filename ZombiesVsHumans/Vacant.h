// Vacant.h - header file for Vacant class

#ifndef A4_ECOSIM_VACANT_H
#define A4_ECOSIM_VACANT_H

#include "Organism.h"

class Vacant : public Organism
{
public:

    Vacant(); // default constructor
    virtual ~Vacant(); // destructor

    void move() override;
    void recruitmentAttempt() override;
    void movementAttempt() override;
    char getSpecies() override;
};

#endif //A4_ECOSIM_VACANT_H
