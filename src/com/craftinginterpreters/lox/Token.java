package com.craftinginterpreters.lox;

class Token {
	TokenType type;
	String lexeme;
	Object literal;
	int line;
	
	public Token(TokenType type, String lexeme, Object literal, int line) {
		super();
		this.type = type;
		this.lexeme = lexeme;
		this.literal = literal;
		this.line = line;
	}

	@Override
	public String toString() {
		return "Token [type=" + type + ", lexeme=" + lexeme + ", literal=" + literal + "]";
	}
	
	
	

}
