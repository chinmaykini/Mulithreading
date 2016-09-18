import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class BlockingQueueLocksProducer<T> implements Runnable{
	
	BlockingQueueLocks<T> queue;


	public BlockingQueueLocksProducer(BlockingQueueLocks<T> queue) {
		super();
		this.queue = queue;
	}


	@Override
	public void run() {
		for(int i = 0; i< 10; i++){
			try {
				System.out.println("[" +ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)+ "] Thread " + Thread.currentThread().getId() + " about to enqueue");
				@SuppressWarnings("unchecked")
				T data = (T) new Integer(Integer.parseInt(""+i));
				queue.enqueue(data);
				System.out.println("[" +ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)+ "] Thread " + Thread.currentThread().getId() + " enqueued item " + i);
				Thread.sleep(1000);
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
