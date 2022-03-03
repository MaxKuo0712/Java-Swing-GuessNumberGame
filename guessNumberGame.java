import java.util.Random;
import java.awt.event.*;
import javax.swing.*;

public class guessNumberGame {
    private JFrame gameFrame;
    private JLabel inputHint;
    private JButton submitButton, cleanButton, showAnsButton;
    private JTextField inputBox;
    private int minValus = 1; // 預設最大值
    private int maxValus = 100; // 預設最小值

    public guessNumberGame() {
        // 設定隨機目標值1~100
        Random random = new Random();
        int targetNumber = random.nextInt(100);

        // 定義框架
        gameFrame = new JFrame();
        gameFrame.setTitle("Guess Number Game");
        gameFrame.setSize(500, 150);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(null); // null為不使用版面配置管理者，每個元件則要使用setBounds()方法來決定元件的位置

        // 標籤1：提示要輸入什麼東西
        inputHint = new JLabel("請輸入" + minValus + "~" + maxValus + "的數字：");
        inputHint.setBounds(0, 5, 500, 20); // 設定絕對位置（x, y, weight, hight）
        inputHint.setHorizontalAlignment(SwingConstants.CENTER);
        gameFrame.add(inputHint);

        // 輸入方塊
        inputBox = new JTextField();
        inputBox.setBounds(140, 30, 200, 20);
        inputBox.setHorizontalAlignment(SwingConstants.CENTER);
        gameFrame.add(inputBox);

        // 確認鍵
        submitButton = new JButton("確認");
        submitButton.setBounds(180, 60, 50, 20);
        gameFrame.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            // 註冊事件，使用匿名類別的匿名物件
            // 處理觸發的事件
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int inputValue = Integer.parseInt(inputBox.getText());
                    Boolean checkAns = (inputValue == targetNumber);

                    if (checkAns == true) {
                        JOptionPane.showMessageDialog(null,
                                "答對了！ 答案是" + Integer.toString(Integer.parseInt(inputBox.getText())), "回答結果",
                                JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    } else {
                        if (inputValue <= minValus) {
                            inputBox.setText(null);
                            JOptionPane.showMessageDialog(null,
                                    "請大於 " + minValus, "回答結果", JOptionPane.INFORMATION_MESSAGE);
                        } else if (inputValue >= maxValus) {
                            inputBox.setText(null);
                            JOptionPane.showMessageDialog(null,
                                    "請小於 " + maxValus, "回答結果", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if (inputValue > targetNumber) {
                                maxValus = inputValue;
                            } else if (inputValue < targetNumber) {
                                minValus = inputValue;
                            }
                            inputBox.setText(null);
                            inputHint.setText("請輸入" + minValus + "~" + maxValus + "的數字：");
                            JOptionPane.showMessageDialog(null,
                                    "答錯了，再猜猜看。\n請輸入" + minValus + "~" + maxValus + "的數字", "回答結果",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    inputBox.setText(null);
                    JOptionPane.showMessageDialog(null, "請輸入數字", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 清除鍵
        cleanButton = new JButton("清除");
        cleanButton.setBounds(250, 60, 50, 20);
        gameFrame.add(cleanButton);
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
        gameFrame.add(showAnsButton);
        showAnsButton.addActionListener(new ActionListener() {
            // 註冊事件，使用匿名類別的匿名物件
            // 處理觸發的事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "答案是 " + targetNumber, "回答結果", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });

        gameFrame.setVisible(true); // 顯示出視窗
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定關閉
    }

    public static void main(String[] args) {
        new guessNumberGame();
    }
}