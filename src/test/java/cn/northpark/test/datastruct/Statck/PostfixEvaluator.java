package cn.northpark.test.datastruct.Statck;

import java.util.Stack;

/**主要的思想如下：
 * 程序从左到右的扫描表达式，提取出操作数，操作符，以及括号
 * 如果提取的项是你操作数直接将其压入strStack栈中
 * 如果提取的项是+，-运算符，处理opStack栈定中的所有运算符，处理完之后将提取出的运算符压入栈中
 * 如果提取的项是*,/运算符，处理栈顶的所有*,/运算符，如果此时的栈顶的运算符是+，-那么直接入栈，处理完之后将提取出的运算符入栈
 * 如果提取的是'(',那么直接压入opStack栈中
 * 如果提取的是')',重复处理来自opStack栈顶的运算符，知道看到栈顶的'('
 */
public class PostfixEvaluator {

	static Stack<String>    strStack ;
    static Stack<Character> opStack  ;
    
    static Stack<Character> tempStack  ;
	//构造函数初始化栈
	public PostfixEvaluator(){
		strStack = new Stack<String>();
		opStack  = new Stack<Character>();
		
		tempStack = new Stack<Character>();
	}

	
	 //这个函数的作用就是使用空格分割字符串，以便后面使用分割函数使得将字符串分割成数组
	    public static String insertBlanks(String s) {
	        String result = "";
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
	                    s.charAt(i) == '&' || s.charAt(i) == '|'
	                )
	                result += " " + s.charAt(i) + " ";
	            else
	                result += s.charAt(i);
	        }
	        return result;
	    }

	    /**
	     * 
	     * @对给定的表达式进行处理
	     * @字符存到字符栈
	     * @内容存到内容栈
	     *
	     * @param expression
	     */
	    public  void evaluateExpression(String expression) {
	        expression = insertBlanks(expression);
	        String[] tokens = expression.split(" ");
	        for (String token : tokens) {
	            if (token.length() == 0)   //如果是空格的话就继续循环，什么也不操作
	                continue;
	            //如果是and or 的话，因为and or 的优先级最低，因此这里的只要遇到& |号，无论操作符栈中的是什么运算符都要运算
	            else if (token.charAt(0) == '|' || token.charAt(0) == '&') {
	                //当栈不是空的，并且栈中最上面的一个元素是加减乘除的人任意一个
	                //strStack.push(token);
	                opStack.push(token.charAt(0));   //将当前的运算符入栈
	            }
	            //如果是左括号的话直接入栈，什么也不用操作,trim()函数是用来去除空格的，由于上面的分割操作可能会令操作符带有空格
	            else if (token.trim().charAt(0) == '(') {
	                opStack.push('(');
	            }
	            //如果是右括号的话，清除栈中的运算符直至左括号
	            else if (token.trim().charAt(0) == ')') {
	                while (opStack.peek() != '(') {
	                	tempStack.push(opStack.pop()); 
	                }
	                opStack.pop();   //这里的是运算完之后清除左括号
	                
	                while(!tempStack.isEmpty()){
	                	
	                	opStack.push(tempStack.pop());
	                }
	            }
	            //不是操作符就直接压入栈到待处理的字符串栈
	            else {
	            	strStack.push(token);
	            }
	        }
	    }
	    


	public static void main(String[] args) {
		
		 String expression = "c001=股票&(w003=A02|f008=1)";
		 
		 System.out.println(insertBlanks(expression));
		  expression = insertBlanks(expression);
		  
		   //打印测试数据
//	        String[] tokens = expression.split(" ");
//	        for (String token : tokens) {
//	            if (token.length() == 0)   //如果是空格的话就继续循环，什么也不操作
//	                continue;
//	            
//	            System.out.println(token);
//	        }
	        
	        //查看计算后的2个栈值
	        PostfixEvaluator a = new PostfixEvaluator();
	        
	        a.evaluateExpression(expression);
	        
	        //集合遍历方式  
            for (String x : strStack) {  
                    System.out.println(x);  
            }  
            
//            //集合遍历方式  
//            for (Character x : opStack) {  
//                    System.out.println(x);  
//            }  
            
            
            while (!opStack.empty()) {  
                System.out.println(opStack.pop());  
            }  
	        
		// TODO Auto-generated method stub
	}

}
