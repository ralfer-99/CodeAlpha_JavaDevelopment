import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class WordCounter extends JFrame {
    private JTextArea textArea;
    private JLabel wordCountLabel;

    public WordCounter() {
        setTitle("Word Counter Application");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        wordCountLabel = new JLabel("Word count: 0");

        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int wordCount = textArea.getText().trim().isEmpty() ? 0 : textArea.getText().split("\\s+").length;
                wordCountLabel.setText("Number of Words : " + wordCount);
            }
        });

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        add(wordCountLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WordCounter().setVisible(true);
        });
    }
}
