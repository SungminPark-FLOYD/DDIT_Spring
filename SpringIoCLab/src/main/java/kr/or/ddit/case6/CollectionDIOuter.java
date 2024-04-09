package kr.or.ddit.case6;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import lombok.Data;

@Data
public class CollectionDIOuter {
	private Object[] array;
	
	private List<String> list;
	
	private Set<Object> set;
	
	private Map<String, Object> map;
	
	private Properties props;
	
}
