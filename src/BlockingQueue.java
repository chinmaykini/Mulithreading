import java.util.LinkedList;

public class BlockingQueue<T> {
	public LinkedList<T> queue;
	public int capacity;
	
	public BlockingQueue(LinkedList<T> queue, int capacity) {
		super();
		this.queue = queue;
		this.capacity = capacity;
	}
	
	public synchronized void enqueue(T item) throws InterruptedException{
		
		while(queue.size() == capacity){
			wait();
		}
		
		if(queue.size() == 0){
			notifyAll();
		}
		
		queue.add(item);
	}

	public synchronized T dequeue() throws InterruptedException{
		
		while(queue.size() == 0){
			wait();
		}
		
		if(queue.size() == capacity){
			notifyAll();
		}
		
		return queue.remove();
	}

}
