package kr.or.ddit.vo;

public class DbVO {
	private String propertyName;
	private String propertyValue;
	private String description;
	
	public DbVO() {
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DbVO [propertyName=" + propertyName + ", propertyValue=" + propertyValue + ", Description="
				+ description + "]";
	}
	
	
	
	
	
}
