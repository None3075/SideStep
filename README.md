# Introduction
Sidestep is a game that I made as a project for my programmation II subject in my first year of my grade in Computer Science + Data Science + IA.

It was my first time programming in Java so there is still room for improvements.

Hope you like it or that it proves useful for your projects!

# SideStep
The games objective is to survive as long as possible by dodging the red balls and with the help of the superpowers that periodically pop up in the screen.

## Keybindings
The default keybindings are the usual W = Up , A = Left , S = Down , D = Right.

This keybindings are programmed in the Player.class in case you want to change them.

## Powerups
There are three types of powerups in the game.

The Bomb clears all the bombs that are at a certain distance from the point it was activated.

The Clock slows the time for an amount of time that can get longer with consecutive activated clocks.

The Shield makes the player invincible (no hitbox) and starts a rainbow animation while it lasts. (It also stacks up like the clock)

All the powerups logic is under the Superpowers.java

## UsernameWindow.java

It is just a simple window that pops up at the start of the game or after dying in which it is displayed the highscore and request for a username.

## Scores

All the scores are saved and loaded from the scores.txt file and is all done by the Score.java class.

## The game's logic

All the remaining logic such as hitboxes, graphics, balls and powerups spawns is programmed in the Gamepanel.java.
