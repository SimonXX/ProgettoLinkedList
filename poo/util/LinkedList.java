package poo.util;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T>{
	
	
	private static class Nodo<E>{
		E info;
		Nodo<E> next, prior;
	}//Nodo
	
	private enum Move { UNKNOWN, FORWARD, BACKWARD }
	
	
	private Nodo<T> first=null, last=null;
	
	private int size=0, modCounter=0;
	
	public int size() { return size; }
	
	public void clear() { 
		first=null; last=null; size=0; 
		modCounter++;//ogni volta bisogna contare le modifiche
	}//clear

	
	
	//PUNTO 2 PROGETTO LINKED LIST
	//IMPLEMENTAZIONE DEI METODI ATTRAVERSO I PUNTATORI
	
	//versione puntatore
	public void addFirst( T e ) {
		
		Nodo<T> n=new Nodo<>();
		n.info=e; 
		n.next=first; 
		n.prior=null;
		
		if( first!=null ) first.prior=n;//lista non vuota
		else last=n;//lista vuota, l'elemento inserito sarà anche last
		first=n;//ovviamente a prescindere dalla lista, l'elemento inserito sarà first
		size++;
		modCounter++;
		
	}//addFirst
	
	public void addLast(T e) {
		
		Nodo<T> n = new Nodo<>();
		n.info=e;
		n.next = null;
		n.prior = last;
		
		if(first!= null) last.next = n;
		else first = n;
		last = n;
		size++;
		modCounter++;
		
	}//addLast
	
	public T getFirst() {
		
		if(first == null)
			return null;
		
		return first.info;
		
	}//getFirst

	
	public T getLast() {
		
		if(first == null)
			return null;
		
		return last.info;	
		
	}//getLast
	
	public T removeFirst() {
		
		if(first == null)//lista vuota
			return null;
		
		T cor = first.info;//valore da restituire
		
		first = first.next;
		first.prior = null;

		size--;
		modCounter++;
		
		return cor;
		
	}//removeFirst
	
	public T removeLast() {
	
		if(first == null)
			return null;
		
		
		T cor = last.info;
		
		last = last.prior;
		last.next = null;

		size--;
		modCounter++;
		
		return cor;
		
	}//removeLast
	

	public void add( T e ) { addLast(e); }//add
	
	public Iterator<T> iterator(){ return new ListIteratore(); }
	public ListIterator<T> listIterator(){ return new ListIteratore(); }
	
	public ListIterator<T> listIterator( int pos ) { return new ListIteratore(pos); }
	
	private class ListIteratore implements ListIterator<T>{
		
	
		private Nodo<T> pre=null, cor=null;

		private Move lastMove=Move.UNKNOWN;
		private int modCounterMirror=modCounter;
		
		private int posCor;//usata per i metodi nextIndex e previousIndex, verrà inizializzata nei costruttori
		
		public ListIteratore() {
			pre=null; cor=first;
			posCor = -1;
		}
		public ListIteratore( final int pos ) {
			if( pos<0 || pos>size ) 
				throw new IllegalArgumentException();
			
			pre=null; cor=first;
			for( int i=0; i<pos; ++i ) {
				pre=cor; 
				cor=cor.next; 
			}
			
			this.posCor = pos-1;
		}
		
		public boolean hasNext() {
			return cor!=null;
		}//hasNext
		
		public T next() {
			if( modCounterMirror!=modCounter ) 
				throw new ConcurrentModificationException();
			if( !hasNext() ) throw new NoSuchElementException();
			lastMove=Move.FORWARD;
			pre=cor; cor=cor.next;
			
			posCor++;
			
			return pre.info;
		}//next
		
		public void remove() {
			if( modCounterMirror!=modCounter ) 
				throw new ConcurrentModificationException();
			if( lastMove==Move.UNKNOWN ) 
				throw new IllegalStateException();
			Nodo<T> r=null; 
			if( lastMove==Move.FORWARD )
				r=pre;
			else
				r=cor;
			if( r==first ) {
				first=first.next;
				if( first==null ) last=null;
				else first.prior=null;
			}
			else if( r==last ) {
				last=last.prior;
				last.next=null;
			}
			else {
				r.prior.next=r.next;
				r.next.prior=r.prior;
			}
		
			if( lastMove==Move.FORWARD ) {
				pre=r.prior;
				posCor--;
			}
				
			else 
				cor=r.next;
			
			
			size--;
			lastMove=Move.UNKNOWN;
			modCounter++;
			modCounterMirror++;
			
			
			
		}//remove
		
		public boolean hasPrevious() { 
			return pre!=null;
		}//hasPrevious
		
		public T previous() { 
			if( modCounterMirror!=modCounter ) 
				throw new ConcurrentModificationException();
			if( !hasPrevious() ) 
				throw new NoSuchElementException();
			lastMove=Move.BACKWARD;
			cor=pre; pre=pre.prior;
			
			posCor--;
			
			return cor.info;
		}//previous
		
		public void add( T e ) {
			if( modCounterMirror!=modCounter ) 
				throw new ConcurrentModificationException();
			Nodo<T> n=new Nodo<>();
			n.info=e;

			n.next=cor;
			
			n.prior=pre; 
			if( cor!=null ) cor.prior=n;
			if( pre!=null ) pre.next=n;

			pre=n;
	
			if( n.next==first ) first=n;
			if( n.prior==last ) last=n;
			size++;
			lastMove=Move.UNKNOWN;
			modCounter++;
			modCounterMirror++;			
		}//add
		
		public void set( T e ) {
			if( modCounterMirror!=modCounter ) 
				throw new ConcurrentModificationException();
			if( lastMove==Move.UNKNOWN )
				throw new IllegalStateException();
			if( lastMove==Move.FORWARD )
				pre.info=e;
			else
				cor.info=e;
		}//set
		
		//PUNTO 3 PROGETTO LINKEDLIST
		//IMPLEMENTAZIONE DEI METODI NEXTINDEX E PREVIOUSINDEX
		
		
		public int nextIndex() {//prende l'indice del successore
			
			return posCor+1;//se siamo in ultima posizione, pos+1 corrisponde alla size della lista
			
		}//nextIndex
		
		public int previousIndex() { //prende l'indice del predecessore
			
			if(posCor < 0)
				return -1;
			
			return posCor; 
			
		}//previousIndex
	
		
		
	}//ListIterator

}//LinkedList
