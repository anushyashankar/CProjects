import java.util.*;

public class MathSet
{
	private mySet one;
	private mySet two;

	public MathSet()
	{
		one = new mySet();
		two = new mySet();
	}

	public MathSet(String o, String t)
	{
		String[] thing1 = o.split(" ");
		String[] thing2 = t.split(" ");

		ArrayList<String> on = new ArrayList<String>();
		ArrayList<String> tw = new ArrayList<String>();

		for (int i = 0; i < thing1.length; i++) {
			on.add(thing1[i]);
		}

		for (int i = 0; i < thing1.length; i++) {
			tw.add(thing2[i]);
		}

		one = new mySet(on);
		two = new mySet(tw);
	}

	public mySet union()
	{
		mySet combine = new mySet();

		for (int i = 0; i < one.size(); i++) {
			combine.add(one.getList().get(i));
		}

		for (int i = 0; i < two.size(); i++) {
			combine.add(two.getList().get(i));
		}

		return combine;
	}

	public mySet intersection()
	{
		mySet combine = new mySet();

		for (int i = 0; i < one.size(); i++) {
			for (int j = 0; j < two.size(); j++) {
				if (one.getList().get(i).equals(two.getList().get(j))) {
					combine.add(one.getList().get(i));
				}
			}
		}

		return combine;
	}

	public mySet differenceAMinusB()
	{
		mySet uniques = new mySet();

		for (int i = 0; i < one.size(); i++) {
			int count = 0;
			for (int j = 0; j < two.size(); j++) {
				if (one.getList().get(i).equals(two.getList().get(j))) {
					count++;
				}
			}
			if (count == 0) {
				uniques.add(one.getList().get(i));
			}
		}

		return uniques;
	}

	public mySet differenceBMinusA()
	{

		mySet uniques = new mySet();

		for (int i = 0; i < two.size(); i++) {
			int count = 0;
			for (int j = 0; j < one.size(); j++) {
				if (two.getList().get(i).equals(one.getList().get(j))) {
					count++;
				}
			}
			if (count == 0) {
				uniques.add(two.getList().get(i));
			}
		}

		return uniques;
	}

	public mySet symmetricDifference()
	{		
		mySet once = this.differenceAMinusB();
		mySet twice = this.differenceBMinusA();
		
		mySet combine = new mySet();
		
		for (int i = 0; i < once.size(); i++) {
			combine.add(once.getList().get(i));
		}

		for (int i = 0; i < twice.size(); i++) {
			combine.add(twice.getList().get(i));
		}
		
		return null;
	}	

	public String toString()
	{
		return "Set one " + one + "\n" +	"Set two " + two +  "\n";
	}
}