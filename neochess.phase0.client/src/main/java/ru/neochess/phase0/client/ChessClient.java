package ru.neochess.phase0.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;

/* Chess client application class */
/*tania test commit*/

public class ChessClient extends JFrame
{
	private static ChessBoard board;
	private BorderLayout layout;

	private JMenuBar bar = new JMenuBar();
	private JMenu gameMenu = new JMenu("Game");
	private JMenuItem resetItem = new JMenuItem("Reset");
	private JMenuItem exitItem = new JMenuItem("Exit");
	//JEditorPane editorPane = new JEditorPane();
	//StyleContext sc = new StyleContext();
	//final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
	JTextArea area1 = new JTextArea("|NeoChess|", 0, 15);

	//JScrollPane editorScrollPane = new JScrollPane(editorPane);





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

		// Cоздание многострочных полей

		// Шрифт и табуляция
		//area1.setFont(new Font("Dialog", Font.PLAIN, 14));

		//area1.setFont(new Font("Dialog", Font.BOLD, 14));

		Font font = new Font("Arial", Font.BOLD, 14);
		area1.setFont(font);
		area1.setForeground(Color.white);
		area1.setTabSize(0);

		//Color color=new Color(255,255,255);

		//Set JTextArea background color to color that you choose
		area1.setBackground(Color.black);


		// Добавим поля в окно

		cont.add(new JScrollPane(area1), BorderLayout.EAST);


		setResizable(false);
		setSize(800,645);




	}
	public void  addTextToArea1 (String line)
	{
			area1.append(line);
	}

	public static void main(String[] args)
	{
		//клиент - начало
		ChessClient cc = new ChessClient();
		
		cc.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{

				board.exitBoard();
				//System.exit(0);
			}
		});		

		cc.show();
	}


}