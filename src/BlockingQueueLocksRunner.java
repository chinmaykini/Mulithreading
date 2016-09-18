import java.util.LinkedList;

public class BlockingQueueLocksRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlockingQueueLocks<Integer> queue = new BlockingQueueLocks<Integer>(new LinkedList<>(), 5);
		BlockingQueueLocksProducer<Integer> producer = new BlockingQueueLocksProducer<Integer>(queue);
		BlockingQueueLocksConsumer<Integer> consumer = new BlockingQueueLocksConsumer<Integer>(queue);
		
		Thread producerThread = new Thread(producer);
		Thread consumerThread = new Thread(consumer);
		
		producerThread.start();
		consumerThread.start();
		
		try {
			producerThread.join();
			consumerThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Done");
	}

}
