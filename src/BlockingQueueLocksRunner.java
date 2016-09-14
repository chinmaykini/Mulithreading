import java.util.LinkedList;

public class BlockingQueueLocksRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlockingQueueLocks queue = new BlockingQueueLocks(new LinkedList<>(), 5);
		BlockingQueueLocksProducer producer = new BlockingQueueLocksProducer(queue);
		BlockingQueueLocksConsumer consumer = new BlockingQueueLocksConsumer(queue);
		
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
