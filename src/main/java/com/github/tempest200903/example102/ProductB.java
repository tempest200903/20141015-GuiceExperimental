package com.github.tempest200903.example102;

class ProductB implements AbstractB {

	private int i = 0;

	private AbstractC c = createAbstractC();

	public int add(int v) {
		return getAbstractC().add(v);
	}

	AbstractC createAbstractC() {
		return new ProductC();
	}

	public AbstractC getAbstractC() {
		return c;
	}

}
