package cn.northpark.test.datastruct.Statck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import cn.northpark.utils.json.JsonUtil;

/**主要的思想如下：
 * 程序从左到右的扫描表达式，提取出操作数，操作符，以及括号
 * 如果提取的项是你操作数直接将其压入strStack栈中
 * 如果提取的项是+，-运算符，处理opStack栈定中的所有运算符，处理完之后将提取出的运算符压入栈中
 * 如果提取的项是*,/运算符，处理栈顶的所有*,/运算符，如果此时的栈顶的运算符是+，-那么直接入栈，处理完之后将提取出的运算符入栈
 * 如果提取的是'(',那么直接压入opStack栈中
 * 如果提取的是')',重复处理来自opStack栈顶的运算符，知道看到栈顶的'('
 * 
 * 4种操作符
 * and or () not
 * &   |  ()  !
 * 优先级  '()' > '!'  > '&' > '|'
 * 
 * 遇到（）弹出括号里面的表达式存到树子节点child
 * 
 * 遇到高优先级的  弹出低优先级的到梳子节点child
 * 
 * 然后就是树node的定义
 * 
 */
public class PostfixEvaluator {

	static Stack<String>    strStack ;
    static Stack<Character> opStack  ;
    
    static Stack<TreeNode> tempStack  ;
    
    static Set<Integer> nodeidset  = new HashSet<Integer>();
	//构造函数初始化栈
	public PostfixEvaluator(){
		strStack = new Stack<String>();
		opStack  = new Stack<Character>();
		
		tempStack = new Stack<TreeNode>();
	}
	
	
	
	/**
	 * @desc 随机数
	 * @return
	 */
	public static Integer getRandomNumber(){
		
		Random random =  new Random();
		int max=99;
	    int min=01;

	    int s = random.nextInt(max)%(max-min+1) + min;
		
		while(!nodeidset.add(s)){
			s =  getRandomNumber();
		}
		return s;
    
	}


	
	 //这个函数的作用就是使用空格分割字符串，以便后面使用分割函数使得将字符串分割成数组
	    public static String insertBlanks(String s) {
	        String result = "";
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
	                    s.charAt(i) == '&' || s.charAt(i) == '|' || s.charAt(i) == '!'
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
	    public  TreeHelper evaluateExpression(String expression) {
	    	
	    	//初始化一棵树
			TreeHelper tree = new TreeHelper();
			List<TreeNode> treeNodes = new ArrayList<TreeNode>();

	        expression = insertBlanks(expression);
	        String[] tokens = expression.split(" ");
	        for (String token : tokens) {
	            if (token.length() == 0)   //如果是空格的话就继续循环，什么也不操作
	                continue;
				//	            4种操作符
				//	            * and or () not
				//	            * &   |  ()  !
				//	            * 优先级  '()' > '!'  > '&' > '|'
				//	            * 
				//	            * 遇到（）弹出括号里面的表达式存到树子节点child
				//	            * 
				//	            * 遇到高优先级的  弹出低优先级的到梳子节点child
				//	            * 
				//	            * 然后就是树node的定义
	            //如果是and or 的话，因为and or 的优先级最低，因此这里的只要遇到& |号，无论操作符栈中的是什么运算符都要运算
	            
	            //如果是加减的话，因为加减的优先级最低，因此这里的只要遇到加减号，无论操作符栈中的是什么运算符都要运算
	            else if (token.charAt(0) == '|' ) {
	            	//当栈不是空的，并且栈中最上面的一个元素是加减乘除的人任意一个
	            	while (!opStack.isEmpty()&&( opStack.peek() == '|' ||  opStack.peek() == '&' || opStack.peek() == '!' )) {
	            		
	            		//把操作符当成父节点
	            		
	            		//把表达式当成子节点集合
	            		
	            		add2Tree(strStack, opStack, treeNodes);
	            		
	            	}
	                opStack.push(token.charAt(0));   //将当前的运算符入栈
	            }
	            
	            else if (token.charAt(0) == '&' ) {
	            	//当前运算符是乘除的时候，因为优先级高于加减，因此要判断最上面的是否是乘除，如果是乘除就运算，否则的话直接入栈
	            	while (!opStack.isEmpty()&&(  opStack.peek() == '&' || opStack.peek() == '!' )) {
	            		
	            		//把操作符当成父节点
	            		
	            		//把表达式当成子节点集合
	            		add2Tree(strStack, opStack, treeNodes);
	            	}
	                opStack.push(token.charAt(0));   //将当前的运算符入栈
	            }
	            
	            else if (token.charAt(0) == '!' ) {
	            	//当前运算符是乘除的时候，因为优先级高于加减，因此要判断最上面的是否是乘除，如果是乘除就运算，否则的话直接入栈
	            	while (!opStack.isEmpty()&&(opStack.peek() == '!' )) {
	            		
	            		//把操作符当成父节点
	            		//把表达式当成子节点集合
	        	    	char op = opStack.pop(); //弹出一个操作符
	                 	Integer random = getRandomNumber();
	        	    	TreeNode treeNode2 = new TreeNode();
	        	    	treeNode2.setNodeName(String.valueOf(op));
	        	    	treeNode2.setSelfId(random);;
	        	    	
	        	    	treeNodes.add(treeNode2);
	        	    	if(!strStack.isEmpty()){
	        	    		String str = strStack.pop(); //从存储数据的栈中弹出连个1个数用来和操作符op运算
	        	    		TreeNode treeNode = new TreeNode(str,random);
	        		    	treeNode.setSelfId(getRandomNumber());
	        	    		treeNodes.add(treeNode);
	        	    	}else{//剩一个操作符节点，但是没有数据的时候，把操作符节点设置为之前的树没有parent节点的父节点
	        	    		//添加完一个数据以后，发现没有数据可以操作，从缓存栈取出一个[例如：!d]的treenode添加到child里面
        	    			if(!tempStack.isEmpty()){
        	    				TreeNode pop = tempStack.pop();
        	    				pop.setParentId(random);
        	    				
        	    				treeNodes.add(pop);
        	    			}
	        	    	}
	            	}
	                opStack.push(token.charAt(0));   //将当前的运算符入栈
	            }
	            //如果是左括号的话直接入栈，什么也不用操作,trim()函数是用来去除空格的，由于上面的分割操作可能会令操作符带有空格
	            else if (token.trim().charAt(0) == '(') {
	                opStack.push('(');
	                strStack.push("(");
	                tempStack.push(new TreeNode("(",0));
	            }
	            //如果是右括号的话，清除栈中的运算符直至左括号
	            else if (token.trim().charAt(0) == ')') {
	                while (opStack.peek() != '(') {
	                	
	                	//把操作符当成父节点
	            		//把表达式当成子节点集合
	        	    	char op = opStack.pop(); //弹出一个操作符
	        	    	Integer random = getRandomNumber();
	        	    	TreeNode treeNode2 = new TreeNode();
	        	    	treeNode2.setNodeName(String.valueOf(op));
	        	    	treeNode2.setSelfId(random);;
	        	    	treeNodes.add(treeNode2);
	        	    	
	        	    	if(op!='!'){//不是！ 弹出2个操作符
	        	    		
		        	    		//优先取(temp里面的)  2个:1
		        	    		if(!tempStack.isEmpty() && tempStack.peek().getNodeName()!="("){
		        	    			TreeNode pop = tempStack.pop();
	        	    				pop.setParentId(random);
	        	    				
	        	    				treeNodes.add(pop);
	        	    				//优先取temp里面的  2个:2
	        	    				if(!tempStack.isEmpty() && tempStack.peek().getNodeName()!="("){
	        	    					TreeNode pop2 = tempStack.pop();
		        	    				pop2.setParentId(random);
		        	    				
		        	    				treeNodes.add(pop2);
	        	    				}else if(!tempStack.isEmpty() && tempStack.peek().getNodeName()=="("){//从temp和str各取一个
	        	    					if(!strStack.isEmpty() &&strStack.peek() != "(" ){
	        	    						String str = strStack.pop(); //从存储数据的栈中弹出连个两个数用来和操作符op运算
					        	    		TreeNode treeNode = new TreeNode(str,random);
					        		    	treeNode.setSelfId(getRandomNumber());
					        	    		treeNodes.add(treeNode);
	        	    					}
	        	    					
	        	    				}
		        	    		}else{
			        	    		//优先取(temp里面的) 但是括号里面没有值，从str去取
			        	    		if(!tempStack.isEmpty() && tempStack.peek().getNodeName()=="("){
			        	    			if(!strStack.isEmpty() &&strStack.peek() != "(" ){//(括号里的 str有一个
			        	    				String str = strStack.pop(); //从存储数据的栈中弹出连个两个数用来和操作符op运算
					        	    		TreeNode treeNode = new TreeNode(str,random);
					        		    	treeNode.setSelfId(getRandomNumber());
					        	    		treeNodes.add(treeNode);
					        	    		
						        	    	if(!strStack.isEmpty() &&strStack.peek() != "("){//操作数还有值
					        	    					String str_2 = strStack.pop(); //从存储数据的栈中弹出连个两个数用来和操作符op运算
							        	    			TreeNode treeNode_2 = new TreeNode(str_2,random);
							        	    			treeNode_2.setSelfId(getRandomNumber());
							        	    			treeNodes.add(treeNode_2);
					        	    				
					        	    		}
		
			        	    			}
			        	    			
			        	    		}
		        	    		}
	        	    		
		        	    			
		        	    	}
	        	    	
	        	    	
	        	    	if(op=='!'){//是！ 弹出1个操作符
	        	    		if(!strStack.isEmpty() && strStack.peek() != "("){//操作数栈不是空 并且不是左括号：有操作数，取出一个
	        	    				
	        	    				String str = strStack.pop(); //从存储数据的栈中弹出连个两个数用来和操作符op运算
	        	    				TreeNode treeNode = new TreeNode(str,random);
	        	    				treeNode.setSelfId(getRandomNumber());
	        	    				treeNodes.add(treeNode);
	        	    				
		        	    	}
	        	    	}
	                	
	        	    	
                        //把没有pid的treeNode 添加到缓存栈
	        	    	tempStack.add(treeNode2);
	                }
	                opStack.pop();   //这里的是运算完之后清除左括号
	                strStack.pop();  //这里的是运算完之后清除左括号
	                
	            }
	            //不是操作符就直接压入栈到待处理的字符串栈
	            else {
	            	strStack.push(token);
	            }
	            
	            
	            
	            
	        }
	        
	      //最后当栈中不是空的时候继续运算，直到栈中为空即可
            while (!opStack.isEmpty()) {
            	//把操作符当成父节点
        		
        		//把表达式当成子节点集合
        		add2Tree(strStack, opStack, treeNodes);
            }
            
            //当缓存栈中不是空的时候 ,把没有父节点的缓存栈的树节点都保存到treeNodeList中
            while (!tempStack.isEmpty()) {
            	TreeNode pop = tempStack.pop();
            	if(pop.getNodeName()!="("){//排除左括号直接删掉
            		treeNodes.add(pop);
            	}
            }
            
            tree.setTempNodeList(treeNodes);
	        tree.generateTree();
	        System.out.println(JsonUtil.object2json(tree));
	        //遍历树
	        tree.getRoot().traverse();
	        
	        tree.sortTree(tree.getRoot());
			return tree;
	    }
	    
	    //这个函数的作用就是处理栈中的两个数据，然后将栈中的两个数据运算之后将结果存储在栈中
	    public void add2Tree(Stack<String> strStack, Stack<Character> opStack,List<TreeNode> treeNodes) {
	    	//把操作符当成父节点
    		//把表达式当成子节点集合
	    	char op = opStack.pop(); //弹出一个操作符
         	Integer random = getRandomNumber();
	    	TreeNode treeNode2 = new TreeNode();
	    	treeNode2.setNodeName(String.valueOf(op));
	    	treeNode2.setSelfId(random);;
	    	
	    	
	    	//treeNodes.add(treeNode2);
	    	if(op!='!'){//不是！ 弹出2个操作符
		    	if(!strStack.isEmpty()){
		    		String str = strStack.pop(); //从存储数据的栈中弹出连个两个数用来和操作符op运算
		    		TreeNode treeNode = new TreeNode(str,random);
			    	treeNode.setSelfId(getRandomNumber());
		    		treeNodes.add(treeNode);
		    		if(!strStack.isEmpty()){
		    			String str_2 = strStack.pop(); //从存储数据的栈中弹出连个两个数用来和操作符op运算
		    			TreeNode treeNode_2 = new TreeNode(str_2,random);
		    			treeNode_2.setSelfId(getRandomNumber());
		    			treeNodes.add(treeNode_2);
		    		}else if(strStack.isEmpty()){//只剩一个操作数，把当前操作数存入当前符号下面，并且把当前没有parent节点的节点存入当前符号
		    			//添加完一个数据以后，发现没有数据可以操作，从缓存栈取出一个[例如：!d]的treenode添加到child里面
		    			if(!tempStack.isEmpty()){
		    				TreeNode pop = tempStack.pop();
		    				pop.setParentId(random);
		    				
		    				treeNodes.add(pop);
		    			}
		    		}
		    	}else if(strStack.isEmpty()){
		    		//拿出一个操作符以后，发现没有数据可以操作，从缓存栈取出所有 的treenode添加到child里面
		    		while (!tempStack.isEmpty()) {
	    				TreeNode pop = tempStack.pop();
	    				if(pop.getNodeName()!="("){//排除左括号直接删掉
	    					pop.setParentId(random);
	    				
	    					treeNodes.add(pop);
	    				}
	    			}
		    	}
	    	}
	    	
	    	if(op=='!'){ //是NOT运算 ！ 弹出1个操作符
	    		if(!strStack.isEmpty()){
		    		String str = strStack.pop(); //从存储数据的栈中弹出1个数用来和操作符op运算
		    		TreeNode treeNode = new TreeNode(str,random);
			    	treeNode.setSelfId(getRandomNumber());
		    		treeNodes.add(treeNode);
		    	}else if(strStack.isEmpty()){//剩一个操作符节点，但是没有数据的时候，把操作符节点设置为之前的树没有parent节点的父节点
		    		//拿出一个操作符以后，发现没有数据可以操作，从缓存栈取出所有 的treenode添加到child里面
	    			while(!tempStack.isEmpty()){
	    				TreeNode pop = tempStack.pop();
	    				if(pop.getNodeName()!="("){//排除左括号直接删掉
		    				pop.setParentId(random);
		    				
		    				treeNodes.add(pop);
	    				}
	    			}
		    	}
	    	}
	    	//把没有pid的treeNode 添加到缓存栈
	    	tempStack.add(treeNode2);
	    	
	    }

	public static void main(String[] args) {
		
		 String expression = "c001=股票&(w003=A02|f008=1&Np066=display)";
		 
		 //
		 
//		 String expression = "a&b|c&!d";
		 
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
	        
//	        //集合遍历方式  
//            for (String x : strStack) {  
//                    System.out.println(x);  
//            }  
            
//            //集合遍历方式  
//            for (Character x : opStack) {  
//                    System.out.println(x);  
//            }  
            
            
//            while (!opStack.empty()) {  
//                System.out.println(opStack.pop());  
//            }  
	        
		// TODO Auto-generated method stub
	}

}
