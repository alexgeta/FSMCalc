package com.teamdev.fsmcalc.gui;

import com.teamdev.fsmcalc.mathcalc.impl.StateMachineCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Alex Geta
 */
public class CalculatorGUI {

    private final JFrame mainFrame = new JFrame();
    private final JLabel messageLabel = new JLabel();
    private final JTextField inputField = new JTextField();
    private final JButton equalsButton = new JButton();
    private final JTextField resultField = new JTextField();
    private final JButton clearButton = new JButton();
    private final ActionListener buttonListener = new ButtonClickListener();
    private final StateMachineCalculator calculator = new StateMachineCalculator();
    private final JLabel errorMessageLabel = new JLabel();
    private final int WIDTH = 650;
    private final int HEIGHT = 90;
    private final String TITLE = "State Machine Calculator";
    private final String LABEL_MESSAGE = "Enter math expression :";
    private final String EQUALS_BUTTON = "=";
    private final String CLEAR_BUTTON = "C";


    public CalculatorGUI() {
        configureGUI();
    }

    private void configureGUI() {

        mainFrame.setTitle(TITLE);
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        messageLabel.setText(LABEL_MESSAGE);
        inputField.setColumns(20);
        equalsButton.setText(EQUALS_BUTTON);
        equalsButton.addActionListener(buttonListener);
        resultField.setColumns(15);
        resultField.setFocusable(false);

        clearButton.setText(CLEAR_BUTTON);
        clearButton.addActionListener(buttonListener);
        errorMessageLabel.setForeground(Color.RED);
        mainFrame.add(messageLabel);
        mainFrame.add(inputField);
        mainFrame.add(equalsButton);
        mainFrame.add(resultField);
        mainFrame.add(clearButton);
        mainFrame.add(errorMessageLabel);

    }

    private void clearAllField() {
        inputField.setText("");
        resultField.setText("");
        errorMessageLabel.setText("");
    }

    private class ButtonClickListener implements ActionListener {

        public void actionPerformed(ActionEvent ev) {

            String command = ev.getActionCommand();
            if (command.equals(EQUALS_BUTTON)) {

                errorMessageLabel.setText("");
                final String mathExpression = (inputField.getText());

                String result = null;
                try {
                    result = String.valueOf(calculator.evaluate(mathExpression));
                } catch (Exception ex) {
                    errorMessageLabel.setText(ex.getMessage());
                }

                resultField.setText(result);
            } else if (command.equals(CLEAR_BUTTON)) {
                clearAllField();
            }
        }
    }
}
