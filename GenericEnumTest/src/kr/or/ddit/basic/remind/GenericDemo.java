package kr.or.ddit.basic.remind;

interface Info{
	int getLevel();
}

class EmployeeInfo implements Info{
    public int rank;
    EmployeeInfo(int rank){ this.rank = rank; }
    public int getLevel() {
    	return this.rank;
    }
}

class Person<T extends Info>{//extends Info를 사용하지 않는 경우 <T extends Object>와 같다.
    public T info;
    Person(T info){
    		this.info = info;
    		info.getLevel();
    }
    
    public <U> void printInfo(U info) {
    	System.out.println(info);
    }
}

class MyArray<T> {
	T element;
	void setElement(T element) { this.element = element; }
	T getElement() { return element; }
}

public class GenericDemo {
    public static void main(String[] args) {
    	Person<EmployeeInfo> p1 = new Person<EmployeeInfo>(new EmployeeInfo(1));
    	MyArray<Integer> myArr = new MyArray<Integer>();
    }
}


