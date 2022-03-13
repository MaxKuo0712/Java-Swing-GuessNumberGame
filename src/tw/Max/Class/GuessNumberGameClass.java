package tw.Max.Class;

import java.awt.event.*;
import javax.swing.*;

public final class GuessNumberGameClass extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel inputHint;
    private JButton submitButton, cleanButton, showAnsButton;
    private JTextField inputBox;
    private int minValue; // 預設最小值
    private int maxValue; // 預設最大值
    private int targetNumber; // 隨機目標值

    public GuessNumberGameClass(int minValue, int maxValue) {
        // 定義視窗
        super("Guess Number Game");
        setSize(500, 150);
        setLocationRelativeTo(null);
        setLayout(null); // null為不使用版面配置管理者，每個元件則要使用setBounds()方法來決定元件的位置

        // 設定minValue, maxValue
    	this.minValue = minValue;
    	this.maxValue = maxValue;
        
        // 設定隨機目標值 minValue ~ maxValue
        targetNumber = setResult(minValue, maxValue);
        
        // 標籤1：提示要輸入什麼東西
        inputHint = new JLabel("請輸入" + minValue + "~" + maxValue + "的數字：");
        inputHint.setBounds(0, 5, 500, 20); // 設定絕對位置（x, y, weight, hight）
        inputHint.setHorizontalAlignment(SwingConstants.CENTER);
        add(inputHint);

        // 輸入方塊
        inputBox = new JTextField();
        inputBox.setBounds(140, 30, 200, 20);
        inputBox.setHorizontalAlignment(SwingConstants.CENTER);
        add(inputBox);

        // 確認鍵
        submitButton = new JButton("確認");
        submitButton.setBounds(180, 60, 50, 20);
        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            // 註冊事件，使用匿名類別的匿名物件
            // 處理觸發的事件
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	int inputValue = Integer.parseInt(inputBox.getText());
                	checkResult(inputValue, getResult());
                } catch (Exception ex) {
                    inputBox.setText(null);
                    JOptionPane.showMessageDialog(null, "請輸入數字", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 清除鍵
        cleanButton = new JButton("清除");
        cleanButton.setBounds(250, 60, 50, 20);
        add(cleanButton);
        cleanButton.addActionListener(new ActionListener() {
            // 註冊事件，使用匿名類別的匿名物件
            // 處理觸發的事件
            @Override
            public void actionPerformed(ActionEvent e) {
                inputBox.setText("");
            }
        });

        // 看答案
        showAnsButton = new JButton("看答案");
        showAnsButton.setBounds(215, 90, 50, 20);
        add(showAnsButton);
        showAnsButton.addActionListener(new ActionListener() {
            // 註冊事件，使用匿名類別的匿名物件
            // 處理觸發的事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "答案是 " + getResult(), "回答結果", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        setVisible(true); // 顯示出視窗
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定關閉
    }
    
    public void checkResult(int inputValue, int targetNumber) {
    	
        Boolean checkAns = (inputValue == targetNumber);

        if (checkAns == true) {
            JOptionPane.showMessageDialog(null, "答對了！ 答案是" + String.valueOf(inputValue), "回答結果",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else {
            if (inputValue <= minValue) {
                inputBox.setText(null);
                JOptionPane.showMessageDialog(null, "請大於 " + minValue, "回答結果",
                		JOptionPane.INFORMATION_MESSAGE);
            } else if (inputValue >= maxValue) {
                inputBox.setText(null);
                JOptionPane.showMessageDialog(null, "請小於 " + maxValue, "回答結果",
                		JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (inputValue > targetNumber) {
                    maxValue = inputValue;
                } else if (inputValue < targetNumber) {
                    minValue = inputValue;
                }
                inputBox.setText(null);
                inputHint.setText("請輸入" + minValue + "~" + maxValue + "的數字：");
                JOptionPane.showMessageDialog(null, 
                		"答錯了，再猜猜看。\n請輸入" + minValue + "~" + maxValue + "的數字", "回答結果",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public int setResult(int minValue, int maxValue) {
    	return (int) (Math.random() * maxValue + minValue);
    }
    
    public int getResult() {
    	return targetNumber;
    }
}
