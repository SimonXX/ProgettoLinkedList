**Progetto LinkedList**

**Punto 1**

Utilizzando esclusivamente i metodi del `ListIterator<T>`, si implementa una variante del metodo static `sort` previsto nell'interfaccia `List<T>`. Il metodo in questione basa il suo funzionamento sull'algoritmo di ordinamento "insertion sort".

Il metodo riceve in input una lista generica in `<T>`, un oggetto comparatore `c` ed una flag booleana.

Il metodo applica, alla lista passata come parametro, l'ordinamento secondo bubbleSort se la flag risulta essere `false`, altrimenti applica l'ordinamento secondo insertionSort: gli elementi costituenti la lista verranno confrontati ed ordinati secondo l'oggetto comparatore passato come parametro.

L'idea di base, sulla quale si fonda la costruzione del metodo, poggia sulla classica implementazione del metodo `insertionSort`, adattato ovviamente al caso della Linked List.

Se la lista risulta essere vuota o costituita da un solo elemento, si ha un caso particolare e si restituisce la lista stessa (che è già ordinata).

Altrimenti, ad ogni iterazione di un ciclo, che termina al finire degli elementi della lista, si prende come riferimento un elemento `x` da ordinare, a partire dalla posizione `i = 1`, sino ad arrivare a `i = size` (si noti che alla posizione 0 l'elemento non segue alcun valore).

Al fine di scandire gli elementi della lista, e spostarsi opportunamente su di essa, prelevandone i valori, si utilizza un list iterator dichiarato di volta in volta a partire dalla posizione `i`. È opportuno notare che gli elementi della sottolista che vanno da 0 a `i-1` saranno ordinati.

All'interno di un ulteriore ciclo, annidato nel precedente, l'elemento `x` viene confrontato, a partire da destra verso sinistra, con i suoi precedenti, memorizzati, di volta in volta, all'interno di una variabile `y`: verranno spostati a destra, di una posizione, gli elementi maggiori del valore `x` preso come riferimento.

Il ciclo interno prosegue, quindi, fin quando il valore `y`, che di volta in volta viene memorizzato attraverso l'iteratore, risulta maggiore del valore `x` (elemento da ordinare nella iterazione corrente). Altrimenti, se l'elemento precedente ad `x` non risulta essere maggiore, non viene effettuato alcuno scambio e si passa direttamente all'elemento successivo, terminando l'iterazione nel ciclo innestato, poiché essendo la sottolista ordinata da 0 a `i-1`, anche i valori a ritroso risulteranno non maggiori.

A termine dell'iterazione del ciclo innestato, ogni volta, il valore di `x` verrà inserito, all'interno della lista, nella posizione corrente dell'iteratore, si avrà di conseguenza una sottolista ordinata da sinistra verso destra sino al valore corrente da ordinare.

Al termine del ciclo più esterno, l'intera lista risulterta ordinata.

Si propone all'interno del main della classe concreta LinkedList, un test del metodo sopra trattato, effettuato sulla linkedList "test1".

**Punto 2**

Utilizzando esclusivamente i puntatori si implementano i metodi proposti in veste iterativa, all'interno della classe concreta LinkedList. I metodi in questione risultano essere `addFirst`, `addLast`, `getFirst`, `getLast`, `removeFirst`, `removeLast`.

- Il metodo `addFirst` aggiunge nella prima posizione della lista l'elemento "e" passato come parametro. Viene quindi creato un nuovo nodo "n" che ha come valore l'elemento stesso (`n.info = e`), come successivo il primo nodo della lista in questione (`n.next = first`), ovvero l'attuale testa, e come precedente il valore nullo (`n.prior = null`).

  Nel caso in cui la lista non fosse vuota (`first != null`), si pone uguale ad `n` il precedente della testa attuale della lista (`first.prior = n`); nel caso in cui la lista fosse vuota, l'elemento inserito sarà anche l'ultimo elemento della lista e di conseguenza si pone la coda della lista uguale proprio all'elemento inserito (`last = n`).

  Indipendentemente dalla lista, il nodo appena inserito costituirà la nuova testa della lista (`first = n`).

  Si incrementano inoltre la lunghezza della lista (`size`) e la variabile `modCounter`, relativa al numero di modifiche effettuate sulla lista (utilizzata al fine di gestire la ConcurrentModificationException dell'iteratore).

- Il metodo `addLast` aggiunge nell'ultima posizione della lista l'elemento "e" passato come parametro. Viene quindi creato un nuovo nodo "n" che ha come valore l'elemento stesso (`n.info = e`), come successivo il valore nullo (`n.next = null`) e come precedente l'ultimo nodo della lista in questione (`n.prior = last`), ovvero l'attuale coda.

  Nel caso in cui la lista non fosse vuota (`first != null`), si pone uguale ad `n` il successivo della coda attuale della lista (`last.next = n`); nel caso in cui la lista fosse vuota, l'elemento inserito sarà anche il primo elemento della lista e di conseguenza si pone la testa della lista uguale proprio all'elemento inserito (`first = n`).

  Indipendentemente dalla lista, il nodo appena inserito costituirà la nuova coda della lista (`last = n`).

  Si incrementano inoltre la lunghezza della lista (`size`) e la variabile `modCounter`, relativa al numero di modifiche effettuate sulla lista (utilizzata al fine di gestire la ConcurrentModificationException dell'iteratore).

- Il metodo `getFirst` restituisce il primo elemento appartenente alla lista in questione. Se quest'ultima risulta essere vuota (`first == null`), si restituisce il valore nullo, altrimenti si restituisce il valore relativo alla testa della lista (`first.info`). Nessuna modifica viene effettuata sulla lista.

- Il metodo `getLast` restituisce l'ultimo elemento appartenente alla lista in questione. Se quest'ultima risulta essere vuota (`first == null`), si restituisce il valore nullo, altrimenti si restituisce il valore relativo alla coda della lista (`last.info`). Nessuna modifica viene effettuata sulla lista.

- Il metodo `removeFirst` rimuove il primo elemento della lista. Se la lista risulta essere vuota (`first == null`), viene sollevata un'eccezione di tipo `NoSuchElementException`. Altrimenti, si imposta come nuova testa della lista il nodo successivo al primo nodo attuale (`first = first.next`).

  Nel caso in cui il nodo successivo al primo nodo sia nullo (`first == null`), significa che la lista contiene un solo elemento. In tal caso, la coda della lista viene posta a valore nullo (`last = null`).

  In ogni caso, si decrementa la lunghezza della lista (`size`) e si incrementa la variabile `modCounter`, relativa al numero di modifiche effettuate sulla lista.

- Il metodo `removeLast` rimuove l'ultimo elemento della lista. Se la lista risulta essere vuota (`first == null`), viene sollevata un'eccezione di tipo `NoSuchElementException`. Altrimenti, si imposta come nuova coda della lista il nodo precedente all'ultimo nodo attuale (`last = last.prior`).

  Nel caso in cui il nodo precedente all'ultimo nodo sia nullo (`last == null`), significa che la lista contiene un solo elemento. In tal caso, la testa della lista viene posta a valore nullo (`first = null`).

  In ogni caso, si decrementa la lunghezza della lista (`size`) e si incrementa la variabile `modCounter`, relativa al numero di modifiche effettuate sulla lista.

Si propone all'interno del main della classe concreta LinkedList, un test dei metodi sopra trattati, effettuato sulla linkedList "test2".

*Nota:* Si prega di notare che la sintassi e la struttura del codice potrebbero variare in base all'implementazione specifica e alle preferenze dell'autore. Si consiglia di adattare il codice alle esigenze del proprio progetto.
