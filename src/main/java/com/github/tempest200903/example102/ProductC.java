package com.github.tempest200903.example102;

class ProductC implements AbstractC {

	private AbstractD d = createAbstractD();

	ProductC() {
	}

	public int add(int v) {
		return getAbstractD().add(v);
	}

	AbstractD createAbstractD() {
		return new ProductD();
	}

	public AbstractD getAbstractD() {
		return d;
	}

}
