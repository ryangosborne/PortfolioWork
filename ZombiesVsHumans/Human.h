// Human.h - header file for Human class

#ifndef A4_ECOSIM_HUMAN_H
#define A4_ECOSIM_HUMAN_H

#include "Organism.h"

class Human : public Organism
{
public:

    Human(); // default constructor
    Human(City *city, int width, int height); // constructor with argument (creating humans and placing them)
    virtual ~Human(); // destructor

    void move() override; // would include movement, check for recruit (if eligible, where recruit would go), update flag
    void movementAttempt() override;
    void recruitmentAttempt() override;
    char getSpecies() override;
};

#endif //A4_ECOSIM_HUMAN_H
