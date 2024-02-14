package com.gamergeo.project.videomanager.service.parse;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ParseConstant {
	
	public final static String EXPECTED_START = "<!DOCTYPE NETSCAPE-Bookmark-file-1>\n"
			+ "<!-- This is an automatically generated file.\n"
			+ "     It will be read and overwritten.\n"
			+ "     DO NOT EDIT! -->\n"
			+ "<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">\n"
			+ "<TITLE>Bookmarks</TITLE>\n"
			+ "<H1>Bookmarks</H1>";
	
	public final static Path FILE_DIRECTORY = Paths.get(System.getProperty("user.dir"), "parse");

	public final static String RAW_SUFFIX = "-raw";

	public final static String XML_EXTENSION = ".xml";
	
	public final static String FOLDER_TAG = "H3";
	
	public final static String FOLDER_CONTENT_TAG = "DL";
	
	public final static String VIDEO_TAG = "A";

	public final static String LINK_ATTRIBUTE = "HREF";
	
	public final static String DATE_ATTRIBUTE = "ADD_DATE";
}
