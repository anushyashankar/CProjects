
import java.util.*;
import static java.lang.System.*;

public class RestaurantRunner
{
	public static void main ( String[] args )
	{
		ListNode z = new ListNode("cheese",
	 			new ListNode("pepperoni",
      		new ListNode("onions",
       		new ListNode("peppers",
	 			new ListNode("bacon",
      		new ListNode("pineapple",
       		new ListNode("sausage",		       		
	 			new ListNode("garlic",null))))))));
			 			
		out.println("Worksheet LL#2 Runner\n\n");	
		
		Restaurant one = new Restaurant(z);
		
		out.println("Original list values\n");	
		out.print("\t");
		one.print();
		out.println("\n");
		
		out.println("\t num nodes = " + one.nodeCount());
		
//		Restaurant.skipEveryOther(z);
//		out.println("\nList values after calling skipEveryOther\n");	
//		out.print("\t");
//		one.print(z);
//		out.println();	
		
		out.println("\nList values after calling nodeCount\n");	
		out.print("\t");
		one.print();
		out.println();		

		one.doubleFirst();		
		out.println("\nList values after calling doubleFirst\n");							
		out.print("\t");
		one.print();
		out.println();	

		one.doubleLast();		
		out.println("\nList values after calling doubleLast\n");							
		out.print("\t");
		one.print();
		out.println();				
		
		
		one.removeXthNode(2);		
		out.println("\nList values after calling removeXthNode(2)\n");					
		out.print("\t");
		one.print();
		out.println();			
		
		
		one.setXthNode(2, "PIZZA!");		
		out.print("\t");
		out.println("\nList values after calling setXthNode(2,PIZZA!)\n");										
		out.print("\t");
		one.print();
		out.println();				
	}
}