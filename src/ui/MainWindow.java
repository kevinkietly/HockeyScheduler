package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane sidebarTabbedPane;
    private MainWindowDelegate delegate;

    public MainWindow(MainWindowDelegate mainWindowDelegate) {
        super("Hockey Scheduler");
        this.delegate = mainWindowDelegate;
        initializeMainPanel();
        initializeSidebarTabbedPane();
        mainPanel.add(sidebarTabbedPane);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void initializeMainPanel() {
        mainPanel = new JPanel();
    }

    private void initializeSidebarTabbedPane() {
        sidebarTabbedPane = new JTabbedPane();
        sidebarTabbedPane.setTabPlacement(SwingConstants.LEFT);
        initializeSidebarTabs();
    }

    private void initializeSidebarTabs() {

        sidebarTabbedPane.addTab("Insert", new InsertPanel(delegate));
        sidebarTabbedPane.addTab("Delete", new DeletePanel(delegate));
        sidebarTabbedPane.addTab("Update", new UpdatePanel(delegate));
        sidebarTabbedPane.addTab("Selection", new selectionPanel(delegate));
        sidebarTabbedPane.addTab("Projection", new ProjectionPanel(delegate));
        sidebarTabbedPane.addTab("Join", new JoinPanel(delegate));
        sidebarTabbedPane.addTab("Aggregation", new aggPanel(delegate));
        sidebarTabbedPane.addTab("Nested Aggregation with Group By", new NAWGBPanel(delegate));
        sidebarTabbedPane.addTab("Division", new DivisionPanel(delegate));
        sidebarTabbedPane.addTab("View", null);
    }
}
