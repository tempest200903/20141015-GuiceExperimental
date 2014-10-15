package com.github.tempest200903.example102;

class StubB extends ProductB {

	@Override
	AbstractC createAbstractC() {
		return new StubC();
	}

}
