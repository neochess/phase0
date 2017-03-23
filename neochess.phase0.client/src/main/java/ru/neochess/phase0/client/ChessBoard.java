package ru.neochess.phase0.client;

import ru.neochess.phase0.client.MoveHandler.MoveHandler;
import ru.neochess.phase0.client.State.ClientStateWrapper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by for on 29.10.16.
 */
public class ChessBoard extends JPanel implements ImageObserver, MouseListener, MouseMotionListener {

    BufferedImage image_buffer;
    int  linecounter = -1;
    int  linenum = 1;
    //chessboard params

    public int gap = 50;
    public int cellsize = 50;
    public int cellnum = 10;
    public int txtleft = 25;
    public int txtright = 15;
    public int txtop = 35;
    public int txtbottom = 20;
    public int boardsize = 600;

    //chessboard params end

    private int x, y;

    public ChessClient chessclient;

    //private int myColor;
    //private String myRace;

    //private boolean myTurn;

    private int grabbed_piece, from_row, from_col, to_row, to_col;

    public Figure grabbed_figure;
   // private Figure replaced_figure;
    private Figure chess_matrix[][] = new Figure[10][10];

    public Board board = new Board();

    public String move = new String();

    public FiguresLibrary fl = FiguresLibrary.init();

    private ImageIcon chessmen_images[] = new ImageIcon[16];

    public ClientStateWrapper clientState;

    private MoveHandler moveHandler = new MoveHandler();

    /*конструктор доски
    0 рисует доску:
    1 добавляет лисенер мышки,
    2 расставляет фигуры в начальное положение
    3 создает объект обертку для состояний и передается в нее */
    public ChessBoard(ChessClient cc) {

        clientState = new ClientStateWrapper(this);

        chessclient = cc;
        this.setSize(boardsize , boardsize );

        image_buffer = new BufferedImage(boardsize , boardsize , BufferedImage.TYPE_INT_RGB);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setInitialBoard();

        clientState.getCurrent().sendState();
      //  grabbed_piece = ChessMen.NOTHING;


    }
    public void exitBoard() {
        clientState.getCurrent().finishGame();
        clientState.getCurrent().sendState();
        System.exit(0);
    }

    public void resetBoard() {
       // setInitialBoard();
        clientState.getCurrent().finishGame();
        clientState.getCurrent().sendState();
    }


    public void setInitialBoard() {

        decodeBoard( UtiliteChess.getInstance().getInitialBoard());
        repaint();
    }


    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }


    public void paint(Graphics g)
    {
        Graphics2D gfx = (Graphics2D) g;
        drawOffscreen();
        gfx.drawImage(image_buffer, 0, 0, this);
    }

    private void drawOffscreen()
    {
        Graphics2D gfx = image_buffer.createGraphics();

        renderChessBoard(gfx);

        if (grabbed_figure != null)
        {
            gfx.drawImage(grabbed_figure.getImage(),
                   - cellsize * grabbed_figure.getImageXshift() + x - 22,
                   - cellsize * grabbed_figure.getImageYshift() +
                            y - 22, this);
        }

    }

    private void renderChessBoard(Graphics2D gfx) {


        boolean b = false;

        gfx.setColor(Color.black);
        gfx.fillRect(0, 0, boardsize, boardsize);

        gfx.setColor(Color.white);

        int x = cellsize + cellsize/2;
        int y = 0;

        for (char a = 'A'; a <= 'J'; a++) {

            gfx.drawString(String.valueOf(a).toString(), x, y + txtop);

            gfx.drawString(String.valueOf(a).toString(), x, y + gap + cellsize * cellnum + txtbottom);

            x += cellsize;

        }

        x = 0;
        y = cellsize + cellsize/2;
        for (int n = cellnum; n >= 1; n--) {

            gfx.drawString(String.valueOf(n).toString(), x+ gap + cellsize * cellnum + txtright, y);

            gfx.drawString(String.valueOf(n).toString(), x + txtleft, y);

            y += cellsize;

        }


        y = gap;
        for (int i = 0; i < cellnum; i++) {
            x = gap;
            b = i % 2 == 0;
            for (int j = 0; j < cellnum; j++) {
                if (b) {
                    gfx.setColor(Color.white);
                }
                else {
                    gfx.setColor(Color.gray);
                }
                b = !b;
                gfx.fillRect(x, y, cellsize, cellsize);

                //paintChessMan(board.getCellByIndex(i,j).getFigure(), x, y, gfx);
                //paintChessMan(chess_matrix[i][j], x, y, gfx);

                x += cellsize;

            }
            y += cellsize;
        }

        board.paintFigures(gfx, this);

    }

    public void mouseClicked(MouseEvent e) {
        if((e.getButton() == MouseEvent.BUTTON3 || e.getButton() == MouseEvent.BUTTON2 ) )
                //&& myTurn)
        {
          if (moveHandler.figureTransform(e.getX(),e.getY(), this) == true) {
                    repaint();
                //отправляем ход
                String encoding = encodeBoard();
                System.out.println(encoding);
                clientState.getCurrent().sendMove( encoding, move);}
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() != MouseEvent.BUTTON1){
            return;
        }

        if (moveHandler.moveBegin(e.getX(),e.getY(), this) == true) {
            x = e.getX();
            y = e.getY();

            repaint();

           }

    }


    public void mouseReleased(MouseEvent e) {

        if (e.getButton() != MouseEvent.BUTTON1){
            return;
        }

   Boolean moveDone = moveHandler.moveEnd(e.getX(),e.getY(), this);
        repaint();

    if (moveDone)
        {String encoding = encodeBoard();
        System.out.println(encoding);

        clientState.getCurrent().sendMove( encoding, move);}

    }


    public void mouseDragged(MouseEvent e) {
//        if (grabbed_piece == ChessMen.NOTHING) return;
        if (grabbed_figure == null) return;

        x = e.getX();
        y = e.getY();
        repaint();
    }


    public void mouseMoved(MouseEvent e) {
    }


    boolean isLegalMove(int piece, int from_row, int from_col, int to_row, int to_col) {

        //if (myTurn == false) return false;
    /*    replaced_figure = board.getCellByIndex(to_row, to_col).getFigure();// на чье место мы хотим встать?


       if (!grabbed_figure.getRace().equals(clientState.myRace)) return false; //не трожь чужие фигурки

        if (replaced_figure != null) {

            if (grabbed_figure.getCode().equals("H") && replaced_figure.getCode().equals("H")) return true;
            //все не так для слона - он может встать на место себя!!

            if (grabbed_figure.getRace().equals(replaced_figure.getRace())) return false;// не руби свои фигурки

        }*/

       // piece.getRace();

//        if (getPieceType(piece) == getPieceType(chess_matrix[to_row][to_col])) return false;
//
//        switch (piece)
//        {
//            case ChessMen.WPAWN:
//                if ((from_row - to_row) == 1 && from_col == to_col)
//                    return true;
//                return false;
//            case ChessMen.BPAWN:
//        }
//
        return true;
    }


    public String encodeBoard() {
        StringBuilder encoding = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                Figure f = board.getCellByIndex(i, j).getFigure();
                if (f != null) {
                    encoding.append(f.encodeFigure());
                } else {
                    encoding.append("ZZZ");
                }

            }
        }

        return encoding.toString();
    }


    public void decodeBoard(String encoding) {
        int row, col;
        String pieceRace;
        String pieceCode;
        String pieceState;
        boolean slon = false;

        Map<String,Integer> row_col = new HashMap();

        board.Clear();

        for (int i = 0; i < encoding.length(); i += 3) {

            row = i / 30;
            col = (i / 3) % 10;
            pieceRace = encoding.substring(i, i + 1);
            pieceCode = encoding.substring(i + 1, i + 2);
            pieceState = encoding.substring(i + 2, i + 3);

            if (!pieceCode.equalsIgnoreCase("Z")) {

                row_col.put("row", row);
                row_col.put("col", col);
                Figure currentFigure =  board.putFigure(pieceRace,pieceState,pieceCode, fl, board, row_col);

            }

        }


    }

    public void addTextArea1(String line)
    {

        linecounter++;
        if (linecounter <= 0)
        chessclient.addTextToArea1("\n");

        else if ((linecounter % 2) == 0)
            chessclient.addTextToArea1(" - ");

        else if ((linecounter % 2) != 0) {
            chessclient.addTextToArea1("\n" + (linenum) + ". ");
            linenum++;
        }

        chessclient.addTextToArea1(line);



    }

}

