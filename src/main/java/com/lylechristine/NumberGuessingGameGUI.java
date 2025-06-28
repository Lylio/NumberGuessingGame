package com.lylechristine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;    
import java.util.Random;
import javax.sound.sampled.*;
import java.net.URL;

public class NumberGuessingGameGUI extends JFrame implements ActionListener {
    private int numberToGuess;
    private int numberOfTries;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;
    private JPanel panel;

    public NumberGuessingGameGUI() {
        super("Number Guessing Game 🎯");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 200);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel promptLabel = new JLabel("Guess a number between 1 and 100:");
        panel.add(promptLabel);

        guessField = new JTextField(10);
        panel.add(guessField);

        guessButton = new JButton("Submit Guess");
        guessButton.addActionListener(this);
        panel.add(guessButton);

        messageLabel = new JLabel(" ");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(panel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        resetGame();
        setVisible(true);
    }

    private void resetGame() {
        numberToGuess = new Random().nextInt(100) + 1;
        numberOfTries = 0;
        guessField.setText("");
        messageLabel.setText(" ");
        panel.setBackground(null);
    }

    private void playSound(String resourceName) {
        try {
            URL soundURL = getClass().getClassLoader().getResource(resourceName);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + resourceName);
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.err.println("Sound error: " + e.getMessage());
        }
    }

    private void flashPanel(Color color) {
        Color originalColor = panel.getBackground();
        panel.setBackground(color);

        Timer timer = new Timer(300, e -> panel.setBackground(originalColor));
        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = guessField.getText();
        try {
            int guess = Integer.parseInt(userInput);
            numberOfTries++;

            if (guess < 1 || guess > 100) {
                messageLabel.setText("❗ Please enter a number between 1 and 100.");
                playSound("wrong.wav");
                flashPanel(Color.ORANGE);
            } else if (guess < numberToGuess) {
                messageLabel.setText("🔼 Too low. Try again.");
                playSound("wrong.wav");
                flashPanel(Color.CYAN);
            } else if (guess > numberToGuess) {
                messageLabel.setText("🔽 Too high. Try again.");
                playSound("wrong.wav");
                flashPanel(Color.PINK);
            } else {
                messageLabel.setText("🎉 Correct! Tries: " + numberOfTries);
                playSound("correct.wav");
                flashPanel(Color.GREEN);

                int option = JOptionPane.showConfirmDialog(this,
                        "🎉 You guessed it in " + numberOfTries + " tries!\nPlay again?",
                        "Game Over",
                        JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("❗ Invalid input. Enter a number.");
            playSound("wrong.wav");
            flashPanel(Color.LIGHT_GRAY);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGameGUI::new);
    }
}
