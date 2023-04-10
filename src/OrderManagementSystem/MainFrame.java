/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OrderManagementSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import static java.awt.event.ActionEvent.ALT_MASK;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Velasco
 */
public class MainFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu fileMenu, optionsMenu, helpMenu;
    private JMenuItem newItem, openItem, saveItem, saveAsItem, exitItem,
            aboutItem,
            statisticsItem;

    private JButton openBtnBar, newOrderBtnBar, statisticsBtnBar, saveBtnBar, saveAsBtnBar;
    private JButton openBtn, newOrderBtn, statisticsBtn, saveBtn, saveAsBtn, clearBtn, aboutBtn;

    private JLabel filePath;
    //  private TextArea area;
    private JTextArea area;
    private JScrollPane scroll;

    private About dialogAbout;
    private NewOrderForm dialogNewOrderForm;
    private Statistics dialogStatistics;

    private ButtonsHandler handler;
    private ArrayList<Order> ordersList;
    private String appID = "cs161020";
    private String path;

    // constructing the members of MainFrame class 
    public MainFrame() {

        super("ORDER MANAGEMENT SYSTEM - JAVA PAOLA.2020");

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        optionsMenu = new JMenu("Options");
        helpMenu = new JMenu("Help");

        handler = new ButtonsHandler();

        newItem = fileMenu.add("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke('N', Event.CTRL_MASK));
        openItem = fileMenu.add("Open...");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', Event.CTRL_MASK));
        fileMenu.addSeparator();
        saveItem = fileMenu.add("Save");
        saveItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK));
        saveAsItem = fileMenu.add("Save As...");
        saveAsItem.setAccelerator(KeyStroke.getKeyStroke('S', Event.CTRL_MASK + ALT_MASK));
        fileMenu.addSeparator();
        exitItem = fileMenu.add("Exit");
        exitItem.setAccelerator(KeyStroke.getKeyStroke('Q', Event.CTRL_MASK));
        statisticsItem = optionsMenu.add("Statistics");
        statisticsItem.setAccelerator(KeyStroke.getKeyStroke('R', Event.CTRL_MASK));
        aboutItem = helpMenu.add("About");
        aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', Event.CTRL_MASK));

        menuBar.add(fileMenu);
        menuBar.add(optionsMenu);
        menuBar.add(helpMenu);

        openBtnBar = new JButton(new ImageIcon("media/open.png"));
        openBtnBar.setOpaque(false);
        openBtnBar.setContentAreaFilled(false);
        newOrderBtnBar = new JButton(new ImageIcon("media/add.png"));
        newOrderBtnBar.setOpaque(false);
        newOrderBtnBar.setContentAreaFilled(false);
        saveBtnBar = new JButton(new ImageIcon("media/save.png"));
        saveBtnBar.setOpaque(false);
        saveBtnBar.setContentAreaFilled(false);
        saveAsBtnBar = new JButton(new ImageIcon("media/saveAs.png"));
        saveAsBtnBar.setOpaque(false);
        saveAsBtnBar.setContentAreaFilled(false);
        statisticsBtnBar = new JButton(new ImageIcon("media/stat.png"));
        statisticsBtnBar.setOpaque(false);
        statisticsBtnBar.setContentAreaFilled(false);

        openBtn = new JButton("Open");
        newOrderBtn = new JButton("New Order");
        statisticsBtn = new JButton("Statistics");
        saveBtn = new JButton("Save");
        saveAsBtn = new JButton("Save As");
        clearBtn = new JButton("Clear");
        aboutBtn = new JButton("About");

        filePath = new JLabel();
        filePath.setFont(new Font("Courier", Font.BOLD, 14));
        area = new JTextArea();
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
        area.setEditable(false);
        scroll = new JScrollPane(area);

        ordersList = new ArrayList<Order>();

    }

    // Basic instructions for the JFrame
    public void initUI() {

        this.setSize(1100, 520);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    // The design of the GUI
    public void prepareUI() {

        setJMenuBar(menuBar);

        // side button bar
        Box box1 = new Box(BoxLayout.Y_AXIS);
        box1.add(openBtnBar);
        box1.add(Box.createVerticalStrut(2));
        box1.add(newOrderBtnBar);
        box1.add(Box.createVerticalStrut(2));
        box1.add(saveBtnBar);
        box1.add(Box.createVerticalStrut(2));
        box1.add(saveAsBtnBar);
        box1.add(Box.createVerticalStrut(2));
        box1.add(statisticsBtnBar);
        this.add(box1, BorderLayout.WEST);

        JPanel panel1 = new JPanel(new GridLayout(2, 1));
        panel1.add(scroll);

        JPanel panel2 = new JPanel();
        panel2.add(openBtn);
        panel2.add(newOrderBtn);
        panel2.add(statisticsBtn);
        panel2.add(saveBtn);
        panel2.add(saveAsBtn);
        panel2.add(clearBtn);
        panel2.add(aboutBtn);

        panel2.setBorder(new TitledBorder("Order Management System"));
        panel1.add(panel2);

        this.add(panel1, BorderLayout.CENTER);
        this.add(filePath, BorderLayout.SOUTH);

    }

    // All Events
    public void actionsPerformed() {

        // Event Handling - Exiting Main frame 
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitMainFrame();
            }
        });

        // Event Handling for Menu Items
        newItem.addActionListener(handler);
        openItem.addActionListener(handler);
        saveAsItem.addActionListener(handler);
        saveItem.addActionListener(handler);
        exitItem.addActionListener(handler);
        statisticsItem.addActionListener(handler);
        aboutItem.addActionListener(handler);

        // Event Handling for Side Button Bar
        saveAsBtnBar.addActionListener(handler);
        saveBtnBar.addActionListener(handler);
        openBtnBar.addActionListener(handler);
        newOrderBtnBar.addActionListener(handler);
        statisticsBtnBar.addActionListener(handler);

        // Event Handling for Main Frame
        openBtn.addActionListener(handler);
        newOrderBtn.addActionListener(handler);
        saveBtn.addActionListener(handler);
        statisticsBtn.addActionListener(handler);
        saveAsBtn.addActionListener(handler);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area.setText("");
                ordersList.clear();
            }
        });
        aboutBtn.addActionListener(handler);

    }

    // All Action Event - Listeners
    class ButtonsHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // Event handling for "Save As"
            if (e.getSource() == saveAsItem || e.getSource() == saveAsBtnBar || e.getSource() == saveAsBtn) {

                if (ordersList.isEmpty()) {
                    JOptionPane.showMessageDialog(saveBtn, "Nothing to save", "Error Saving", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                chooseSaveFile();

                // Event handling for "Save"
            } else if (e.getSource() == saveBtn || e.getSource() == saveBtnBar || e.getSource() == saveItem) {

                if (ordersList.isEmpty()) {
                    JOptionPane.showMessageDialog(saveBtn, "Nothing to save", "Error Saving", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (path == null || path.isEmpty()) {
                    chooseSaveFile();
                } else {
                    saveOrdersList(path);
                }
                // Event handling for "Exit"
            } else if (e.getSource() == exitItem) {

                exitMainFrame();

                // Event handling for "Open/Load Order"
            } else if (e.getSource() == openItem || e.getSource() == openBtn || e.getSource() == openBtnBar) {
                final JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter extFilter = new FileNameExtensionFilter("*.csv", "csv");
                fc.setFileFilter(extFilter);
                int returnVal = fc.showOpenDialog(openBtn);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String fileName = fc.getSelectedFile().getPath();

                    if (fileName != null && !fileName.isEmpty()) {

                        ordersList.clear();
                        area.setText("");
                        openFromFile(fileName);
                        filePath.setText(fileName);
                        path = fileName;
                        repaint();
                    }
                }
                // Event handling for "Add new order"
            } else if (e.getSource() == newItem || e.getSource() == newOrderBtnBar || e.getSource() == newOrderBtn) {
                dialogNewOrderForm = new NewOrderForm(MainFrame.this, "Form Page", true);
                dialogNewOrderForm.prepareUI();
                dialogNewOrderForm.actionsUI();
                dialogNewOrderForm.initUI();

                // Event handling for "Statistics Report"
            } else if (e.getSource() == statisticsBtn || e.getSource() == statisticsBtnBar || e.getSource() == statisticsItem) {

                if (!ordersList.isEmpty()) {
                    dialogStatistics = new Statistics(MainFrame.this);

                    dialogStatistics.prepareUI();
                    dialogStatistics.actionsUI();
                    dialogStatistics.initUI();
                } else {
                    JOptionPane.showMessageDialog(null, "No statistcs to show", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // Event handling for "About Dialog"
            } else if (e.getSource() == aboutBtn || e.getSource() == aboutItem) {
                dialogAbout = new About(MainFrame.this);
                dialogAbout.prepareUI();
                dialogAbout.initUI();
            }
        }
    }

    // Method: writing on a file using BurfferedWriter - 'Save' & 'Save As'
    private void saveOrdersList(String fileName) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (Order entry : ordersList) {
                writer.write(entry.formatEntryOnSave());
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(saveBtn, "Saved to: " + fileName + "\nRecords: " + ordersList.size(),
                    "Saving", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error writing on the file", "Error Write", JOptionPane.ERROR_MESSAGE);
        }

    }

    // Method: writing on exiting Main Frame
    private void exitMainFrame() {

        int i = JOptionPane.showConfirmDialog(MainFrame.this, "Do you want to exit  without saving?\n", "EXIT", JOptionPane.YES_NO_CANCEL_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

        if (i == JOptionPane.NO_OPTION) {

            if (ordersList.isEmpty()) {
                JOptionPane.showMessageDialog(saveBtn, "Nothing to save", "Error Saving", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            if (path == null || path.isEmpty()) {
                int flag = chooseSaveFile();
                if (flag != 0) {
                    return;
                }
            } else {
                saveOrdersList(path);
            }

            System.exit(0);
        }
    }

    // Method: to create a JFileChooser object
    private int chooseSaveFile() {

        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(saveBtn);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            if (path != null && !path.isEmpty()) {
                saveOrdersList(path);
                filePath.setText(path);
            }
        }
        if (returnVal == JFileChooser.CANCEL_OPTION) {
            return 1;
        }

        return 0;
    }

    // Method: reading from a file using BufferedReader - 'Open'
    private void openFromFile(String fileName) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            String[] token;
            Order user;

            while (reader.ready()) {
                line = reader.readLine();
                token = line.split(";");
                user = new Order(token[0].trim(), token[1].trim(), token[2].trim(), token[3].trim(), token[4].trim(),
                        Integer.parseInt(token[5]), Double.parseDouble(token[6]), Double.parseDouble(token[7]));
                ordersList.add(user);
            }

            reader.close();
            showList();

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "File not found", "Error Read", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to open file", "Error Read", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method: diplay orders on TextArea
    private void showList() {
        for (Order user : ordersList) {

            area.append(user.toString());
            area.append("\n");
        }
    }

    // Inner Class of JDialog - New Order 
    class NewOrderForm extends JDialog {

        private Font font = new Font("TimesRoman", Font.BOLD, 16);
        private JLabel titleForm;

        private JLabel appIDLbl, orderIDLbl, orderDateLbl, clientNameLbl, itemNameLbl,
                unitsCountLbl, netItemPriceLbl, taxPercentLbl;

        private JTextField appIDTF, orderIDTF, orderDateTF, clientNameTF, itemNameTF,
                unitsCountTF, netItemPriceTF, taxPercentTF;

        private JButton cancelBtn, addBtn, clearBtn;

        public NewOrderForm(Frame owner, String title, boolean modal) {

            super(owner, title, modal);

            titleForm = new JLabel("Create a new order entry", SwingConstants.CENTER);
            titleForm.setFont(new Font("Verdan", Font.BOLD + Font.ITALIC, 16));

            appIDLbl = new JLabel("App ID: ");
            appIDLbl.setFont(font);
            orderIDLbl = new JLabel("Order ID: ");
            orderIDLbl.setFont(font);
            orderDateLbl = new JLabel("Order Date: ");
            orderDateLbl.setFont(font);
            clientNameLbl = new JLabel("Client Name: ");
            clientNameLbl.setFont(font);
            itemNameLbl = new JLabel("Item Name: ");
            itemNameLbl.setFont(font);
            unitsCountLbl = new JLabel("Units Count");
            unitsCountLbl.setFont(font);
            netItemPriceLbl = new JLabel("Net Item Price");
            netItemPriceLbl.setFont(font);
            taxPercentLbl = new JLabel("Tax Percentage");
            taxPercentLbl.setFont(font);

            appIDTF = new JTextField(15);
            appIDTF.setText(MainFrame.this.appID);
            appIDTF.setEditable(false);
            orderIDTF = new JTextField(15);
            orderDateTF = new JTextField(15);
            clientNameTF = new JTextField(15);
            itemNameTF = new JTextField(15);
            unitsCountTF = new JTextField(15);
            netItemPriceTF = new JTextField(15);
            taxPercentTF = new JTextField(15);

            cancelBtn = new JButton("Cancel");
            addBtn = new JButton("Add");
            clearBtn = new JButton("Clear");

        }

        public void prepareUI() {

            this.add(titleForm, BorderLayout.NORTH);

            JPanel panel1 = new JPanel(new GridLayout(8, 2));
            panel1.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.gray));
            panel1.add(appIDLbl);
            panel1.add(appIDTF);
            panel1.add(orderIDLbl);
            panel1.add(orderIDTF);
            panel1.add(orderDateLbl);
            panel1.add(orderDateTF);
            panel1.add(clientNameLbl);
            panel1.add(clientNameTF);
            panel1.add(itemNameLbl);
            panel1.add(itemNameTF);
            panel1.add(unitsCountLbl);
            panel1.add(unitsCountTF);
            panel1.add(netItemPriceLbl);
            panel1.add(netItemPriceTF);
            panel1.add(taxPercentLbl);
            panel1.add(taxPercentTF);
            this.add(panel1, BorderLayout.CENTER);

            JPanel panel2 = new JPanel();
            panel2.setBorder(BorderFactory.createLoweredBevelBorder());
            panel2.add(addBtn);
            panel2.add(cancelBtn);
            panel2.add(clearBtn);
            this.add(panel2, BorderLayout.SOUTH);

        }

        public void initUI() {

            this.setSize(600, 350);
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }

        // Event handling 
        public void actionsUI() {

            // Anonymous inner class for clearBtn event handling
            clearBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearFields();

                }
            });

            // Anonymous inner class for addBtn event handling
            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String appID = appIDTF.getText().trim();
                    String orderID = orderIDTF.getText().trim();
                    String orderDate = orderDateTF.getText().trim();
                    String clientName = clientNameTF.getText().trim();
                    String itemName = itemNameTF.getText().trim();
                    String unitsCountInput = unitsCountTF.getText().trim();
                    String netItemPriceInput = netItemPriceTF.getText().trim();
                    String taxPercentInput = taxPercentTF.getText().trim();

                    int flag = checkFields(orderID, orderDate, clientName, itemName, unitsCountInput, netItemPriceInput, taxPercentInput);
                    if (flag != 0) {
                        return;
                    }

                    try {
                        int unitsCount = Integer.parseInt(unitsCountInput);
                        double netItemPrice = Double.parseDouble(netItemPriceInput);
                        double taxPercent = Double.parseDouble(taxPercentInput);

                        Order order = new Order();
                        order.setAppID(appID);
                        order.setOrderID(orderID);
                        order.setOrderDate(orderDate);
                        order.setClientName(clientName);
                        order.setItemName(itemName);
                        order.setUnitsCount(unitsCount);
                        order.setNetItemPrice(netItemPrice);
                        order.setTaxPercent(taxPercent);

                        MainFrame.this.ordersList.add(order);
                        MainFrame.this.area.append(order.toString());
                        MainFrame.this.area.append("\n");
                        JOptionPane.showMessageDialog(MainFrame.this, "Successfully added", "Add", JOptionPane.INFORMATION_MESSAGE);

                        clearFields();
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Fields: [int]Units Coumt, [double]Net Item Price and [double]Tax Percentage ",
                                "Wrong Input: ", JOptionPane.ERROR_MESSAGE);

                    }
                }
            });

            // Anonymous inner class for cancelBtn event handling
            cancelBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }

        // Method to empty out the text fields
        private void clearFields() {
            orderIDTF.setText("");
            orderDateTF.setText("");
            clientNameTF.setText("");
            itemNameTF.setText("");
            unitsCountTF.setText("");
            netItemPriceTF.setText("");
            taxPercentTF.setText("");
        }

        // Method to check if fields are empty 
        private int checkFields(String orderID, String orderDate, String clientName,
                String itemName, String unitsCount, String netItemPrice, String taxPercent) {
            if (orderID.isEmpty() || orderID == null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Order ID is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            } else if (orderDate.isEmpty() || orderDate == null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Order Date is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            } else if (clientName.isEmpty() || clientName == null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Client Name is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            } else if (itemName.isEmpty() || itemName == null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Item Name is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            } else if (unitsCount.isEmpty() || unitsCount == null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Units Count is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            } else if (netItemPrice.isEmpty() || netItemPrice == null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Net Item Price is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            } else if (taxPercent.isEmpty() || taxPercent == null) {
                JOptionPane.showMessageDialog(MainFrame.this, "Tax Percent is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            } else {
                return 0;
            }
        }

    }

    // Inner Class of JDialog - Statistcs
    class Statistics extends JDialog {

        private JLabel title;
        private JLabel totalNoOrdersLbl, totalNetPriceOrdersLbl, totalPriceOrdersLbl,
                maxPriceOrderLbl, minPriceOrderLbl;
        private JTextField totalNoOrdersTF, totalNetPriceOrdersTF, totalPriceOrdersTF,
                maxPriceOrderTF, minPriceOrderTF;
        private JButton closeBtn;
        private Font font = new Font("Serif", Font.BOLD, 16);

        public Statistics(JFrame owner) {
            super(owner, "Statistics Report", true);

            title = new JLabel("Statistics of current orders", SwingConstants.CENTER);
            title.setFont(new Font("Courier", Font.BOLD + Font.ITALIC, 16));
            totalNoOrdersLbl = new JLabel("Total no. of orders");
            totalNoOrdersLbl.setFont(font);
            totalNetPriceOrdersLbl = new JLabel("Total net price of all orders");
            totalNetPriceOrdersLbl.setFont(font);
            totalPriceOrdersLbl = new JLabel("Total price of all orders");
            totalPriceOrdersLbl.setFont(font);
            maxPriceOrderLbl = new JLabel("Most expensive order (orderID)");
            maxPriceOrderLbl.setFont(font);
            minPriceOrderLbl = new JLabel("Least expensive order (orderID)");
            minPriceOrderLbl.setFont(font);

            totalNoOrdersTF = new JTextField(25);
            totalNoOrdersTF.setEditable(false);
            totalNoOrdersTF.setHorizontalAlignment(JTextField.CENTER);
            totalNoOrdersTF.setFont(new Font("Courier", Font.BOLD, 16));
            totalNetPriceOrdersTF = new JTextField(25);
            totalNetPriceOrdersTF.setEditable(false);
            totalNetPriceOrdersTF.setHorizontalAlignment(JTextField.CENTER);
            totalNetPriceOrdersTF.setFont(new Font("Courier", Font.BOLD, 16));
            totalPriceOrdersTF = new JTextField(25);
            totalPriceOrdersTF.setEditable(false);
            totalPriceOrdersTF.setHorizontalAlignment(JTextField.CENTER);
            totalPriceOrdersTF.setFont(new Font("Courier", Font.BOLD, 16));
            maxPriceOrderTF = new JTextField(25);
            maxPriceOrderTF.setEditable(false);
            maxPriceOrderTF.setHorizontalAlignment(JTextField.CENTER);
            maxPriceOrderTF.setFont(new Font("Courier", Font.BOLD, 16));
            minPriceOrderTF = new JTextField(25);
            minPriceOrderTF.setEditable(false);
            minPriceOrderTF.setHorizontalAlignment(JTextField.CENTER);
            minPriceOrderTF.setFont(new Font("Courier", Font.BOLD, 16));

            closeBtn = new JButton("Close");

        }

        public void prepareUI() {

            this.add(title, BorderLayout.NORTH);
            title.setBorder(BorderFactory.createRaisedBevelBorder());

            JPanel panel1 = new JPanel(new GridLayout(10, 1));
            panel1.add(totalNoOrdersLbl);
            panel1.add(totalNoOrdersTF);
            panel1.add(totalNetPriceOrdersLbl);
            panel1.add(totalNetPriceOrdersTF);
            panel1.add(totalPriceOrdersLbl);
            panel1.add(totalPriceOrdersTF);
            panel1.add(maxPriceOrderLbl);
            panel1.add(maxPriceOrderTF);
            panel1.add(minPriceOrderLbl);
            panel1.add(minPriceOrderTF);
            this.add(panel1, BorderLayout.CENTER);

            JPanel panel2 = new JPanel();
            panel2.setBorder(BorderFactory.createRaisedBevelBorder());
            panel2.add(closeBtn);
            panel2.setBackground(Color.LIGHT_GRAY);
            this.add(panel2, BorderLayout.SOUTH);

            processingData();

        }

        private void processingData() {
            // Number of orders
            int size = ordersList.size();
            totalNoOrdersTF.setText(Integer.toString(size));

            //Calculating the Total Net Price & Total Price 
            double netSumOrders = 0;
            double netAndTaxSumOrders = 0;
            double max = 0;
            int maxIndex = 0;
            double min = ordersList.get(0).totalOrderPrice();
            int minIndex = 0;
            for (int i = 0; i < size; i++) {
                netSumOrders = netSumOrders + ordersList.get(i).totalOrderNetPrice();
                netAndTaxSumOrders = (netAndTaxSumOrders + ordersList.get(i).totalOrderPrice());

                // max order ID
                if (ordersList.get(i).totalOrderPrice() > max) {
                    max = ordersList.get(i).totalOrderPrice();
                    maxIndex = i;
                }

                // min order ID
                if (ordersList.get(i).totalOrderPrice() < min) {
                    min = ordersList.get(i).totalOrderPrice();
                    minIndex = i;
                }
            }

            String netTotalString = Double.toString(netSumOrders);
            totalNetPriceOrdersTF.setText(netTotalString + "$");
            String netAndTaxTotalString = Double.toString(netAndTaxSumOrders);
            totalPriceOrdersTF.setText(netAndTaxTotalString + "$");
            String maxString = Double.toString(max);
            maxPriceOrderTF.setText(ordersList.get(maxIndex).getOrderID() + " (" + maxString + "$)");
            String minString = Double.toString(min);
            minPriceOrderTF.setText(ordersList.get(minIndex).getOrderID() + " (" + minString + "$)");
        }

        public void initUI() {
            this.setSize(370, 470);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setResizable(false);
        }

        public void actionsUI() {
            closeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
        }
    }
}
