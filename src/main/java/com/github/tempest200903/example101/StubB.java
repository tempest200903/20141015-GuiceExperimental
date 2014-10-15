package com.github.tempest200903.example101;

class StubB implements AbstractB {

	AbstractC c = new StubC();

	public int add(int v) {
		return 3;
	}

	public AbstractC getAbstractC() {
		return c;
	}

}
