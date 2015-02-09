# Created collaboratively by Akshay Kalose and Chaitya Shah using Cloud9

Feature list
===================
- 100 index RAM (Can be replaced as new information is added that isn't permanent)
- Trust Level
- Confidence Level
- Permanent Database memory
- Maximum Time Before Response

Classes
==================

SmartBot
-------
Runs all the components of the bot.

Memory
------
Stores and manages temporary memory and permanent memory.

Trust
-------
Determines trust of user.

Communication
-------------
Handles IO.

Relations
===================
Relations are built through the use of delimiters. Originally starting with only "is" and "are",
the bot learns other delimeters based on statements from the user. For example:
"My name is Chaitya"
Would create the relationship of Name => Chaitya
Because of the delimiter "is"
Like babies, much of the initial information that is given to the robot will be absorbed without question.
If you told the bot, "Berries are stupid", It would make the association.
If you later tell the bot "I love berries" It would respond with something like "I think berries are stupid!"
If you challenge the bot and say "No, berries are great!", the bot would trust you less and ignore your claim/association.

So, based on this: In the code, the map will contain associations in this format:
<item, definition>

If we come across a new delimiter that isn't added yet, such as "like", the bot will recognize it.
For example: "I like berries"
On seeing this message, the bot will not find any delimiters in the arraylist in it. So it will search
through its map showing things and their definitions based on other delimiters. If it finds "I" and/or "berries"
in the map, it will take the word connecting those two and add it to the delimiters arraylist.
This way, it can "learn" when new delimiters are being used. If the sentence only contains
knowledge that the bot does not have yet, it will respond, that it does not know enough to understand
the message. 