package kr.or.ddit.exception;

import java.sql.SQLException;

public class PersistenceException extends RuntimeException{
	private SQLException adaptee;

	public PersistenceException(SQLException adaptee) {
		super();
		this.adaptee = adaptee;
	}
	
	public Throwable getCause() {
		return adaptee;
	}
}
