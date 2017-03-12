# Project4
README Group 90
The structure of Critters1 will be broken into ten main classes.
Sub-dividing these classes we will basically have, an overarching “Critters” class, 
a  “Params” class for general parameters, four types of critter classes, a very specific 
type of critter for the Algae class, and a “Main” class to run the game in. We did not 
create any new classes that were not required by the requirements, but we do handle Encounters
in a non-provided method. Encounter works by loading an ArrayList with the Critters at any given location and then
iterating through each location and performing the encounters. All of these methods will be private,
so they are only accessible within the classes that we wrote them in. The methods that we personally write will include a:
Reproduce, makeCritter, deleteCritter, walk, run, fight, encounter, map, viewMap, and controller. 
To store our various Critters we will be using an ArrayList<Critters> that holds the various Critters that we wish to populate the field
with. At times we will call an ArrayList<ArrayList<Critters>> to determine the various types of Critters
that interact with each other at any given time. 
