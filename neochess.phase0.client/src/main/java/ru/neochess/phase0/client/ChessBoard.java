package ru.neochess.phase0.client;

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
    //chessboard params

    int gap = 50;
    int cellsize = 50;
    int cellnum = 10;
    int txtleft = 25;
    int txtright = 15;
    int txtop = 35;
    int txtbottom = 20;
    int boardsize = 600;

    //chessboard params end

    private int x, y;

    public ChessClient chessclient;

    private int myColor;
    private String myRace;

    private boolean myTurn;

    private int grabbed_piece, from_row, from_col, to_row, to_col;

    private Figure grabbed_figure;
   // private Figure replaced_figure;
    private Figure chess_matrix[][] = new Figure[10][10];

    private Board board = new Board();

    FiguresLibrary fl = FiguresLibrary.init();

    private ImageIcon chessmen_images[] = new ImageIcon[16];

    public ClientStateWrapper clientState;


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

        grabbed_piece = ChessMen.NOTHING;


    }

    public void resetBoard() {
      /*  setInitialBoard();
        repaint();
        String encoding = encodeBoard();

        try {
            serverconnection.send(encoding);
            serverconnection.send("@RESET");
        } catch (Exception ex) {

        }*/
    }


    private void setInitialBoard() {

        //  1     2        3       4    5   6   7   8   9   10
        // King Queen King(error) King  Z   Z   Z   Z   Z   Z
        //  Z
        //

//        decodeBoard("WA1WS1WA1WA1ZZZZZZZZZZZZZZZZZZZZZ");
        //decodeBoard("WA1WS1WS1WA1ZZZZZZZZZZZZZZZZZZZZZ");

        decodeBoard( UtiliteChess.getInstance().getInitialBoard());
    }


    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }


    public void paint(Graphics g) {
        Graphics2D gfx = (Graphics2D) g;
        drawOffscreen();
        gfx.drawImage(image_buffer, 0, 0, this);
    }

    private void drawOffscreen() {
        Graphics2D gfx = image_buffer.createGraphics();

        renderChessBoard(gfx);

//        if (grabbed_piece != ChessMen.NOTHING)
        if (grabbed_figure != null) {
//            gfx.drawImage(chessmen_images[grabbed_piece].getImage(), x - 22, y - 22, this);
            gfx.drawImage(grabbed_figure.getImage(), x - 22, y - 22, this);
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
            Map<String,Integer> row_col = new HashMap();
            Figure selectFigure;
            Figure newFigure;

            from_row = (e.getY() - gap) / cellsize;
            from_col = (e.getX() - gap) / cellsize;

            if (from_row < 0 || from_row > 9) return;
            if (from_col < 0 || from_col > 9) return;



            selectFigure = board.getCellByIndex(from_row, from_col).getFigure();

            //обычная пешка
            if (selectFigure != null &&
                    selectFigure.getCode().compareTo("O") == 0) {

                    newFigure = fl.getFigureByCode("R"); //Боевая пешка
                    newFigure.setState(selectFigure.getState());
                    newFigure.setRace(selectFigure.getRace());

                    row_col.put("row", from_row);
                    row_col.put("col", from_col);
                    newFigure.placeOnBoard(board, row_col);
                    board.saveFigure(newFigure);

                    board.removeFigure(selectFigure);

                    selectFigure = null;
                    //myTurn = false; // изменнение пешки считается ходом.
                    repaint();

                newFigure.printCells();
                newFigure = null;

                //отправляем ход
                String encoding = encodeBoard();
                System.out.println(encoding);


                clientState.getCurrent().sendMove( encoding);

           /*     try {
                    serverconnection.send(encoding);
                    serverconnection.send("@TOKEN");
                } catch (Exception ex) {

                }*/
            }


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
        from_row = (e.getY() - gap) / cellsize;
        from_col = (e.getX() - gap) / cellsize;

        if (from_row < 0 || from_row > 9) return;
        if (from_col < 0 || from_col > 9) return;

//        grabbed_piece = chess_matrix[from_row][from_col];
//        grabbed_figure = chess_matrix[from_row][from_col];
        grabbed_figure = board.getCellByIndex(from_row, from_col).getFigure();

        if(grabbed_figure==null) return;

//        if ((getPieceType(grabbed_piece) != myColor) || !myTurn)
//        {
//            grabbed_piece = ChessMen.NOTHING;
//            return;
//        }

//        chess_matrix[from_row][from_col] = null; //fl.getEmptyFigure(); //ChessMen.NOTHING;
        if (grabbed_figure.getCode().equals("H"))
        {
            from_row = grabbed_figure.getRow();
            from_col =  grabbed_figure.getCol();
        }


        board.removeFigure(grabbed_figure);

        x = e.getX();
        y = e.getY();

        repaint();
    }


    public void mouseReleased(MouseEvent e) {

        if (e.getButton() != MouseEvent.BUTTON1){
            return;
        }

        Map<String,Integer> row_col = new HashMap();

//        if (grabbed_piece == ChessMen.NOTHING) return;
        if(grabbed_figure == null) return;

        to_row = (e.getY() - gap) / cellsize;
        to_col = (e.getX() - gap) / cellsize;

        if (to_row < 0 || to_row > 9 || to_col < 0 || to_col > 9) {
//            chess_matrix[from_row][from_col] = grabbed_figure; //grabbed_piece;

            //board.getCellByIndex(from_row, from_col).placeIn(grabbed_figure);

//        public Board placeOnBoard(Board board, Map<String,Integer> row_col) {

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            grabbed_figure.placeOnBoard(board, row_col);

            //grabbed_piece = ChessMen.NOTHING;
            grabbed_figure = null;

            repaint();
            return;
        }

        if ((from_row == to_row && from_col == to_col)
                ||
                !isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col)) {
//            chess_matrix[from_row][from_col] = grabbed_figure; //grabbed_piece;
            board.saveFigure(grabbed_figure);
            //board.getCellByIndex(from_row, from_col).placeIn(grabbed_figure);

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            grabbed_figure.placeOnBoard(board, row_col);


            //grabbed_piece = ChessMen.NOTHING;
            grabbed_figure = null;
            repaint();
            return;
        }

        if (isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col)) {
//            chess_matrix[to_row][to_col] = grabbed_figure; //grabbed_piece;
            board.saveFigure(grabbed_figure);
            //board.getCellByIndex(to_row, to_col).placeIn(grabbed_figure);

            row_col.put("row", to_row);
            row_col.put("col", to_col);
            grabbed_figure.placeOnBoard(board, row_col);

        } else {
//            chess_matrix[from_row][from_col] = grabbed_figure; // grabbed_piece;
            board.saveFigure(grabbed_figure);
            //board.getCellByIndex(from_row, from_col).placeIn(grabbed_figure);

            row_col.put("row", from_row);
            row_col.put("col", from_col);
            grabbed_figure.placeOnBoard(board, row_col);

        }

        //System.out.println(grabbed_figure.getDesc()+" : x="+grabbed_figure.getxCoord()/50+", y="+grabbed_figure.getyCoord()/50);
        grabbed_figure.printCells();

        //grabbed_piece = ChessMen.NOTHING;
        grabbed_figure = null;
       // myTurn = false;

        repaint();

        String encoding = encodeBoard();
        System.out.println(encoding);

        clientState.getCurrent().sendMove( encoding);

     /*   try {
            serverconnection.send(encoding);
            serverconnection.send("@TOKEN");
        } catch (Exception ex) {

        }*/

        //myTurn = false;

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


  //  public synchronized void receiveData(String line) {


  //   clientState.processMSG(line);

      /* if (line.charAt(0) == '@') {


           processCommand(line);
            return;
        }
        decodeBoard(line);
        repaint();
        myTurn = true;*/

   // }


  /*private void processCommand(String command) {
        if (command.compareTo("@BLACK") == 0) {

            myColor = ChessMen.BLACK;
            myRace = "B";
           // chessclient.setTitle("Chess Client - BLACK");
            resetBoard();
        } else if (command.compareTo("@WHITE") == 0) {
            System.out.println("I am WHITE");
            myColor = ChessMen.WHITE;
            myRace = "W";
            chessclient.setTitle("Chess Client - WHITE");
            resetBoard();
            myTurn = true;
        }
        if (command.compareTo("@RESET") == 0) {
            if (myColor == ChessMen.WHITE) myTurn = true;
        } else if (command.compareTo("@TOKEN") == 0) myTurn = true;
    }*/


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

}

