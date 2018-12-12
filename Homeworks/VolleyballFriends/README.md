Учениците от ж.к. Лозенец искат да играят в тазгодишния турнир по волейбол. Естествено само най-добрите N+1 са поканени да участват. Едно от децата на организаторите на турнира вече е записано в екипа. Останалите N обаче имат условие - не искат да участват в екипа ако не участват и най-добрите им приятели.

Всички ученици имат свой номер, по който да ги разпознаем. Номерата нямат определена подредба и дори могат да са отрицателни. Всеки ученик знае номерата на неговите един или повече най-добри приятели, присъствието на всеки от които ще го мотивира да се запише. Това приятелство е едностранно - ако ученик 1 има 2 за най-добър приятел, не е задължително 2 да има 1 за най-добър приятел.

Знаейки кой ученик кого смята за най-добър приятел, намерете броя на финалния състав на екипа.

Input Format

На първия ред получавате число m - номера на първия участник. На втория ред получавате число N. На следващите N реда получавате двойки числа разделени с разстояние. Първото от тях отговаря на номера на най-добрия приятел, който трябва да е част от екипа. Ако е така, тогава втория ученик (представляван от второто число) се включва към екипа. На всеки ред се интересувате само от текущия състав на екипа, независимо какво може да следва нататък - ако най-добрият приятел 2 на съответния ученик 1 не е част от отбора, когато 1 го потърси, 1 си тръгва разочарован.

Constraints

1 <= N <= 250 000

Номерата на учениците са в интервала [int.MIN_VALUE, int.MAX_VALUE]

Output Format

На единствения ред от изхода изпишете броя на учениците в екипа.

Sample Input 0

18
5
21 10
18 21
21 14
15 75
75 3
Sample Output 0

3
Explanation 0

18, 21, 14

Sample Input 1

543
8
-123 14
25 2
543 -831
543 29
-831 29
29 25
-999 3
543 12852
Sample Output 1

5
Explanation 1

543, -831, 29, 25, 12852