package kr.or.ddit.exception;

import java.io.IOException;
import java.io.UncheckedIOException;

public class FileCopyFailureException extends UncheckedIOException{

	public FileCopyFailureException(IOException cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public FileCopyFailureException(String message, IOException cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
