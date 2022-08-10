
public class LLQueue implements MyQueue {
	
	private LinkedList queue;
	
	public LLQueue() {
		queue = new LinkedList();
	}
	
	public LLQueue(Object o) {
		queue = new LinkedList();
		queue.addFirst(o);
	}
	
	public int size() {
		LLQueue temp = this;
		temp.queue = this.queue;
		while (!temp.empty()) {
			//temp.queue = temp.queue.getFirst().next();
		}
	}
	
	//Tests if this queue is empty
	public boolean empty() {
		if (queue == null)
			return true;
		return false;
	}
	
	//Inserts the specified element into this queue //Different from add()? //always return true
	public boolean offer(Object item) {
		queue.addLast(item);
		return true;
	}
	//Retrieves and removes the head of this queue,
	//or returns null if this queue is empty. //Different from remove()?
	public Object poll() {
		if (this.empty())
			return null;
		return queue.removeFirst();
	}
	//Retrieves, but does not remove, the head of this queue,
	//or returns null if this queue is empty
	public Object peek() {
		return queue.getFirst();
	}

	//Returns a String representation of your queue
	//public String toString();

	//Adds q to the end of the current queue. Queue q is then cleared.
	public void append(LLQueue q) {
		while (!q.empty()) {
			queue.addLast(q.poll());
		}
	}
	
	//Inserts q to the current queue alternately.
	//Queue q is then cleared.
	public LLQueue alternate(LLQueue q) {
		if (this.size() == q.size()) {
			LLQueue combine = new LLQueue();
			int count = 0;
			while (!this.empty() && !q.empty()) {
				if (count % 2 == 0)
					combine.offer(this.poll());
				else
					combine.offer(q.poll());
			}
			combine.append(q);
			this.queue = combine.queue;
			return combine;
		}
		
	}
	
	public String toString() {
		LLQueue temp = this;
		temp.queue = this.queue;
		String s = "";
		
		while (!temp.empty()) {
			s += temp.poll() + " ";
		}
		
		return s;
	}

	public static void main(String[] args) {
		LLQueue queue = new LLQueue();
		for (int k = 1; k <= 5; k++)
			queue.offer(new Integer(k));
		LLQueue queue2 = new LLQueue();
		for (int k = 11; k <= 15; k++)
			queue2.offer(new Integer(k));
		LLQueue queue3 = new LLQueue();
		for (int k = 21; k <= 27; k++)
			queue3.offer(new Integer(k));
		queue.append(queue3);
		System.out.println( queue);
		while (!(queue.empty())) {
			System.out.print(queue.poll() + " ");
		}
//		LLQueue queue4 = queue3.alternate(queue2);
//		System.out.println("Alternate");
//		System.out.println(queue4);
//		LLQueue queue5 = queue3.alternate(queue2);
//		System.out.println("Alternate");
//		System.out.println(queue4);
	}
}
