package com.github.tempest200903.example102;

class StubC extends ProductC {

	@Override
	AbstractD createAbstractD() {
		return new StubD();
	}

}
