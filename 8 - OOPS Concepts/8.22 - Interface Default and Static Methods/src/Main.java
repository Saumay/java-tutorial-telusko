/*
From Java 8, we can have default methods in interface.
	Reason why default methods were added (Full Story):
		- Stream API was added in Java 8
		- But to enable that, few methods have to be added to interfaces like List interface
		- If new methods were declared in that, there were 2 problems doing that:
			1) Existing code would have broken for all the previous versions, which is not at all acceptable.
			2) Implementation has to be done in all the classes that were implementing that interface.
		- In order to solve this problem, concept of default methods was introduced, where you can also define methods in an interface
		- So, all the new methods that had to be added because of Stream API can now be directly defined in the interface itself and can be directly used by developers.
		
		- But... this created another problem, of multiple inheritance of interfaces
		
		- Multiple inheritance was supported for Interfaces because we can define the same method that was present in two different interfaces because that method was only declared there.
		- But now, we can have definitions also in an interface, and this is now the same case why multiple inheritance was restricted for classes(diamond problem).
		- To solve this, the disputed method needs to be overriden by implementing class in case of such a conflict.

Default methods features:
	- No need to override default methods. We can directly call it using interface reference.
	- If default method is overrided, overrided method will be called.
	- In case of multiple inheritance, and if two or more parent interfaces contains same methods, you would have to override those methods.
	- You can't create a default method in an interface that's overriding a method of Object class

Static methods can now also be written in interface in Java 8
*/
public class Main {
	public static void main(String[] args) {
		Abc abc = new AbcImpl();
		abc.show();				// will call Abc interface show

		AbcImpl abc2 = new AbcImpl();
		abc2.show();			// will call Abc interface show

		Child child = new Child();
		child.show();			// will call Parent class show
		/* Third Rules in Java:
			- show is present in Parent, Abc and Xyz.
			- But, it will call Parent's show because in Java, class is stronger than interface. And for extending class, there is no multiple inheritance. So, ambiguity isn't there.
		*/

		Abc.printStatic();		// will call Abc interface printStatic
	}
}


interface Abc {
	default void show() {
		System.out.println("In Abc interface Show");
	}
	
	static void printStatic() {
		System.out.println("Inside Abc interface printStatic");
	}
}


class AbcImpl implements Abc {
	/*											// No need to override default method.
	public void show() {
		System.out.println("In implemented class Show");
	}
	*/
}


interface Xyz {
	default void show() {
		System.out.println("In Xyz interface Show");
	}
}


class Parent {
	public void show() {
		System.out.println("In Parent class Show");
	}
}


class Child extends Parent implements Abc, Xyz {
}

/*
interface Error
{
	default boolean equals(Object o) {		// error: default method equals in interface Error overrides a member of java.lang.Object
	}
}*/