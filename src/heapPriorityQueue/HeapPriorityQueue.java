package heapPriorityQueue;
import java.io.PrintWriter;

public class HeapPriorityQueue<T extends Comparable<T>> {
	private T[] queue;
	private int pos;

	// *************************************************************************************************************************
	// Constructor
	@SuppressWarnings("unchecked")
	public HeapPriorityQueue(int initialSize) {
		this.queue = (T[]) new Comparable[initialSize];
		this.pos = 0;
	}

	public HeapPriorityQueue() {
		this(1);
	}

	// *************************************************************************************************************************
	// Methods

	public void add(T val) {
		if (this.getSize() >= this.queue.length - 1) {
			expand();
		}

		this.queue[this.pos++] = val;
		if (this.getSize() > 1) {
			performSwapUp();
		}
	}

	public T peek() {
		return this.queue[0];
	}

	// if the queue is empty we return null, else we return the value at the
	// beginning of the array.
	public T remove() {
		if (this.getSize() > 0) {
			T returnVal = this.queue[0];
			this.swap(0, this.pos - 1);
			this.queue[--pos] = null;
			
			performSwapDown();
			return returnVal;
		}
		return null;
	}

	// **************************************************************************************************************************
	// Helper methods
	@SuppressWarnings("unchecked")
	private void expand() {
		T[] newQueue = (T[]) new Comparable[this.queue.length * 2];

		for (int i = 0; i < this.queue.length; i++) {
			newQueue[i] = this.queue[i];
		}

		this.queue = newQueue;
	}

	private void performSwapUp() {
		// tempPos starts at the end of the heap array
		int tempPos = this.pos - 1;
		int parentPos = (tempPos - 1) / 2;

		// while temPos is larger its parent, we swap it up
		while (parentPos >= 0 && this.cmp(tempPos, parentPos) < 0) {
			// if the child is smaller than its parent we perform swapping.
			this.swap(tempPos, parentPos);

			tempPos = parentPos;
			parentPos = (tempPos - 1) / 2;
		}
	}

	private void performSwapDown() {
		// we start at the beginning of the heap array and swap down
		int tempPos = 0;

		// we will keep swapping down queue in order to maintain the order after
		// removing the first element of the queue
		int smallerChildPos = this.getSmallerChildPos(tempPos);
		while (smallerChildPos != -1 && this.cmp(tempPos, smallerChildPos) > 0) {
			// while we still have children and the children is smaller than the current, we
			// swap them
			swap(tempPos, smallerChildPos);

			tempPos = smallerChildPos;
			smallerChildPos = this.getSmallerChildPos(tempPos);		
			}

	}

	private int getSmallerChildPos(int pos) {
		int firstChildPos = 2 * pos + 1;
		int secondChildPos = 2 * pos + 2;

		if (firstChildPos >= this.getSize() && secondChildPos >= this.getSize()) {
			return -1;
		}
		if (firstChildPos >= this.getSize()) {
			return secondChildPos;
		} else if (secondChildPos >= this.getSize()) {
			return firstChildPos;
		} else {
			// return the position with the kid with
			return this.cmp(firstChildPos, secondChildPos) < 0 ? firstChildPos : secondChildPos;
		}
	}

	private void swap(int pos1, int pos2) {
		if (pos1 != pos2) {
			T temp = this.queue[pos1];
			this.queue[pos1] = this.queue[pos2];
			this.queue[pos2] = temp;
		}
	}

	public int getSize() {
		return this.pos;
	}

	// print out the whole queue
	public void dump(PrintWriter pen) {
		for (int i = 0; i < this.getSize(); i++) {
			pen.print(this.queue[i] + " ");
		}
		pen.flush();
		pen.println("");
	}

	private int cmp(int pos1, int pos2) {
		return this.queue[pos1].compareTo(this.queue[pos2]);
	}
}
