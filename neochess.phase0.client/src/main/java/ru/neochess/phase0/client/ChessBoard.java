package ru.neochess.phase0.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by for on 29.10.16.
 */
class ChessBoard extends JPanel implements ImageObserver, MouseListener, MouseMotionListener {

    BufferedImage image_buffer;
    ChessServerConnection serverconnection;

    private int x, y;

    private ChessClient chessclient;

    private int myColor;

    private boolean myTurn;

    private int grabbed_piece, from_row, from_col, to_row, to_col;
    private Figure grabbed_figure;

    //    private int chess_matrix[][] = new int[10][10];
//    private String chess_matrix[][] = new String[3][3];
    private Figure chess_matrix[][] = new Figure[10][10];

    FiguresLibrary fl = FiguresLibrary.init();

    /*
Piece codes:
A - wking
B - wqueen
C - wrook
D - wbishop
E - wknight
F - wpawn
G - bking
H - bqueen
I - brook
J - bbishop
K - bknight
L - bpawn
N - bSLON

M - nothing
*/
    private String chessmen_files[] = {
            "/figures/wking.gif", "/figures/wqueen.gif", "/figures/wrook.gif",
            "/figures/wbishop.gif", "/figures/wknight.gif", "/figures/wpawn.gif",
            "/figures/bking.gif", "/figures/bqueen.gif", "/figures/brook.gif",
            "/figures/bbishop.gif", "/figures/bknight.gif", "/figures/bpawn.gif",
            "", "", "", // это 12 13 и 14
            "/figures/bSLON.png"
    };

    private ImageIcon chessmen_images[] = new ImageIcon[16];


    public ChessBoard(ChessClient cc) {
        chessclient = cc;
        this.setSize(500, 500);

        initChessMatrix();

        //CreateChessmenImages();
        image_buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        setBoard();
        serverconnection = new ChessServerConnection(this);

        processCommand("@BLACK");
        processCommand("@TOKEN");

        grabbed_piece = ChessMen.NOTHING;
    }

    private void initChessMatrix() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                chess_matrix[i][j] = null; //fl.getEmptyFigure();
            }
        }
    }

    public void resetBoard() {
        setBoard();
        repaint();
        String encoding = encodeBoard();

        try {
            serverconnection.send(encoding);
            serverconnection.send("@RESET");
        } catch (Exception ex) {

        }
    }


    private void setBoard() {

        decodeBoard("WA1WS1WA1WA1ZZZZZZZZZZZZZZZZZZZZZWA1WA1WA1");

//        for (int i=0; i<10; i++)
//            for (int j=0; j<10; j++)
//                chess_matrix[i][j] = ChessMen.NOTHING;
//
//        chess_matrix[0][0] = ChessMen.BROOK;
//        chess_matrix[0][1] = ChessMen.BKNIGHT;
//        chess_matrix[0][2] = ChessMen.BBISHOP;
//        chess_matrix[0][3] = ChessMen.BQUEEN;
//        chess_matrix[0][4] = ChessMen.BKING;
//        chess_matrix[0][5] = ChessMen.BBISHOP;
//        chess_matrix[0][6] = ChessMen.BKNIGHT;
//        chess_matrix[0][7] = ChessMen.BROOK;
//
//        chess_matrix[0][8] = ChessMen.BSLON;
//        //chess_matrix[0][9] = ChessMen.BSLON;
//        //chess_matrix[1][8] = ChessMen.BSLON;
//        //chess_matrix[1][9] = ChessMen.BSLON;
//
//        for (int i=0; i<8; i++)
//        {
//            chess_matrix[1][i] = ChessMen.BPAWN;
//            chess_matrix[8][i] = ChessMen.WPAWN;
//        }
//
//
//        chess_matrix[9][0] = ChessMen.WROOK;
//        chess_matrix[9][1] = ChessMen.WKNIGHT;
//        chess_matrix[9][2] = ChessMen.WBISHOP;
//        chess_matrix[9][3] = ChessMen.WQUEEN;
//        chess_matrix[9][4] = ChessMen.WKING;
//        chess_matrix[9][5] = ChessMen.WBISHOP;
//        chess_matrix[9][6] = ChessMen.WKNIGHT;
//        chess_matrix[9][7] = ChessMen.WROOK;
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
        if (grabbed_figure != null)
//            gfx.drawImage(chessmen_images[grabbed_piece].getImage(), x - 22, y - 22, this);
            gfx.drawImage(grabbed_figure.getImage(), x - 22, y - 22, this);
    }


    private void renderChessBoard(Graphics2D gfx) {
        int x = 0, y = 0;
        boolean b = false;

        for (int i = 0; i < 10; i++) {
            x = 0;
            b = i % 2 == 0;
            for (int j = 0; j < 10; j++) {
                if (b) gfx.setColor(Color.white);
                else gfx.setColor(Color.gray);
                b = !b;
                gfx.fillRect(x, y, 50, 50);

                paintChessMan(chess_matrix[i][j], x, y, gfx);

                x += 50;

            }
            y += 50;
        }
    }


    //    private void paintChessMan(int piece, int x, int y, Graphics2D gfx)
    private void paintChessMan(Figure fig, int x, int y, Graphics2D gfx) {
        if (fig != null) {

            try {
                gfx.drawImage(fig.getImage(), x + 2, y + 2, this);
            } catch (Exception ex) {
                System.out.println("Не нашел картинку для фигуры " + fig.getDesc());
            }

        }

    }


    private void CreateChessmenImages() {
        for (int i = 0; i < chessmen_images.length; i++) {
            //URL imageURL = getClass().getResource("/figures/bbishop.gif");
            //System.out.println(imageURL);
            chessmen_images[i] = new ImageIcon(getClass().getResource(chessmen_files[i]));
        }
    }


    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }


    public void mousePressed(MouseEvent e) {
        from_row = e.getY() / 50;
        from_col = e.getX() / 50;

        if (from_row < 0 || from_row > 9) return;
        if (from_col < 0 || from_col > 9) return;

//        grabbed_piece = chess_matrix[from_row][from_col];
        grabbed_figure = chess_matrix[from_row][from_col];

//        if ((getPieceType(grabbed_piece) != myColor) || !myTurn)
//        {
//            grabbed_piece = ChessMen.NOTHING;
//            return;
//        }

        chess_matrix[from_row][from_col] = null; //fl.getEmptyFigure(); //ChessMen.NOTHING;
        x = e.getX();
        y = e.getY();

        repaint();
    }


    public void mouseReleased(MouseEvent e) {
//        if (grabbed_piece == ChessMen.NOTHING) return;
        if(grabbed_figure == null) return;

        to_row = e.getY() / 50;
        to_col = e.getX() / 50;

        if (to_row < 0 || to_row > 9 || to_col < 0 || to_col > 9) {
            chess_matrix[from_row][from_col] = grabbed_figure; //grabbed_piece;
            //grabbed_piece = ChessMen.NOTHING;
            grabbed_figure = null;

            repaint();
            return;
        }

        if ((from_row == to_row && from_col == to_col)
                ||
                !isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col)) {
            chess_matrix[from_row][from_col] = grabbed_figure; //grabbed_piece;
            //grabbed_piece = ChessMen.NOTHING;
            grabbed_figure = null;
            repaint();
            return;
        }

        if (isLegalMove(grabbed_piece, from_row, from_col, to_row, to_col))
            chess_matrix[to_row][to_col] = grabbed_figure; //grabbed_piece;
        else
            chess_matrix[from_row][from_col] = grabbed_figure; // grabbed_piece;

        //grabbed_piece = ChessMen.NOTHING;
        grabbed_figure = null;

        repaint();

        String encoding = encodeBoard();

        try {
            serverconnection.send(encoding);
            serverconnection.send("@TOKEN");
        } catch (Exception ex) {

        }

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


    public int getPieceType(int piece) {
        switch (piece) {
            case ChessMen.WKING:
            case ChessMen.WQUEEN:
            case ChessMen.WROOK:
            case ChessMen.WBISHOP:
            case ChessMen.WKNIGHT:
            case ChessMen.WPAWN:
                return ChessMen.WHITE;
            case ChessMen.BKING:
            case ChessMen.BQUEEN:
            case ChessMen.BROOK:
            case ChessMen.BBISHOP:
            case ChessMen.BKNIGHT:
            case ChessMen.BPAWN:
                return ChessMen.BLACK;
        }
        return ChessMen.NOTHING;
    }


    boolean isLegalMove(int piece, int from_row, int from_col, int to_row, int to_col) {

        return true;

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
//        return true;
    }


    public synchronized void receiveData(String line) {
        if (line.charAt(0) == '@') {
            processCommand(line);
            return;
        }
        decodeBoard(line);
        repaint();

    }


    private void processCommand(String command) {
        if (command.compareTo("@BLACK") == 0) {
            myColor = ChessMen.BLACK;
            chessclient.setTitle("Chess Client - BLACK");
            resetBoard();
        } else if (command.compareTo("@WHITE") == 0) {
            System.out.println("I am WHITE");
            myColor = ChessMen.WHITE;
            chessclient.setTitle("Chess Client - WHITE");
            resetBoard();
            myTurn = true;
        }
        if (command.compareTo("@RESET") == 0) {
            if (myColor == ChessMen.WHITE) myTurn = true;
        } else if (command.compareTo("@TOKEN") == 0) myTurn = true;
    }


    /*
   Piece codes:
   A - wking
   B - wqueen
   C - wrook
   D - wbishop
   E - wknight
   F - wpawn
   G - bking
   H - bqueen
   I - brook
   J - bbishop
   K - bknight
   L - bpawn
   M - nothing
   */
    public String encodeBoard() {
        String encoding = "";
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {

                if (chess_matrix[i][j] != null) {
                    encoding += chess_matrix[i][j].encodeFigure();
                } else {
                    encoding += "ZZZ";
                }

//                switch(chess_matrix[i][j])
//                {
//                    case ChessMen.WKING: 	encoding += "A"; break;
//                    case ChessMen.WQUEEN: 	encoding += "B"; break;
//                    case ChessMen.WROOK: 	encoding += "C"; break;
//                    case ChessMen.WBISHOP: 	encoding += "D"; break;
//                    case ChessMen.WKNIGHT: 	encoding += "E"; break;
//                    case ChessMen.WPAWN: 	encoding += "F"; break;
//                    case ChessMen.BKING: 	encoding += "G"; break;
//                    case ChessMen.BQUEEN: 	encoding += "H"; break;
//                    case ChessMen.BROOK: 	encoding += "I"; break;
//                    case ChessMen.BBISHOP: 	encoding += "J"; break;
//                    case ChessMen.BKNIGHT: 	encoding += "K"; break;
//                    case ChessMen.BPAWN: 	encoding += "L"; break;
//                    case ChessMen.NOTHING: 	encoding += "M"; break;
//                }
            }

        return encoding;
    }


    public void decodeBoard(String encoding) {
        int row, col;
        String pieceRace;
        String pieceCode;
        String pieceState;

        Map<String,Integer> xy = new HashMap();

        //if (encoding.length() < 100) return;

//        Figure prevF = null;

        for (int i = 0; i < 300; i += 3) {
            if (i >= encoding.length()) return;

            row = i / 30;
            col = (i / 3) % 10;
            pieceRace = encoding.substring(i, i + 1); //encoding.charAt(i);
            pieceCode = encoding.substring(i + 1, i + 2); //encoding.charAt(i+1);
            pieceState = encoding.substring(i + 2, i + 3); //encoding.charAt(i+2);

            if (pieceCode.equalsIgnoreCase("Z")) {
                chess_matrix[row][col] = null;
            } else {

                if (chess_matrix[row][col] == null) {

                    Figure currentFigure = fl.getFigureByCode(pieceCode);
                    currentFigure.setState(pieceState);
                    currentFigure.setRace(pieceRace);

//                    if (currentFigure != null && currentFigure==prevF){
//                        System.out.println("фигура в "+row+" "+col+"та же что и предыдущая --- и это ошибка!");
//                    }
//                    prevF = currentFigure;
//                    if (currentFigure != null && currentFigure==prevF){
//                        System.out.println("этот всегда должен сойтись");
//                    }

                    //chess_matrix[row][col] = currentFigure;

                    xy.put("x", row);
                    xy.put("y", col);
                    currentFigure.placeOnBoard(chess_matrix, xy);

                } else {
//                    if (chess_matrix[row][col] != null && chess_matrix[row][col]==prevF){
//                        System.out.println("фигура в "+row+" "+col+"та же что и предыдущая");
//                    }
                }
            }
//            switch (piece)
//            {
//                case 'A': chess_matrix[row][col] = ChessMen.WKING; break;
//                case 'B': chess_matrix[row][col] = ChessMen.WQUEEN; break;
//                case 'C': chess_matrix[row][col] = ChessMen.WROOK; break;
//                case 'D': chess_matrix[row][col] = ChessMen.WBISHOP; break;
//                case 'E': chess_matrix[row][col] = ChessMen.WKNIGHT; break;
//                case 'F': chess_matrix[row][col] = ChessMen.WPAWN; break;
//                case 'G': chess_matrix[row][col] = ChessMen.BKING; break;
//                case 'H': chess_matrix[row][col] = ChessMen.BQUEEN; break;
//                case 'I': chess_matrix[row][col] = ChessMen.BROOK; break;
//                case 'J': chess_matrix[row][col] = ChessMen.BBISHOP; break;
//                case 'K': chess_matrix[row][col] = ChessMen.BKNIGHT; break;
//                case 'L': chess_matrix[row][col] = ChessMen.BPAWN; break;
//                case 'M': chess_matrix[row][col] = ChessMen.NOTHING; break;
//            }

        }

    }

}

