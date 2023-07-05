package poo.util;

import java.util.ListIterator;

public class Main {


    public static void main( String[] args ) {

        //Test prova punto 1
        //Insertion sort

        System.out.println("Punto 1");
        LinkedList<Integer> test1 = new LinkedList<>();

        test1.add(5);
        test1.add(2);
        test1.add(11);
        test1.add(10);
        test1.add(-23);
        test1.addLast(12);
        test1.addLast(25);
        test1.addLast(21);

        System.out.println("lista non ordinata: " + test1);

        List.sort(test1, (i1,i2)->{
            return i1<i2?-1:i1>i2?1:0;
        }, true );

        System.out.println("lista ordinata: " + test1);

        System.out.println();
        System.out.println("Punto 2");

        //Test prova punto 2
        //Implementazion metodi con i puntatori

        LinkedList<Integer> test2 = new LinkedList<>();
        System.out.println(test2);
        test2.addFirst(4);
        System.out.println(test2);

        test2.addFirst(8);
        System.out.println(test2);

        test2.addLast(5);
        System.out.println(test2);

        test2.addLast(3);
        System.out.println(test2);

        test2.removeFirst();
        System.out.println(test2);

        test2.removeLast();
        System.out.println(test2);

        System.out.println("Primo elemento lista: " + test2.getFirst());
        System.out.println("Ultimo elemento lista: " + test2.getLast());


        System.out.println();
        System.out.println("Punto 3");

        //Punto 3
        //implementazione metodi nextIndex e previousIndex

        LinkedList<Integer> test3 = new LinkedList<>();
        test3.add(8);
        test3.add(4);
        test3.add(-2);
        test3.add(12);
        test3.add(17);
        test3.add(19);
        test3.add(22);;

        System.out.println(test3);

        ListIterator<Integer> lit = test3.listIterator();

        System.out.println("Next index: " + lit.nextIndex());
        System.out.println("Previous index: " + lit.previousIndex());

        System.out.println();
        lit.next();
        System.out.println("Eseguendo una next");

        System.out.println("Next index: " + lit.nextIndex());
        System.out.println("Previous index: " + lit.previousIndex());

        lit.next();
        lit.next();
        lit.next();

        System.out.println();
        System.out.println("Eseguendo altre 3 next");

        System.out.println("Next index: " + lit.nextIndex());
        System.out.println("Previous index: " + lit.previousIndex());

        lit.previous();
        System.out.println();
        System.out.println("Eseguendo una previous");
        System.out.println("Next index: " + lit.nextIndex());
        System.out.println("Previous index: " + lit.previousIndex());

        lit.remove();
        System.out.println();
        System.out.println("Eseguendo una remove");
        System.out.println(test3);
        System.out.println("Next index: " + lit.nextIndex());
        System.out.println("Previous index: " + lit.previousIndex());

        lit.next();
        lit.previous();
        lit.previous();
        System.out.println();
        System.out.println("un'altra next e due previous");

        System.out.println("Next index: " + lit.nextIndex());
        System.out.println("Previous index: " + lit.previousIndex());

        //Usando un iteratore inizializzato ad una specifica posizione
        int pos = 2;
        System.out.println();
        System.out.println("Iteratore inizializzato a " + pos);
        LinkedList<Integer> test4 = new LinkedList<>();
        test4.add(8);
        test4.add(4);
        test4.add(-2);
        test4.add(12);
        test4.add(17);
        test4.add(19);
        test4.add(22);;

        System.out.println(test4);

        ListIterator<Integer> lit2 = test4.listIterator(pos);

        System.out.println("Next index: " + lit2.nextIndex());

        System.out.println("Previous index: " + lit2.previousIndex());

    }//main

}
