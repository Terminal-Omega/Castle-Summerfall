# Documentation for the framework

This is intended for Thomas to use while building the actual game. It will describe how to do various things.

## Generator
---

### Generate a floor

usage: Generator.generateFloor(xCoord, yCoord)  
returns: Floor

### Generate a room

usage: Generator.generateRoom()  
returns: Room

### Generate an interactable

usage: Generator.generateInteractable()  
returns: an interactable of some sort

## Interactable
The interactable class is for storing everything in the game that is not a room, door, or actor. extensions of the interactable class include Container and Weapon.
