package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;

class Scanner {
	List<Token> tokens; 
	public Scanner(String source) {
		tokens = new ArrayList<Token>();
		tokens.add(new Token(TokenType.BANG, "!", null, 1));
		tokens.add(new Token(TokenType.DOT, ".", null, 1));
		tokens.add(new Token(TokenType.BANG, "!", null, 1));
	}

	public List<Token> scanTokens() {
		// TODO Auto-generated method stub
		return tokens;
	}

}
