**Forschungsdaten**

Die Pinguine der PUM erheben im Laufe ihrer Arbeit und Forschung eine große Menge an Daten. Viele von den Daten treten in Paaren auf und werden deswegen als 2-Tupel gespeichert. Die Pinguine wollen natürlich den Speicherverbrauch der Daten minimieren. Sie haben festgestellt, dass sie viele von den Daten bereits schon mal gespeichert haben. Um diese Redundanz zu vermeiden, wollen sie sich an den Stringpools in Java orientieren und haben sich folgende Struktur ausgedacht.

**Tuple Trouble**

**Traue keiner Statistik, die du nicht selbst gespeichert hast**

Zuerst benötigen wir eine Möglichkeit, die Forschungsdaten zu speichern. Hierzu wollen wir ein 2-Tupel verwenden, um ein Paar an Daten zu speichern. Da Daten in allen möglichen Formen anfallen können (Zahlen, Strings, Filme etc.), soll dieses Tupel generisch sein. Damit der Pool funktioniert, benötigen wir noch eine weitere Eigenschaft: Da unsere Pinguine nach höchsten wissenschaftlichen Standards arbeiten und keine Daten im Nachhinein "korrigiert" werden müssen, sollen die Tupel unveränderlich sein. Folgendes benötigen wir in der Tuple-Klasse:

1. Zwei private Attribute, die als Typ den generischen Typparameter T bzw. S haben.
2. Einen Konstruktor welcher die Argumente T t und S s in dieser Reihenfolge entgegennimmt und abspeichert.
3. Getter für die Attribute t und s.
4. equals die genau dann true zurückgibt, wenn die gespeicherten Attribute übereinstimmen.
5. Und hashCode, die einen Hashwert für das jeweilige Tupel zurückgibt. Um diese zu implementieren darf die static Methode Objects.hash verwendet werden.

**Ein Pool nicht zum schwimmen**

Jetzt brauchen wir eine Möglichkeit, um unsere Tupel zu speichern. Hierfür sollt ihr jetzt die TuplePool-Klasse erweitern. Diese stellt Funktionalität zum Speichern und Finden von Tupeln zur Verfügung und verwaltet ein TupleHashSet, welches wir euch zur Verfügung gestellt haben. Hier sollen drei Methoden implementiert werden:

1. Konstruktor Im Konstruktor soll das TupleHashSet erstellt werden, welches wir verwalten.

2. insert(): Mit dieser Methode soll ein Tupel in den Pool eingefügt werden. Falls ein gleiches Tupel bereits vorhanden ist, wird das übergebene Tupel verworfen. Es soll das Tupel zurückgegeben werden, welches letztendlich im Pool gespeichert ist.

3. getByValue(): Diese Methode soll ein Tupel mit den übergebenen Werten in dem Pool finden. Falls kein Tupel gefunden wurde, soll null zurück gegeben werden.

4. getNumberOfTuples(): Um die Größe eures Pools abzuschätzen, soll die Menge an einzigartigen Tupeln gezählt, und über diese Methode abfragbar sein.

Hinweis: Das TupleHashSet hat nur eine begrenzte Größe. Ihr müsst nicht prüfen, ob diese Größe überschritten wird.

**Jetzt wird wieder in die Hände gespuckt …**

Um nicht unnötig Tupel zu erstellen, wollen wir nicht direkt den Konstruktor der Tupel-Klasse aufrufen. Stattdessen erstellen wir die Klasse TupleFactory, die das Erstellen von Tupeln verwaltet. Diese Fabrik produziert uns die benötigten Tupel-Objekte. Die TupleFactory bekommt die Werte zum Erstellen übergeben und gibt ein Tupel-Objekt zurück.

1. Konstruktor: Hier wird ein TuplePool initialisiert

2. create(): Hier werden zwei Werte T t und S s übergeben. Falls bereits ein Tupel mit diesen Werten im Pool ist, wird dieses zurückgegeben. Falls noch kein Tupel existiert, soll ein Neues erstellt, in den Pool eingefügt und zurückgegeben werden.

3. intern(): Falls doch schon ein Tupel-Objekt erstellt wurde, soll es mit der intern()-Methode in den Pool eingefügt werden können. Auch intern() gibt wie create() am Ende das Objekt zurück, welches sich im Pool befindet.

**Iteratoren Iterieren**

In Java gibt es das Interface Iterable, welches einen standardisierten Weg ermöglicht, auf Elemente einer Sammlung zuzugreifen. Unser TupleHashSet soll nun das Interface implementieren. Das Interface bietet mehrere Methoden an, wir wollen allerdings nur die iterator()-Methode implementieren. Die Methode an sich gibt einfach ein Iterator-Objekt zurück. Dieses Iterator-Objekt muss jedoch selbst implementiert werden.

**The Iterator is coming**

Ein Iterator hat zwei Standard-Methoden:

1. hasNext(): Um zu überprüfen, ob überhaupt noch ein unbesuchtes Objekt zur Verfügung steht, gibt es die hasNext()-Methode. Diese überprüft ob noch ein weiteres Objekt existiert und gibt dementsprechend ein boolean zurück.
2. next(): Diese Methode gibt des nächste Element in der Sammlung zurück. Da next() nur aufgerufen werden sollte, falls man zuvor überprüft hat, ob es überhaupt ein nächstes Element gibt, soll next() eine NoSuchElementException werfen, falls es kein Element mehr gibt.

Hinweis: Außer den geforderten Änderungen, darf der Klasse keine weitere public Methode hinzugefügt werden.
