package com.github.tempest200903.example101;

import javax.inject.Inject;

class StubC implements AbstractC {

	@Inject
	AbstractD d = new StubD();

	public AbstractD getAbstractD() {
		return d;
	}

}
