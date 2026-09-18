package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {

	static boolean hadError = false;

	public static void main(String[] args) throws IOException {
		if (args.length > 1) {
			System.out.println("Usage: jlox [script]");
			System.exit(64);
		} else if (args.length == 1) {
			runFile(args[0]);
		} else {
			runPrompt();
		}
	}

	private static void runFile(String fileName) throws IOException {
		byte[] allBytes = Files.readAllBytes(Paths.get(fileName));
		run(new String(allBytes, Charset.defaultCharset()));

	}

	private static void runPrompt() throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		for (;;) {
			System.out.print("> ");
			String line = input.readLine();
			if (line.equals("")) {
				break;
			}
			run(line);
		}
		System.out.println("Goodbye!");
	}

	private static void run(String source) {
		System.out.println(source);
		Scanner scanner = new Scanner(source);
		List<Token> tokens = scanner.scanTokens();
		for (Token token : tokens)
			System.out.println(token);
	}

	static void error(int line, String message) {
		report(line, "", message);
	}

	private static void report(int line, String where, String message) {
		System.err.println("[line " + line + "] Error" + where + ": " + message);
		hadError = true;
	}
}