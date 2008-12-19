import java.awt.event.ActionEvent;


public class PrintTask implements Runnable
{
   private final int sleepTime;
   private final String taskName;
   private int seconds = 0;
   private JavaTimersUI UserInterFaceThread;
   private JavaTimersUI method;
   private int textboxNum;
   
   /**
    * 
    * @param name the thread anme (not really needed)
    * @param interval (thread tick time)
    * @param parent ( parent as in the object that called this thread
    * @param textBox ( the input box to update )
    */

   	public PrintTask( String name, int interval, JavaTimersUI parent, int textBox)
   	{
   		super();
   		this.taskName = name; 
   		this.sleepTime = interval; 
   		this.UserInterFaceThread = parent;
   		this.textboxNum = textBox;

   	} 

   	public void run()
   	{
   		int y = 1;
   		try 
   		{
   			while(y > 0)
   			{
   				Thread.sleep( sleepTime ); 
   				this.seconds++;   
   				// call the updateClock in teh parent,
   				// pass it the seconds passed for this thread
   				// and the textbox number to update
   				this.UserInterFaceThread.updateClock(seconds,this.textboxNum);
   				
   			}
   		} 
   		catch ( InterruptedException exception )
   		{
		   System.out.printf( "%s %s\n", taskName, "terminated prematurely due to interruption" );
   		} 

   	} 
} 

