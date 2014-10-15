package com.github.tempest200903.example103;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ProductATest {

	private AbstractA createAbstractA() {
		return createStubInjector().getInstance(StubA.class);
	}

	private Injector createStubInjector() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(AbstractB.class).to(StubB.class);
				bind(AbstractBFactory.class).to(StubBFactory.class);
				bind(AbstractCFactory.class).to(StubCFactory.class);
			}
		});
		return injector;
	}

	/**
	 * テスト成功。
	 */
	@Test
	public void testStab1() {
		AbstractA abstractA = createAbstractA();
		System.out.println("abstractA.getClass() =: " + abstractA.getClass());
		AbstractB abstractB = abstractA.getAbstractB();
		System.out.println("abstractB.getClass() =: " + abstractB.getClass());
		AbstractC abstractC = abstractB.getAbstractC();
		System.out.println("abstractC.getClass() =: " + abstractC.getClass());
		AbstractD abstractD = abstractC.getAbstractD();
		System.out.println("abstractD.getClass() =: " + abstractD.getClass());
		abstractD.add(3);
		abstractA.add(3);
	}

}
