package kr.or.ddit.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SampleVO {
	private String id;
	private String name;
	private String role;
	
}
