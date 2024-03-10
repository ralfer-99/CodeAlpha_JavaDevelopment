import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

class Employee {
    private String name;
    private double hourlyRate;
    private double hoursWorked;

    public Employee(String name, double hourlyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getName() {
        return name;
    }

    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class PayrollSystem extends JFrame {
    private ArrayList<Employee> employees;

    public PayrollSystem() {
        employees = new ArrayList<>();
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Employee Payroll System");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField nameField = new JTextField(20);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        JTextField rateField = new JTextField(20);
        panel.add(new JLabel("Hourly Rate:"));
        panel.add(rateField);

        JTextField hoursField = new JTextField(20);
        panel.add(new JLabel("Hours Worked:"));
        panel.add(hoursField);

        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                double rate = Double.parseDouble(rateField.getText());
                double hours = Double.parseDouble(hoursField.getText());

                Employee emp = new Employee(name, rate);
                emp.setHoursWorked(hours);
                employees.add(emp);

                JOptionPane.showMessageDialog(null, "Employee added successfully");
            }
        });
        panel.add(addButton);

        JButton generateButton = new JButton("Generate Pay Stub");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder payStub = new StringBuilder();
                for (Employee emp : employees) {
                    payStub.append("Name: ").append(emp.getName()).append("\n");
                    payStub.append("Salary: $").append(emp.calculateSalary()).append("\n\n");
                }
                JTextArea payStubArea = new JTextArea(payStub.toString(), 10, 30);
                payStubArea.setEditable(false);
                JOptionPane.showMessageDialog(null, new JScrollPane(payStubArea), "Pay Stub",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        panel.add(generateButton);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PayrollSystem();
    }
}
