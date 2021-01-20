/*
 	CS 310 Unit 1
 	
	@author Trey Scott McAtee
	I, Trey Scott McAtee, have worked on the unit 1 deliverables lexer.java and 
	the FSA.pdf solely on my own, in compliance with the academic integrity policy. 
	10/9/20
*/

package unit;

public class Lexer extends model.AbstractLexer {
	
	private char[] sentence;
	private int i;
	
	@Override
	public void initialize(char[] sentence) {
		this.sentence = sentence;
		i = -1;
	}

	@Override
	public void lex() {
		LEXEME = null;
		TOKEN = null;
		
		if (sentence == null || i >= sentence.length)
			return;
		do {
			i++;
			if (i >= sentence.length)
				return;
		}
		while (sentence[i] == ' ');
		
		if (sentence[i] == 'b' || sentence[i] =='B') {
			if (i+1 < sentence.length && sentence[i+1] == 'o'|| sentence[i+1] =='O') {
				i++;
				if (i+1 < sentence.length && sentence[i+1] == 'o'|| sentence[i+1] =='O') {
					i++;
					if (i+1 < sentence.length && sentence[i+1] == 'l'|| sentence[i+1] =='L')
						i++;
						TOKEN = Tokens.BEGIN_BOOL;
				}
			}
		}
		
		else if (sentence[i] == '=')
			TOKEN = Tokens.ASSIGNMENT;
		else if (sentence[i] == ',')
			TOKEN = Tokens.END_BOOL;
		else if (sentence[i] == 't'|| sentence[i] =='T') {
			if (i+1 < sentence.length && sentence[i+1] == 'e'|| sentence[i+1] =='E') {
				i++;
				if (i+1 < sentence.length && sentence[i+1] == 's' || sentence[i+1] =='S') {
					i++;
					if (i+1 < sentence.length && sentence[i+1] == 't'|| sentence[i+1] =='T')
						i++;
						TOKEN = Tokens.BEGIN_TEST;
				}
			}
		}
		else if (sentence[i] == '?')
			TOKEN = Tokens.END_TEST;
		else if (sentence[i] == '<') {
			if (i+1 < sentence.length && sentence[i+1] == '-') {
				i++;
				if (i+1 < sentence.length && sentence[i+1] == '>') {
					i++;
					TOKEN = Tokens.EQUIVALENCE;
				}
			}
		}
		else if (sentence[i] == '-') {
			if (i+1 < sentence.length && sentence[i+1] == '>') {
				i++;
				TOKEN = Tokens.IMPLICATION;
				}
			}
		else if (sentence[i] == 'v')
			TOKEN = Tokens.DISJUNCTION;
		else if (sentence[i] == '^') 
			TOKEN = Tokens.CONJUNCTION;
		else if (sentence[i] == '\'')
			TOKEN = Tokens.NEGATION;
		else if (sentence[i] == '(')
			TOKEN = Tokens.OPEN_PAREN;
		else if (sentence[i] == ')')
			TOKEN = Tokens.CLOSE_PAREN;
		else if (sentence[i] == 'A'||sentence[i] =='B'||sentence[i] =='C'||sentence[i] =='D'||sentence[i] =='E'||sentence[i] =='F'
				||sentence[i] =='G'||sentence[i] =='H'||sentence[i] =='I'||sentence[i] =='J'||sentence[i] =='K'||sentence[i] =='L'
				||sentence[i] =='M'||sentence[i] =='N'||sentence[i] =='O'||sentence[i] =='P'||sentence[i] =='Q'||sentence[i] =='R'
				||sentence[i] =='S'||sentence[i] =='T'||sentence[i] =='U'||sentence[i] =='V'||sentence[i] =='W'||sentence[i] =='X'
				||sentence[i] =='Y'||sentence[i] =='Z')   
			TOKEN = Tokens.VARIABLE_NAME;
		else if (sentence[i] == '0')
			TOKEN = Tokens.FALSE_LITERAL;
		else if (sentence[i] == '1')
			TOKEN = Tokens.TRUE_LITERAL;
		else throw new RuntimeException("Unexpected: " + sentence[i]);
		
		return;
	}
}

