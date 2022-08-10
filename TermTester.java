public class TermTester {
	public static void main(String[] args) {
		Term p = new Term(3,5);
		Term q = new Term (2,5);
		Term r = new Term (4,6);
		System.out.println (p.add(q));
		System.out.println (q.multiply(r));
	}
}