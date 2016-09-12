

public class Philospher implements Runnable{

	int eats=1;
	String name;
	ChopStick left,right;
	
	public Philospher(String name,ChopStick left,ChopStick right){
		this.name=name;
		this.left=left;
		this.right=right;
	}
	
	void pickUp(){
		System.out.println(name+":"+"Trying to pick left"+left);
		left.pickUp();
		System.out.println(name+":"+"Picked the left"+left);
		System.out.println(name+":"+"Trying to pick right"+right);
		right.pickUp();
		System.out.println(name+":"+"Picked the right"+right);
	}
	
	void putDown(){
		System.out.println(name+":"+"PutDown right"+right);
		right.putDown();
		System.out.println(name+":"+"PutDown left"+left);
		left.putDown();
	}
	public void chew(){
		System.out.println(name+": CHEWING");
	}
	
	void eat() {
//		if (name.equals("P4")){
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		pickUp();
		chew();
		putDown();
	}
	
	public void run(){
		for ( int i =0; i < eats;i++){
			eat();
		}
	}
	
	public String toString(){
		return name+":"+this.left+","+this.right;
	}
}
