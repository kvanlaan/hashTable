package project4;

/**
 * Generic linked list node. It serves as the basic building block for storing
 * data in linked chains of nodes.
 * 
 * @author Katrina Van Laan
 * 
 */
class ListNode<E> {
	private E data; // data to be stored
	private ListNode<E> next; // connection to next node

	/**
	 * Constructs a new list nodes with no links to neighboring nodes.
	 * 
	 * @param data
	 *            the data to be stored in this node
	 */
	ListNode(E data) {
		this(null, data, null);
	}

	/**
	 * Constructs a new list node with links to neighboring nodes.
	 * 
	 * @param prev
	 *            the node before this one
	 * @param data
	 *            the data to be stored in this node
	 * @param next
	 *            the node after this one
	 */
	ListNode(ListNode<E> prev, E data, ListNode<E> next) {

		this.data = data;
		this.next = next;
	}

	/**
	 * Returns the current data.
	 * 
	 * @return the current data
	 */
	public E getValue() {
		return data;
	}

	/**
	 * Returns the current next node.
	 * 
	 * @return the current next node
	 */
	public ListNode<E> getNext() {
		return next;
	}

	/**
	 * Sets the data to the given new value.
	 * 
	 * @param data
	 *            the new data
	 */
	public void setValue(E data) {
		this.data = data;
	}

	/**
	 * Sets the next node to the given new value.
	 * 
	 * @param next
	 *            the new next node
	 */
	public void setNext(ListNode<E> next) {
		this.next = next;
	}

}
