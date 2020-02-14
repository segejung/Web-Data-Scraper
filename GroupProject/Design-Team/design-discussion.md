# Design Discussion

## Design 1 [Roger Kiew]
![Design 1](../images/Design1.png)
### Pros
SJ: The cleanest looking UML from our team. It has a global variable that scores and settings are accessed from. 
### Cons
SJ: The class diagram is broad and does not address a lot of the detail in the requirements. The diagram could help with more detail on enterWord(), statistics, and initializeBoard. 

## Design 2 [SeGe Jung]
![Design 2](../images/Design2.png)

### Pros
Roger:
    Well structured, listed both UI and the classes
### Cons
SJ: UI could be consolidated into one Main UI
Roger: No global access to each game's score/settings etc.

## Design 3 [Brian Hoang]
![Design 3](../images/Design3.png) 
### Pros
SJ: Concise and structured view of the classes. Address all of the functions of the wordgame application.
Roger: The classes contained all necessary fields/methods.

### Cons
Roger:
    The classes are interconnected so the structure is not as clear.

## Design 4 
### Cons
SJ: Details lack a bit and could use couple more classes. It would be worth articulating the enterWord() method as it is listed as one of the requirements. For example, I added some boolean to see if the word is a certain length. There is no information on what the board will consist of. Vowels, weights, letter ‘Qu’, or the size. Would like to see some more classes articulating this work. Aggregation of board can be classes letters and word.

## Design 4 [Mo Yang]
![Design 4](../images/Design4.png) 
### Pros
SJ: Good broad overview of the application 
Roger: The design is simple, easy to read.
### Cons
SJ: Lacks to show all of the requirements. Also the relationships between classes lack sophistication. All the arrows suggest same relationship between all classes whereas there might be association or aggregation relationships. 
Would be upgraded with more details. For example, for the timer, you can write timer: int: 0..4. 
The requirements of player entering words that are more than two letters are not reflected in the class diagram. 
There are two statistics: one is word and one is score. This is not reflected in the diagram.


Roger: The arrows are a little bit confusing. For example, which line does "minus 5 points" belong to?

## Team Design


## Summary
SJ: All the team members had solid structure in their UML class diagrams. The level of detailed differed, however, by group effort, we maximized our pros and minimized our cons. 
This process was done through weekly meetings and threads in Microsoft Teams. 
