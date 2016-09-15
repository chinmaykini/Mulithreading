import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class BlockingQueueLocks {
	
	LinkedList queue;
	int capacity;
	Lock lock = new ReentrantLock();
	Condition isFull = lock.newCondition();
	Condition isEmpty = lock.newCondition();
	
	public BlockingQueueLocks(LinkedList queue, int capacity) {

		super();
		lock.lock();
		this.queue = queue;
		this.capacity = capacity;
		lock.unlock();
		
	}
	
	public void enqueue(Object item) throws InterruptedException{
		lock.lock();	
		try{
			while(queue.size() == capacity){
				isFull.await();
			}
			queue.add(item);
			isEmpty.signalAll();
			
		} finally{
			lock.unlock();
		}

		
	}
	
	public Object dequeue() throws InterruptedException{
		lock.lock();
		
		try{
			while(queue.size() == 0){
				isEmpty.await();
			}
			
			Object item = queue.remove();
			isFull.signalAll();
			return item;
		} finally{
			lock.unlock();
		}

	}
	

}
