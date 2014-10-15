package com.github.tempest200903.example102;

class StubA extends ProductA {

	@Override
	AbstractB createAbstractB() {
		return new StubB();
	}

}
