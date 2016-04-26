package tetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class instruction
{
	private String fileName = "resources/Instructions.txt";
	protected StringBuilder sb = new StringBuilder();

	public instruction()
	{
		JFrame instructions = new JFrame("HELP");
		
		instructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// call the importing method to get text file		
		createString();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder (new EtchedBorder(), "Instructions"));
		
		JTextArea textArea = new JTextArea(50, 100);  // set size of the GUI instruction here 
		textArea.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		panel.add(scroll);
		
		instructions.add(panel);
		instructions.pack();
		
		textArea.setText(getString());		
		
		instructions.setVisible(true);
	}
	
	// return imported text from text file
	public String getString()
	{
		return (sb.toString());
	}
	
	// import text file 
	public void createString()
	{
		Scanner inputStream = null;
		
		try {
			
			inputStream = new Scanner(new FileInputStream(fileName));
			
			while(inputStream.hasNextLine())
			{
				sb.append(inputStream.nextLine());
				sb.append("\n");
			}
			
			inputStream.close();
			
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Something is wrong!", "Error", JOptionPane.WARNING_MESSAGE);
		}
	}
}
