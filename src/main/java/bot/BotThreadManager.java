package bot;

import java.util.ArrayList;
import java.util.List;

public class BotThreadManager {
	public static List<Thread> threadList = new ArrayList<>();

	public static void waitThreads(Thread thread) {
		synchronized(threadList) {
			threadList.add(thread);
		}
		
		Runnable runnable = new Runnable() {
				
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(thread.isAlive())
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				synchronized (threadList) {
					threadList.remove(thread);
				}
			}
		};
		
		new Thread(runnable).start();

	}
}
