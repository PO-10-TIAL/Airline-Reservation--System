import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class AirlineReservationUI {
    private final AirlineService service = new AirlineService();
    private User currentUser;

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JLabel statusLabel;

    private JTextField customerLoginEmailField;
    private JPasswordField customerLoginPasswordField;
    private JTextField registerNameField;
    private JTextField registerEmailField;
    private JPasswordField registerPasswordField;
    private JTextField registerPhoneField;
    private JTextField adminEmailField;
    private JPasswordField adminPasswordField;
    private JTextField adminRegisterNameField;
    private JTextField adminRegisterEmailField;
    private JPasswordField adminRegisterPasswordField;
    private JTextField adminRegisterPhoneField;
    private JTextField adminRegisterCodeField;

    private DefaultTableModel customerFlightTableModel;
    private DefaultTableModel customerReservationTableModel;
    private JTextField searchOriginField;
    private JTextField searchDestinationField;
    private JTextField bookingFlightNumberField;
    private JTextField bookingSeatsField;

    private DefaultTableModel adminFlightTableModel;
    private DefaultTableModel adminReservationTableModel;
    private DefaultTableModel adminCustomerTableModel;
    private JTextField adminFlightNumberField;
    private JTextField adminOriginField;
    private JTextField adminDestinationField;
    private JTextField adminDepartField;
    private JTextField adminArriveField;
    private JTextField adminSeatsField;
    private JTextField adminPriceField;
    private JTextField adminCustomerSearchField;
    private JLabel overviewFlightsLabel;
    private JLabel overviewCustomersLabel;
    private JLabel overviewReservationsLabel;
    private JLabel overviewSeatsLabel;
    private JButton adminFlightButton;
    private JButton adminReservationButton;
    private JButton adminCustomerButton;
    private JButton adminOverviewButton;
    private JButton activeAdminNavButton;

    private static final String CARD_WELCOME = "welcome";
    private static final String CARD_CUSTOMER_LOGIN = "customerLogin";
    private static final String CARD_REGISTER = "register";
    private static final String CARD_ADMIN_LOGIN = "adminLogin";
    private static final String CARD_CUSTOMER_DASHBOARD = "customerDashboard";
    private static final String CARD_ADMIN_DASHBOARD = "adminDashboard";
    private static final String CARD_ADMIN_REGISTER = "adminRegister";

    public void show() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        frame = new JFrame("Airline Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1040, 720);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 249, 252));

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        cardsPanel.add(createWelcomePanel(), CARD_WELCOME);
        cardsPanel.add(createCustomerLoginPanel(), CARD_CUSTOMER_LOGIN);
        cardsPanel.add(createRegistrationPanel(), CARD_REGISTER);
        cardsPanel.add(createAdminLoginPanel(), CARD_ADMIN_LOGIN);
        cardsPanel.add(createAdminRegistrationPanel(), CARD_ADMIN_REGISTER);
        cardsPanel.add(createCustomerDashboardPanel(), CARD_CUSTOMER_DASHBOARD);
        cardsPanel.add(createAdminDashboardPanel(), CARD_ADMIN_DASHBOARD);

        frame.setLayout(new BorderLayout());
        frame.add(cardsPanel, BorderLayout.CENTER);
        frame.add(createStatusPanel(), BorderLayout.SOUTH);
        switchToCard(CARD_WELCOME);
        frame.setVisible(true);
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint sky = new GradientPaint(0, 0, new Color(12, 74, 148), 0, getHeight(), new Color(2, 24, 66));
                g2.setPaint(sky);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(255, 255, 255, 35));
                g2.fillOval(getWidth() - 420, 40, 360, 180);
                g2.fillOval(100, getHeight() - 280, 420, 200);

                g2.setColor(new Color(255, 255, 255, 140));
                int[] x = {getWidth() - 420, getWidth() - 260, getWidth() - 240, getWidth() - 380};
                int[] y = {200, 220, 250, 230};
                g2.fillPolygon(x, y, x.length);
                g2.fillRect(getWidth() - 320, 220, 28, 14);
                g2.setStroke(new BasicStroke(3f));
                g2.drawLine(getWidth() - 420, 220, getWidth() - 310, 205);
                g2.drawLine(getWidth() - 330, 187, getWidth() - 280, 210);
            }
        };
        panel.setOpaque(true);

        JLabel title = new JLabel("Skyline Airline", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 44));

        JLabel subtitle = new JLabel("Book flights faster. Fly with comfort and confidence.", SwingConstants.CENTER);
        subtitle.setForeground(new Color(235, 245, 255));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JLabel tagline = new JLabel("Trusted reservation flow for every traveler. Ready for takeoff in just a few clicks.", SwingConstants.CENTER);
        tagline.setForeground(new Color(215, 230, 255));
        tagline.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JPanel customerCard = buildWelcomePanelCard("Customer Portal", "Login or sign up to book your next trip.", new Color(255, 255, 255), new Color(0, 102, 204));
        JPanel adminCard = buildWelcomePanelCard("Admin Control", "Manage flights, customers, and reservations.", new Color(255, 255, 255), new Color(220, 20, 60));

        JButton customerLoginButton = new JButton("Customer Login");
        JButton customerRegisterButton = new JButton("Customer Register");
        JButton adminLoginButton = new JButton("Admin Login");
        JButton adminRegisterButton = new JButton("Register Admin");
        applyButtonStyle(customerLoginButton, new Color(0, 102, 204));
        applyButtonStyle(customerRegisterButton, new Color(255, 255, 255));
        applyButtonStyle(adminLoginButton, new Color(220, 20, 60));
        applyButtonStyle(adminRegisterButton, new Color(255, 255, 255));
        customerRegisterButton.setForeground(new Color(0, 102, 204));
        adminRegisterButton.setForeground(new Color(220, 20, 60));

        customerLoginButton.addActionListener(e -> switchToCard(CARD_CUSTOMER_LOGIN));
        customerRegisterButton.addActionListener(e -> switchToCard(CARD_REGISTER));
        adminLoginButton.addActionListener(e -> switchToCard(CARD_ADMIN_LOGIN));
        adminRegisterButton.addActionListener(e -> switchToCard(CARD_ADMIN_REGISTER));

        JPanel customerButtons = new JPanel(new GridLayout(1, 2, 12, 0));
        customerButtons.setOpaque(false);
        customerButtons.add(customerLoginButton);
        customerButtons.add(customerRegisterButton);

        JPanel adminButtons = new JPanel(new GridLayout(1, 2, 12, 0));
        adminButtons.setOpaque(false);
        adminButtons.add(adminLoginButton);
        adminButtons.add(adminRegisterButton);

        customerCard.add(customerButtons, BorderLayout.SOUTH);
        adminCard.add(adminButtons, BorderLayout.SOUTH);

        JPanel cardGrid = new JPanel(new GridLayout(1, 2, 24, 0));
        cardGrid.setOpaque(false);
        cardGrid.add(customerCard);
        cardGrid.add(adminCard);

        JPanel hero = new JPanel(new GridLayout(3, 1, 12, 12));
        hero.setOpaque(false);
        hero.add(title);
        hero.add(subtitle);
        hero.add(tagline);

        JPanel heroPanel = new JPanel(new BorderLayout(0, 32));
        heroPanel.setOpaque(false);
        heroPanel.setBorder(BorderFactory.createEmptyBorder(60, 64, 64, 64));
        heroPanel.add(hero, BorderLayout.NORTH);
        heroPanel.add(cardGrid, BorderLayout.CENTER);

        panel.add(heroPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCustomerLoginPanel() {
        JPanel panel = createFormPanel("Customer Login", createTicketIcon(40));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        customerLoginEmailField = new JTextField(18);
        customerLoginPasswordField = new JPasswordField(18);

        addFormField(formPanel, "Email:", customerLoginEmailField, 0);
        addFormField(formPanel, "Password:", customerLoginPasswordField, 1);

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");
        applyButtonStyle(loginButton, new Color(33, 150, 243));
        applyButtonStyle(backButton, new Color(117, 117, 117));
        loginButton.addActionListener(this::handleCustomerLogin);
        backButton.addActionListener(e -> switchToCard(CARD_WELCOME));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        actionPanel.setOpaque(false);
        actionPanel.add(backButton);
        actionPanel.add(loginButton);

        JPanel card = new JPanel(new BorderLayout(0, 20));
        card.setOpaque(true);
        card.setBackground(new Color(255, 255, 255, 240));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 82, 155), 2), BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        card.add(formPanel, BorderLayout.CENTER);
        card.add(actionPanel, BorderLayout.SOUTH);

        panel.add(card, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createRegistrationPanel() {
        JPanel panel = createFormPanel("New Customer Registration", createPassportIcon(40));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        registerNameField = new JTextField(18);
        registerEmailField = new JTextField(18);
        registerPasswordField = new JPasswordField(18);
        registerPhoneField = new JTextField(18);

        addFormField(formPanel, "Full Name:", registerNameField, 0);
        addFormField(formPanel, "Email:", registerEmailField, 1);
        addFormField(formPanel, "Password:", registerPasswordField, 2);
        addFormField(formPanel, "Phone:", registerPhoneField, 3);

        JButton submitButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        applyButtonStyle(submitButton, new Color(76, 175, 80));
        applyButtonStyle(backButton, new Color(117, 117, 117));
        submitButton.addActionListener(this::handleCustomerRegistration);
        backButton.addActionListener(e -> switchToCard(CARD_WELCOME));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        actionPanel.setOpaque(false);
        actionPanel.add(backButton);
        actionPanel.add(submitButton);

        JPanel card = new JPanel(new BorderLayout(0, 20));
        card.setOpaque(true);
        card.setBackground(new Color(255, 255, 255, 240));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 82, 155), 2), BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        card.add(formPanel, BorderLayout.CENTER);
        card.add(actionPanel, BorderLayout.SOUTH);

        panel.add(card, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAdminLoginPanel() {
        JPanel panel = createFormPanel("Administrator Login", createPassportIcon(40));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        adminEmailField = new JTextField(18);
        adminPasswordField = new JPasswordField(18);

        addFormField(formPanel, "Email:", adminEmailField, 0);
        addFormField(formPanel, "Password:", adminPasswordField, 1);

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register Admin");
        JButton backButton = new JButton("Back");
        applyButtonStyle(loginButton, new Color(33, 150, 243));
        applyButtonStyle(registerButton, new Color(103, 58, 183));
        applyButtonStyle(backButton, new Color(117, 117, 117));
        loginButton.addActionListener(this::handleAdminLogin);
        registerButton.addActionListener(e -> switchToCard(CARD_ADMIN_REGISTER));
        backButton.addActionListener(e -> switchToCard(CARD_WELCOME));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        actionPanel.setOpaque(false);
        actionPanel.add(backButton);
        actionPanel.add(registerButton);
        actionPanel.add(loginButton);

        JPanel card = new JPanel(new BorderLayout(0, 20));
        card.setOpaque(true);
        card.setBackground(new Color(255, 255, 255, 240));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 82, 155), 2), BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        card.add(formPanel, BorderLayout.CENTER);
        card.add(actionPanel, BorderLayout.SOUTH);

        panel.add(card, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAdminRegistrationPanel() {
        JPanel panel = createFormPanel("Create Admin Account", createPassportIcon(40));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        adminRegisterNameField = new JTextField(18);
        adminRegisterEmailField = new JTextField(18);
        adminRegisterPasswordField = new JPasswordField(18);
        adminRegisterPhoneField = new JTextField(18);
        adminRegisterCodeField = new JTextField(18);

        addFormField(formPanel, "Full Name:", adminRegisterNameField, 0);
        addFormField(formPanel, "Email:", adminRegisterEmailField, 1);
        addFormField(formPanel, "Password:", adminRegisterPasswordField, 2);
        addFormField(formPanel, "Phone:", adminRegisterPhoneField, 3);
        addFormField(formPanel, "Admin Code:", adminRegisterCodeField, 4);

        JButton submitButton = new JButton("Create Admin");
        JButton backButton = new JButton("Back");
        applyButtonStyle(submitButton, new Color(103, 58, 183));
        applyButtonStyle(backButton, new Color(117, 117, 117));
        submitButton.addActionListener(this::handleAdminRegistration);
        backButton.addActionListener(e -> switchToCard(CARD_WELCOME));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        actionPanel.setOpaque(false);
        actionPanel.add(backButton);
        actionPanel.add(submitButton);

        JPanel card = new JPanel(new BorderLayout(0, 20));
        card.setOpaque(true);
        card.setBackground(new Color(255, 255, 255, 240));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 82, 155), 2), BorderFactory.createEmptyBorder(24, 24, 24, 24)));
        card.add(formPanel, BorderLayout.CENTER);
        card.add(actionPanel, BorderLayout.SOUTH);

        panel.add(card, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCustomerDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 249, 252));

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        JLabel title = new JLabel("Customer Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(25, 45, 75));
        JButton logoutButton = new JButton("Logout");
        applyButtonStyle(logoutButton, new Color(229, 57, 53));
        logoutButton.addActionListener(e -> {
            currentUser = null;
            switchToCard(CARD_WELCOME);
            setStatus("Logged out.");
        });
        topBar.add(title, BorderLayout.WEST);
        topBar.add(logoutButton, BorderLayout.EAST);
        topBar.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.addTab("Search Flights", createCustomerSearchPanel());
        tabbedPane.addTab("My Reservations", createCustomerReservationsPanel());

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createCustomerSearchPanel() {
        JPanel panel = createContentPanel();

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        searchPanel.setOpaque(false);
        searchOriginField = new JTextField(14);
        searchDestinationField = new JTextField(14);
        JButton searchButton = new JButton("Search");
        JButton refreshButton = new JButton("Show All");
        applyButtonStyle(searchButton, new Color(0, 122, 204));
        applyButtonStyle(refreshButton, new Color(38, 166, 91));

        searchPanel.add(labelWithFont("Origin:"));
        searchPanel.add(searchOriginField);
        searchPanel.add(labelWithFont("Destination:"));
        searchPanel.add(searchDestinationField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        customerFlightTableModel = new DefaultTableModel(new Object[]{"Flight #", "Origin", "Destination", "Depart", "Arrive", "Seats", "Price"}, 0);
        JTable flightTable = new JTable(customerFlightTableModel);
        configureTable(flightTable);
        flightTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && flightTable.getSelectedRow() >= 0) {
                    bookingFlightNumberField.setText(customerFlightTableModel.getValueAt(flightTable.getSelectedRow(), 0).toString());
                }
            }
        });

        searchButton.addActionListener(e -> {
            String origin = searchOriginField.getText().trim();
            String destination = searchDestinationField.getText().trim();
            if (origin.isEmpty() || destination.isEmpty()) {
                showMessage("Validation error", "Please enter both origin and destination.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            refreshCustomerFlightTable(service.searchFlights(origin, destination));
        });
        refreshButton.addActionListener(e -> refreshCustomerFlightTable(service.getAllFlights()));

        JPanel bookingPanel = new JPanel(new GridBagLayout());
        bookingPanel.setOpaque(false);
        bookingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 211, 222)), "Book Selected Flight"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        bookingFlightNumberField = new JTextField(16);
        bookingSeatsField = new JTextField(6);

        addFieldToPanel(bookingPanel, labelWithFont("Flight #:"), bookingFlightNumberField, gbc, 0);
        addFieldToPanel(bookingPanel, labelWithFont("Seats:"), bookingSeatsField, gbc, 1);

        JButton bookButton = new JButton("Book Flight");
        applyButtonStyle(bookButton, new Color(76, 175, 80));
        bookButton.addActionListener(this::handleCustomerFlightBooking);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        bookingPanel.add(bookButton, gbc);

        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(flightTable), BorderLayout.CENTER);
        panel.add(bookingPanel, BorderLayout.SOUTH);

        refreshCustomerFlightTable(service.getAllFlights());
        return panel;
    }

    private JPanel createCustomerReservationsPanel() {
        JPanel panel = createContentPanel();

        customerReservationTableModel = new DefaultTableModel(new Object[]{"Reservation #", "Flight #", "Seats", "Price", "Booked"}, 0);
        JTable reservationTable = new JTable(customerReservationTableModel);
        configureTable(reservationTable);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 12));
        bottomPanel.setOpaque(false);
        JTextField cancelReservationField = new JTextField(14);
        JButton cancelButton = new JButton("Cancel Reservation");
        applyButtonStyle(cancelButton, new Color(229, 57, 53));
        cancelButton.addActionListener(e -> {
            String reservationId = cancelReservationField.getText().trim();
            if (reservationId.isEmpty()) {
                showMessage("Validation error", "Please enter a reservation ID.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (service.cancelReservation(reservationId, currentUser.id())) {
                refreshCustomerReservationTable();
                refreshCustomerFlightTable(service.getAllFlights());
                showMessage("Success", "Reservation cancelled successfully.", JOptionPane.INFORMATION_MESSAGE);
                setStatus("Reservation " + reservationId + " cancelled.");
                cancelReservationField.setText("");
            } else {
                showMessage("Cancel failed", "Unable to cancel reservation.", JOptionPane.ERROR_MESSAGE);
            }
        });

        bottomPanel.add(labelWithFont("Reservation ID:"));
        bottomPanel.add(cancelReservationField);
        bottomPanel.add(cancelButton);

        panel.add(new JScrollPane(reservationTable), BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createAdminDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(229, 241, 255));

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        JLabel title = new JLabel("Airline Operations Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(8, 35, 82));
        JLabel subtitle = new JLabel("Monitor flights, reservations and passenger activity in real time.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(77, 96, 132));

        JPanel headingPanel = new JPanel(new BorderLayout(0, 8));
        headingPanel.setOpaque(false);
        headingPanel.add(title, BorderLayout.NORTH);
        headingPanel.add(subtitle, BorderLayout.SOUTH);

        JButton logoutButton = new JButton("Logout");
        applyButtonStyle(logoutButton, new Color(220, 20, 60));
        logoutButton.setPreferredSize(new Dimension(140, 42));
        logoutButton.addActionListener(e -> {
            currentUser = null;
            switchToCard(CARD_WELCOME);
            setStatus("Admin logged out.");
        });

        topBar.add(headingPanel, BorderLayout.WEST);
        topBar.add(logoutButton, BorderLayout.EAST);
        topBar.setBorder(BorderFactory.createEmptyBorder(22, 22, 18, 22));

        JPanel summaryPanel = new JPanel(new GridLayout(1, 4, 18, 18));
        summaryPanel.setOpaque(false);
        overviewFlightsLabel = overviewCard("Flights", "0", Color.WHITE, new Color(0, 102, 204));
        overviewCustomersLabel = overviewCard("Customers", "0", Color.WHITE, new Color(33, 150, 243));
        overviewReservationsLabel = overviewCard("Reservations", "0", Color.WHITE, new Color(0, 150, 136));
        overviewSeatsLabel = overviewCard("Available Seats", "0", Color.WHITE, new Color(220, 20, 60));
        summaryPanel.add(overviewFlightsLabel);
        summaryPanel.add(overviewCustomersLabel);
        summaryPanel.add(overviewReservationsLabel);
        summaryPanel.add(overviewSeatsLabel);

        JPanel sideNav = new JPanel();
        sideNav.setLayout(new BoxLayout(sideNav, BoxLayout.Y_AXIS));
        sideNav.setOpaque(false);
        sideNav.setBorder(BorderFactory.createEmptyBorder(20, 18, 20, 18));

        adminFlightButton = createSideNavButton("Flight Management");
        adminReservationButton = createSideNavButton("Reservations");
        adminCustomerButton = createSideNavButton("Customers");
        adminOverviewButton = createSideNavButton("Overview");

        sideNav.add(adminFlightButton);
        sideNav.add(Box.createVerticalStrut(12));
        sideNav.add(adminReservationButton);
        sideNav.add(Box.createVerticalStrut(12));
        sideNav.add(adminCustomerButton);
        sideNav.add(Box.createVerticalStrut(12));
        sideNav.add(adminOverviewButton);
        sideNav.add(Box.createVerticalGlue());

        JPanel adminContentPanel = new JPanel(new CardLayout());
        adminContentPanel.setOpaque(false);
        adminContentPanel.add(createAdminFlightPanel(), "flights");
        adminContentPanel.add(createAdminReservationPanel(), "reservations");
        adminContentPanel.add(createAdminCustomerPanel(), "customers");
        adminContentPanel.add(createAdminOverviewPanel(), "overview");

        adminFlightButton.addActionListener(e -> {
            switchAdminCard(adminContentPanel, "flights");
            setActiveAdminNavButton(adminFlightButton);
        });
        adminReservationButton.addActionListener(e -> {
            switchAdminCard(adminContentPanel, "reservations");
            setActiveAdminNavButton(adminReservationButton);
        });
        adminCustomerButton.addActionListener(e -> {
            switchAdminCard(adminContentPanel, "customers");
            setActiveAdminNavButton(adminCustomerButton);
        });
        adminOverviewButton.addActionListener(e -> {
            switchAdminCard(adminContentPanel, "overview");
            setActiveAdminNavButton(adminOverviewButton);
        });

        setActiveAdminNavButton(adminFlightButton);

        JPanel contentWrapper = new JPanel(new BorderLayout(18, 18));
        contentWrapper.setOpaque(false);
        contentWrapper.setBorder(BorderFactory.createEmptyBorder(0, 22, 22, 22));
        contentWrapper.add(summaryPanel, BorderLayout.NORTH);

        JPanel adminPanel = new JPanel(new BorderLayout(18, 0));
        adminPanel.setOpaque(false);
        adminPanel.add(sideNav, BorderLayout.WEST);
        adminPanel.add(adminContentPanel, BorderLayout.CENTER);

        contentWrapper.add(adminPanel, BorderLayout.CENTER);

        panel.add(topBar, BorderLayout.NORTH);
        panel.add(contentWrapper, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAdminFlightPanel() {
        JPanel panel = createContentPanel();

        adminFlightTableModel = new DefaultTableModel(new Object[]{"Flight #", "Origin", "Destination", "Depart", "Arrive", "Seats", "Price"}, 0);
        JTable flightTable = new JTable(adminFlightTableModel);
        configureTable(flightTable);

        flightTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && flightTable.getSelectedRow() >= 0) {
                adminFlightNumberField.setText(adminFlightTableModel.getValueAt(flightTable.getSelectedRow(), 0).toString());
                adminOriginField.setText(adminFlightTableModel.getValueAt(flightTable.getSelectedRow(), 1).toString());
                adminDestinationField.setText(adminFlightTableModel.getValueAt(flightTable.getSelectedRow(), 2).toString());
                adminDepartField.setText(adminFlightTableModel.getValueAt(flightTable.getSelectedRow(), 3).toString());
                adminArriveField.setText(adminFlightTableModel.getValueAt(flightTable.getSelectedRow(), 4).toString());
                String seatsText = adminFlightTableModel.getValueAt(flightTable.getSelectedRow(), 5).toString();
                adminSeatsField.setText(seatsText.split("/")[0]);
                adminPriceField.setText(adminFlightTableModel.getValueAt(flightTable.getSelectedRow(), 6).toString().replace("KSh ", ""));
            }
        });

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 211, 222)), "Flight Editor"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        adminFlightNumberField = new JTextField(12);
        adminOriginField = new JTextField(12);
        adminDestinationField = new JTextField(12);
        adminDepartField = new JTextField(10);
        adminArriveField = new JTextField(10);
        adminSeatsField = new JTextField(6);
        adminPriceField = new JTextField(8);

        addFieldToPanel(formPanel, labelWithFont("Flight #:"), adminFlightNumberField, gbc, 0);
        addFieldToPanel(formPanel, labelWithFont("Origin:"), adminOriginField, gbc, 1);
        addFieldToPanel(formPanel, labelWithFont("Destination:"), adminDestinationField, gbc, 2);
        addFieldToPanel(formPanel, labelWithFont("Depart:"), adminDepartField, gbc, 3);
        addFieldToPanel(formPanel, labelWithFont("Arrive:"), adminArriveField, gbc, 4);
        addFieldToPanel(formPanel, labelWithFont("Seats:"), adminSeatsField, gbc, 5);
        addFieldToPanel(formPanel, labelWithFont("Price:"), adminPriceField, gbc, 6);

        JButton addButton = new JButton("Add Flight");
        JButton updateButton = new JButton("Update Flight");
        JButton deleteButton = new JButton("Delete Flight");
        applyButtonStyle(addButton, new Color(76, 175, 80));
        applyButtonStyle(updateButton, new Color(255, 187, 51));
        applyButtonStyle(deleteButton, new Color(229, 57, 53));

        addButton.addActionListener(this::handleAddFlight);
        updateButton.addActionListener(this::handleUpdateFlight);
        deleteButton.addActionListener(this::handleDeleteFlight);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 8));
        actionPanel.setOpaque(false);
        actionPanel.add(addButton);
        actionPanel.add(updateButton);
        actionPanel.add(deleteButton);

        JPanel leftPanel = new JPanel(new BorderLayout(0, 16));
        leftPanel.setOpaque(false);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(actionPanel, BorderLayout.SOUTH);

        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(new JScrollPane(flightTable), BorderLayout.CENTER);

        refreshAdminFlightTable();
        return panel;
    }

    private JPanel createAdminReservationPanel() {
        JPanel panel = createContentPanel();

        adminReservationTableModel = new DefaultTableModel(new Object[]{"Reservation #", "Flight #", "Passenger", "Seats", "Price", "Booked"}, 0);
        JTable reservationTable = new JTable(adminReservationTableModel);
        configureTable(reservationTable);

        panel.add(new JScrollPane(reservationTable), BorderLayout.CENTER);
        refreshAdminReservationTable();
        return panel;
    }

    private JPanel createAdminCustomerPanel() {
        JPanel panel = createContentPanel();

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 12));
        top.setOpaque(false);
        adminCustomerSearchField = new JTextField(18);
        JButton searchButton = new JButton("Search");
        applyButtonStyle(searchButton, new Color(33, 150, 243));
        searchButton.addActionListener(e -> refreshAdminCustomerTable(service.searchCustomers(adminCustomerSearchField.getText())));
        top.add(labelWithFont("Search customers:"));
        top.add(adminCustomerSearchField);
        top.add(searchButton);

        adminCustomerTableModel = new DefaultTableModel(new Object[]{"Customer ID", "Name", "Email", "Phone"}, 0);
        JTable customerTable = new JTable(adminCustomerTableModel);
        configureTable(customerTable);

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(customerTable), BorderLayout.CENTER);
        refreshAdminCustomerTable(service.getAllCustomers());
        return panel;
    }

    private JPanel createAdminOverviewPanel() {
        JPanel panel = createContentPanel();
        panel.setLayout(new GridLayout(2, 2, 20, 20));

        overviewFlightsLabel = overviewCard("Flights", "0", Color.WHITE, new Color(0, 102, 204));
        overviewCustomersLabel = overviewCard("Customers", "0", Color.WHITE, new Color(33, 150, 243));
        overviewReservationsLabel = overviewCard("Reservations", "0", Color.WHITE, new Color(0, 150, 136));
        overviewSeatsLabel = overviewCard("Available Seats", "0", Color.WHITE, new Color(220, 20, 60));

        panel.add(overviewFlightsLabel);
        panel.add(overviewCustomersLabel);
        panel.add(overviewReservationsLabel);
        panel.add(overviewSeatsLabel);
        refreshOverview();
        return panel;
    }

    private JLabel overviewCard(String title, String value, Color background, Color accent) {
        JLabel label = new JLabel(String.format("<html><div style='text-align:center'><span style='font-size:22pt; font-weight:bold; color:%s'>%s</span><br/><span style='font-size:12pt; color:#4a4a4a'>%s</span></div></html>",
                toHexString(accent), value, title));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(background);
        label.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(accent, 1), BorderFactory.createEmptyBorder(14, 10, 14, 10)));
        return label;
    }

    private String toHexString(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    private JPanel createFormPanel(String heading, ImageIcon icon) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(9, 45, 108));
        JLabel title = new JLabel(heading, icon, SwingConstants.LEFT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        title.setIconTextGap(16);
        title.setBorder(BorderFactory.createEmptyBorder(24, 24, 16, 24));

        panel.add(title, BorderLayout.NORTH);
        panel.setBorder(BorderFactory.createEmptyBorder(36, 36, 36, 36));
        return panel;
    }

    private void addFormField(JPanel panel, String label, JComponent field, int row) {
        GridBagLayout layout = (GridBagLayout) panel.getLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.weightx = 0;
        JLabel fieldLabel = labelWithFont(label);
        panel.add(fieldLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        panel.add(field, gbc);
    }

    private void addFieldToPanel(JPanel panel, JLabel label, JComponent field, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JPanel createStatusPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        panel.setBackground(new Color(237, 241, 245));
        statusLabel = new JLabel("Ready.");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(statusLabel, BorderLayout.WEST);
        return panel;
    }

    private void handleCustomerLogin(ActionEvent ignored) {
        try {
            String email = customerLoginEmailField.getText().trim();
            String password = new String(customerLoginPasswordField.getPassword());
            currentUser = service.authenticate(email, password);
            if (!currentUser.isCustomer()) {
                throw new IllegalArgumentException("Please login with a customer account.");
            }
            refreshCustomerFlightTable(service.getAllFlights());
            refreshCustomerReservationTable();
            setStatus("Welcome, " + currentUser.name() + "!");
            switchToCard(CARD_CUSTOMER_DASHBOARD);
        } catch (IllegalArgumentException ex) {
            showMessage("Login failed", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCustomerRegistration(ActionEvent ignored) {
        try {
            String name = registerNameField.getText().trim();
            String email = registerEmailField.getText().trim();
            String password = new String(registerPasswordField.getPassword());
            String phone = registerPhoneField.getText().trim();
            currentUser = service.registerCustomer(name, email, password, phone);
            showMessage("Registered", "Customer account created successfully. Welcome, " + currentUser.name() + "!", JOptionPane.INFORMATION_MESSAGE);
            refreshCustomerFlightTable(service.getAllFlights());
            refreshCustomerReservationTable();
            switchToCard(CARD_CUSTOMER_DASHBOARD);
            clearRegisterForm();
            setStatus("Logged in as " + currentUser.name() + ".");
        } catch (IllegalArgumentException ex) {
            showMessage("Registration failed", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAdminLogin(ActionEvent ignored) {
        try {
            String email = adminEmailField.getText().trim();
            String password = new String(adminPasswordField.getPassword());
            currentUser = service.authenticate(email, password);
            if (!currentUser.isAdmin()) {
                throw new IllegalArgumentException("Administrator credentials required.");
            }
            refreshAdminFlightTable();
            refreshAdminReservationTable();
            refreshAdminCustomerTable(service.getAllCustomers());
            refreshOverview();
            setStatus("Admin access granted.");
            switchToCard(CARD_ADMIN_DASHBOARD);
        } catch (IllegalArgumentException ex) {
            showMessage("Login failed", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAdminRegistration(ActionEvent ignored) {
        try {
            String name = adminRegisterNameField.getText().trim();
            String email = adminRegisterEmailField.getText().trim();
            String password = new String(adminRegisterPasswordField.getPassword());
            String phone = adminRegisterPhoneField.getText().trim();
            String code = adminRegisterCodeField.getText().trim();
            service.registerAdmin(name, email, password, phone, code);
            showMessage("Admin Created", "Administrator account created successfully. Please login using the admin credentials.", JOptionPane.INFORMATION_MESSAGE);
            clearAdminRegisterForm();
            setStatus("Admin account created.");
            switchToCard(CARD_ADMIN_LOGIN);
        } catch (IllegalArgumentException ex) {
            showMessage("Registration failed", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCustomerFlightBooking(ActionEvent ignored) {
        if (currentUser == null || !currentUser.isCustomer()) {
            showMessage("Not logged in", "Please login as a customer before booking.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String flightNumber = bookingFlightNumberField.getText().trim();
        String seatsText = bookingSeatsField.getText().trim();
        if (flightNumber.isEmpty() || seatsText.isEmpty()) {
            showMessage("Validation error", "Please select a flight and enter the number of seats.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int seats;
        try {
            seats = Integer.parseInt(seatsText);
        } catch (NumberFormatException ex) {
            showMessage("Validation error", "Please enter a valid number of seats.", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            Reservation reservation = service.bookFlightForUser(flightNumber, currentUser.id(), seats);
            refreshCustomerFlightTable(service.getAllFlights());
            refreshCustomerReservationTable();
            refreshAdminReservationTable();
            refreshAdminFlightTable();
            showMessage("Booked", "Your reservation " + reservation.reservationId() + " is confirmed.", JOptionPane.INFORMATION_MESSAGE);
            setStatus("Booked reservation " + reservation.reservationId() + ".");
            bookingFlightNumberField.setText("");
            bookingSeatsField.setText("");
        } catch (IllegalArgumentException | IllegalStateException ex) {
            showMessage("Booking failed", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            setStatus("Booking failed: " + ex.getMessage());
        }
    }

    private void handleAddFlight(ActionEvent ignored) {
        try {
            String flightNumber = adminFlightNumberField.getText().trim();
            String origin = adminOriginField.getText().trim();
            String destination = adminDestinationField.getText().trim();
            String depart = adminDepartField.getText().trim();
            String arrive = adminArriveField.getText().trim();
            int seats = Integer.parseInt(adminSeatsField.getText().trim());
            double price = Double.parseDouble(adminPriceField.getText().trim());
            service.addFlight(flightNumber, origin, destination, depart, arrive, seats, price);
            refreshAdminFlightTable();
            refreshOverview();
            showMessage("Success", "Flight added successfully.", JOptionPane.INFORMATION_MESSAGE);
            setStatus("Added flight " + flightNumber + ".");
            clearAdminFlightForm();
        } catch (NumberFormatException ex) {
            showMessage("Input error", "Please enter valid numbers for seats and price.", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException ex) {
            showMessage("Unable to add flight", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdateFlight(ActionEvent ignored) {
        try {
            String flightNumber = adminFlightNumberField.getText().trim();
            String origin = adminOriginField.getText().trim();
            String destination = adminDestinationField.getText().trim();
            String depart = adminDepartField.getText().trim();
            String arrive = adminArriveField.getText().trim();
            int seats = Integer.parseInt(adminSeatsField.getText().trim());
            double price = Double.parseDouble(adminPriceField.getText().trim());
            service.updateFlight(flightNumber, origin, destination, depart, arrive, seats, price);
            refreshAdminFlightTable();
            refreshCustomerFlightTable(service.getAllFlights());
            refreshOverview();
            showMessage("Success", "Flight updated successfully.", JOptionPane.INFORMATION_MESSAGE);
            setStatus("Updated flight " + flightNumber + ".");
        } catch (NumberFormatException ex) {
            showMessage("Input error", "Please enter valid numbers for seats and price.", JOptionPane.WARNING_MESSAGE);
        } catch (IllegalArgumentException ex) {
            showMessage("Unable to update flight", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeleteFlight(ActionEvent ignored) {
        try {
            String flightNumber = adminFlightNumberField.getText().trim();
            if (flightNumber.isEmpty()) {
                showMessage("Validation error", "Please select a flight number to delete.", JOptionPane.WARNING_MESSAGE);
                return;
            }
            service.deleteFlight(flightNumber);
            refreshAdminFlightTable();
            refreshCustomerFlightTable(service.getAllFlights());
            refreshOverview();
            showMessage("Deleted", "Flight " + flightNumber + " has been removed.", JOptionPane.INFORMATION_MESSAGE);
            setStatus("Deleted flight " + flightNumber + ".");
            clearAdminFlightForm();
        } catch (IllegalArgumentException ex) {
            showMessage("Unable to delete flight", ex.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshCustomerFlightTable() {
        refreshCustomerFlightTable(service.getAllFlights());
    }

    private void refreshCustomerFlightTable(List<Flight> flights) {
        customerFlightTableModel.setRowCount(0);
        for (Flight flight : flights) {
            customerFlightTableModel.addRow(new Object[]{
                    flight.getFlightNumber(),
                    flight.getOrigin(),
                    flight.getDestination(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getAvailableSeats() + "/" + flight.getTotalSeats(),
                    String.format("KSh %.2f", flight.getPrice())
            });
        }
        setStatus("Showing " + flights.size() + " flights.");
    }

    private void refreshCustomerReservationTable() {
        customerReservationTableModel.setRowCount(0);
        if (currentUser != null) {
            List<Reservation> reservations = service.getReservationsForUser(currentUser);
            for (Reservation reservation : reservations) {
                customerReservationTableModel.addRow(new Object[]{
                        reservation.reservationId(),
                        reservation.flight().getFlightNumber(),
                        reservation.seatCount(),
                        String.format("KSh %.2f", reservation.totalPrice()),
                        reservation.bookingDate()
                });
            }
            setStatus("You have " + reservations.size() + " reservation(s).");
        }
    }

    private void refreshAdminFlightTable() {
        adminFlightTableModel.setRowCount(0);
        for (Flight flight : service.getAllFlights()) {
            adminFlightTableModel.addRow(new Object[]{
                    flight.getFlightNumber(),
                    flight.getOrigin(),
                    flight.getDestination(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getTotalSeats() + "/" + flight.getAvailableSeats(),
                    String.format("KSh %.2f", flight.getPrice())
            });
        }
    }

    private void refreshAdminReservationTable() {
        adminReservationTableModel.setRowCount(0);
        for (Reservation reservation : service.getAllReservations()) {
            adminReservationTableModel.addRow(new Object[]{
                    reservation.reservationId(),
                    reservation.flight().getFlightNumber(),
                    reservation.passenger().name(),
                    reservation.seatCount(),
                    String.format("KSh %.2f", reservation.totalPrice()),
                    reservation.bookingDate()
            });
        }
    }

    private void refreshAdminCustomerTable(List<User> customers) {
        adminCustomerTableModel.setRowCount(0);
        for (User customer : customers) {
            adminCustomerTableModel.addRow(new Object[]{
                    customer.id(),
                    customer.name(),
                    customer.email(),
                    customer.phone()
            });
        }
    }

    private void refreshOverview() {
        overviewFlightsLabel.setText(String.format("<html><div style='text-align:center'><span style='font-size:20pt; font-weight:bold'>%d</span><br/><span style='font-size:11pt; color:#5a5a5a'>Flights</span></div></html>", service.getTotalFlightCount()));
        overviewCustomersLabel.setText(String.format("<html><div style='text-align:center'><span style='font-size:20pt; font-weight:bold'>%d</span><br/><span style='font-size:11pt; color:#5a5a5a'>Customers</span></div></html>", service.getTotalCustomerCount()));
        overviewReservationsLabel.setText(String.format("<html><div style='text-align:center'><span style='font-size:20pt; font-weight:bold'>%d</span><br/><span style='font-size:11pt; color:#5a5a5a'>Reservations</span></div></html>", service.getTotalReservationCount()));
        overviewSeatsLabel.setText(String.format("<html><div style='text-align:center'><span style='font-size:20pt; font-weight:bold'>%d</span><br/><span style='font-size:11pt; color:#5a5a5a'>Available Seats</span></div></html>", service.getAvailableSeatCount()));
    }

    private void switchToCard(String cardName) {
        cardLayout.show(cardsPanel, cardName);
    }

    private void switchAdminCard(JPanel adminCardPanel, String cardName) {
        CardLayout adminLayout = (CardLayout) adminCardPanel.getLayout();
        adminLayout.show(adminCardPanel, cardName);
    }

    private void clearRegisterForm() {
        registerNameField.setText("");
        registerEmailField.setText("");
        registerPasswordField.setText("");
        registerPhoneField.setText("");
    }

    private void clearAdminFlightForm() {
        adminFlightNumberField.setText("");
        adminOriginField.setText("");
        adminDestinationField.setText("");
        adminDepartField.setText("");
        adminArriveField.setText("");
        adminSeatsField.setText("");
        adminPriceField.setText("");
    }

    private void clearAdminRegisterForm() {
        adminRegisterNameField.setText("");
        adminRegisterEmailField.setText("");
        adminRegisterPasswordField.setText("");
        adminRegisterPhoneField.setText("");
        adminRegisterCodeField.setText("");
    }

    private void setStatus(String message) {
        if (statusLabel != null) {
            statusLabel.setText(message);
        }
    }

    private void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(frame, message, title, messageType);
    }

    private void applyButtonStyle(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(160, 42));
    }

    private JButton createSideNavButton(String title) {
        JButton button = new JButton(title);
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(8, 35, 82));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1), BorderFactory.createEmptyBorder(12, 18, 12, 18)));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return button;
    }

    private void setActiveAdminNavButton(JButton button) {
        if (activeAdminNavButton != null) {
            activeAdminNavButton.setBackground(new Color(255, 255, 255));
            activeAdminNavButton.setForeground(new Color(8, 35, 82));
            activeAdminNavButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 1), BorderFactory.createEmptyBorder(12, 18, 12, 18)));
        }
        activeAdminNavButton = button;
        activeAdminNavButton.setBackground(new Color(0, 102, 204));
        activeAdminNavButton.setForeground(Color.WHITE);
        activeAdminNavButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(0, 82, 155), 2), BorderFactory.createEmptyBorder(12, 18, 12, 18)));
    }

    private JLabel labelWithFont(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(new Color(48, 63, 84));
        return label;
    }

    private JPanel createContentPanel() {
        JPanel panel = new JPanel(new BorderLayout(14, 14));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        return panel;
    }

    private JPanel buildWelcomePanelCard(String title, String subtitle, Color background, Color accent) {
        JPanel card = new JPanel(new BorderLayout(0, 12));
        card.setOpaque(true);
        card.setBackground(background);
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(accent, 2), BorderFactory.createEmptyBorder(20, 20, 20, 20)));

        JLabel cardTitle = new JLabel(title);
        cardTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        cardTitle.setForeground(new Color(20, 35, 65));

        JLabel cardSubtitle = new JLabel(String.format("<html><body style='width:220px'>%s</body></html>", subtitle));
        cardSubtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cardSubtitle.setForeground(new Color(90, 110, 140));

        JPanel textPanel = new JPanel(new BorderLayout(0, 10));
        textPanel.setOpaque(false);
        textPanel.add(cardTitle, BorderLayout.NORTH);
        textPanel.add(cardSubtitle, BorderLayout.CENTER);

        card.add(textPanel, BorderLayout.NORTH);
        return card;
    }

    private JPanel createIconCard(String text, ImageIcon icon) {
        JPanel card = new JPanel(new BorderLayout());
        card.setOpaque(true);
        card.setBackground(new Color(255, 255, 255, 210));
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 230, 240), 1));
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel textLabel = new JLabel(text, SwingConstants.CENTER);
        textLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        textLabel.setForeground(new Color(50, 75, 105));
        card.add(iconLabel, BorderLayout.CENTER);
        card.add(textLabel, BorderLayout.SOUTH);
        return card;
    }

    private ImageIcon createAirplaneIcon(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(255, 255, 255, 230));
        g.fillOval(size / 6, size / 3, size / 2, size / 5);
        g.setColor(new Color(30, 136, 229));
        int[] x = {size / 3, size / 2, size / 3, size / 5};
        int[] y = {size / 2, size / 3, size / 2, size / 3 + size / 10};
        g.fillPolygon(x, y, x.length);
        g.fillRect(size / 2 - 4, size / 3, size / 8, size / 6);
        g.dispose();
        return new ImageIcon(image);
    }

    private ImageIcon createTicketIcon(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(255, 235, 59));
        g.fillRoundRect(size / 8, size / 6, size * 3 / 4, size / 2, 16, 16);
        g.setColor(new Color(255, 193, 7));
        g.fillRect(size / 8 + 8, size / 4, size / 3, size / 8);
        g.setColor(new Color(96, 125, 139));
        g.fillOval(size / 8 + 4, size / 3, size / 10, size / 10);
        g.fillOval(size * 5 / 8, size / 3, size / 10, size / 10);
        g.dispose();
        return new ImageIcon(image);
    }

    private ImageIcon createLuggageIcon(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(76, 175, 80));
        g.fillRoundRect(size / 6, size / 5, size / 2, size / 2, 14, 14);
        g.fillRect(size / 3, size / 7, size / 6, size / 8);
        g.setColor(new Color(56, 142, 60));
        g.fillRect(size / 4, size * 3 / 5, size / 5, size / 10);
        g.setColor(new Color(255, 255, 255));
        g.fillRect(size / 3 + 4, size / 3, size / 10, size / 20);
        g.dispose();
        return new ImageIcon(image);
    }

    private ImageIcon createPassportIcon(int size) {
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(33, 150, 243));
        g.fillRoundRect(size / 6, size / 8, size / 2, size * 3 / 4, 18, 18);
        g.setColor(new Color(255, 235, 59));
        g.fillOval(size / 3, size / 4, size / 4, size / 4);
        g.setColor(new Color(255, 255, 255));
        g.drawLine(size / 3 + 4, size / 2, size / 3 + size / 4 - 4, size / 2);
        g.dispose();
        return new ImageIcon(image);
    }

    private void configureTable(JTable table) {
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);
        table.setRowHeight(26);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setForeground(new Color(40, 55, 71));
        table.setSelectionBackground(new Color(0, 120, 215));
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(237, 241, 245));
        header.setForeground(new Color(34, 49, 63));
    }
}
