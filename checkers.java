import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 2 PLAYER CHECKERS
 *
 * IM SORRY I DIGRESSED FROM THE ORIGINAL CODE A BIT:
 * - I revised board to make it an int[][] so that it can hold empty squares (0), red checkers (1), or black checkers (2)
 * - I also made it so that if you click on a checker it highlights it (:
 * - I took any logic out of the paint method and put the logic in the appropriate places on in the click function.
 * - Thus most of the implementation of the stuff required is in the clicked method, not the paint method where all your comments are.
 */

public class checkers extends JPanel implements MouseListener {

    final int WIDTH = 400;
    final int SQWIDTH = WIDTH/8;
    int[][] board;
    int[] firstClick, lastClick;
    boolean redTurn;

    public checkers() {
        //Initialize all of your PIVs in the constructor. 'redTurn' needs to be true, 'firstClick' and 'lastClick' needs to be -1.
        redTurn = true;

        firstClick = new int[2];
        lastClick = new int[2];
        board = new int[8][8];
        firstClick[0] = -1;
        firstClick[1] = -1;
        lastClick[0] = -1;
        lastClick[1] = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = 0;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j) % 2 == 1) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j) % 2 == 1) {
                    board[i][j] = 2;
                } else {
                    board[i][j] = 0;
                }
            }
        }

        //Fill the board with circle pieces. Use the 2D array of blackPieces and redPieces
        //to set where the pieces are.


        addMouseListener(this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.getContentPane().add(new checkers());
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLUE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void checkForJump(boolean red) {
        //This method will take the boolean input 'red' and will determine if a red piece or black piece jumped another piece.
        //If it did jump, then the 2D array of the opposite color needs to be updated to show one less piece.
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 200, 150);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(100, 100, 400, 400);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        if (redTurn) {
            g.drawString("Red's Turn", 50, 50);
        } else {
            g.drawString("Black's Turn", 50, 50);
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                g.setColor((i+j)%2==0?Color.WHITE:Color.LIGHT_GRAY);
                g.fillRect(9*SQWIDTH - j*SQWIDTH, 9*SQWIDTH - i*SQWIDTH, SQWIDTH, SQWIDTH);
                switch(board[i][j]){
                    case 1:
                        g.setColor(Color.RED);
                        g.fillOval(9*SQWIDTH - j*SQWIDTH, 9*SQWIDTH - i*SQWIDTH, SQWIDTH, SQWIDTH);
                        break;
                    case 2:
                        g.setColor(Color.BLACK);
                        g.fillOval(9*SQWIDTH - j*SQWIDTH, 9*SQWIDTH - i*SQWIDTH, SQWIDTH, SQWIDTH);
                        break;
                }
            }
        }
        if(firstClick[0] != -1){
            int i = firstClick[1];
            int j = firstClick[0];
            g.setColor(Color.ORANGE);
            g.drawOval(9*SQWIDTH - j*SQWIDTH, 9*SQWIDTH - i*SQWIDTH, SQWIDTH, SQWIDTH);
            g.drawOval(9*SQWIDTH - j*SQWIDTH + 1, 9*SQWIDTH - i*SQWIDTH + 1, SQWIDTH-2, SQWIDTH-2);

        }

        //Below is the algorithm to change turns


        //Write the algorithm using nested loops to clear the board for the squares.
        //You will need 2 different set of nested loops.


        //Write the algorithm using nested loops with an if statement on the inside
        //to set all of the red and black pieces where they belong.


        //Below you need to write a series of if statements that will tell the user
        //what color piece has been clicked on. Run the .jar file to get an idea
        //of what it should look like.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //This method will need to be updated to show which color piece was clicked on.


        int x = e.getX();
        int y = e.getY();
        int i;
        int j;
        System.out.println("Mouse Clicked!");
        j = 7-((x - 100) / SQWIDTH);
        i = 7-((y - 100) / SQWIDTH);
        System.out.println(j + ", " + i);

        if (firstClick[0] == -1) { //first click
            lastClick[0] = -1;
            lastClick[1] = -1;
            if(i < 0 || i >= 8 || j < 0 || j >=8){
                System.out.println("firstclick out of bounds");
                return;
            }
            if((board[i][j] == 1 && redTurn) || (board[i][j] == 2 && !redTurn)) {
                firstClick[0] = j;
                firstClick[1] = i;
                System.out.println("firstclick valid " +j +","+i);
            } else {
                System.out.println("firstclick invalid " +j +","+i);
            }
        } else { //second click
            if(!validMove(board, firstClick[1], firstClick[0], i, j, !redTurn)){ //invalid
                firstClick[0] = -1;
                firstClick[1] = -1;
                repaint();
                return;
            }
            lastClick[0] = j;
            lastClick[1] = i;
            if (redTurn) {
                board[lastClick[1]][lastClick[0]] = 1;
            } else {
                board[lastClick[1]][lastClick[0]] = 2;
            }
            board[firstClick[1]][firstClick[0]] = 0;
            firstClick[0] = -1;
            firstClick[1] = -1;
            redTurn = !redTurn;

        }
        repaint();

    }
    public boolean validMove(int[][]board, int i1, int j1, int i2, int j2, boolean up){ //up to see if forwards is up
        if(i2 < 0 || i2 >= 8 || j2 < 0 || j2 >=8 || (i2+j2)%2 != 1){
            System.out.println("second click out of bounds");
            return false;
        }
        if(board[i2][j2] != 0){
            System.out.println("second click not empty space");
            return false;
        }
        if((validJump(board,i1,j1,i2,j2,up))){
            board[(i2 + i1) / 2][(j2 + j1) / 2] = 0;
            return true;
        } else if( !((i2-i1 == 1 && Math.abs(j2-j1)==1 && !up) || (i2-i1 == -1  && Math.abs(j2-j1)==1 && up))){ //if only one space up and one space sideways
            System.out.println("not 1 space/wrong dir");
            return false;
        }
        return true;
    }
    public boolean validJump(int[][]board, int i1, int j1, int i2, int j2, boolean up) { //up to see if forwards is up
        if(((i2-i1 == 2 && Math.abs(j2-j1)==2 && !up) || (i2-i1 == -2 && Math.abs(j2-j1)==2 && up))){
            if(redTurn) {
                if (board[(i2 + i1) / 2][(j2 + j1) / 2] == 2) {
                    return true;
                }
            } else if (board[(i2 + i1) / 2][(j2 + j1) / 2] == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
