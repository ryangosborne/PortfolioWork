// Zombie.h - header file for Zombie class

#ifndef A4_ECOSIM_ZOMBIE_H
#define A4_ECOSIM_ZOMBIE_H

#include "Organism.h"

class Zombie : public Organism
{
public:
    int lastEaten;

    Zombie(); // default constructor
    Zombie( City *city, int width, int height );
    virtual ~Zombie(); // destructor

    void move() override; // eating (if possible), moving (if didn't eat), turn
    void movementAttempt() override;
    void recruitmentAttempt() override;
    void starve();
    char getSpecies() override;
};

#endif //A4_ECOSIM_ZOMBIE_H
