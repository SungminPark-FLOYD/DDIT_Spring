package kr.or.ddit.servlet09;

import java.io.File;
import java.io.Serializable;

public class FileWrapper implements Serializable,Comparable<FileWrapper>{
	private File adaptee;
	private String path;

	public FileWrapper() {
		
	}
	public FileWrapper(File adaptee, String path) {
		super();
		this.adaptee = adaptee;
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getName() {
		return adaptee.getName();
	}
	
	public boolean isFile() {
		return adaptee.isFile();
	}
	
	public boolean isFolder() {
		return adaptee.isDirectory();
	}
	
	public Long getSize() {
		return adaptee.length();
	}

	@Override
	public int compareTo(FileWrapper o) {
		if(isFile() ^ o.isFile()) {
			return isFolder() ? -1 : 1;
		}else {
			return getName().compareToIgnoreCase(o.getName());
		}
		
	}
	

}
