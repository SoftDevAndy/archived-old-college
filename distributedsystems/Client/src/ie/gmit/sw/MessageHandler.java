package ie.gmit.sw;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// MessageHandler has a threadpool and sends queued messages on a blockingqueue to be decrypted.
// It holds the in queue and out queue
// It also holds the checkrequest function which is used to check periodically if the queue has a decrypted message waiting for them

public class MessageHandler 
{
	private final int MAX = 8;
	
	private ExecutorService executor = Executors.newFixedThreadPool(MAX);	
	private BlockingQueue<MessageRequest> inQ = new ArrayBlockingQueue<MessageRequest>(MAX);
	private HashMap<Long, String> outQ = new HashMap<Long, String>();
		
	public void request(int maxKeyLength,String cypherText,long taskNumber)
	{		 
		MessageRequest messageRequest = new MessageRequest(maxKeyLength,cypherText,taskNumber);
		
		inQ.offer(messageRequest);	
		outQ.put(taskNumber, null);
		
		System.out.println("inQ " + inQ.toString());
		
		executor.submit(() -> {
		    WorkerThread wt = new WorkerThread(messageRequest,outQ);
		});
	}
	
	public String checkRequest(long taskNumber)
	{
		String uncyphered = null;
				
		if(outQ.containsKey(taskNumber))
		{
			uncyphered = outQ.get(taskNumber);	
			outQ.remove(taskNumber);
		}
		
		System.out.println(taskNumber + " " + outQ.toString());
		
		return uncyphered;
	}
}
