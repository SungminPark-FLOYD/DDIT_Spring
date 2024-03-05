package kr.or.ddit.vo;

/**
 * Domain Layer : raw data 구조를 반영하여 모델링함
 * MemberData.properties 파일의 저장된 사용자의 정보 로딩에 필요한 객체
 * model : id, name, gender, age, address
 *
 */
public class PersonVo {

	public PersonVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PersonVo(String id, String name, String gender, String age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.address = address;
	}


	private String id;
	private String name;
	private String gender;
	private String age;
	private String address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "PersonVo [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", address=" + address
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersonVo other = (PersonVo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
}
