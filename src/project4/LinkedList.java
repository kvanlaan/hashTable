package project4;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * This is for creating, adding to, removing from and querying the LinkedList
 * 
 * @author Katrina Van Laan
 */
public class LinkedList<E> {
	// initializing head, current, tail and item count values;
	private ListNode<E> head;
	private ListNode<E> current;
	private ListNode<E> tail;
	private int itemCount = 0;

	/**
	 * Constructor for an empty linked list;
	 *
	 */
	public LinkedList() {
		head = null;
		current = null;
		tail = null;
		itemCount = 0;
	}

	/**
	 * Adds a new ListNode with E item as its data, immediately behind the
	 * current node in the LinkedLoop. After the new item has been added, the
	 * new item becomes the current item.
	 * 
	 * @param E
	 *            item, becomes data in new ListNode
	 * @return boolean signifying that the item has been added;
	 */
	public boolean add(E item) {
		if (isEmpty()) {// initialize list
			head = new ListNode(item);
			current = head;

		} else {
			tail.setNext(new ListNode(item));
			tail = tail.getNext();
		}
		itemCount++;
		return true;
	}

	/**
	 * Returns the data of the head node in the list, the first node in the list
	 * 
	 * @return E item, the data in the head node
	 * @throws NoSuchElementException
	 *             if the list is empty
	 */
	public E getFirst() throws NoSuchElementException {
		if (isEmpty()) {// empty list test
			throw new NoSuchElementException();
		} else {// returns data
			return head.getValue();
		}
	}

	/**
	 * Removes the head node in the list and moves the head to the next node
	 * inthe list
	 * 
	 * @return E item, the data in the node which has been removed
	 * @throws NoSuchElementException
	 *             if the list is empty
	 */
	public E removeFirst() throws NoSuchElementException {

		if (head == null) {// empty list test
			throw new NoSuchElementException();
		} else {// returns data
			head = head.getNext();
			itemCount--;
			return head.getValue();
		}
	}

	/**
	 * Returns true if this list contains the specified element. More formally,
	 * returns true if and only if this list contains at least one element e
	 * such that (o==null ? e==null : o.equals(e))
	 * 
	 * @param o
	 *            - element whose presence in this list is to be tested
	 * @return true if this list contains the specified element
	 */

	public boolean contains(Object o) {
		current = head;
		if (current == o) {
			return true;
		}
		while (current != null) {
			current = current.getNext();
			if (current.getValue() == o) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present. If this list does not contain the element, it is
	 * unchanged. More formally, removes the element with the lowest index i
	 * such that (o==null ? get(i)==null : o.equals(get(i))) (if such an element
	 * exists). Returns true if this list contained the specified element (or
	 * equivalently, if this list changed as a result of the call).
	 * 
	 * @param o
	 *            - element to be removed from this list, if present
	 * @return boolean true if this list contained the specified element
	 */
	public boolean remove(Object o) {
		current = head;
		if (current == o) {
			removeFirst();
			return true;
		}
		while (current.getNext() != null) {
			if (current.getNext().getValue() == o) {
				current.setNext(current.getNext().getNext());
				itemCount--;
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if the LinkedList is empty, i.e., contains no items.
	 * 
	 * @return boolean true if the LinkedList is empty, boolean false if it is
	 *         not;
	 * 
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (itemCount > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Returns the itemCount, number of items in the LinkedLoop.
	 * 
	 * @return int representing the itemCount.
	 * 
	 */
	public int size() {
		// TODO Auto-generated method stub
		return itemCount;
	}

	/**
	 * Iterator for the LinkedList class
	 * 
	 * @return Iterator for the LinkedList class
	 */

	public Iterator iterator() {
		Iterator iter = this.iterator();
		return iter;
	}

}
