

public class DiningPhilospher {

	public static void main(String args[]){
		ChopStick[] cArray= new ChopStick[5];
		Philospher[] pArray = new Philospher[5];
		for( int i =0; i < cArray.length; i++)
			cArray[i]=new ChopStick("C"+(i));
		int prev=pArray.length-1;
		for( int i=0; i < pArray.length; i++){
			int newPrev=(i-1)%pArray.length ;
			pArray[i]=new Philospher("P"+i,cArray[i],cArray[newPrev < 0? newPrev+pArray.length:newPrev]);
			prev=(prev+1)%pArray.length;
		}
		pArray[0] = new Philospher("P"+0,cArray[0],cArray[4]);
		pArray[1] = new Philospher("P"+1,cArray[0],cArray[1]);
		pArray[2] = new Philospher("P"+2,cArray[1],cArray[2]);
		pArray[3] = new Philospher("P"+3,cArray[2],cArray[3]);
		pArray[4] = new Philospher("P"+4,cArray[3],cArray[4]);
		
		
		//Here is 4 is doing different than other philopshers
		pArray[0] = new Philospher("P"+0,cArray[4],cArray[0]);
		pArray[1] = new Philospher("P"+1,cArray[1],cArray[0]);
		pArray[2] = new Philospher("P"+2,cArray[2],cArray[1]);
		pArray[3] = new Philospher("P"+3,cArray[3],cArray[2]);
		pArray[4] = new Philospher("P"+4,cArray[4],cArray[3]);
		
		Thread[] philThreads = new Thread[5];
		for ( int i=0; i < pArray.length; i++){
			//System.out.println(pArray[i]);
			philThreads[i] = new Thread(pArray[i]);
		}
		
		for ( int i=0; i < pArray.length; i++){
			philThreads[i].start();;
		}
//		System.out.println(-1%5+5);
//		System.out.println(-2%5+5);
//		System.out.println(-3%5+5);
//		System.out.println(-4%5+5);
//		System.out.println(1%5);
		
		
		//System.out.println(changePi("xpix"));
	}
	
	public static String changePi(String str) {
		  if( str.length() < 2)
		    return str;
		  String newString= changePi(str.substring(1));
		  char currChar = str.charAt(0);
		  char firstCharNew = newString.charAt(0);
		  if( "pi".equals(currChar+""+firstCharNew)){
		    return "3.14"+newString.substring(1);
		  }
		  return currChar+newString;
		}

}
