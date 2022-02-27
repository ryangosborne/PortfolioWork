#include <iostream>
#include <chrono>
#include <thread>
#include <windows.h>
#include "City.h"
#include "GameSpecs.h"

using namespace std;

void ClearScreen()
{
    cout << "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
}

void Col(int c)
{
    HANDLE hConsole;
    hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole, c);
}

int main() {
    City *city = new City(); // creating the city, which creates the organisms too
    chrono:: seconds interval(PAUSE_SECONDS);

    // printing outside loop to show original state (Turn 0)
    ClearScreen();
    Col(0);
    cout << " GENERATION: " << city->getGeneration() << "              ";
    cout << "HUMANS: " << city->countType(HUMAN_CH) << "     ";
    cout << "ZOMBIES: " << city->countType(ZOMBIE_CH) << endl;
    cout << *city;

    while (city->hasDiversity()) { // while both humans and zombies exist
        this_thread::sleep_for(interval); // sleep happens
        ClearScreen(); // clear screen
        city->move(); // calls City's move which triggers all Human & Zombie actions
        city->reset(); // reset move flags for organisms
        cout << " GENERATION: " << city->getGeneration() << "              ";
        cout << "HUMANS: " << city->countType(HUMAN_CH) << "       ";
        cout << "ZOMBIES: " << city->countType(ZOMBIE_CH) << endl;
        cout << *city; // prints updated city (overloaded <<)
        if(city->getGeneration() == ITERATIONS){
            break;
        }
    } // end while
    if(!city->hasDiversity()) {
        cout << " * * Extinction Event * * " << endl;
    }else{
        cout << "Simulation Complete" << endl;
    }
} // end main