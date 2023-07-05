package poo.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T>{
	
	
	default int size() {
		int c=0;
		for( @SuppressWarnings("unused") T x: this ) c++;
		return c;
	}//size
	
	default void clear() {
		Iterator<T> it=this.iterator();
		while( it.hasNext() ) {
			it.next();
			it.remove();
		}
	}//clear
	
	default boolean contains( T e ) {
		for( T x: this )
			if( x.equals(e) ) return true;
		return false;
	}//contains
	
	default void add( T e ) {
		ListIterator<T> lit=listIterator( size() );
		lit.add( e );
	}//add
	
	default void remove( T e ) {
		Iterator<T> it=iterator();
		while( it.hasNext() ) {
			T x=it.next();
			if( x.equals(e) ) { it.remove(); break; }
		}
	}//remove
	
	default boolean isEmpty() {
		return !listIterator().hasNext();
	}//isEmpty
	
	default boolean isFull() {
		return false;
	}//isFull
	
	ListIterator<T> listIterator();
	ListIterator<T> listIterator( int pos );
	
	//bubble sort
	static <T> void sort( List<T> lis, Comparator<? super T> c ){
		
		//default: bubble sort
		if( lis.size()<=1 ) return;
		boolean scambi=true;
		int limite=lis.size(), pus=0;
		while( scambi ) {
			ListIterator<T> lit=lis.listIterator();
			T x=lit.next(), y=null;
			int pos=1; scambi=false; //ottimismo
			while( pos<limite ) {
				y=lit.next();
				if( c.compare(x,y)>0 ) {
					lit.set(x);
					lit.previous(); lit.previous();
					lit.set(y);
					lit.next(); lit.next(); 
					scambi=true; pus=pos;
				}
				else { x=y; }
				pos++;
			}//while interno
			limite=pus;
		}//while esterno
	}//sort
	
	
	//PUNTO 1 PROGETTO LINKEDLIST
	//VARIANETE DEL METODO STATIC SORT, CHE SFRUTTA INSERTION SORT
	static <T> void sort( List<T> lis, Comparator<? super T> c, boolean flag ) {
		//se la booleana è false, il metodo richiama il bubble sort precedente, altrimenti si usa l'insertion sort(adattato alla linked list)
		
		if( !flag ) sort(lis,c);
		
		else {
			
			if(lis.size()<=1)return;
		
			for(int i=1; i<lis.size(); i++) {
				
				ListIterator<T> lit = lis.listIterator(i);
				T x=null;
				
				x = lit.next();
				
				boolean scambio = false;
				lit.previous();
				
				do {
					T y = lit.previous();
					
					if(c.compare(y, x)>0) {
						lit.next();
						lit.next();
						lit.set(y);
						scambio = true;
					}else {
						lit.next();
						lit.next();
						break;
					}
					
					lit.previous();
					lit.previous();
					
					
				}while(lit.hasPrevious() && scambio);
					
					
				lit.set(x);
		
		}
			
		}
	}//sort
	

}//List
