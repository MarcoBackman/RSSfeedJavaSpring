package com.example.webapplication.component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RSSFeed {

	Logger logger = LogManager.getLogger();

	private final String WEB_RSS_URL = "https://rss.com/podcasts";

	/**
	 * Reads the local file and sends out into http response
	 * Target file must be smaller than 1GB
	 * @param urlName name of the url path without root url
	 * @return stream of each string line
	 */
	public String getFromWebRss(Marker mk, String urlName, boolean isFullUrl) throws IOException {
		String content;
		String urlPath;
		if (isFullUrl) {
			urlPath = urlName;
		} else {
			urlPath = String.format("%s/%s/", WEB_RSS_URL, urlName);
		}

		InputStreamReader inputStream = null;
		try {
			URL url = new URL(urlPath);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			inputStream = new InputStreamReader(con.getInputStream());
			BufferedReader reader = new BufferedReader(inputStream);
			content = reader.lines().collect(Collectors.toList()).toString();
			System.out.println(content);
		} catch (IOException err) {
			logger.warn(mk, "Failed to get file on fileName={}", urlName, err);
			return "";
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return content;
	}

	/**
	 * Reads the local file and sends out into http response
	 * Target file must be smaller than 1GB
	 * @param fileName name of the file without extension
	 * @return stream of each string line
	 */
	public String readFromFile(Marker mk, String fileName) {
		String content;
		try {
			File file = new File(fileName);
			content = Files.readString(file.toPath());
		} catch (IOException err) {
			logger.warn(mk, "Failed to get file on fileName={}", fileName, err);
			return "";
		}
		return content;
	}

	/**
	 * Reads the local file and sends out into http response
	 * This is used for large files more than 1GB
	 * @param fileName name of the file without extension
	 * @return stream of each string line
	 */
	public Stream<String> getFileContentStream(Marker mk, String fileName) {
		File file = new File(fileName);
		try (Stream<String> contentStream = Files.lines(file.toPath())) {
			return contentStream;
		} catch (IOException err) {
			logger.warn(mk, "Failed to get file on fileName={}", fileName, err);
			return Stream.empty();
		}
	}
}
