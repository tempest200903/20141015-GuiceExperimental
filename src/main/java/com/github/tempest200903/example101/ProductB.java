package com.github.tempest200903.example101;

class ProductB implements AbstractB{
	
	private int i = 0;

	private ProductC c = new ProductC();
	
	public int add(int v) {
		i += v;
		// add が改修中で動作しないのでスタブで置き換えたい。
		if (i > 0) {
			throw new RuntimeException("Not Implemented Yet");
		}
		return i;
	}

	public AbstractC getAbstractC() {
		return c;
	}

}
