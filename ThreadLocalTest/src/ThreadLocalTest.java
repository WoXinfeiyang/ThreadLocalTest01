import java.util.HashMap;


/**
 * 文件名称:ThreadLocal测试例程ThreadLocalTest
 * 时间：2016 5-23 17:35
 * 说明：1、同步机制是为了同步多个线程对相同资源的并发访问，是多个线程之间进行通信的有效方式；而ThreadLocal是为了隔离多个
 * 线程的数据共享，隔离多个线程之间的共享冲突。
 * */
public class ThreadLocalTest {
	private static HashMap<Integer,Integer> mMap=new HashMap<Integer,Integer>();

	private static ThreadLocal<Integer> mThreadLocal=new ThreadLocal<Integer>();
	
	static class  FirstThread extends Thread
	{
		int n;
		int i=0;
		
		public FirstThread() {
			super();
			// TODO Auto-generated constructor stub
		}

		public FirstThread(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			if(mMap.get(0)!=null){
				n=mMap.get(0);
			}
			for(i=0;i<5;i++){
				n++;
			}
			mMap.put(0,n);
			System.out.println(currentThread().getName()+",value="+mMap.get(0));
		}		
	}
	
	static class SecondThread extends Thread
	{
		int n=0;
		int i=0;
		
		public SecondThread(String name) {
			// TODO Auto-generated constructor stub
			super(name);
		}
		
		@Override
		public void run() {
			if(mThreadLocal.get()!=null){
				n=mThreadLocal.get();
			}
			for(i=0;i<5;i++){
				n++;
			}
			mThreadLocal.set(n);
			System.out.println(currentThread().getName()+",value="+mThreadLocal.get());
		}		
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int i=0;
		for(i=0;i<5;i++){
			new FirstThread("FirstThread"+i).start();			
		}

		for(i=0;i<5;i++){
			new SecondThread("SecondThread"+i).start();			
		}
	}

}
