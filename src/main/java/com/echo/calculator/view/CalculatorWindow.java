package com.echo.calculator.view;

/**
 * Created by echo on 16-9-24.
 */

import com.echo.calculator.constant.CalcOperator;
import com.echo.calculator.controller.CalculatorController;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;

public class CalculatorWindow extends JFrame {
  private JTextField textField;
  private JTextArea textArea;
  private JButton cancelButton, equalButton, dotButton;
  private JButton signButton, addButton, subButton, multiplyButton, divButton, sineButton;
  private JButton[] numButton;

  public CalculatorWindow() {
    this.setSize(600, 440);
    this.setResizable(false);
    this.setTitle("计算器");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Container wnd = getContentPane();
    wnd.setLayout(null);

    JPanel dispPanel = new JPanel();
    JPanel controlPanel = new JPanel();
    JPanel historyPanel = new JPanel();
    wnd.add(dispPanel);
    wnd.add(controlPanel);
    wnd.add(historyPanel);
    dispPanel.setBounds(0, 0, 400, 60);
    controlPanel.setBounds(0, 60, 400, 360);
    historyPanel.setBounds(400, 0, 200, 400);

    dispPanel.setBorder(new LineBorder(Color.GRAY));
    textField = new JTextField("0");
    // textField.setBorder(new LineBorder(Color.RED));
    textField.setSize(new Dimension(380, 60));
    Font dispFont = new Font("Arial", Font.PLAIN, 24);
    textField.setFont(dispFont);
    textField.setHorizontalAlignment(SwingConstants.RIGHT);
    dispPanel.setLayout(null);
    dispPanel.add(textField);
    // dispPanel.setMinimumSize(new Dimension(400, 500));
    //textArea for historyPanel
    historyPanel.setLayout(new BorderLayout());
    JButton copyButton, saveButton, clearButton;
    JPanel jpanel;
    JScrollPane jscrollPane;
    textArea = new JTextArea(10, 15);
    textArea.setTabSize(4);
    textArea.setFont(new Font("标楷体", Font.BOLD, 16));
    textArea.setLineWrap(true);// 激活自动换行功能
    textArea.setWrapStyleWord(true);// 激活断行不断字功能
    textArea.setBackground(Color.WHITE);

    jscrollPane = new JScrollPane(textArea);
    jpanel = new JPanel();
    jpanel.setLayout(new GridLayout(1, 3));

    ActionListener al = new HistoryTestAreaActionListener(this, textArea);
    copyButton = new JButton("复制");
    copyButton.setActionCommand("copy");

    copyButton.addActionListener(al);
    saveButton = new JButton("保存");
    saveButton.setActionCommand("save");
    saveButton.addActionListener(al);
    clearButton = new JButton("清除");
    clearButton.setActionCommand("clear");
    clearButton.addActionListener(al);

    jpanel.add(copyButton);
    jpanel.add(saveButton);
    jpanel.add(clearButton);

    historyPanel.add(jscrollPane, BorderLayout.CENTER);
    historyPanel.add(jpanel, BorderLayout.SOUTH);

    GridBagLayout gridbag = new GridBagLayout();
    // controlPanel.setLayout(new GridLayout(4,4,10,20));
    controlPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
    controlPanel.setLayout(gridbag);
    controlPanel.setBorder(new EmptyBorder(20, 10, 20, 10));

    cancelButton = new JButton("C");
    signButton = new JButton("+/-");
    addButton = new JButton("+");
    subButton = new JButton("-");
    multiplyButton = new JButton("*");
    divButton = new JButton("/");
    sineButton = new JButton("Sin");
    equalButton = new JButton("=");
    numButton = new JButton[10];
    for (int i = 0; i < 10; i++) {
      numButton[i] = new JButton(String.valueOf(i));
    }
    dotButton = new JButton(".");

    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(3, 2, 3, 2);
    c.fill = GridBagConstraints.BOTH;
    c.weightx = 1.0;
    c.weighty = 1.0;
    gridbag.setConstraints(multiplyButton, c);
    controlPanel.add(multiplyButton);
    gridbag.setConstraints(divButton, c);
    controlPanel.add(divButton);
    gridbag.setConstraints(signButton, c);
    controlPanel.add(signButton);
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(cancelButton, c);
    controlPanel.add(cancelButton);

    c.gridwidth = 1;
    gridbag.setConstraints(subButton, c);
    controlPanel.add(subButton);
    gridbag.setConstraints(numButton[9], c);
    controlPanel.add(numButton[9]);
    gridbag.setConstraints(numButton[8], c);
    controlPanel.add(numButton[8]);
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(numButton[7], c);
    controlPanel.add(numButton[7]);

    c.gridwidth = 1;
    gridbag.setConstraints(addButton, c);
    controlPanel.add(addButton);
    gridbag.setConstraints(numButton[6], c);
    controlPanel.add(numButton[6]);
    gridbag.setConstraints(numButton[5], c);
    controlPanel.add(numButton[5]);
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(numButton[4], c);
    controlPanel.add(numButton[4]);

    c.gridwidth = 1;
    gridbag.setConstraints(sineButton, c);
    controlPanel.add(sineButton);
    gridbag.setConstraints(numButton[3], c);
    controlPanel.add(numButton[3]);
    gridbag.setConstraints(numButton[2], c);
    controlPanel.add(numButton[2]);
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(numButton[1], c);
    controlPanel.add(numButton[1]);

    c.gridwidth = 1;
    gridbag.setConstraints(equalButton, c);
    controlPanel.add(equalButton);
    gridbag.setConstraints(dotButton, c);
    controlPanel.add(dotButton);
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridbag.setConstraints(numButton[0], c);
    controlPanel.add(numButton[0]);

    this.addWindowListener(new WindowAdapter() {
      public void windowOpened(WindowEvent e) {
        equalButton.requestFocus();
      }
    });
  }

  class CalculatorHotKey extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      char key = e.getKeyChar();
      if (key >= '0' && key <= '9') {
        numButton[key - '0'].doClick();
      } else switch (e.getKeyChar()) {
        case 'c':
          cancelButton.doClick();
          break;
        case '~':
          signButton.doClick();
          break;
        case '+':
          addButton.doClick();
          break;
        case '-':
          subButton.doClick();
          break;
        case '*':
          multiplyButton.doClick();
          break;
        case '/':
          divButton.doClick();
          break;
        case '=':
          equalButton.doClick();
          break;
        case '.':
          dotButton.doClick();
          break;
        case '\n':
          equalButton.doClick();
          break;
      }
    }

  }

  public void setController(CalculatorController controller) {
    controller.setTextField(textField);
    controller.setTextArea(textArea);

    CalculatorHotKey keyMap = new CalculatorHotKey();

    cancelButton.addKeyListener(keyMap);
    cancelButton.addActionListener(controller);
    cancelButton.setActionCommand(CalcOperator.CLEAR.value);

    signButton.addKeyListener(keyMap);
    signButton.addActionListener(controller);
    signButton.setActionCommand(CalcOperator.SIGN.value);

    addButton.addKeyListener(keyMap);
    addButton.addActionListener(controller);
    addButton.setActionCommand(CalcOperator.PLUS.value);

    subButton.addKeyListener(keyMap);
    subButton.addActionListener(controller);
    subButton.setActionCommand(CalcOperator.MINUS.value);

    multiplyButton.addKeyListener(keyMap);
    multiplyButton.addActionListener(controller);
    multiplyButton.setActionCommand(CalcOperator.TIMES.value);

    divButton.addKeyListener(keyMap);
    divButton.addActionListener(controller);
    divButton.setActionCommand(CalcOperator.DIVISION.value);

    sineButton.addKeyListener(keyMap);
    sineButton.addActionListener(controller);
    sineButton.setActionCommand(CalcOperator.SINE.value);


    equalButton.addKeyListener(keyMap);
    equalButton.addActionListener(controller);
    equalButton.setActionCommand(CalcOperator.EQUAL.value);

    dotButton.addKeyListener(keyMap);
    dotButton.addActionListener(controller);
    dotButton.setActionCommand(CalcOperator.DOT.value);

    for (int i = 0; i < 10; i++) {
      numButton[i].addKeyListener(keyMap);
      numButton[i].addActionListener(controller);
      numButton[i].setActionCommand(String.valueOf(i));
    }
  }
}
