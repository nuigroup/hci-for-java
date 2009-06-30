
public class Test {
	private String name;
	
	public Test(String name) {
		this.name = name;
	}
	
	public void print() {
		System.out.println(name);
	}
	
	public class Inner {
		private String msg;
		public Inner(String msg) {
			this.msg = msg;
		}
		public void output() {
			System.out.println(Test.this);
			System.out.println(name);
			Test.this.print();
			print();
			System.out.println(this);
			System.out.println(msg);
		}
	}
	
	public static void main(String[] args) {
		Test t = new Test("Bob");
		Inner i = t.new Inner("hello");
		
		t.print();
		i.output();
	}
}
