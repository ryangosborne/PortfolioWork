# OOPAssignment

# Geoff's video: https://youtu.be/aF4cTL3b9ZQ

Notes:
- make GUI-based application to show OOP concepts like inheritance & JavaSwing
x- first screen: opening screen with title, image and "build a character" button
x- second screen: Character Generator (users should only be allowed to interact with Enter Name, radio buttons, reroll and start battle).
x- when user selects character type with radio button, description comes up in text box below and character stats are filled in on the right. image of character also appears
x- user can re-roll character stats which randomly assigns a number 1-100 in each field. user can repeat as much as they want
x- when user selects weapon with radio button, description and image come up and weapon stats are assigned.
- third screen: Battle to the Death! this shows player info and a monster and their class and info.
x- good idea to have a Game class as JFrame, inside of that, different JPanels for the different screens
x- probably should be a Creatures class with subclasses Player Characters and Monster Characters, and then maybe Warrior/Wizard/Cleric subclasses for Player.
x- good idea that with the Game JFrame (which holds most stuff) starts, you create an object of each type, maybe set some of the default values. then keep those, and make accessible to creation screen. so when you get to the creation screen, there is stuff already loaded
- needs to demonstrate composition and aggregation


To Do List:
- await Jason advice
- solve the text / class / action listener problem(s)
- put in all images to Game panel 3, set to visible false and then make one visible depending on which is selected (via action listener?)
- build third screen (shows creates who will fight, their stats -> SEE WHO WINS button)
- build fourth screen (battle results)


                HP      Defense     Agility     Attack
    - warrior   55      11          2           15
    - wizard    50      8           15          10
    - cleric    50      9           13          11
    --------------------------------------------------
    - dragon    100     25          10          25
    - goblin    45      5           12          7
    - troll     60      5           11          17
    - zombie    12      1           2           5