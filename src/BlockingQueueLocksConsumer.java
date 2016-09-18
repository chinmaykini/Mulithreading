import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class BlockingQueueLocksConsumer<T> implements Runnable{

	BlockingQueueLocks<T> queue;
	
	public BlockingQueueLocksConsumer(BlockingQueueLocks<T> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		for(int i = 0; i< 10; i++){
			try {
				System.out.println("[" +ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)+ "] Thread " + Thread.currentThread().getId() + " about to dequeue");
				T item = queue.dequeue();
				System.out.println("[" +ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)+ "] Thread " + Thread.currentThread().getId() + " dequed item " + (Integer) item);
				Thread.sleep(5000);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

}
