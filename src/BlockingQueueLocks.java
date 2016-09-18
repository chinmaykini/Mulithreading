import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class BlockingQueueLocks<T> {
	
	LinkedList<T> queue;
	int capacity;
	Lock lock = new ReentrantLock();
	Condition isFull = lock.newCondition();
	Condition isEmpty = lock.newCondition();
	
	public BlockingQueueLocks(LinkedList<T> queue, int capacity) {

		super();
		lock.lock();
		this.queue = queue;
		this.capacity = capacity;
		lock.unlock();
		
	}
	
	public void enqueue(T item) throws InterruptedException{
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
	
	public T dequeue() throws InterruptedException{
		lock.lock();
		
		try{
			while(queue.size() == 0){
				isEmpty.await();
			}
			
			T item = queue.remove();
			isFull.signalAll();
			return item;
		} finally{
			lock.unlock();
		}

	}
	

}
