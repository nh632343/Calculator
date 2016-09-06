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
	
	
	//�˷�������׺���ʽת��Ϊ��׺���ʽ
	 private static char[] houZhui(String qian) {
		Deque<Character> stack=new ArrayDeque<Character>();    //����ջ      push��ջ   pop��ջ  peekFirst�鿴ջ��
		ArrayList<Character> hou=new ArrayList<Character>();   //��׺���ʽ
		
		for(int i=0;i<qian.length();++i){
			char temp=qian.charAt(i);
			if(temp=='('){         //������ֱ����ջ
				stack.push('(');
			}
			else if(temp=='*'||temp=='/'){   //�˳�����ջ������ջ����Ϊ�˳���
				while(stack.peekFirst()!=null){
					if(stack.peekFirst()=='*'||stack.peekFirst()=='/')
						hou.add(stack.pop());
					else break;
				}
				stack.push(temp);
				hou.add(' ');       //�ո���������������ֵ
			}
			else if(temp=='+'||temp=='-'){    //�Ӽ�����ջ������ջ��ֻ��Ϊ�����Ż��
				while(stack.peekFirst()!=null){
				    if(stack.peekFirst()=='(')   break;
				    else hou.add(stack.pop());
				}
				stack.push(temp);
				hou.add(' ');
			}
			else if(temp==')'){  //������      ջ��һֱ��ջֱ�������ų�ջ
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
		//ת��Ϊchar[]
		Object[] temp1=hou.toArray();
		char[] result=new char[temp1.length];
		for(int i=0;i<temp1.length;++i){
			result[i]=(Character)temp1[i];
		}
		return result;
	}
	
	 //��׺���ʽ����    ע�ⷵ��ֵ���ܲ������֣������
	 private static double op(char[] bds) {
		 Deque<Double> stack=new ArrayDeque<Double>();    //��ֵջ
		 String parse=null;
		 int i=0,j=0;     //i����ָ����ֵ��ͷ    jָ����ֵ��β
		 while(j<bds.length){
			 if((bds[j]>='0'&&bds[j]<='9')||bds[j]=='.'){  //�������ֲ�����
				 ++j;
			 }
			 else{  //�����˷��Ż��߿ո�
				 if(bds[i]>='0'&&bds[i]<='9'){    //����ֵ��ջ
					 parse=new String(bds,i,j-i);
					 stack.push(Double.parseDouble(parse));
				 }
				 if(bds[j]==' '){++j;  i=j; }  //�����ո�
				 else{ //�����Ӽ��˳�
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
