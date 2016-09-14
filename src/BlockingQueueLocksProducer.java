import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BlockingQueueLocksProducer implements Runnable{
	
	BlockingQueueLocks queue;


	public BlockingQueueLocksProducer(BlockingQueueLocks queue) {
		super();
		this.queue = queue;
	}


	@Override
	public void run() {
		for(int i = 0; i< 10; i++){
			try {
				System.out.println("[" +ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME)+ "] Thread " + Thread.currentThread().getId() + " about to enqueue");
				queue.enqueue(Integer.parseInt(""+i));
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
