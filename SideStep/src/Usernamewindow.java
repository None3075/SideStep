import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class Usernamewindow extends JFrame implements ActionListener{
	JButton button;
	JTextField textField;
	static String Username;
	Font font;
	static GameFrame frame;
	
	Usernamewindow(){
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("Resources/Misc/arcade.otf"));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Score highscore=new Score();
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 500);
		panel.setBackground(Color.BLACK);
		panel.setLayout(new BorderLayout());
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(500,500);
		ImageIcon image = new ImageIcon("Resources/Sprites/logo.png");
		this.setTitle("SideStepGame");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		
		button = new JButton();
		button.setBounds(150, 300, 200, 100);
		button.addActionListener(this);
		button.setText("PLAY");
		
		button.setFocusable(false);
		button.setHorizontalTextPosition(JButton.CENTER);
		button.setVerticalTextPosition(JButton.BOTTOM);
		font  = font.deriveFont(Font.BOLD, 25);
		button.setFont(font);
		button.setForeground(Color.WHITE);
		button.setBackground(Color.BLACK);
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE,10, false));

		textField = new JTextField();
		textField.setPreferredSize(new Dimension(250,40));
		font  = font.deriveFont(Font.PLAIN, 20);
		textField.setFont(font);
		textField.setForeground(new Color(0x00FF00));
		textField.setBackground(Color.black);
		textField.setCaretColor(Color.white);
		textField.setText("username");
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBounds(125, 225, 250, 50);
	

		
		font  = font.deriveFont(Font.BOLD, 50);

		JLabel titlelabel = new JLabel();
		titlelabel.setText("SideStep");
		titlelabel.setVerticalAlignment(JLabel.BOTTOM);
		titlelabel.setHorizontalAlignment(JLabel.CENTER);
		titlelabel.setFont(font);
		titlelabel.setForeground(Color.WHITE);
		titlelabel.setBounds(0, 0, 500, 150);

		font  = font.deriveFont(Font.BOLD, 25);

		JLabel HSlabel = new JLabel();
		HSlabel.setText("HighScore:"+highscore.MaxPlayer+" "+highscore.MaxValue);
		HSlabel.setVerticalAlignment(JLabel.BOTTOM);
		HSlabel.setHorizontalAlignment(JLabel.CENTER);
		HSlabel.setFont(font);
		HSlabel.setForeground(Color.WHITE);
		HSlabel.setBounds(-7, 0, 500, 200);

		JLabel label = new JLabel();
		label.setBounds(0, 0, 500, 500);

		label.add(button);
		label.add(textField);
		label.add(titlelabel);
		label.add(HSlabel);
		panel.add(label);
		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(image.getImage());
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			Username = textField.getText();
			this.dispose();
			frame = new GameFrame();
		}	
	}
	public static void restart(){
		frame.dispose();
		Usernamewindow window = new Usernamewindow();
	}
}
