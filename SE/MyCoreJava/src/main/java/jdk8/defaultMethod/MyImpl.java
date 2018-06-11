package jdk8.defaultMethod;

public class MyImpl implements InterfaceOne, InterfaceTwo {

	@Override
	public String getName() {
		return InterfaceOne.super.getName();
	}

	// Error without overriding one method: Duplicate default methods named getName
	// with the parameters () and () are inherited from the types InterfaceTwo and
	// InterfaceOne.

}
