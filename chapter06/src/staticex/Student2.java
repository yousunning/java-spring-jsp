package staticex;

public class Student2 {
	// student 에서 생성자를 학번 자동 부여하기
	// serialNum 변수 정보은닉

	// 정적변수 = 스태틱 변수 = 클래스 변수
	public static int serialNum = 1000;
	// 인스턴스 변수 *new
	int studentID;
	String studentName;
	int gread;
	String address;

	// 생성자

	public Student2() {
		serialNum++;
		studentID = serialNum;
	}

	public String getStudentName() {
		return studentName;

	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	// 스태틱한 시리얼시작 *미리 만들어야 하는애 , 정적메모리!!
	public static int getSerialNum() {
		int i = 10;
		// studentName = "kim"; 안됨.
		return serialNum;
	}

	public static void setStudentNum(int serialNum) {
		Student.serialNum = serialNum;
	}
}
