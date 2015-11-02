import java.io.Console;
import java.lang.Math;
import java.util.ArrayList;

public class bruteforce {
	static String u = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$&1234567890.";
	static int l = u.length();
	static int trial = 0;
	//static ArrayList<String> tried = new ArrayList();
	
	public static void main(String[] args) {
		Console cnsl = System.console();
		String pwd = new String(cnsl.readPassword());
		bruteforcel(pwd);
	}
	
	static void bruteforcel(String a) {
		String pwd = a;
		String trai1 = "";
		String trai2 = "";
		while(!(trai1.equals(pwd))) {
			trai1 = generate(trial);
			if(trial % 10000000 == 0) System.out.println(trial);
			trial++;
		}
		System.out.println("Success! Your password is " + trai1 + ".");
	}
	
	static String generate(int t) {
		String a = "";
		if(t > (l - 1)) {
			int chunk = exp(l, ((int)Math.floor(Math.log(t)/Math.log(l))));
			a += u.charAt(intDiv(t, chunk) - 1) + generate(t - chunk*intDiv(t, chunk));
		} else {
			a += u.charAt((int)t);
		}
		return a;
	}
	
	static int intDiv(int a, int b) {
		return ((a-(a%b))/b);
	}
	
	static int exp(int a, int b) {
		if(b == 1) return a;
		if(b % 2 == 0) return exp(a*a, b/2);
		if(b % 2 == 1) return a*exp(a*a, (b-1)/2);
		return 1;
	}
	
	/*static void stop() {
	}
	
	static void bruteforcer(String a) {
		String pwd = a;
		String trai = "";
		while(!(trai.equals(pwd))) {
			trai = randomize(pwd.length());
			if(trial % 1000000 == 0) System.out.println(trial);
			trial++;
		}
		System.out.println("Success! Your password is " + trai);
	}
	
	static String randomize(int a) {
		StringBuffer b = new StringBuffer("");
		while(tried.contains(b.toString())) {
			b = new StringBuffer("");
			int lim = (int)Math.ceil(Math.random()*a);
			while(b.length() < lim) {
				b.append(u.charAt((int)Math.floor(Math.random()*l)));
			}
		}
		tried.add(b.toString());
		return b.toString();
	}*/
}

/*class bthread implements Runnable {
	private Thread thread;
	private String threadName;
	private String p;
	private int trial;

	bthread(String name, String pwd, int s){
       threadName = name;
	   p = pwd;
	   trial = s;
       System.out.println("Creating " +  threadName);
	}
	
   public void run() {
		System.out.println("Running " +  threadName );
		String trai1 = "";
    try {
        while(!(trai1.equals(p))) {
			trai1 = generate(trial);
			if(trial % 10000000 == 0) System.out.println(threadName + ": " + trial);
			trial += 100;
		}
		bruteforce.stop();
    } catch (InterruptedException e) {
        System.out.println("Thread " +  threadName + " interrupted.");
    }
		System.out.println("Thread " +  threadName + " exiting.");
	}
	
	static String generate(int t) {
		String a = "";
		if(t > (l - 1)) {
			int chunk = exp(l, ((int)Math.floor(Math.log(t)/Math.log(l))));
			a += u.charAt(intDiv(t, chunk) - 1) + generate(t - chunk*intDiv(t, chunk));
		} else {
			a += u.charAt((int)t);
		}
		return a;
	}
   
   public void start ()
   {
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
	}
}*/