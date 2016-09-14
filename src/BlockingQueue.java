import java.util.LinkedList;

public class BlockingQueue {
	public LinkedList queue;
	public int capacity;
	
	public BlockingQueue(LinkedList queue, int capacity) {
		super();
		this.queue = queue;
		this.capacity = capacity;
	}
	
	public synchronized void enqueue(Object item) throws InterruptedException{
		
		while(queue.size() == capacity){
			wait();
		}
		
		if(queue.size() == 0){
			notifyAll();
		}
		
		queue.add(item);
	}

	public synchronized Object dequeue() throws InterruptedException{
		
		while(queue.size() == 0){
			wait();
		}
		
		if(queue.size() == capacity){
			notifyAll();
		}
		
		return queue.remove();
	}

}
