
import java.util.ArrayList;
import static java.lang.System.*;

public class Heap
{
	private ArrayList<Integer> list;

	public Heap()
	{
		list = new ArrayList<Integer>();
	}

	public void add(int value)
	{
		list.add(value);
		swapUp(list.size()-1);
	}

	public void swapUp(int bot)
	{
		//steps from video:
		// starts access from bottom
		// checks if bottom hasn't gone past root (index 1)
		// find bottom's parent (bot/2) and check if bottom is larger than its parent's
		//   if it is, swap bottom and parent
		// set bot to parent and start again

		while (bot > 0) { //not really sure about this (if it's equal to the root)
			//			System.out.println("hello");
			//			endless loop
			int botParent = bot/2;

			if (list.get(botParent) > list.get(bot)) {
				swap(list.get(bot), list.get(botParent));
				botParent = bot;
			}

			else
				return;
		}
	}

	public void remove( )
	{
		list.set(0,list.get(list.size()-1));
		list.remove(list.size()-1);
		swapDown(list.size());
	}

	public void swapDown(int top)
	{
		int root=0;

		while(root<list.size()) {
			int left = root * 2 + 1;
			int right = root * 2 + 2;

			int max = 0;

			if (left != 1) {
				if (right != 1)
					max = Math.max(left, right);
				else
					max = left;
			}

			if (max > root)
				swap (max, root);
				root = max;
			if (max <= root)
				System.out.println("cannot be swapped");
		}
	}

	private void swap(int start, int finish)
	{	
		int temp = start;
		start = finish;
		finish = temp;
	}

	public void print()
	{
		out.println("\n\nPRINTING THE HEAP!\n\n");
		out.println(toString());
	}

	public String toString()
	{
		return list.toString();
	}
}