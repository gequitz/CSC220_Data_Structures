/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pj2;

/**
 *
 * @author Maria
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



public final class PrefixEvaluation {
	private final List<Token> tokens;
	private String infixExpression;
	private String postfixExpression;
	
	
	
	public PrefixEvaluation() {
		tokens = new ArrayList<Token>();
		infixExpression = "";
		postfixExpression = "";
	}
	
	
	
	public void addToken(final Token token) {
		token.index = tokens.size();
		tokens.add(token);
	}
	
	
	
	public String prefixExpressionToString() {
		final StringBuilder stringBuilder = new StringBuilder();
		
		for (final Token token : tokens) {
			stringBuilder.append(token.value);
			stringBuilder.append(" ");
		}
		
		String expression = stringBuilder.toString();
		expression = expression.trim();
		return expression;
	}
	
	
	
	public double evaluate() throws Exception {
		final Stack<Token> operators = new Stack<Token>();
		final Stack<Token> numbers = new Stack<Token>();
		final Stack<String> infixExpressions = new Stack<String>();
		final Stack<String> postfixExpressions = new Stack<String>();
		
		try {
			while (tokens.size() > 0) {
				final Token currentToken = tokens.remove(0);
				
				if (currentToken.type == TokenType.NUMBER) {
					numbers.push(currentToken);
				} else if (currentToken.type == TokenType.OPERATOR) {
					operators.push(currentToken);
				}
				
				infixExpressions.push(currentToken.value);
				postfixExpressions.push(currentToken.value);
				
				while (operators.size() > 0) {
					final Token topOperator = operators.peek();
					final int operatorIndex = topOperator.index;
					
					final int operandCount = getOperandCountForOperator(topOperator.value);
					
					if (numbers.size() >= operandCount) {
						
						Token number2 = numbers.peek();
						if (number2.index > operatorIndex) {
							number2 = numbers.pop();
						} else {
							break;
						}
						
						Token number1 = numbers.peek();
						if (number1.index > operatorIndex) {
							number1 = numbers.pop();
						} else {
							numbers.push(number2);
							break;
						}
						
						final Token operator = operators.pop();
						
						String number1Expression = infixExpressions.pop();
						String number2Expression = infixExpressions.pop();
						String operatorExpression = infixExpressions.pop();
						infixExpressions.push("(" + number2Expression + " " + operatorExpression + " "
							+ number1Expression + ")");
						
						number1Expression = postfixExpressions.pop();
						number2Expression = postfixExpressions.pop();
						operatorExpression = postfixExpressions.pop();
						postfixExpressions.push(number2Expression + " " + number1Expression + " "
							+ operatorExpression);
						
						final double result = evaluateResult(number1.value, operator.value, number2.value);
						final Token resultToken = new Token(Double.toString(result), TokenType.NUMBER,
							number2.index);
						numbers.push(resultToken);
						
					} else {
						break;
					}
				}
			}
			
		} catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		double answer = 0;
		
		if (numbers.size() == 1) {
			answer = Double.parseDouble(numbers.pop().value);
		} else {
			throw new Exception("Error in parsing");
		}
		
		infixExpression = infixExpressions.pop();
		postfixExpression = postfixExpressions.pop();
		
		return answer;
	}
	
	
	
	private double evaluateResult(final String number1String, final String operator, final String number2String) {
		double result = 0;
		
		final double number1 = Double.parseDouble(number1String);
		final double number2 = Double.parseDouble(number2String);
		
		if (operator.equals("+")) {
			result = number1 + number2;
		} else if (operator.equals("-")) {
			result = number1 - number2;
		} else if (operator.equals("*")) {
			result = number1 * number2;
		} else if (operator.equals("/")) {
			result = number1 / number2;
		} else {
			throw new IllegalArgumentException("Operator unknown");
		}
		
		return result;
	}
	
	
	
	private int getOperandCountForOperator(final String operator) {
		int operandCount = 0;
		
		if (operator.equals("+") || operator.equals("-") || operator.equals("*") || operator.equals("/")) {
			operandCount = 2;
		} else {
			throw new IllegalArgumentException("Operator unknown");
		}
		
		return operandCount;
	}
	
	
	
	private static final class Token {
		public final String value;
		public final TokenType type;
		public int index;
		
		
		
		public Token(final String value, final TokenType type) {
			this(value, type, -1);
		}
		
		
		
		public Token(final String value, final TokenType type, final int index) {
			this.value = value;
			this.type = type;
			this.index = index;
		}
		
		
		
		@Override
		public String toString() {
			return value + " (" + type.toString() + ", " + index + ")";
		}
	}
	
	
	
	private enum TokenType {
		OPERATOR, NUMBER
	}
	
	
	
	public static void main(final String[] args) throws Exception {
		final PrefixEvaluation prefixEvaluation = new PrefixEvaluation();
		prefixEvaluation.addToken(new Token("*", TokenType.OPERATOR));
		prefixEvaluation.addToken(new Token("+", TokenType.OPERATOR));
		prefixEvaluation.addToken(new Token("12", TokenType.NUMBER));
		prefixEvaluation.addToken(new Token("8", TokenType.NUMBER));
		prefixEvaluation.addToken(new Token("+", TokenType.OPERATOR));
		prefixEvaluation.addToken(new Token("3", TokenType.NUMBER));
		prefixEvaluation.addToken(new Token("7", TokenType.NUMBER));
		
		final String prefixExression = prefixEvaluation.prefixExpressionToString();
		
		final double answer = prefixEvaluation.evaluate();
		
		final String infixExpression = prefixEvaluation.infixExpression;
		final String postfixExpression = prefixEvaluation.postfixExpression;
		
		System.out.println(prefixExression + " = " + infixExpression + " = " + postfixExpression + " = " + answer);
	}
}
