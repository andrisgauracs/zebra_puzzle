# zebra_puzzle
A Java version for solving the famous Zebra puzzle - http://en.wikipedia.org/wiki/Zebra_Puzzle

These are the original rules of the puzzle:

1. There are five houses.
2. The Englishman lives in the red house.
3. The Spaniard owns the dog.
4. Coffee is drunk in the green house.
5. The Ukrainian drinks tea.
6. The green house is immediately to the right of the ivory house.
7. The Old Gold smoker owns snails.
8. Kools are smoked in the yellow house.
9. Milk is drunk in the middle house.
10. The Norwegian lives in the first house.
11. The man who smokes Chesterfields lives in the house next to the man with the fox.
12. Kools are smoked in the house next to the house where the horse is kept.
13. The Lucky Strike smoker drinks orange juice.
14. The Japanese smokes Parliaments.
15. The Norwegian lives next to the blue house.

For this puzzle the rules are described in the input.txt file in such matter:

5
SAME;nationality;English;color;Red
SAME;nationality;Spaniard;pet;Dog
SAME;drink;Coffee;color;Green
SAME;drink;Tea;nationality;Ukrainian
TO_THE_LEFT_OF;color;Ivory;color;Green
SAME;smoke;Old gold;pet;Snails
SAME;smoke;Kools;color;Yellow
SAME;drink;Milk;position;3
SAME;nationality;Norwegian;position;1
NEXT_TO;pet;Fox;smoke;Chesterfields
NEXT_TO;smoke;Kools;pet;Horse
SAME;smoke;Lucky strike;drink;Orange juice
SAME;smoke;Parliaments;nationality;Japanese
NEXT_TO;color;Blue;nationality;Norwegian
SAME;drink;Water
SAME;pet;Zebra

The result of the puzzle is printed out on the console and an output.xml file is generated.
