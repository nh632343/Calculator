import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;






public class SuperCalcu {

	/**
	 * @param args
	 */
	private JFrame win;
	private JTextField tf;
	private Panel panel_2;
	private Button[] btnNum_2,btnFun_2;
	private String[] btnNumS_2,btnFunS_2;
	private boolean dotExist;
	private int leftN,rightN;
	
	private void tfAdd(String add) {
		String string=tf.getText();
		string=string+add;
		tf.setText(string);
	}
	
	private class NumListener_2 implements ActionListener{
		private String addString;
		public NumListener_2(String addString) {
			this.addString=addString;
		}
		public void actionPerformed(ActionEvent arg0) {
			if(!dotExist){   //如果小数点不存在， 小数点键可用
				btnNum_2[10].setEnabled(true);
			}
			tfAdd(addString);
		}
		
	}
	
	private class FourListener implements ActionListener{
        private String addString;
		public FourListener(String addString) {
	          this.addString=addString;
		}

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dotExist=false;
			btnNum_2[10].setEnabled(false);
			tfAdd(addString);
		}
		
	}
	

	public SuperCalcu() {
		dotExist=false;
		leftN=0;
		rightN=0;
		int i=0;
		
		win=new JFrame("SuperCalcu");
		tf=new JTextField();
		tf.setEditable(false);
		tf.setHorizontalAlignment(JTextField.RIGHT);
		win.add("North",tf);
		
		btnNum_2=new Button[12];
		btnNumS_2=new String[]{"1","2","3","4","5","6","7","8","9","0",".","="};
		for(i=0;i<btnNum_2.length;++i){  //监听设置
			btnNum_2[i]=new Button(btnNumS_2[i]);
			if(i<10){
			    NumListener_2 numListener=new NumListener_2(btnNumS_2[i]);
			    btnNum_2[i].addActionListener(numListener);
			}
			if(i==10){
				btnNum_2[10].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						dotExist=true;
						String string=tf.getText();
						string=string+".";
						tf.setText(string);
						btnNum_2[10].setEnabled(false);
					}
				});
			}
			if(i==11){
				btnNum_2[11].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try{
							double result=0;
							if(leftN==rightN){
								result=Zhan.kuoHaoOp(tf.getText());
								if(Double.isInfinite(result)){  //结果为无穷大
									throw new ArithmeticException();
								}
								tf.setText(String.valueOf(result));
							}
							else if(rightN+1==leftN){
								result=Zhan.kuoHaoOp(tf.getText()+")");
								if(Double.isInfinite(result)){   //结果无穷大
									throw new ArithmeticException();
								}
								tf.setText(String.valueOf(result));
							}
							else{
								JOptionPane.showMessageDialog(null, "括号数量有误","错误",JOptionPane.ERROR_MESSAGE);
							}
						}catch (ArithmeticException e) {
							JOptionPane.showMessageDialog(null, "错误 出现除以0 请检查","错误",JOptionPane.ERROR_MESSAGE);
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, "计算错误 请检查","错误",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
			}
	   }
		
		btnFun_2=new Button[8];
		btnFunS_2=new String[]{"C","del","+","-","*","/","(",")"};
		for(i=0;i<btnFun_2.length;++i){
			btnFun_2[i]=new Button(btnFunS_2[i]);
			if(i==0){                   //C
				btnFun_2[0].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						leftN=0;
						rightN=0;
						dotExist=false;
						tf.setText("");
						
					}
				});
			}
			if(i==1){                //dele
				btnFun_2[1].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String string=tf.getText();
						if(string.length()!=0){
							if(string.charAt(string.length()-1)=='.'){
								btnNum_2[10].setEnabled(true);
							}
							if(string.charAt(string.length()-1)=='('){
								--leftN;
							}
							if(string.charAt(string.length()-1)==')'){
								--rightN;
							}
							string=string.substring(0, string.length()-1);
							tf.setText(string);
						}
					}
				});
			}
			if(i>1&&i<6){   //+-*/
				btnFun_2[i].addActionListener(new FourListener(btnFunS_2[i]));
			}
			if(i==6){    //(
				btnFun_2[6].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						btnNum_2[10].setEnabled(false);
						leftN++;
						tfAdd("(");
					}
				});
			}
			if(i==7){    //(
				btnFun_2[7].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						btnNum_2[10].setEnabled(false);
						rightN++;
						tfAdd(")");
					}
				});
			}
		}
		
		panel_2=new Panel(new GridLayout(4, 5,10,10));
		i=0;
		for(int j=0,k=0;i<20;++i){
			if(i%5<3){
				panel_2.add(btnNum_2[j]);
				++j;
			}
			else{
				panel_2.add(btnFun_2[k]);
				++k;
			}
		}
		win.add("Center",panel_2);
        win.addWindowListener(new MyWindowsAdapter());
		
		win.setSize(400, 400);
		win.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new SuperCalcu();
	}
}
