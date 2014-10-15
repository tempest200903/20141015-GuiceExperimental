package com.github.tempest200903.example102;

class ProductA implements AbstractA {

	private int i = 0;

	private AbstractB b = createAbstractB();

	int add(int v) {
		i += v;
		// add が改修中で動作しないのでスタブで置き換えたい。
		if (i > 0) {
			throw new RuntimeException("Not Implemented Yet");
		}
		return i;
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
