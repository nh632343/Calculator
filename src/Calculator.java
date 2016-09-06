
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JOptionPane;
import javax.swing.JTextField;




public class Calculator {

	/**
	 * @param args
	 */
	final     int INIT=0;
	final	  int ADD=1;
	final	  int MINUS=2;
	final	  int MULTI=3;
	final	  int DIVI=4;
	final	  int WRONG=5;
	
	
	private Frame win;
	private JTextField tf;
	private MenuBar menuBar;
	private Menu menu;
	private MenuItem menuItem[];
	private int panel_state;
	//面板一变量
	private Button[] btnNum_1;
	private String[] btnNumS_1;
	private Button[] btnFun_1;
	private String[] btnFunS_1;
	private Panel panel_1;
	private double temp1,temp2;
	private int state;
	private int i;
	//面板二变量
	private Panel panel_2;
	private Button[] btnNum_2,btnFun_2;
	private String[] btnNumS_2,btnFunS_2;
	private boolean dotExist;
	private int leftN,rightN;
	
	//  面板一方法
	private void wrongClear() {
		if(state==WRONG){
			tf.setText("");
			state=INIT;
		}
	}
	
	private void addNum(String add) {
		String string=tf.getText();
		string=string+add;
		tf.setText(string);
	}
	
	private void addPeriod() {
		String string=tf.getText();
		if(!string.contains(".")){
			string=string+".";
			tf.setText(string);
		}
	}
	
	private boolean op() {
		int cmp=0;
		temp2=Double.parseDouble(tf.getText());
		switch(state){
		case ADD: temp1=temp1+temp2;
		          state=INIT;
		          cmp=(int) temp1;
                  if((double)cmp-temp1==0){
                	  tf.setText(String.valueOf(cmp));
                  }
                  else {
                  tf.setText(String.valueOf(temp1));}
		          break;
		case MINUS:temp1=temp1-temp2;
                 state=INIT;
                 cmp=(int) temp1;
                 if((double)cmp-temp1==0){
               	  tf.setText(String.valueOf(cmp));
                 }
                 else {
                 tf.setText(String.valueOf(temp1));}
                  break;
		case MULTI:temp1=temp1*temp2;
                   state=INIT;
                   cmp=(int) temp1;
                   if((double)cmp-temp1==0){
                 	  tf.setText(String.valueOf(cmp));
                   }
                   else {
                   tf.setText(String.valueOf(temp1));}
                   break;
		case DIVI:if(temp2==0){
			      tf.setText("被除数不能为0，重新开始");
			      state=WRONG;
			      return false;
		          }
		          temp1=temp1/temp2;
                  state=INIT;
                  cmp=(int) temp1;
                  if((double)cmp-temp1==0){
                	  tf.setText(String.valueOf(cmp));
                  }
                  else {
                  tf.setText(String.valueOf(temp1));}
                  break;
		    
		}
		return true;
	}
	
	private void fourBtn(int flag) {
		temp1=Double.parseDouble(tf.getText());
		state=flag;
		tf.setText("");
	}
	
	
	private class NumListener implements ActionListener{
		private String addString;
		public NumListener(String addString) {
			this.addString=addString;
		}
		public void actionPerformed(ActionEvent arg0) {
			wrongClear();
			addNum(addString);
			
		}
		
	}
	
	//面板二方法
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
	
	
	public Calculator(){
		win=new Frame("calcu");
		tf=new JTextField();
		tf.setEditable(false);
		tf.setHorizontalAlignment(JTextField.RIGHT);
		win.add("North",tf);
		state=INIT;
		//----------第一个面板设置-----------
		
		btnNum_1=new Button[12];
		btnNumS_1=new String[]{"1","2","3","4","5","6","7","8","9","0",".","="};
		for(i=0;i<btnNum_1.length;++i){  //监听设置
			btnNum_1[i]=new Button(btnNumS_1[i]);
			
			if(i<10){
			    NumListener numListener=new NumListener(btnNumS_1[i]);
			    btnNum_1[i].addActionListener(numListener);
			}
			if(i==10){
				btnNum_1[i].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						wrongClear();
						addPeriod();
					}
				});
			}
			if(i==11){
				btnNum_1[i].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						wrongClear();
						if(state!=INIT&&!tf.getText().equals("")){
							op();
						}
					}
				});
			}
			
		}
		

		btnFun_1=new Button[8];
		btnFunS_1=new String[]{"+","C","-","del","*","","/",""};
		for(i=0;i<btnFun_1.length;++i){
			btnFun_1[i]=new Button(btnFunS_1[i]);
			
		}
		btnFun_1[0].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wrongClear();
				if(!tf.getText().equals("")){
					if(state>0&&state<5){
						if(op()){
							fourBtn(ADD);
						}
					}
					else{fourBtn(ADD);}
				}
			}
		});
		btnFun_1[2].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wrongClear();
				if(!tf.getText().equals("")){
					if(state>0&&state<5){
						if(op()){
							fourBtn(MINUS);
						}
					}
					else{fourBtn(MINUS);}
				}
			}
		});
		btnFun_1[4].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wrongClear();
				if(!tf.getText().equals("")){
					if(state>0&&state<5){
						if(op()){
							fourBtn(MULTI);
						}
					}
					else{fourBtn(MULTI);}
				}
			}
		});
		btnFun_1[6].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wrongClear();
				if(!tf.getText().equals("")){
					if(state>0&&state<5){
						if(op()){
							fourBtn(DIVI);
						}
					}
					else{fourBtn(DIVI);}
				}
			}
		});
		btnFun_1[3].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string=tf.getText();
				if(!string.equals(""))
				     string=string.substring(0,string.length()-1);
				tf.setText(string);
			}
		});
		btnFun_1[1].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				state=INIT;
				tf.setText("");
			}
		});
        
		
		panel_1=new Panel(new GridLayout(4,5,5,5));
		i=0;
		for(int j=0,k=0;i<20;++i){
			if(i%5<3){
				panel_1.add(btnNum_1[j]);
				++j;
			}
			else{
				panel_1.add(btnFun_1[k]);
				++k;
			}
		}
		win.add("Center",panel_1);
		panel_state=1;
		
		//第二个面板
		dotExist=false;
		leftN=0;
		rightN=0;
		
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
		
		// ------------- 设置菜单------------------
		menuItem=new MenuItem[]{new MenuItem("普通"),new MenuItem("高级")};
		menuItem[0].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(panel_state==2){
					leftN=0;
					rightN=0;
					dotExist=false;
					tf.setText("");
				   win.remove(panel_2);
					win.add("Center",panel_1);
					panel_state=1;
					win.validate();}
			}
		});
		menuItem[1].addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(panel_state==1){
					state=INIT;
					tf.setText("");
					win.remove(panel_1);
					win.add("Center",panel_2);
					panel_state=2;
					win.validate();
					
				}
			}
		});
		
		menu=new Menu("类型");
		menu.add(menuItem[0]);
		menu.add(menuItem[1]);
		menuBar=new MenuBar();
		menuBar.add(menu);
		win.setMenuBar(menuBar);
		
		win.setSize(400, 400);
		win.setVisible(true);
		win.addWindowListener(new MyWindowsAdapter());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new Calculator();
	}

}
