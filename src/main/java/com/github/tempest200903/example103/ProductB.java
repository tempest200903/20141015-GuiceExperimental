package com.github.tempest200903.example103;

class ProductB implements AbstractB {

	public int add(int v) {
		return getAbstractC().add(v);
	}

	AbstractC createAbstractC() {
		return new ProductC();
	}

	public AbstractC getAbstractC() {
		AbstractC c = createAbstractC();
		return c;
	}

}
