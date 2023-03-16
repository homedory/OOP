package lab03;

public class Staff {
	private String name;
	private int age;
	private String department;
	private int workDays;
	private int vacationDays;
	
	Staff(String name, int age) {
		this.name = name;
		this.age = age;
		this.department = "None";
		this.workDays = 0;
		this.vacationDays = 20;
	}
	
	Staff(String name, int age, String department, int workDays, int vaacationDays) {
		this.name = name;
		this.age = age;
		this.department = department;
		this.workDays = workDays;
		this.vacationDays = vaacationDays;
	}
	
	public boolean sameCareer(Staff anotherStaff) {
		return ((this.department.equals(anotherStaff.department)) && (this.workDays == anotherStaff.workDays));
	}
	
	public void setCareer(String department, int workDays) {
		this.department = department;
		this.workDays = workDays;
	}
	
	public String toString() {
		return "Name: " + this.name + ", Age: " + this.age + ", Department: " + this.department + ", workDays: " + this.workDays + ", VacationDays: " + this.vacationDays;
	}
	
	public void vacation(int days) {
		if(days > vacationDays) {
			System.out.println(name + ",남은 휴가 일 수 부족.");
		}
		else {
			vacationDays -= days;
			System.out.println(name + ", 휴가 " + days + " 사용. 남은 휴가 일 수: " + vacationDays);
		}
	}
	
	public String getName() {
		return name;
	}
}
