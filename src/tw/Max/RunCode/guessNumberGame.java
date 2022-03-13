package tw.Max.RunCode;

import javax.swing.*;

import tw.Max.Class.GuessNumberGameClass;

public class guessNumberGame {
	private static int minValue;
	private static int maxValue;

    public static void main(String[] args) {
    	JFrame jFrame = new JFrame();
    	String inputMinValue;
    	String inputMaxValue;
    	
    	inputMinValue = JOptionPane.showInputDialog(jFrame, "請輸入遊戲最小值 (至少為1，預設為1)");
    	setMinValue(inputMinValue);
    	
    	inputMaxValue = JOptionPane.showInputDialog(jFrame, "請輸入遊戲最大值 (至少為100，預設為100)");
    	setMaxValue(inputMaxValue);

		JOptionPane.showMessageDialog(null, "開始遊戲囉！\n最小值：" + minValue + " 最大值：" + maxValue,
				"開始遊戲", JOptionPane.INFORMATION_MESSAGE);
		
    	new GuessNumberGameClass(minValue, maxValue);
    }
    
    public static void setMinValue(String inputMinValue) {
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
    }
    
    public static void setMaxValue(String inputMaxValue) {
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
    }
}