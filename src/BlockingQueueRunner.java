import java.util.LinkedList;

public class BlockingQueueRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlockingQueue queue = new BlockingQueue(new LinkedList<>(), 5);
		BlockingQueueProducer producer = new BlockingQueueProducer(queue);
		BlockingQueueConsumer consumer = new BlockingQueueConsumer(queue);
		
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
