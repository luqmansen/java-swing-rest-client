import REST.Request;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class FactGenUI{

    private FactGenUI() {

        generateFactButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    ContentParser theFact = new ContentParser();


                    System.out.println("Retrieving fact image...");
                    factFetchProgressBar.setString("Retrieving fact image...");
                    factImageLabel.setIcon(new ImageIcon(theFact.getFactImage()));

                    System.out.println("Retrieving fact title...");
                    factFetchProgressBar.setString("Retrieving fact title...");
                    factTitleLabel.setText(theFact.getFactTitle());

                    System.out.println("Retrieving fact content...");
                    factFetchProgressBar.setString("Retrieving fact content...");
                    factContentTextArea.setText(theFact.getFactContent());

                    factFetchProgressBar.setString("Done!");

                    // Open the fact sauce in default browser
                    factSauceTextArea.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            System.out.println("Length: "+ factSauceTextArea.getText().length());
                            try {
                                WebHandler.openURI(factSauceTextArea.getText());
                            } catch (IOException | URISyntaxException except) {
                                showErrorPopup(except.toString());
                            }
                        }
                    });
                }
                catch (RuntimeException | IOException except){
                    System.out.println(except);
                    showErrorPopup(except.toString());
                }
                finally {
                    factFetchProgressBar.setString("not running");
                }
            }
        });

        // Show the fact image or not
        toggleFactImageCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (toggleFactImageCheckBox.isSelected()) factImageLabel.setVisible(true);
                else factImageLabel.setVisible(false);
            }
        });
    }

    public static void main(String[] args) throws IOException{
        frame = new JFrame("Fact Generator");

        // Swing JFrame preparation
        frame.setResizable(false);
        frame.setContentPane(new FactGenUI().rootPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setIconImage(ImageIO.read(new File("assets/fr-profile-192.jpg"))); // throws IOException
        frame.pack();
        frame.setVisible(true);
    }

    // Spawn a MessageBox containing caught exceptions
    private void showErrorPopup(String exceptionParam) {
        System.out.println(exceptionParam);
        JOptionPane.showMessageDialog(
                frame,
                "Exception thrown: \n" + exceptionParam + "\nPlease make sure that you are connected to the Internet",
                "Error!",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // Swing Elements declaration
    private static JFrame frame;
    private JPanel rootPanel;
    private JButton generateFactButton;
    private JPanel factImagePanel;
    private JLabel factTextLabel;
    private JLabel factSauceLabel;
    private JLabel factTitleLabel;
    private JLabel factImageLabel;
    private JTextArea factContentTextArea;
    private JTextArea factSauceTextArea;
    private JTextArea factTitleTextArea;
    private JCheckBox toggleFactImageCheckBox;
    private JProgressBar factFetchProgressBar;

}