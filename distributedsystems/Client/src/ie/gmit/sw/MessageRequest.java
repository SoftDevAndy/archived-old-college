package ie.gmit.sw;

// This message request object is a basic class
// Its used to hold keylenght,jobid and message variables
// this object is passed to the message queue

public class MessageRequest
{
	private int keyLenght;
	private long jobid;
	private String message;
	
	public MessageRequest(int k,String m, long j)
	{
		this.keyLenght = k;
		this.jobid = j;
		this.message = m;
	}
	
	public int getKeyLenght() 
	{
		return keyLenght;
	}
	
	public void setKeyLenght(int keyLenght) 
	{
		this.keyLenght = keyLenght;
	}
	
	public String getMessage() 
	{
		return message;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
	}
	
	public long getJob() 
	{
		return jobid;
	}	
}
