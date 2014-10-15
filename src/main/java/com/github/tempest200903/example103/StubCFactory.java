package com.github.tempest200903.example103;

class StubCFactory implements AbstractCFactory {

	public AbstractC createAbstractC() {
		return new StubC();
	}

}
