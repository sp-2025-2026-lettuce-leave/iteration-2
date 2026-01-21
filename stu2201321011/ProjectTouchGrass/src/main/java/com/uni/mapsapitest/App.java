package com.uni.mapsapitest;
import me.friwi.jcefmaven.CefAppBuilder;
import me.friwi.jcefmaven.MavenCefAppHandlerAdapter;
import org.cef.CefApp;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class App {

	enum Screen{
		Login,
		Register,
		ListEventEnterCode,
		MakeEvent,
		BrowseCodes,
	}
	
    // The component that will hold our map
    private final JPanel mapPanel = new JPanel(new BorderLayout());
    private JFrame frame;
    private JPanel loginPanel, registerPanel, listEventEnterCodePanel, makeEventPanel, browseCodesPanel;
    private CefApp cefApp;
    private CefBrowser browser;
    private Screen currentScreen = null;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().initAndShowGUI());
    }
    
    private JPanel mapScreenToPanel(Screen screen) {
    	switch(currentScreen) {
			case Login: return loginPanel;
			case Register: return registerPanel;
			case ListEventEnterCode: return listEventEnterCodePanel;
			case MakeEvent: return makeEventPanel;
			case BrowseCodes: return browseCodesPanel;
			default: return null;
		}
    }
    
    private void switchScreen(Screen newScreen) {
    	if (currentScreen != null) {
        	JPanel oldPanel = mapScreenToPanel(currentScreen);
        	frame.remove(oldPanel);
    	}
    	currentScreen = newScreen;
    	JPanel newPanel = mapScreenToPanel(currentScreen);
    	frame.add(newPanel);
    	frame.revalidate();
        frame.repaint();
    }

    /**
     * @wbp.parser.entryPoint
     */
    private void initAndShowGUI() {
        // 1. Create your Main Application Window
        frame = new JFrame("Project Touch Grass"); // Example App Name
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        //frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
        frame.getContentPane().setLayout(new BorderLayout());
        
        initLoginPanel();
        initRegisterPanel();
        initListEventEnterCodePanel();
        switchScreen(Screen.Register);

        frame.setVisible(true);

        // 4. Initialize Chromium in the background
        new Thread(this::initChromium).start();

        // 5. Cleanup on Close
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (cefApp != null) cefApp.dispose();
                frame.dispose();
                System.exit(0);
            }
        });
    }
    
    private void initLoginPanel() {
    	loginPanel = new JPanel(new GridBagLayout());

    	// Username label
    	GridBagConstraints gbc1 = new GridBagConstraints();
    	gbc1.gridx = 0;
    	gbc1.gridy = 0;
    	gbc1.insets = new Insets(5, 5, 5, 5);
    	gbc1.anchor = GridBagConstraints.EAST;
    	loginPanel.add(new JLabel("Username:"), gbc1);

    	// Username field
    	GridBagConstraints gbc2 = new GridBagConstraints();
    	gbc2.gridx = 1;
    	gbc2.gridy = 0;
    	gbc2.insets = new Insets(5, 5, 5, 5);
    	gbc2.fill = GridBagConstraints.HORIZONTAL;
    	JTextField usernameField = new JTextField(15);
    	loginPanel.add(usernameField, gbc2);

    	// Password label
    	GridBagConstraints gbc3 = new GridBagConstraints();
    	gbc3.gridx = 0;
    	gbc3.gridy = 1;
    	gbc3.insets = new Insets(5, 5, 5, 5);
    	gbc3.anchor = GridBagConstraints.EAST;
    	loginPanel.add(new JLabel("Password:"), gbc3);

    	// Password field
    	GridBagConstraints gbc4 = new GridBagConstraints();
    	gbc4.gridx = 1;
    	gbc4.gridy = 1;
    	gbc4.insets = new Insets(5, 5, 5, 5);
    	gbc4.fill = GridBagConstraints.HORIZONTAL;
    	JPasswordField passwordField = new JPasswordField(15);
    	loginPanel.add(passwordField, gbc4);

    	// Login button
    	GridBagConstraints gbc5 = new GridBagConstraints();
    	gbc5.gridx = 0;
    	gbc5.gridy = 2;
    	gbc5.gridwidth = 2;
    	gbc5.insets = new Insets(10, 5, 5, 5);
    	gbc5.anchor = GridBagConstraints.CENTER;
    	JButton loginButton = new JButton("Login");
    	loginPanel.add(loginButton, gbc5);
    	
    	loginButton.addActionListener(e -> switchScreen(Screen.ListEventEnterCode));

    }
    
    private void initRegisterPanel() {
    	registerPanel = new JPanel(new GridBagLayout());

    	// Username label
    	GridBagConstraints gbc1 = new GridBagConstraints();
    	gbc1.gridx = 0;
    	gbc1.gridy = 0;
    	gbc1.insets = new Insets(5, 5, 5, 5);
    	gbc1.anchor = GridBagConstraints.EAST;
    	registerPanel.add(new JLabel("Username:"), gbc1);

    	// Username field
    	GridBagConstraints gbc2 = new GridBagConstraints();
    	gbc2.gridx = 1;
    	gbc2.gridy = 0;
    	gbc2.insets = new Insets(5, 5, 5, 5);
    	gbc2.fill = GridBagConstraints.HORIZONTAL;
    	JTextField usernameField = new JTextField(15);
    	registerPanel.add(usernameField, gbc2);

    	// Password label
    	GridBagConstraints gbc3 = new GridBagConstraints();
    	gbc3.gridx = 0;
    	gbc3.gridy = 1;
    	gbc3.insets = new Insets(5, 5, 5, 5);
    	gbc3.anchor = GridBagConstraints.EAST;
    	registerPanel.add(new JLabel("Password:"), gbc3);

    	// Password field
    	GridBagConstraints gbc4 = new GridBagConstraints();
    	gbc4.gridx = 1;
    	gbc4.gridy = 1;
    	gbc4.insets = new Insets(5, 5, 5, 5);
    	gbc4.fill = GridBagConstraints.HORIZONTAL;
    	JPasswordField passwordField = new JPasswordField(15);
    	registerPanel.add(passwordField, gbc4);

    	// register button
    	GridBagConstraints gbc5 = new GridBagConstraints();
    	gbc5.gridx = 0;
    	gbc5.gridy = 2;
    	gbc5.gridwidth = 2;
    	gbc5.insets = new Insets(10, 5, 5, 5);
    	gbc5.anchor = GridBagConstraints.CENTER;
    	JButton registerButton = new JButton("Register");
    	registerPanel.add(registerButton, gbc5);

    }
    
    private void initListEventEnterCodePanel() {
    	listEventEnterCodePanel = new JPanel(new GridLayout(0, 2, 0, 0));
    	
    	// 3. Setup the Map Panel (Initially showing a loading state)
        JLabel loadingLabel = new JLabel("Initializing Map Engine...", SwingConstants.CENTER);
        mapPanel.add(loadingLabel, BorderLayout.CENTER);
        listEventEnterCodePanel.add(mapPanel);

        // 2. Add some "Application" sidebars (Just to show it's a real app)
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // ===== TOP AREA =====
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Enter code:");
        JTextField textField = new JTextField();
        JButton submitButton = new JButton("Submit");

        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.EAST);

        topPanel.add(label);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(inputPanel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // ===== CARDS LIST =====
        JPanel cardsContainer = new JPanel();
        cardsContainer.setLayout(new BoxLayout(cardsContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(cardsContainer);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ===== BUTTON ACTION =====
        submitButton.addActionListener(e -> {
            String text = textField.getText().trim();
            if (text.isEmpty()) return;

            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(new CompoundBorder(
                    new LineBorder(Color.GRAY),
                    new EmptyBorder(8, 8, 8, 8)
            ));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

            JLabel titleLabel = new JLabel("Event Title");
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));

            JTextArea description = new JTextArea(text);
            description.setLineWrap(true);
            description.setWrapStyleWord(true);
            description.setEditable(false);
            description.setOpaque(false);

            card.add(titleLabel);
            card.add(Box.createVerticalStrut(5));
            card.add(description);

            cardsContainer.add(card);
            cardsContainer.add(Box.createVerticalStrut(10));

            cardsContainer.revalidate();
            cardsContainer.repaint();

            textField.setText("");
        });
        
        listEventEnterCodePanel.add(mainPanel);
    }

    private void initChromium() {
        try {
            CefAppBuilder builder = new CefAppBuilder();
            builder.setInstallDir(new File("jcef-bundle"));
            
            // Handle app state to prevent hanging
            builder.setAppHandler(new MavenCefAppHandlerAdapter() {
                @Override
                public void stateHasChanged(org.cef.CefApp.CefAppState state) {
                    if (state == CefApp.CefAppState.TERMINATED) System.exit(0);
                }
            });

            cefApp = builder.build();
            CefClient client = cefApp.createClient();

            // 1. Locate the file relative to the project directory
            File localFile = new File("index.html"); 

            // 2. Check if it exists (good for debugging)
            if (!localFile.exists()) {
                System.err.println("Error: index.html not found at " + localFile.getAbsolutePath());
            }

            // 3. Convert to a valid browser URL (file:///...)
            // .toURI().toString() automatically handles slashes and spaces for your OS
            String localUrl = localFile.toURI().toString(); 

            // 4. Create browser with the local URL
            // Remember: Use OSR (true) to prevent freezing
            browser = client.createBrowser(localUrl, true, false);

            Component browserUI = browser.getUIComponent();

            // 5. Update Swing UI when ready
            SwingUtilities.invokeLater(() -> {
                mapPanel.removeAll();
                mapPanel.add(browserUI, BorderLayout.CENTER);
                mapPanel.revalidate();
                mapPanel.repaint();
            });

        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> 
                JOptionPane.showMessageDialog(null, "Map failed to load: " + e.getMessage())
            );
        }
    }
}