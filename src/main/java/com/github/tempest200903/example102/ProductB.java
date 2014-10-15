package com.github.tempest200903.example102;

class ProductB implements AbstractB {

	private int i = 0;

	private AbstractC c = createAbstractC();

	public int add(int v) {
		i += v;
		// add が改修中で動作しないのでスタブで置き換えたい。
		if (i > 0) {
			throw new RuntimeException("Not Implemented Yet");
		}
		return i;
	}

	AbstractC createAbstractC() {
		return new ProductC();
	}

	public AbstractC getAbstractC() {
		return c;
	}

}
