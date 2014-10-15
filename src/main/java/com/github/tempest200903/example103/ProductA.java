package com.github.tempest200903.example103;

class ProductA implements AbstractA {

	public int add(int v) {
		return getAbstractB().add(v);
	}

	AbstractB createAbstractB() {
		return new ProductB();
	}

	public AbstractB getAbstractB() {
		AbstractB b = createAbstractB();
		return b;
	}

}
