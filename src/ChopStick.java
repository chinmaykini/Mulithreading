

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {

	String name;
	Lock lock;
	public ChopStick(String name){
		this.name=name;
		lock = new ReentrantLock();
	}
	
	
	void pickUp(){
		lock.lock();
	}
	
	void putDown(){
		lock.unlock();
	}
	
	public String toString(){
		return name;
	}
}
