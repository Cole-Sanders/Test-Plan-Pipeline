/**
 * 
 */
package edu.ncsu.csc216.stp.model.util;

/**
 * An array based list that changes the position of elements through
 * swap operations. 
 * @author Cole Sanders
 * @param <E> type for the SwapList
 *
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** An array based list of elements E */
	private E[] list;
	/** The number of elements in the list */
	private int size;
	/** The initial capacity of the list */
	private static final int INITIAL_CAPACITY = 10;
	
	/**
	 * Constructs a SwapList by creating a generic object array of length ten and
	 * initializing the size field to zero.
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		Object[] objList = new Object[INITIAL_CAPACITY];
		list = (E[]) objList;
		size = 0;
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	@Override
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		try {
			checkCapacity(size);
			list[size] = element;
			++size;
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot add element.");
		}
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
		E element = list[idx];
		if (idx != size - 1) {
			for (int i = idx; i < size; ++i) {
				try {
					list[i] = list[i + 1];
				} catch(IndexOutOfBoundsException e) {
					list[i] = null;
				}
			}
		} else {
			list[idx] = null;
		}
		--size;
		return element;
	}
	
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveUp(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E placeHolder = list[idx];
			list[idx] = list[idx - 1];
			list[idx - 1] = placeHolder;
		}
	}
	
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveDown(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			E placeHolder = list[idx];
			list[idx] = list[idx + 1];
			list[idx + 1] = placeHolder;
		}
	}
	
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToFront(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E placeHolder = list[idx];
			for (int i = idx - 1; i >= 0; --i) {
				list[i + 1] = list[i];
			}
			list[0] = placeHolder;
		}
	}
	
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	@Override
	public void moveToBack(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			E placeHolder = list[idx];
			for (int i = idx; i < size - 1; ++i) {
				list[i] = list[i + 1];
			}
			list[size - 1] = placeHolder;
		}
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
		return list[idx];
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
	 * Checks if the list is full. If it is the capacity is doubled.
	 * @param size the number of elements in the list
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int size) {
		if (size > 0 && size % INITIAL_CAPACITY == 0) {
			Object[] objSwap = new Object[size * 2];
			E[] newSwap = (E[]) objSwap;
			for (int i = 0; i < size; ++i) {
				newSwap[i] = list[i];
			}
			list = newSwap;
		}
	}
}
