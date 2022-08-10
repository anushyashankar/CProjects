import java.util.*;

public class AlgoOps {

	public static Stack Calcu(String s) {
		String num = "1234567890";
		String op = "+-/X";
		Stack<Integer> numb = new Stack<Integer>();
		Stack<String> operator = new Stack<String>();
		
		while (!s.isEmpty()) {
			String n = "";
			if (num.contains(s.substring(0,1))) {
				n = s.substring(0,1);
				int thing = Integer.parseInt(n);
				numb.add(thing);
			}
			else if (s.substring(0,1).equals("("))
				operator.add("(");
			
			else if (op.contains(s.substring(0,1))) {
				if (!operator.isEmpty()) {
					while(operator.peek().equals("X")||operator.peek().equals("/")) {
						if (operator.peek().equals("X")) {
							int mult = numb.pop() * numb.pop();
							operator.pop();
							numb.add(mult);
						}
						else {
							int sec = numb.pop();
							int div = numb.pop()/sec;
							numb.add(div);
							operator.pop();
						}
					}
				}
				operator.add(s.substring(0,1));
			}
			else if (s.substring(0,1).equals("")) {
				while(!operator.peek().equals("(")) {
					if (operator.peek().equals("+")) {
						int add = numb.pop()+numb.pop();
						numb.add(add);
					}
					else if (operator.peek().equals("/")) {
						int sec = numb.pop();
						int fir = numb.pop();
						int divide = fir/sec;
						numb.add(divide);
					}
					else if (operator.peek().equals("X")) {
						int mult = numb.pop()*numb.pop();
						numb.add(mult);
					}
					else if (operator.peek().equals("-")) {
						int sec = numb.pop();
						int fir = numb.pop();
						int sub = fir-sec;
						numb.add(sub);
					}
					operator.pop();
				}
				operator.pop();
			}
			s = s.substring(1);
		}
		if (s.isEmpty()) {
			while (!operator.isEmpty()) {
				if (operator.peek().equals("+")) {
					int add = numb.pop()+numb.pop();
					numb.add(add);
				}
				else if (operator.peek().equals("/")) {
					int sec = numb.pop();
					int fir = numb.pop();
					int divide = fir/sec;
					numb.add(divide);
				}
				else if (operator.peek().equals("X")) {
					int mult = numb.pop()*numb.pop();
					numb.add(mult);
				}
				else if (operator.peek().equals("-")) {
					int sec = numb.pop();
					int fir = numb.pop();
					int sub = fir - sec;
					numb.add(sub);
				}
				operator.pop();
			}
		}
		return numb;
	}



	public static boolean precedent(String s) {
		//String oper = "X/";
		if (s.indexOf("X") != -1)
			return true;
		if (s.indexOf("/") != -1)
			return true;
		return false;
	}

	public static Stack calculations(String s) {
		Stack<Integer> numb = new Stack<Integer>();
		Stack<String> operator = new Stack<String>();

		String num = "0123456789";
		String temp = s.substring(0, 1);
		String firstNum = "";

		int index = 0;

		while (!temp.equals(" ")) {
			if (num.contains(temp)) {
				firstNum += temp;
			}
			index++;
			temp = s.substring(index, index + 1);
		}

		int first = Integer.parseInt(firstNum);
		numb.add(first);

		index++;
		temp = s.substring(index, index+1);
		//System.out.println(temp);
		operator.add(temp);

		index++;
		index++; //skip first space

		temp = s.substring(index, index + 1);

		String secondNum = "";
		//System.out.println(temp);

		while (index < s.length()) {
			if (num.contains(temp)) {
				secondNum += temp;
			}
			index++;
			if (index != s.length()) {
				temp = s.substring(index, index + 1);
			}
		}

		int second = Integer.parseInt(secondNum);
		numb.add(second);

		String op = operator.pop();
		if (op.equals("+")) {
			int add = numb.pop() + numb.pop();
			numb.add(add);
		}
		else if (op.equals("/")) {
			int sec = numb.pop();
			int fir = numb.pop();

			int divide = fir/sec;
			numb.add(divide);
		}
		else if (op.equals("X")) {
			int mult = numb.pop() * numb.pop();
			numb.add(mult);
		}
		else if (op.equals("-")) {
			int sec = numb.pop();
			int fir = numb.pop();

			int sub = fir - sec;
			numb.add(sub);
		}

		return numb;
	}

	public static void main(String[] args) {
		String thing = "(2 X 3) + 5";

		System.out.println(Calcu(thing));
	}

}
