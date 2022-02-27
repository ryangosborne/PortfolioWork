// GameSpecs.h - header file for GameSpecs class
// no functionality for this class -- using this header as a quick way to change behaviour of game

#ifndef A4_ECOSIM_GAMESPECS_H
#define A4_ECOSIM_GAMESPECS_H

const int GRIDSIZE = 20; // size of the square grid
const int HUMAN_STARTCOUNT = 100; // initial Humans
const int ZOMBIE_STARTCOUNT = 5; // initial Zombie count
const int HUMAN_BREED = 3; // steps until a Human breeds
const int ZOMBIE_BREED = 8; // steps until a Zombie breeds
const int ZOMBIE_STARVE = 3; // steps until a Zombie starves and converts to Human
const char HUMAN_CH = 72; // 79 "O"// ASCII Human
const char ZOMBIE_CH = 90; // 90 "Z"// ASCII zombie
const char VACANT_CH = 45; // "-" in ASCII
const int PAUSE_SECONDS = 2; // pause between steps
const int ITERATIONS = 1000; // max number of steps

//Colors
//0  = black				//9  = bright blue
//1  = blue					//10 = bright green
//2  = green				//11 = bright turquoise
//3  = turquise				//12 = bright red
//4  = red					//13 = bright pink
//5  = pink					//14 = bright yellow
//6  = yellow				//15 = bright white
//7  = white regular		//16+ = solid blocks
//8  = white pale

//const int HUMAN_COLOR = 3; // turquoise
//const int ZOMBIE_COLOR = 14; // bright yellow

#endif //A4_ECOSIM_GAMESPECS_H
