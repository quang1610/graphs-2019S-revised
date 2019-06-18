package heapPriorityQueue;
import java.io.PrintWriter;
import java.util.Random;

public class HeapPriorityQueueExp {
	public static void main(String[] args) {
		HeapPriorityQueue<String> hpq = new HeapPriorityQueue<String>();
		HeapPriorityQueue<Integer> intHpq = new HeapPriorityQueue<Integer>();
		Random rand = new Random();
		
		PrintWriter pen = new PrintWriter(System.out, true);
		
		// in order
		pen.println("In order test");
		hpq.add("a");
		hpq.add("b");
		hpq.add("c");
		hpq.add("d");
		hpq.add("e");
		hpq.add("f");
		hpq.dump(pen);
		while (hpq.getSize() > 0) {
			pen.println("Remove: " + hpq.remove());
		}
		pen.println("");
		
		//reverse
		pen.println("Reverse test");
		hpq.add("f");
		hpq.add("e");
		hpq.add("d");
		hpq.add("c");
		hpq.add("b");
		hpq.add("a");
		while (hpq.getSize() > 0) {
			pen.println("Remove: " + hpq.remove());
		}
		
		// randomTest
		for(int i  = 0; i < 1000; i++) {
			intHpq.add(rand.nextInt());
		}
		
		int removed = intHpq.remove();
		while(hpq.getSize() > 0) {
			int justRemoved = intHpq.remove();
			if(justRemoved < removed) {
				pen.println("Error");
			}
			removed = justRemoved;
		}
		
	}
}
