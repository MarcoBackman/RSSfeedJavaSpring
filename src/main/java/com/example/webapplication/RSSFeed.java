package com.example.webapplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RSSFeed {

	private static final String FILENAME = "./src/events.rss";
	private static String content;
	
	public RSSFeed() {
		Path fileName = Path.of(FILENAME);
		try {
			content = Files.readString(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getContent() {
		if (content == null) {
			System.out.println("Content null");
			return "";
		}
		System.out.println("Returning Content");
		return content;
	}
}
