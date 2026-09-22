package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;
import static com.craftinginterpreters.lox.TokenType.*;

class Scanner {
	private final String source;
	private final List<Token> tokens = new ArrayList<Token>();
	private int start = 0;
	private int current = 0;
	private int line = 1;

	public Scanner(String source) {
		this.source = source;
	}

	public List<Token> scanTokens() {
		while (!isAtEnd()) {
			start = current;
			Token token = scanToken();
		}
		addToken(EOF);
		return tokens;
	}

	private char advance() {
		return source.charAt(current++);
	}

	private Token scanToken() {
		char c = advance();
		switch (c) {
		case '{':
			addToken(TokenType.LEFT_BRACE);
			break;
		case '}':
			addToken(TokenType.RIGHT_BRACE);
			break;
		case '(':
			addToken(TokenType.LEFT_PAREN);
			break;
		case ')':
			addToken(TokenType.RIGHT_PAREN);
			break;
		case ',':
			addToken(TokenType.COMMA);
			break;
		case '.':
			addToken(TokenType.DOT);
			break;
		case '-':
			addToken(TokenType.MINUS);
			break;
		case '+':
			addToken(TokenType.PLUS);
			break;
		case ';':
			addToken(TokenType.SEMICOLON);
			break;
		case '*':
			addToken(TokenType.STAR);
			break;
		default:
			break;
		}
		return null;
	}

	private void addToken(TokenType token) {
		addToken(token, null);

	}

	private void addToken(TokenType token, Object literal) {
		String text = source.substring(start, current);
		tokens.add(new Token(token, text, literal, line));
	}

	private boolean isAtEnd() {
		return current >= source.length();
	}

}
