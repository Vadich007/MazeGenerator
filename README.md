# Генератор лабиринтов

Данное приложение позволяет генерировать лабиринты произвольного размера и находить пути между точками.
Также приложение открыто для модификации и позволяет легко добавлять новые алгоритмы генерации и решения лабиринтов. 

## Алгоритмы генераций

На данный момент в приложении реализованно два алгоритма генерации: случайный алгоритм Краскала и алгоритм Эйлера

### Cлучайный алгоритм Краскала

Этот алгоритм является рандомизированной версией алгоритма Крускала.

1. Создайте список всех стен и создайте набор для каждой ячейки, каждая из которых содержит только одну ячейку.
2. Для каждой стены, в произвольном порядке:
   1. Если ячейки, разделенные этой стеной, принадлежат к разным наборам
      1. Удалите текущую стену.
      2. Соедините наборы ранее разделенных ячеек.

Существует несколько структур данных, которые можно использовать для моделирования наборов ячеек. Эффективная реализация, использующая структуру данных с непересекающимися наборами, позволяет выполнять каждую операцию объединения и поиска в двух наборах практически за постоянное амортизированное время, поэтому время работы этого алгоритма по существу пропорционально количеству стен, доступных в лабиринте.

Не имеет большого значения, был ли список стен изначально рандомизирован или стена была случайно выбрана из неслучайного списка, в любом случае код одинаково прост.

Поскольку целью этого алгоритма является создание минимального связующего дерева из графа с равновзвешенными ребрами, он, как правило, создает регулярные шаблоны, которые довольно легко разгадать.

[Подробнее об алгоритмах генерации на Википедии](https://en.wikipedia.org/wiki/Maze_generation_algorithm)

### Алгоритм Эйлера

Алгоритм Эллера создает "идеальные" лабиринты, имеющие только один путь между любыми двумя ячейками, по одной строке за раз. Сам алгоритм невероятно быстр и намного экономичнее по объему памяти, чем другие популярные алгоритмы (такие как Прима и Краскала), требующие объема памяти, пропорционального только одной строке. Это позволяет создавать лабиринты неограниченной длины в системах с ограниченной памятью.

1. Инициализируйте ячейки первой строки так, чтобы каждая из них существовала в своем собственном наборе.
2. Теперь случайным образом соедините соседние ячейки, но только если они не находятся в одном наборе. При соединении соседних ячеек объедините ячейки обоих наборов в один набор, указывая, что все ячейки в обоих наборах теперь соединены (есть путь, который соединяет любые две ячейки в наборе).
3. Для каждого набора случайным образом создайте вертикальные связи вниз к следующей строке. Каждый оставшийся набор должен иметь по крайней мере одну вертикальную связь. Ячейки в следующей строке, соединенные таким образом, должны разделять набор ячейки над ними.
4. Дополните следующий ряд, поместив оставшиеся ячейки в отдельные наборы.
5. Повторяйте, пока не достигнете последнего ряда.
6. В последней строке соедините все соседние ячейки, которые не разделяют набор, и исключите вертикальные соединения, и все готово!

[Подробнее об алгоритме Эйлера](http://www.neocomputer.org/projects/eller.html)

## Алгоритмы поиска кратчайшего пути 

На данный момент в приложении реализованно два алгоритма поиска кратчайшего пути: алгоритм Дейкстры и поиск в ширину(BFS)

### Поиск в ширину(BFS)

Один из методов обхода графа. Пусть задан граф G = (V, E) и выделена исходная вершина s
Алгоритм поиска в ширину систематически обходит все ребра для «открытия» всех вершин, достижимых из
s, вычисляя при этом расстояние (минимальное количество рёбер) от s до каждой достижимой из s вершины. Алгоритм работает как для ориентированных, так и для неориентированных графов.

1. Поместить узел, с которого начинается поиск, в изначально пустую очередь.
2. Извлечь из начала очереди узел u и пометить его как развёрнутый.
   1. Если узел
   u является целевым узлом, то завершить поиск с результатом «успех».
   2. В противном случае, в конец очереди добавляются все преемники узла
   u, которые ещё не развёрнуты и не находятся в очереди.
3. Если очередь пуста, то все узлы связного графа были просмотрены, следовательно, целевой узел недостижим из начального; завершить поиск с результатом «неудача».
4. Вернуться к п. 2.

Примечание: деление вершин на развёрнутые и не развёрнутые необходимо для произвольного графа (так как в нём могут быть циклы). Для дерева эта операция не нужна, так как каждая вершина будет выбрана один-единственный раз.

[Подробнее об алгоритме поиска в ширину](https://ru.wikipedia.org/wiki/%D0%9F%D0%BE%D0%B8%D1%81%D0%BA_%D0%B2_%D1%88%D0%B8%D1%80%D0%B8%D0%BD%D1%83)

### Алгоритм Дейкстры

Алгоритм на графах, изобретённый нидерландским учёным Эдсгером Дейкстрой в 1959 году. Находит кратчайшие пути от одной из вершин графа до всех остальных. Алгоритм работает только для графов без рёбер отрицательного веса.

Каждой вершине из множества вершин V сопоставим метку — минимальное известное расстояние от этой вершины до стартовой вершины a.
Алгоритм работает пошагово — на каждом шаге он «посещает» одну вершину и пытается уменьшать метки.
Работа алгоритма завершается, когда все вершины посещены.

Инициализация.

Метка самой вершины a полагается равной 0, метки остальных вершин — бесконечности.
Это отражает то, что расстояния от a до других вершин пока неизвестны.
Все вершины графа помечаются как непосещённые.

Шаг алгоритма.

Если все вершины посещены, алгоритм завершается.
В противном случае, из ещё не посещённых вершин выбирается вершина u, имеющая минимальную метку.
Мы рассматриваем все возможные маршруты, в которых u является предпоследним пунктом. Вершины, в которые ведут рёбра из u, назовём соседями этой вершины. Для каждого соседа вершины u, кроме отмеченных как посещённые, рассмотрим новую длину пути, равную сумме значений текущей метки u и длины ребра, соединяющего u с этим соседом.
Если полученное значение длины меньше значения метки соседа, заменим значение метки полученным значением длины. Рассмотрев всех соседей, пометим вершину u как посещённую и повторим шаг алгоритма.

[Подробнее об алгоритме Дейкстры](https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%94%D0%B5%D0%B9%D0%BA%D1%81%D1%82%D1%80%D1%8B)

## Структура проекта

Проект состоит из следующих директорий и файлов:

- [pom.xml](./pom.xml) – дескриптор сборки, используемый maven, или Project
  Object Model. В нем описаны зависимости проекта и шаги по его сборке
- [src/](./src) – директория, которая содержит исходный код приложения и его
  тесты:
    - [src/main/](./src/main) – здесь находится код вашего приложения
    - [src/test/](./src/test) – здесь находятся тесты вашего приложения
- [mvnw](./mvnw) и [mvnw.cmd](./mvnw.cmd) – скрипты maven wrapper для Unix и
  Windows, которые позволяют запускать команды maven без локальной установки
- [checkstyle.xml](checkstyle.xml),
  [checkstyle-suppression.xml](checkstyle-suppression.xml), [pmd.xml](pmd.xml) и
  [spotbugs-excludes.xml](spotbugs-excludes.xml) – в проекте используются
  [линтеры](https://en.wikipedia.org/wiki/Lint_%28software%29) для контроля
  качества кода. Указанные файлы содержат правила для используемых линтеров
- [.mvn/](./.mvn) – служебная директория maven, содержащая конфигурационные
  параметры сборщика
- [lombok.config](lombok.config) – конфигурационный файл
  [Lombok](https://projectlombok.org/), библиотеки помогающей избежать рутинного
  написания шаблонного кода
- [.editorconfig](.editorconfig) – файл с описанием настроек форматирования кода
- [.github/workflows/build.yml](.github/workflows/build.yml) – файл с описанием
  шагов сборки проекта в среде Github
- [.gitattributes](.gitattributes), [.gitignore](.gitignore) – служебные файлы
  для git, с описанием того, как обрабатывать различные файлы, и какие из них
  игнорировать

## Сборка проекта

Для того чтобы запустить проект на Windows:

```shell
./mvnw compile
./mvnw exec:java -Dexec.mainClass="backend.academy.Main"
```

Для того чтобы запустить проект на Linux:

```shell
./mvn compile
./mvn exec:java -Dexec.mainClass="backend.academy.Main"
```

## Инструкция по использованию

На данный момент приложение поддерживает только консольный интерфейс.

При запуске приложение предложит выбрать алгоритм генерации, параметры лабиринта, алгоритм поиска, начальную и конечную точку.

После чего сначала будет выведен сгенерированный лабиринт, а потом этот же лабиринт, но с построенным путем между заданными точками. 

Каждая ячейка лабиринта получает одну из трех типов поверхности: монетка, проход или грязь.
Поверхность влияет на скорость прохода в соседнюю ячейку, так проход через монетку является самым быстрым, а через грязь самым долгим, что учитывается при поиске ближайшего пути.

Для ровного форматирования каждая ячейка выводится в консоль в виде четырех символов

⬜⬛ \
⬛⬛ - где левый верхний символ является истинной ячейкой лабиринта, правый верхний и левый нижний являются стенами, разделяющими ячейку от других справа и снизу соответственно, а правый нижний всегда является стеной, он используется для выравнивания вывода.

Типы ячеек: \
⬜ - свободная ячейка(проход) \
⬛ - стена \
🟫 - грязь(плохое покрытие) \
🟨 - монетка(хорошее покрытие) \ 
🟩 - проложенный путь \
🟦 - конец пути \
🟪 - начало пути
