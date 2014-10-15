package com.github.tempest200903.example103;

import javax.inject.Inject;

class StubC extends ProductC {

	@Inject
	AbstractDFactory abstractDFactory;

	@Override
	AbstractD createAbstractD() {
		return abstractDFactory.createAbstractD();
	}

}
