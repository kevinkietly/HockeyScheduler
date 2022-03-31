package ui;

import delegates.MainWindowDelegate;

import javax.swing.*;

public class MainWindow extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane sidebarTabbedPane;
    private MainWindowDelegate mainWindowDelegate;

    public MainWindow(MainWindowDelegate mainWindowDelegate) {
        super("Hockey Scheduler");
        this.mainWindowDelegate = mainWindowDelegate;
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
        sidebarTabbedPane.addTab("Insert", new InsertPanel(mainWindowDelegate));
        sidebarTabbedPane.addTab("Delete", null);
        sidebarTabbedPane.addTab("Update", null);
        sidebarTabbedPane.addTab("Selection", null);
        sidebarTabbedPane.addTab("Projection", null);
        sidebarTabbedPane.addTab("Join", null);
        sidebarTabbedPane.addTab("Aggregation", null);
        sidebarTabbedPane.addTab("Nested Aggregation with Group By", null);
        sidebarTabbedPane.addTab("Division", null);
        sidebarTabbedPane.addTab("View", null);
    }
}
