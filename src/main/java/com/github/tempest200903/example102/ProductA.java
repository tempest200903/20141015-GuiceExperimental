package com.github.tempest200903.example102;

class ProductA implements AbstractA {

	private int i = 0;

	private AbstractB b = createAbstractB();

	public int add(int v) {
		return getAbstractB().add(v);
	}

	AbstractB createAbstractB() {
		return new ProductB();
	}

	public AbstractB getAbstractB() {
		return b;
	}

	AbstractB getB() {
		return b;
	}

}
