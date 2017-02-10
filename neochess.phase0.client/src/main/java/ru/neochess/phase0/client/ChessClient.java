package ru.neochess.phase0.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Chess client application class */
/*tania test commit*/

public class ChessClient extends JFrame
{
	private ChessBoard board;
	private BorderLayout layout;

	private JMenuBar bar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game");
	private JMenuItem resetItem = new JMenuItem("Reset");
	private JMenuItem exitItem = new JMenuItem("Exit");

	
	public ChessClient()
	{
		super("Chess Client");
		Container cont = this.getContentPane();
			
		layout = new BorderLayout(1,1);
		cont.setLayout(layout);		

		//клиент создает объект доска и передает себя в него
		board = new ChessBoard(this);			
		cont.add(board, BorderLayout.CENTER);

		setJMenuBar(bar);

		gameMenu.add(resetItem);
		gameMenu.add(exitItem);

		bar.add(gameMenu);

		exitItem.addActionListener(e -> board.exitBoard());

		resetItem.addActionListener(e -> board.resetBoard());

		setResizable(false);
		setSize(600,645);
	}


	public static void main(String[] args)
	{
		//клиент - начало
		ChessClient cc = new ChessClient();
		
		cc.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});		

		cc.show();
	}


}