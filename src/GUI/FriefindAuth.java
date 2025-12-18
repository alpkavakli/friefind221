package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.*;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class FriefindAuth extends JFrame {

	private CardLayout cardLayout;
	private JPanel mainContainer;
	private File userFile = new File("users.txt");

	public FriefindAuth() {
		setBackground(SystemColor.inactiveCaption);
		setTitle("Friefind");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		mainContainer = new JPanel(cardLayout);

		JPanel loginPanel = createLoginPanel();
		JPanel signupPanel = createSignupPanel();

		mainContainer.add(loginPanel, "Login");
		mainContainer.add(signupPanel, "Signup");

		getContentPane().add(mainContainer);

		if (!userFile.exists()) {
			try {
				userFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private JPanel createLoginPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(199, 229, 252));
		panel.setForeground(new Color(192, 231, 255));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		
		JPasswordField passField = new JPasswordField();
		passField.setBounds(205, 81, 145, 50);
		
		JButton loginBtn = new JButton("Log In");
		loginBtn.setBounds(50, 155, 145, 50);
		loginBtn.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		
		JButton goToSignupBtn = new JButton("Create an Account");
		goToSignupBtn.setBounds(205, 155, 145, 50);
		goToSignupBtn.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		panel.setLayout(null);

		JLabel label = new JLabel("Username:");
		label.setBounds(50, 21, 145, 50);
		label.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		panel.add(label);

		JTextField userField = new JTextField();
		userField.setBounds(205, 21, 145, 50);
		panel.add(userField);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setBounds(50, 81, 145, 50);
		label_1.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		panel.add(label_1);
		panel.add(passField);
		panel.add(loginBtn);
		panel.add(goToSignupBtn);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(100, 227, 222, 16);
		panel.add(lblNewLabel);

		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = userField.getText();
				String pass = new String(passField.getPassword());

				if (checkLogin(user, pass)) {
					JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + user);
					// Buraya Ana Ekranı açacak kod gelecek
				} else {
					lblNewLabel.setText("Invalid username or password!");
				}

			}
		});

		goToSignupBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText("");
				cardLayout.show(mainContainer, "Signup");
			}
		});

		return panel;
	}

	private JPanel createSignupPanel() {
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBackground(SystemColor.activeCaption);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

		JTextField newUserField = new JTextField();
		newUserField.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		newUserField.setBackground(SystemColor.activeCaption);
		newUserField.setBounds(205, 31, 145, 40);

		JPasswordField newPassField = new JPasswordField();
		newPassField.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		newPassField.setBounds(205, 96, 145, 40);

		JButton registerBtn = new JButton("Register");
		registerBtn.setBackground(SystemColor.activeCaption);
		registerBtn.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		registerBtn.setForeground(SystemColor.controlDkShadow);
		registerBtn.setBounds(50, 165, 145, 50);

		JButton backBtn = new JButton("Back");
		backBtn.setBackground(SystemColor.activeCaption);
		backBtn.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		backBtn.setForeground(SystemColor.controlDkShadow);
		backBtn.setBounds(205, 165, 145, 50);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		lblUsername.setForeground(SystemColor.controlDkShadow);
		lblUsername.setBounds(50, 21, 145, 50);
		panel.add(lblUsername);
		panel.add(newUserField);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
		lblPassword.setForeground(SystemColor.controlDkShadow);
		lblPassword.setBounds(50, 91, 145, 50);
		panel.add(lblPassword);
		panel.add(newPassField);
		panel.add(registerBtn);
		panel.add(backBtn);

		registerBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String user = newUserField.getText();
				String pass = new String(newPassField.getPassword());

				if (user.isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all fields!");
				} else {
					saveUser(user, pass);
					JOptionPane.showMessageDialog(null, "Registration Successful! You can login now.");
					cardLayout.show(mainContainer, "Login");
				}
			}
		});

		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(mainContainer, "Login");
			}
		});

		return panel;
	}

	private String hashPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void saveUser(String user, String pass) {
		FileWriter fw;
		try {
			fw = new FileWriter(userFile, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(user + "," + hashPassword(pass));
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean checkLogin(String user, String pass) {
		if (!userFile.exists())
			return false;
		try (BufferedReader br = new BufferedReader(new FileReader(userFile))) {
			String line;
			String hashedInput = hashPassword(pass);
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length >= 2 && parts[0].equals(user) && parts[1].equals(hashedInput)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}