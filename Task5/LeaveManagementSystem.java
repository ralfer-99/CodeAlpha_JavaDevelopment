import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class LeaveManagementSystem extends JFrame {
    private Map<String, Integer> leaveBalances = new HashMap<>();
    private int leaveRequestIdCounter = 1;

    public LeaveManagementSystem() {
        setTitle("Employee Leave Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JTextField employeeNameField = new JTextField();
        JComboBox<String> employeeComboBox = new JComboBox<>();
        JTextArea leaveRequestArea = new JTextArea();
        JTextArea leaveBalanceArea = new JTextArea();

        add(new JLabel("Employee Name: "));
        add(employeeNameField);

        JPanel submitPanel = new JPanel(new FlowLayout());
        submitPanel.add(new JLabel("Select Employee: "));
        submitPanel.add(employeeComboBox);
        JButton submitButton = new JButton("Submit Leave Request");
        submitPanel.add(submitButton);
        add(submitPanel);

        add(new JScrollPane(leaveRequestArea));

        JPanel approveRejectPanel = new JPanel(new FlowLayout());
        JButton approveButton = new JButton("Approve Leave Request");
        approveRejectPanel.add(approveButton);
        JButton rejectButton = new JButton("Reject Leave Request");
        approveRejectPanel.add(rejectButton);
        add(approveRejectPanel);

        add(new JScrollPane(leaveBalanceArea));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeName = employeeNameField.getText().trim();
                if (!employeeName.isEmpty()) {
                    if (!leaveBalances.containsKey(employeeName)) {
                        leaveBalances.put(employeeName, 0);
                    }
                    leaveRequestArea.append("Leave request submitted for Employee: " + employeeName + "\n");
                    employeeComboBox.addItem(employeeName);
                    employeeNameField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter employee name");
                }
            }
        });

        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmployee = (String) employeeComboBox.getSelectedItem();
                if (selectedEmployee != null) {
                    leaveBalances.put(selectedEmployee, leaveBalances.get(selectedEmployee) + 1);
                    leaveRequestArea.append("Leave request approved for Employee: " + selectedEmployee + "\n");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an employee");
                }
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedEmployee = (String) employeeComboBox.getSelectedItem();
                if (selectedEmployee != null) {
                    leaveRequestArea.append("Leave request rejected for Employee: " + selectedEmployee + "\n");
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an employee");
                }
            }
        });

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    leaveBalanceArea.setText("");
                    for (Map.Entry<String, Integer> entry : leaveBalances.entrySet()) {
                        leaveBalanceArea.append(entry.getKey() + " : " + entry.getValue() + "\n");
                    }
                });
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LeaveManagementSystem().setVisible(true));
    }
}
