package com.github.tempest200903.example103;

import javax.inject.Inject;

class StubB extends ProductB {

	@Inject
	AbstractCFactory abstractCFactory;

	@Override
	AbstractC createAbstractC() {
		return abstractCFactory.createAbstractC();
	}

}
