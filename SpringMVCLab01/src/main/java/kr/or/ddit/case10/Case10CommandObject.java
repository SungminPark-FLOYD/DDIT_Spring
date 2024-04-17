package kr.or.ddit.case10;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
public class Case10CommandObject implements Serializable{
	private String[] uploader;
	private int count;
	
	@JsonIgnore
	@ToString.Exclude
	private transient MultipartFile[] uploadFile;
	
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = uploadFile;
		this.voList = new ArrayList<UploadFileVO>();
		
		for(MultipartFile single: uploadFile) {
			if(single.isEmpty()) continue;
			voList.add(new UploadFileVO(single));
		}
	}
	
	private List<UploadFileVO> voList;
}
