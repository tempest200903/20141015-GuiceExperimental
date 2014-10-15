package com.github.tempest200903.example103;

import javax.inject.Inject;

class StubA extends ProductA {

	@Inject
	AbstractBFactory abstractBFactory;

	@Override
	AbstractB createAbstractB() {
		return abstractBFactory.createAbstractB();
	}

}
