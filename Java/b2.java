import java.lang.Exception;
import java.lang.InterruptedException;
import java.lang.Math;

class b2 {
	static String pwd = "";
	static BThread[] bthreads = new BThread[2];
	
	public static void main(String[] args) {
		while(true) {
			//take input
			input();
			//initiate bruteforce
			initBruteforce();
		}
	}
	
	private static void input() {
		boolean validated = false;
		System.out.print("Enter a password: ");
		while(!validated) {
			try {
				pwd = new String(System.console().readPassword());
				if(pwd.length() > 4 || pwd.length() == 0) throw new Exception();
				validated = true;
			}
			catch(Exception e) {
				System.out.print("\nEnter a password of length between 1 and 4 inclusive:");
			}
		}
	}
	
	private static void initBruteforce() {
		for(int i = 0; i < 2; i++) {
			bthreads[i] = new BThread(pwd, i);
		}
		for(int i = 0; i < 2; i++) {
			bthreads[i].run();
		}
	}
	
	public static void arrest() {
		for(int i = 0; i < 2; i++) {
			bthreads[i].commitSuicide();
		}
	}
}

class BThread extends Thread {
	private int init = 0;
	private String pwd = "";
	private String name = "";
	private volatile boolean interrupt = false;
	String u = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$&1234567890";
	int l = u.length();
	
	BThread(String pwd, int i) {
		this.name = "Thread " + String.valueOf(i);
		this.pwd = pwd;
		this.init = i;
	}
	
	public void run() {
		try {
			String trai = "";
			int limit = exp(l, pwd.length() + 1);
			while(!(trai.equals(pwd) && !this.interrupt && this.init<limit)) {
				trai = generate(this.init);
				this.init += 2;
			}
			b2.arrest();
			System.out.println("Success! Your password is " + trai + ".");
        } catch (Exception ex) {
            if (ex instanceof InterruptedException) {
                messageLog.stdwar("Process Agent Stopped");
            }else {
                messageLog.stderr("FATAL Error: "+ex.getMessage());
                Logger.getLogger(ProcessAgent.class.getName()).log(Level.SEVERE, null, ex);            
            }
        }    
	}
	
	private String generate(int t) {
		String a = "";
		if(t > (l - 1)) {
			int chunk = exp(l, ((int)Math.floor(Math.log(t)/Math.log(l))));
			a += u.charAt(intDiv(t, chunk) - 1) + generate(t - chunk*intDiv(t, chunk));
		} else {
			a += u.charAt((int)t);
		}
		return a;
	}
	
	private int intDiv(int a, int b) {
		return ((a-(a%b))/b);
	}
	
	private int exp(int a, int b) {
		if(b == 1) return a;
		if(b % 2 == 0) return exp(a*a, b/2);
		return a*exp(a*a, (b-1)/2);
	}
	
	public void commitSuicide() {
		interrupt = true;
	}
}