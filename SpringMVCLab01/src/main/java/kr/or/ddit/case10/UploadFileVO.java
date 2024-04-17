package kr.or.ddit.case10;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UploadFileVO {
	private final MultipartFile file;
	public UploadFileVO(MultipartFile file) {
		this.file = file;
		this.fileName = file.getOriginalFilename();
		this.fileSize = file.getSize();
		this.saveName = UUID.randomUUID().toString();
	}
	private String fileName;
	private String saveName;
	private long fileSize;
	
	public UploadFileVO(String fileName, String saveName, long fileSize) {
		this(null, fileName, saveName, fileSize);
		
	}
	
	
}
