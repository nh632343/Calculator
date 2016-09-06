import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;


public class Zhan {

	/**
	 * @param args
	 */
	public class DiviZero extends Exception{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;} 
	
	
	//此方法将中缀表达式转换为后缀表达式
	 private static char[] houZhui(String qian) {
		Deque<Character> stack=new ArrayDeque<Character>();    //符号栈      push入栈   pop出栈  peekFirst查看栈顶
		ArrayList<Character> hou=new ArrayList<Character>();   //后缀表达式
		
		for(int i=0;i<qian.length();++i){
			char temp=qian.charAt(i);
			if(temp=='('){         //左括号直接入栈
				stack.push('(');
			}
			else if(temp=='*'||temp=='/'){   //乘除号入栈条件：栈顶不为乘除号
				while(stack.peekFirst()!=null){
					if(stack.peekFirst()=='*'||stack.peekFirst()=='/')
						hou.add(stack.pop());
					else break;
				}
				stack.push(temp);
				hou.add(' ');       //空格用来区别两个数值
			}
			else if(temp=='+'||temp=='-'){    //加减号入栈条件：栈顶只能为左括号或空
				while(stack.peekFirst()!=null){
				    if(stack.peekFirst()=='(')   break;
				    else hou.add(stack.pop());
				}
				stack.push(temp);
				hou.add(' ');
			}
			else if(temp==')'){  //右括号      栈顶一直出栈直到左括号出栈
				while(stack.peekFirst()!='('){
					hou.add(stack.pop());
				}
				stack.pop();
				hou.add(' ');
			}
			else {
				hou.add(temp);
			}
		}
		while(stack.peekFirst()!=null){
			hou.add(stack.pop());
		}
		//转换为char[]
		Object[] temp1=hou.toArray();
		char[] result=new char[temp1.length];
		for(int i=0;i<temp1.length;++i){
			result[i]=(Character)temp1[i];
		}
		return result;
	}
	
	 //后缀表达式运算    注意返回值可能不是数字（无穷大）
	 private static double op(char[] bds) {
		 Deque<Double> stack=new ArrayDeque<Double>();    //数值栈
		 String parse=null;
		 int i=0,j=0;     //i用来指向数值开头    j指向数值结尾
		 while(j<bds.length){
			 if((bds[j]>='0'&&bds[j]<='9')||bds[j]=='.'){  //遇到数字不操作
				 ++j;
			 }
			 else{  //遇到了符号或者空格
				 if(bds[i]>='0'&&bds[i]<='9'){    //将数值入栈
					 parse=new String(bds,i,j-i);
					 stack.push(Double.parseDouble(parse));
				 }
				 if(bds[j]==' '){++j;  i=j; }  //遇到空格
				 else{ //遇到加减乘除
					 double op2=stack.pop(),op1=stack.pop();
					 if(bds[j]=='+')  stack.push(op1+op2);
					 if(bds[j]=='-')  stack.push(op1-op2);
					 if(bds[j]=='*')  stack.push(op1*op2);
					 if(bds[j]=='/')  stack.push(op1/op2);
					 ++j;  i=j;
				 }
			 }
		 }
		 
		 return stack.pop();
	 }
	 
	 public static double kuoHaoOp(String string){
		 return op(houZhui(string));
	 }
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        double a=kuoHaoOp("7/(1+1)");
        System.out.print(a);
	}

}
