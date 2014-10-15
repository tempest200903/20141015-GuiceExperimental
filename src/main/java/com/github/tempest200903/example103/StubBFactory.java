package com.github.tempest200903.example103;

class StubBFactory implements AbstractBFactory {

	public AbstractB createAbstractB() {
		return new StubB();
	}

}
