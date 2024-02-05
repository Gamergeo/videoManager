package com.gamergeo.project.videomanager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VideoManagerException extends RuntimeException {

	private static final long serialVersionUID = -8326742143429383201L;
	
    public VideoManagerException(String message) {
        super(message);
    	log.error(message);
    }

    public VideoManagerException(String message, Throwable cause) {
        super(message, cause);
    	log.error(message);
    }
}
