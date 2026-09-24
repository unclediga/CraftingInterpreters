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
			scanToken();
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
			addToken(LEFT_BRACE);
			break;
		case '}':
			addToken(RIGHT_BRACE);
			break;
		case '(':
			addToken(LEFT_PAREN);
			break;
		case ')':
			addToken(RIGHT_PAREN);
			break;
		case ',':
			addToken(COMMA);
			break;
		case '.':
			addToken(DOT);
			break;
		case '-':
			addToken(MINUS);
			break;
		case '+':
			addToken(PLUS);
			break;
		case ';':
			addToken(SEMICOLON);
			break;
		case '*':
			addToken(STAR);
			break;
		case '!':
			addToken(match('=') ? BANG_EQUAL : BANG);
			break;
		case '=':
			addToken(match('=') ? EQUAL_EQUAL : EQUAL);
			break;
		case '>':
			addToken(match('=') ? GREATER_EQUAL : GREATER);
			break;
		case '<':
			addToken(match('=') ? LESS_EQUAL : LESS);
			break;
		case '/':
			if (match('/'))
				while (peek() != '\n' && !isAtEnd())
					advance();
			else
				addToken(SLASH);
			break;
		case ' ':
		case '\t':
		case '\r':
			break;
		case '\n':
			line++;
			break;
		case '"':
			string();
			break;
		default:
			if (isDigit(c))
				number();
			else
				Lox.error(line, "Unexpected character.");
		}
		return null;
	}

	private void number() {
		advance();
		while (!isAtEnd()) {
			if (peek() == '.' && isDigit(peeknext()))
				advance();
			else if (isDigit(peek())) {
				advance();
			} else
				break;
		}
		addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
	}

	private char peeknext() {
		if (source.length() <= current)
			return '\0';
		return source.charAt(current + 1);
	}

	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

	private void string() {
		while (peek() != '"' && !isAtEnd())
			advance();
		if (!isAtEnd()) {
			advance();
			addToken(STRING, source.substring(start + 1, current - 1));
		} else
			Lox.error(line, "Unclosed string");
	}

	private char peek() {
		if (isAtEnd())
			return '\0';
		return source.charAt(current);
	}

	private boolean match(char next) {

		char c = advance();
		if (c == next)
			return true;
		current--;
		return false;
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
