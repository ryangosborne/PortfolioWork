# ZombiesVsHumans

This was my final assignment for Programming C++ (PROG 2100) which I received a mark of 98%.

The goal of this assignment was to create a 2d predator-prey simulation. In this case, my instructor chose zombies as the predators and humans as the prey (uh-oh!). The solution required a strong understanding of OOP, pointers, polymorphism, debugging, and error handling.

About the solution: Each turn, the program prints to console a 20x20 grid which represents a city. This grid depicts the locations of humans with an H and zombies with a Z. 

For every turn taken, each human tries to complete certain conditional steps, followed by zombies trying to complete certain conditional steps. For humans, they will first attempt to move one space (either up, down, left, or right - chosen randomly from surrounding available locations). Humans will also attempt to "recruit" after three turns and this will cause a new human to be created on the grid in an adjacent space. 

Once it's time for the zombies to go, they first will identify if there are any humans in any surrounding spaces (including diagonal) and if so, the zombie will move to the said human location and "consume" the human, resulting in the human object being removed from the grid. If a zombie has no humans to eat, it will move to a randomly-selected available position. If a zombie does not consume a human wihin three full times, the zombie's virus was fade and turn back into a human (yay!). However, if a zombie survives for 8 turns and has recently eaten a human, it can also convert a surrounding human into a zombie!

The simulation will last for 1,000 turns OR until either species has gone extinct.

The file GameSpecs.h is an easy way to change some parameters of the game, such as the size of the grid, initial start count for humans, zombies, etc.
