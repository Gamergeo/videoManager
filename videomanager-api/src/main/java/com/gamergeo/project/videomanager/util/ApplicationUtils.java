package com.gamergeo.project.videomanager.util;

import java.util.List;
import java.util.stream.StreamSupport;

public class ApplicationUtils {

	public static <T> List<T> toList(final Iterable<T> iterable) {
	    return StreamSupport.stream(iterable.spliterator(), false)
	                        .toList();
	}
}
