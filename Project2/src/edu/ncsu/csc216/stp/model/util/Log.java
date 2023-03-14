/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * Represents as list where elements can only be added
 * to the end of the list.  Once an element is added, it cannot
 * be removed. Duplicates are allowed.
 * @author Cole Sanders
 * @param <E> type for the Log
 *
 */
public class Log<E> implements ILog<E> {
	/** An array based list of elements E */
	private E[] log;
	/** The number of elements in the list */
	private int size;
	/** The initial capacity of the list */
	private static final int INIT_CAPACITY = 10;
	
	/**
	 * Constructs a Log and initializes the log and size fields.
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		Object[] objLog = new Object[INIT_CAPACITY];
		log = (E[]) objLog;
		size = 0;
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param value element to add
	 * @throws NullPointerException if element is null 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void add(E value) {
		if (value == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (size > 0 && size % INIT_CAPACITY == 0) {
			Object[] objLog = new Object[size * 2];
			E[] newLog = (E[]) objLog;
			for (int i = 0; i < size; ++i) {
				newLog[i] = log[i];
			}
			log = newLog;
		}
		log[size] = value;
		++size;
	}
	
	/**
	 * Returns the element at the given index.
	 * @param index index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return log[index];
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
}
