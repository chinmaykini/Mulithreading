import java.util.LinkedList;

public class BlockingQueueRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BlockingQueue<Integer> queue = new BlockingQueue(new LinkedList<Integer>(), 5);
		BlockingQueueProducer<Integer> producer = new BlockingQueueProducer(queue);
		BlockingQueueConsumer<Integer> consumer = new BlockingQueueConsumer(queue);
		
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
