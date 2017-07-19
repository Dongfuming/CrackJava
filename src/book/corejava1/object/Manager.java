package book.corejava1.object;

/**
 * @author Dongfuming
 * @date 2016-7-3 下午6:36:55
 */
public class Manager extends Employee {
	
	private Double bonus;

	public Manager(String name, Double salary, Double bonus) {
		super(name, salary);
		this.bonus = bonus;
	}
	
	public Double getBonus() {
		return bonus;
	}
	
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}
		Manager other = (Manager)obj;
		return this.bonus == other.bonus;
	}
	
	@Override
	public int hashCode() {
		return super.hashCode() + 17 * new Double(this.bonus).hashCode();
	}

	@Override
	public String toString() {
		return "Manager [bonus=" + bonus + ", name=" + getName() + ", salary="
				+ getSalary() + "]";
	}
	
	@Override
	public int compareTo(Employee other) {
		if (this.getClass() != other.getClass()) {
			throw new ClassCastException("两个比较的对象不一样");
		}
		Manager otherManager = (Manager)other;
		double thisSalary = this.getSalary() + this.bonus; 
		
		return Double.compare(thisSalary, otherManager.getSalary());
	}
}
