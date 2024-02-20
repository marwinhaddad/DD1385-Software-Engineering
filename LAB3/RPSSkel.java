import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

class RPSSkel extends JFrame implements ActionListener{

	JButton closebutton;
	Gameboard myboard,
			computersboard;
	int counter; // To count ONE ... TWO  and on THREE you play

	Socket socket;
	BufferedReader in;
	PrintWriter out;

	String cmpChoice;
	String pChoice;

	RPSSkel() {
		closebutton = new JButton("Close");
		myboard = new Gameboard("Player", this); // Must be changed
		computersboard = new Gameboard("Computer");
		counter = 0;

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// add the boards to the panel
		JPanel boards = new JPanel();
		boards.setLayout(new GridLayout(1,2));
		boards.add(myboard);
		boards.add(computersboard);

		// first add game boards...
		add(boards, BorderLayout.CENTER);

		// ... then close button
		add(closebutton, BorderLayout.SOUTH);

		// set size of window
		setSize(350, 650);
		setVisible(true);

		// same as the client-class
		try {
			String host = "localhost";
			socket = new Socket(host, 4713);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
			out =  new PrintWriter(socket.getOutputStream());

			out.println("Blahonga");
			out.flush();

			System.out.println(in.readLine());

		} catch (IOException e) {
			System.err.println(e);
		}

		// anonymous inner class for actionListener for close button
		// https://www.geeksforgeeks.org/anonymous-inner-class-java/
		closebutton.addActionListener(e -> {
			out.println("");
			out.flush();
			System.exit(0);
		});
    }

	// main method to run program
    public static void main (String[] u) {
		new RPSSkel();
    }

	// when winner/ loser detected update text, background color and score
	private void checkWinner() {

		if (Objects.equals(pChoice, cmpChoice)) {
			myboard.setLower("DRAW");
			computersboard.setLower("DRAW");

		} else if ((Objects.equals(pChoice, "ROCK") && Objects.equals(cmpChoice, "SCISSORS")) ||
				   (Objects.equals(pChoice, "PAPER") && Objects.equals(cmpChoice, "ROCK"))    ||
				   (Objects.equals(pChoice, "SCISSORS") && Objects.equals(cmpChoice, "PAPER"))) {

			myboard.setLower("WON");
			computersboard.setLower("LOST");

			myboard.wins();

		} else {
			myboard.setLower("LOST");
			computersboard.setLower("WON");

			computersboard.wins();
		}
	}

	public void actionPerformed(ActionEvent e) {

		// Count down
		if (counter == 0) {
			// reset color
			myboard.resetColor();
			computersboard.resetColor();

			myboard.setUpper("ONE...");
			computersboard.setUpper("ONE...");

		} else if (counter == 1) {
			myboard.setUpper("TWO...");
			computersboard.setUpper("TWO...");

		} else {
			// get player's choice
			pChoice = e.getActionCommand();

			// send player's choice to server and receive computer choice
			try {
				out.println(pChoice);
				out.flush();

				cmpChoice = in.readLine();
			} catch (IOException ex) {
				System.err.println(ex);
			}

			// decide winner
			checkWinner();

			// display choices
			myboard.setUpper(pChoice);
			computersboard.setUpper(cmpChoice);

			// turn choices yellow
			myboard.markPlayed(pChoice);
			computersboard.markPlayed(cmpChoice);

			// reset counter
			counter = -1;
		}

		counter ++;
	}
}
