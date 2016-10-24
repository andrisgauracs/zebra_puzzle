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

5<br>
SAME;nationality;English;color;Red<br>
SAME;nationality;Spaniard;pet;Dog<br>
SAME;drink;Coffee;color;Green<br>
SAME;drink;Tea;nationality;Ukrainian<br>
TO_THE_LEFT_OF;color;Ivory;color;Green<br>
SAME;smoke;Old gold;pet;Snails<br>
SAME;smoke;Kools;color;Yellow<br>
SAME;drink;Milk;position;3<br>
SAME;nationality;Norwegian;position;1<br>
NEXT_TO;pet;Fox;smoke;Chesterfields<br>
NEXT_TO;smoke;Kools;pet;Horse<br>
SAME;smoke;Lucky strike;drink;Orange juice<br>
SAME;smoke;Parliaments;nationality;Japanese<br>
NEXT_TO;color;Blue;nationality;Norwegian<br>
SAME;drink;Water<br>
SAME;pet;Zebra<br>

The result of the puzzle is printed out on the console and an output.xml file is generated.
