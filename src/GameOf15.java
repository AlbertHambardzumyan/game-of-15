import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GameOf15 extends JFrame implements ActionListener {

    private JButton pieces[][] = new JButton[4][4];

    private GameOf15() {
        super("Game of 15");
        setLayout(new GridLayout(pieces.length, pieces.length));
        for (int row = 0; row < pieces.length; row++)
            for (int col = 0; col < pieces.length; col++) {
                pieces[row][col] = new JButton("0");
                pieces[row][col].addActionListener(this);
                pieces[row][col].setFocusPainted(false);
                add(pieces[row][col]);
                pieces[row][col].setVisible(false);
            }

        shuffle();
        setSize(250, 250);
        setVisible(true);
    }

    private void shuffle() {
        Random generator = new Random();
        int randRow, randCol;

        for (int value = 1; value < pieces.length * pieces.length; value++) {
            do {
                randRow = generator.nextInt(4);
                randCol = generator.nextInt(4);
            } while (!pieces[randRow][randCol].getText().equals("0"));

            pieces[randRow][randCol].setText("" + value);
            pieces[randRow][randCol].setVisible(true);
        }
    }

    public void actionPerformed(ActionEvent click) {
        JButton source = (JButton) click.getSource();

        for (int row = 0; row < pieces.length; row++)
            for (int col = 0; col < pieces.length; col++)
                if (pieces[row][col] == source) {
                    try {
                        swap(pieces[row - 1][col], source);
                    } catch (ArrayIndexOutOfBoundsException atBorder) {
                    }
                    try {
                        swap(pieces[row + 1][col], source);
                    } catch (ArrayIndexOutOfBoundsException atBorder) {
                    }
                    try {
                        swap(pieces[row][col - 1], source);
                    } catch (ArrayIndexOutOfBoundsException atBorder) {
                    }
                    try {
                        swap(pieces[row][col + 1], source);
                    } catch (ArrayIndexOutOfBoundsException atBorder) {
                    }
                }
    }

    private void swap(JButton zero, JButton clicked) {
        if (zero.getText().equals("0")) {
            zero.setText(clicked.getText());
            zero.setVisible(true);
            clicked.setText("0");
            clicked.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new GameOf15();
    }
}

