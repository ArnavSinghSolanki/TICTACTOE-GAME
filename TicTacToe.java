import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[9];
    char currentPlayer = 'X';

    public TicTacToe() {
        setTitle("Tic Tac Toe Game");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked.getText().equals("")) {
            clicked.setText(String.valueOf(currentPlayer));
            if (checkWinner()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetGame();
            } else if (isDraw()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWinner() {
        int[][] winPatterns = {
            {0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}
        };

        for (int[] pattern : winPatterns) {
            if (!buttons[pattern[0]].getText().equals("") &&
                buttons[pattern[0]].getText().equals(buttons[pattern[1]].getText()) &&
                buttons[pattern[1]].getText().equals(buttons[pattern[2]].getText())) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    private void resetGame() {
        for (JButton b : buttons) {
            b.setText("");
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
