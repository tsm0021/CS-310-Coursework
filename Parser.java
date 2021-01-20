/*
 	CS 310 Unit 2
 	
	@author Trey Scott McAtee
	I, Trey Scott McAtee, have worked on the unit 2 deliverables lexer.java, parser.java, and 
	the railroad diagram solely on my own, in compliance with the academic integrity policy. 
	10/9/20
*/


package unit;

import model.AbstractLexer.Tokens;

public class Parser extends model.AbstractParser {
	Lexer LEXER = new unit.Lexer();

	int i = 0;
	int j = 0;
	char[] var;
	boolean value;
	int[] storage = new int[26];

	
	@Override
	public boolean evaluate(char[] sentence) {
		System.out.println("Input for Test Number "+ i +": " +  String.copyValueOf(sentence));
		LEXER.initialize(sentence);
		LEXER.lex();
		boolean value = program();
		expect(null);
		System.out.println("Output for Test Number "+ i +": " + value);
		i++;
		return value;
	}

	@Override
	public boolean accept(Tokens token) {
		if(LEXER.TOKEN == token) {
			LEXER.lex();
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean peek(Tokens token) {
		if(LEXER.TOKEN == token) 
			return true;
		else 
			return false;
	}
	
	@Override
	public void expect(Tokens token) {
		if(LEXER.TOKEN == token) {
			LEXER.lex();
		}
		else 
			throw new RuntimeException("Expected : "+token+" Found : " + LEXER.TOKEN);
	}
	
	public boolean program() {
		for (int k = 0; k < 26; k++) {
			storage[k] = -1;			//initializing array to placeholder value
		}
		while (accept(Tokens.BEGIN_BOOL)) {
			if (peek(Tokens.VARIABLE_NAME))
				assignment();
//			if (peek(Tokens.ASSIGNMENT))
//				assignment();
//			if (accept(Tokens.TRUE_LITERAL))
//				value = bool();
//			else if (accept(Tokens.FALSE_LITERAL))
//				bool();
			accept(Tokens.END_BOOL);
		}
		accept(Tokens.BEGIN_TEST);
//		while(accept(Tokens.ASSIGNMENT))
//			assignment();
		return evaluation();
	}
	
	public void assignment() {
		if (accept(Tokens.VARIABLE_NAME))
			accept(Tokens.ASSIGNMENT);
			if (bool()) {
				storage[j] = 1;
				j++;
			}
			else {
				storage[j] = 0;	
				j++;
			}
	}
	
	public boolean evaluation() {
		if (storage[j] == -1)
			if (bool()==true){
				storage[j] = 1;
				j++;
			}
			else if (bool() == false) {
				storage[j] = 0;
				j++;
			}
		if (peek(Tokens.EQUIVALENCE))
			value = equivalence();
		else if (peek(Tokens.IMPLICATION))
			value = implication();
		else if (peek(Tokens.DISJUNCTION))
			value = disjunction();
		else if (peek(Tokens.CONJUNCTION))
			value = conjunction();
		else if (peek(Tokens.NEGATION))
			value = negation();
		accept(Tokens.END_TEST);
		return value;
	}
	
	public boolean equivalence() {
		value = implication();
		while (accept(Tokens.EQUIVALENCE)) {
			if (storage[j-1] == 1){
				value = true == bool();
			}
			else if (storage[j-1] == 0) {
				value = false == bool();
			}
		}
		return value;
	}
	
	public boolean implication() {
		value = disjunction();
		while (accept(Tokens.IMPLICATION)) {
			if (storage[j-1] == 1){
				if (bool() == false)
					return value;
			}
			else {
				bool();
				return true;
			}
		}
		return value;
	}
	
	public boolean disjunction() {
		value = conjunction();
		while (accept(Tokens.DISJUNCTION)) {
			if (storage[j-1] == 1){
				value = true | !conjunction();
			}
			else if (storage[j-1] == 0){
				value = false | !conjunction();
			}
		}
		return value;
	}
	
	public boolean conjunction() {
		value = negation();
		while (accept(Tokens.CONJUNCTION)) {
			if (storage[j-1] == 1){
				value = true & negation();
			}
			else if (storage[j-1] == 0){
				value = false & negation();
			}
		}
		return value;
	}
	
	public boolean negation() {
		if (storage[j-1] == 1){
			value = true;
		}
		else if (storage[j-1] == 0){
			value = false;
		}
		if (accept(Tokens.NEGATION)) {
			if (value == true) 
				return value = false;
			else if (value == false)
				return value = true;
		}
		return value;
	}
	
	public boolean expression() {
		if (accept(Tokens.OPEN_PAREN)) {
			accept(Tokens.EQUIVALENCE);
			value = equivalence();
			accept(Tokens.CLOSE_PAREN);
		}
		else if (accept(Tokens.TRUE_LITERAL) || accept(Tokens.FALSE_LITERAL))
			value = bool();
		return value;
	}
	
	public boolean bool() {
		if (accept(Tokens.TRUE_LITERAL))
			value = true;
		else if (accept(Tokens.FALSE_LITERAL))
			value = false;
		else if (accept(Tokens.VARIABLE_NAME)) {
			if (storage[j-1] == 0) {
				value = false;
			}
			else if (storage[j-1] == 1) {
				value = true;
			}
		}
		return value;
	}
	
	public char variable() {
		char[] var = LEXER.LEXEME;
		expect(Tokens.VARIABLE_NAME);
		return var[0];
	}
}
