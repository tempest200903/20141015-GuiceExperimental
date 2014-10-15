package com.github.tempest200903.example101;

class ProductD  implements AbstractD{

	private int i = 0;

	public int add(int v) {
		i += v;
		// add が改修中で動作しないのでスタブで置き換えたい。
		if (i > 0) {
			throw new RuntimeException("Not Implemented Yet");
		}
		return i;
	}

}
