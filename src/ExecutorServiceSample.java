import java.util.*;
import java.util.concurrent.*;

public class ExecutorServiceSample {
	
	
	// Words are more than tasks, they service themselves
	public static String[] words = {"word1", "word2", "word3", "word4", "word5", 
									"word6", "word7", "word8", "word9", "word10",
									"word11", "word12", "word13", "word14", "word15"};
	
	public static int totalWork = words.length;
	public static int wordDone = 0;
	public static final int threadSize = 5;
	
	// Define whats thread must do when the do the main thread call back to report progress
	public static void reportProgress(String message, int value, long threadid){
		wordDone = wordDone + value;
		System.out.println( "Thread " + threadid + " checked in. Progress = " + wordDone*100/totalWork + "%");

	}
	
	// Implement a callable class for the task you need to perform and implement Callable *****
	public static class WordLengthCallable implements Callable<String>{
		
		public String word;
		public int sleepTime;
		
		// pass the variables here whatever your tasks needs
		public WordLengthCallable(String word, int sleepTime) {
			this.word = word;
			this.sleepTime = sleepTime;
		}
	
		// define the callable task whatever you want to do per thread
		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			System.out.println("Thread " + Thread.currentThread().getId() + " received word = " + word);
			
			for(int i = 0 ; i < sleepTime ; i++){
				Thread.sleep(1000);
			}
			
			reportProgress("", 1, Thread.currentThread().getId());
			return "Length of Word " + word + " is = " + word.length();
		}
	}

	
	// Main class
	public static void main(String[] args) throws InterruptedException, ExecutionException {
	
		
		// The executor Service
		
		
		// ExecutorService thread pool is given but getting threads from Executors class
		ExecutorService threadPool = Executors.newFixedThreadPool(threadSize);
		
		//Create a list of Define Tasks for All Threads
		//That can be done by creating a set of callables or runnables
		//Callable has Integer cause the task is sum and it will return sum
		List<Callable<String>> tasks = new ArrayList<>();
		
		
		// add all your tasks to the set of custom callables
		for(int i = 0 ; i < words.length ; i++){
			Callable<String> task = new WordLengthCallable(words[i], i);
			tasks.add(task);
		}
		
		// Invoke all threads to execute the task. 
		// Futures will hold results, it will be blocked until all tasks return.
		// u can submit time out when u do future.get
		List<Future<String>> futures = threadPool.invokeAll(tasks);
		
		System.out.println("------Results-----");
		for(Future<String> future : futures){
			System.out.println(future.get());
		}
		
		// shutdown the thread pool else it will keep running
		threadPool.shutdown();
	}
}
