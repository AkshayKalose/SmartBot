Feature list
===================
- 100 index RAM (Can be replaced as new information is added that isn't permanent)
- Trust Level
- Confidence Level
- Permanent Database memory
- Maximum Time Before Response

Classes
====================

SmartBot
-------
Contains main bot and controls input/output.(or should we have another class to do IO?)

Memory
------
Stores temporary memory and permanent memory (merge relations class into memory for relations?)

Emotions(better word?)(Maybe just Happiness Level? 0 - 100)
-------
Holds emotion level and determines if message is positive or negative from API?

Trust
-------
Determines trust of user(or merge emotion and this class into one main "SOME WORD" class)

Communication
-------------
Handles IO.