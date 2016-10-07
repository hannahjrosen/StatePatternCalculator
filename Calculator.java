import java.awt.Button;

public class Calculator {
	State RFO = new RFO();
	State RSO = new RSO();
	State Er = new Er();
	String operator="";
	String v1 ="";
	String v2="";
	
	 class RFO implements State {
		public String addToNumber(String appendedNumber){
			return v1+= appendedNumber;
		}
		public State changeState(String op){
			operator = op;
			return RSO;
		}
	}
	
	class RSO implements State{
		public String addToNumber(String appendedNumber){
			return v2+= appendedNumber;
		}
		
		public State changeState(String operator) {
				return RFO;
		}
	}
	
	class Er implements State{
		String er;

		@Override
		public String addToNumber(String s) {
			Er.changeState(s);
			return "Error";
		}

		@Override
		public State changeState(String s) {
			return RFO;
		}
	}
	
	public String calculate(){
		double first = Double.parseDouble(v1);
		double answer=0;

		 if(operator.equals("x^2")){
				answer = first*first;
			} else if (operator.equals("sqrt")){
				answer = Math.sqrt(first);
			} else if (operator.equals("1/x")){
				answer = 1/first;
			} else{
			double second = Double.parseDouble(v2);
			
			if (v2.equals("0") && operator.equals("/")){
				 return Er.addToNumber(v2);
			} else if (operator.equals("+")){
				answer = first+second;
			} else if (operator.equals("-")){
				answer = first - second;
			} else if (operator.equals("*")){
				answer = first * second;
			} else if (operator.equals("/")){
				answer = first / second;
			}
		}
		return Double.toString(answer);

	}
		
}
