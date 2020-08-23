package honeybubblenailshop;


public class MemberDTO {
	private int no;
	private String name;
	private String phone;
	private String type;
	private String memo;
	
	//기본 생성자 ----------------------------------------------------------------------------------------------------
	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	//필드 초기값 부여 생성자  ----------------------------------------------------------------------------------------------------
	public MemberDTO(int no, String name, String phone, String type, String memo) {
		super();
		this.no = no;
		this.name = name;
		this.phone = phone;
		this.type = type;
		this.memo = memo;
	}

	//Getter & Setter 
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	
}