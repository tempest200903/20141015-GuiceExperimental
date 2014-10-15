package com.github.tempest200903.example101;

import javax.inject.Inject;

class ProductA {

	private int i = 0;

	/**
	 * 外部から変更できない private フィールドでも inject できる。
	 */
	@Inject
	private AbstractB b;

	int add(int v) {
		i += v;
		// add が改修中で動作しないのでスタブで置き換えたい。
		if (i > 0) {
			throw new RuntimeException("Not Implemented Yet");
		}
		return i;
	}

	AbstractB getB() {
		return b;
	}

}
