package com.gamergeo.project.videomanager.service.parse;

public class ParseResult {
	
	private int parsed;
	
	private int imported;
	
	public void incrementParsed() {
		parsed++;
	}
	
	public void incrementImported() {
		imported++;
	}
	
	public int getNotImported() {
		return parsed - imported;
	}

	public int getImported() {
		return imported;
	}

	public void setImported(int imported) {
		this.imported = imported;
	}

	public int getParsed() {
		return parsed;
	}

	public void setParsed(int parsed) {
		this.parsed = parsed;
	}
	
}
