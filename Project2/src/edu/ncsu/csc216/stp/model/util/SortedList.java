/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;


/**
 * A LinkedList that keeps objects in sorted order as defined by the
 * Comparable interface.
 * @author Cole Sanders
 * @param <E> type for SortedList
 *
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	
	/** The size of the list */
	private int size;
	/** The reference to the first ListNode in the list */
	private ListNode front;
	
	/**
	 * Constructs a SortedList and initializes its field.
	 */
	public SortedList() {
		size = 0;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is already in the list
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (front != null) {
			ListNode current = front;
			for (int i = 0; i < size; ++i) {
				if (current.data.equals(element)) {
					throw new IllegalArgumentException("Cannot add duplicate element.");
				}
				current = current.next;
			}
			current = front;
			if (element.compareTo(front.data) < 0) {
				ListNode add = new ListNode(element, front);
				front = add;
			} else {
				ListNode prevCurrent = front;
				for (int i = 0; i < size; ++i) {
					if (element.compareTo(current.data) < 0) {
						prevCurrent.next = new ListNode(element, current);
						break;
					} else if (i == size - 1) {
						current.next = new ListNode(element, null);
					}
					prevCurrent = current;
					current = current.next;
				}
			}
		} else {
			front = new ListNode(element, null);
		}
		++size;
	}
	
	/**
	 * Returns the element from the given index.  The element is
	 * removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E remove(int idx) {
		checkIndex(idx);
		E remove;
		if (size == 1) {
			remove = front.data;
			front = null;
		} else if (idx != 0) {
			ListNode current = front;
			for (int i = 0; i < idx - 1; ++i) {
				current = current.next;
			}
			remove = current.next.data;
			current.next = current.next.next;
		} else {
			remove = front.data;
			front = front.next;
		}
		--size;
		return remove;
	}
	
	/**
	 * Returns true if the element is in the list.
	 * @param element element to search for
	 * @return true if element is found
	 */
	@Override
	public boolean contains(E element) {
		if (front == null) {
			return false;
		}
		ListNode current = front;
		if (front.data.equals(element)) {
			return true;
		}
		for (int i = 0; i < size - 1; ++i) {
			current = current.next;
			if (current.data.equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public E get(int idx) {
		checkIndex(idx);
		ListNode current = front;
		if (idx == 0) {
			return front.data;
		} else {
			for (int i = 0; i < idx - 1; ++i) {
				current = current.next;
			}
			return current.next.data;
		}
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Checks if the index is valid for the list.
	 * @param idx the index value
	 * @throws IndexOutOfBoundsException if the index is outside the scope of the
	 * list.
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * A class that represents a single node in a linked list. Each node contains
	 * a data fields storing the desired value and a next field to reference the next
	 * node in the list.
	 * @author Cole Sanders
	 *
	 */
	private class ListNode {
		
		/** The data the ListNode is storing */
		public E data;
		/** The reference to the next ListNode */
		public ListNode next;
		
		
		/**
		 * Constructs a ListNode and initializes the data and next fields with passed in parameter
		 * values.
		 * @param data the data used to initialize the data field.
		 * @param next the reference to the next ListNode used to initialize the next field.
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
	
}
