import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class JavaTimersUI extends JFrame  implements ActionListener
{
	
	private JPanel panel1;
	private GridBagConstraints gc;
	private JTextField timer1, timer2, timer3;
	private JButton timer1button, timer2button, timer3button;
	private Thread thread1, thread2, thread3;
	
	
	public JavaTimersUI()
	{
		this.setSize(300, 150);
		this.setTitle("Timers");
		this.setVisible(true);
		this.setLocation(50,50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.panel1 = new JPanel();
		this.panel1.setLayout(new GridBagLayout());
		
		
		this.timer1button = new JButton();
		this.timer1button.setText("Timer 1 Setup");		
		this.timer1button.addActionListener(this);		
		this.addItemToPanel(this.panel1, this.timer1button, 1, 2, 1, 1, GridBagConstraints.EAST);
		
		
		this.timer2button = new JButton();
		this.timer2button.setText("Timer 2 Setup");		
		this.timer2button.addActionListener(this);		
		this.addItemToPanel(this.panel1, this.timer2button, 2, 2, 1, 1, GridBagConstraints.EAST);

		
		this.timer3button = new JButton();
		this.timer3button.setText("Timer 3 Setup");		
		this.timer3button.addActionListener(this);			
		this.addItemToPanel(this.panel1, this.timer3button, 3, 2, 1, 1, GridBagConstraints.EAST);
		
		
		this.timer1 = new JTextField(10);
		this.addItemToPanel(this.panel1, this.timer1, 1, 1, 1, 1, GridBagConstraints.WEST);
		
		this.timer2 = new JTextField(10);
		this.addItemToPanel(this.panel1, this.timer2, 2, 1, 1, 1, GridBagConstraints.WEST);
		
		this.timer3 = new JTextField(10);
		this.addItemToPanel(this.panel1, this.timer3, 3, 1, 1, 1, GridBagConstraints.WEST);
		
		this.add(panel1);
		this.pack();
		
		this.thread1 = new Thread();
		this.thread2 = new Thread();
		this.thread3 = new Thread();
		
	}
	
	
	
	/**
	 * Adds components to the panel component
	 * @param p panael container
	 * @param c compoent to add
	 * @param x x position
	 * @param y y position
	 * @param width span columns
	 * @param height span rows
	 * @param align alignment
	 */
	
	private void addItemToPanel(JPanel p, JComponent c, int x, int y, int width, int height, int align)
	{
		this.gc = new GridBagConstraints();
		this.gc.gridx = x;
		this.gc.gridy = y;
		this.gc.gridwidth = width;
		this.gc.gridheight = height;
		this.gc.weightx = 100.0;
		this.gc.weighty = 100.0;
		this.gc.insets = new Insets(5,5,5,5);
		this.gc.anchor = align;
		this.gc.fill = GridBagConstraints.NONE;
		p.add(c,gc);
	}
	
	
	/**
	 * addActionListener
	 * Implemented Event Handler Method from ActionEvent
	 * Invoked when an action occurs. (Button click)
	 * 
	 * actionPerformed
	 * @param e ActionEvent
	 */
	
	public void actionPerformed(ActionEvent e)
	{
		
		int time;
		
		
		// ask the user for a time
		
		try
		{
			time = Integer.parseInt(JOptionPane.showInputDialog("Enter Clock Ticktime (1 second = 1000 ) : "));
		}
		catch (Exception p)
		{
			time = 1000;		
		}

		
		// if its button one
		if(e.getSource()==this.timer1button)
		{
			if(thread1.isAlive())
			{
				// if its alive kill it
				thread1.interrupt();			
			}
			else
			{
				// print task 
				// name of the task
				// the paret, ie this, so we can call functions from this class
				// integer definfing which textbox the time is put into
				thread1 = new Thread( new PrintTask( "task1", time, (JavaTimersUI)this, 1) );
				thread1.start();
			}
		}
		
		
		if(e.getSource()==this.timer2button)
		{
			if(thread2.isAlive())
			{
				thread2.interrupt();			
			}
			else
			{
				thread2 = new Thread( new PrintTask( "task2", time, (JavaTimersUI)this, 2) );
				thread2.start();
			}
		}
		
		
		if(e.getSource()==this.timer3button)
		{
			if(thread3.isAlive())
			{
				thread3.interrupt();			
			}
			else
			{
				thread3 = new Thread( new PrintTask( "task3", time, (JavaTimersUI)this, 3) );
				thread3.start();
			}
		}
	}	
	
	
	/**
	 * This function is called from the threads
	 * it passing the seconds from the thread instance and a INTEGER used to reference which textbox to update
	 * @param seconds
	 * @param textBoxnum
	 */
	
	public void updateClock(int seconds, int textBoxnum)
	{
		int minutes = (int) Math.floor(seconds / 60);
		int newseconds = seconds % 60;		
		
		if(textBoxnum==1)
		{
			// timer1 = Jtextbox
			this.timer1.setText(minutes + ":" + newseconds);
		}
		
		if(textBoxnum==2)
		{
			//timer2 = Jtextbox
			this.timer2.setText(minutes + ":" + newseconds);
		}
		
		if(textBoxnum==3)
		{
			// timer3 = Jtextbox
			this.timer3.setText(minutes + ":" + newseconds);
		}
		
		

	}
	
	
	public static void main(String args[])
	{
		new JavaTimersUI();
	}

}
