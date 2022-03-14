package tw.Max.RunCode;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GuessNumberGame extends JFrame {
	private static final long serialVersionUID = 1L;
	private int minValue;
	private int maxValue;
	private int answer;
	
	private JLabel inputHint;
	private JButton submitButton, cleanButton, showAnsButton;
	private JTextField inputBox;
	
	public GuessNumberGame() {
		super("Guess Number Game"); // 設定視窗名稱
		setSize(300, 150); // 設定視窗大小
		setLocationRelativeTo(null); // 打開後直接出現在螢幕正中央
		setLayout(new BorderLayout());
		
		minValue = setMinValue(); // 設定最小值
		maxValue = setMaxValue(); // 設定最大值
		
		// 標籤1：提示要輸入什麼東西
		inputHint = new JLabel("請輸入" + minValue + "~" + maxValue + "的數字：");
		inputHint.setHorizontalAlignment(SwingConstants.CENTER); // 置中
		add(inputHint, BorderLayout.NORTH); // 放置在BorderLayout的最上方
		
		// 輸入方塊
		inputBox = new JTextField();
		inputBox.setHorizontalAlignment(SwingConstants.CENTER); // 置中
		inputBox.setFont(new Font("Arial", Font.PLAIN, 20));
		add(inputBox, BorderLayout.CENTER); // 放置在BorderLayout的中間位置
		
		// 放置Button用的Panel
		JPanel downPanel = new JPanel(new FlowLayout()); // Panel使用FlowLayout
		add(downPanel, BorderLayout.SOUTH); // 放置在BorderLayout的最下方
		
		// 確認鍵
		submitButton = new JButton("確認");
		downPanel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            // 註冊事件，使用匿名類別的匿名物件
            // 處理觸發的事件
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	int inputValue = Integer.parseInt(inputBox.getText());
                	checkResult(inputValue, answer);
                } catch (Exception ex) {
                    inputBox.setText(null);
                    JOptionPane.showMessageDialog(null, "請輸入數字", "輸入錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		
		// 清除鍵
		cleanButton = new JButton("清除");
		downPanel.add(cleanButton);
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
		downPanel.add(showAnsButton);
        showAnsButton.addActionListener(new ActionListener() {
            // 註冊事件，使用匿名類別的匿名物件
            // 處理觸發的事件
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "答案是 " + answer, "回答結果", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
		
        setVisible(true); // 顯示出視窗
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 設定關閉
	}

    public static void main(String[] args) {
    	GuessNumberGame GuessNumber = new GuessNumberGame();
    	GuessNumber.startGame();
    }
    
    private void startGame() {
		JOptionPane.showMessageDialog(null, "開始遊戲囉！\n最小值：" + minValue + " 最大值：" + maxValue,
				"開始遊戲", JOptionPane.INFORMATION_MESSAGE);
		
		answer = setAns(minValue, maxValue);  // 設定隨機目標值 minValue ~ maxValue
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
    
    private int setMinValue() {
    	String inputMinValue = JOptionPane.showInputDialog(null, "請輸入遊戲最小值 (至少為1，預設為1)");
    	
    	if (inputMinValue == null || inputMinValue.equals("")) {
			JOptionPane.showMessageDialog(null, "因沒有輸入最小值，依照預設為1", "最小值設定",
					JOptionPane.WARNING_MESSAGE);
    		minValue = 1;
    	} else {
    		if(Integer.parseInt(inputMinValue) < 1) {
    			JOptionPane.showMessageDialog(null, "因少於1，依照預設為1", "最小值設定",
    					JOptionPane.WARNING_MESSAGE);
    			minValue = 1;
    		} else {
    			minValue = Integer.parseInt(inputMinValue);
    		}
    	}
		return Integer.parseInt(inputMinValue);
    }
    
    private int setMaxValue() {
    	String inputMaxValue = JOptionPane.showInputDialog(null, "請輸入遊戲最大值 (至少為100，預設為100)");
    	
    	if (inputMaxValue == null || inputMaxValue.equals("")) {
			JOptionPane.showMessageDialog(null, "因沒有輸入最大值，依照預設為100", "最大值設定",
					JOptionPane.WARNING_MESSAGE);
    		maxValue = 100;
    	} else {
    		if(Integer.parseInt(inputMaxValue) < 100) {
    			JOptionPane.showMessageDialog(null, "因少於100，依照預設為100", "最大值設定",
    					JOptionPane.WARNING_MESSAGE);
    			maxValue = 100;
    		} else {
    			maxValue = Integer.parseInt(inputMaxValue);
    		}
    	}
    	return Integer.parseInt(inputMaxValue);
    }

    public int setAns(int minValue, int maxValue) {
    	return (int) (Math.random() * (maxValue - minValue) + minValue);
    }

}