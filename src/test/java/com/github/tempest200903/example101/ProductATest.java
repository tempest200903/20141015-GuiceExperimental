package com.github.tempest200903.example101;

import org.junit.Test;

import com.github.tempest200903.example101.AbstractB;
import com.github.tempest200903.example101.AbstractC;
import com.github.tempest200903.example101.AbstractD;
import com.github.tempest200903.example101.ProductA;
import com.github.tempest200903.example101.ProductB;
import com.github.tempest200903.example101.StubB;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static org.junit.Assert.assertEquals;

public class ProductATest {

	private Injector createProductInjector() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(AbstractB.class).to(ProductB.class);
				// bind(AbstractC.class).to(ProductC.class);
				// bind(AbstractD.class).to(ProductD.class);
			}
		});
		return injector;
	}

	private Injector createStubInjector() {
		Injector injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(AbstractB.class).to(StubB.class);
				// bind(AbstractC.class).to(StubC.class);
				// bind(AbstractD.class).to(StubD.class);
			}
			// inject するのは最初の B だけでいい。
			// StubB から StubC を new するとこころは StubB に直接書けばいいので
			// わざわざ　inject する必要はない。
		});
		return injector;
	}

	/**
	 * テスト失敗。 Product* が全て完成したら成功。
	 */
	@Test
	public void testProduct() {
		Injector injector = createProductInjector();
		ProductA a = injector.getInstance(ProductA.class);
		// a.b に injection していないので、 b.add() が失敗する。
		int bi = a.getB().add(3);
		assertEquals(3, bi);
	}

	/**
	 * テスト成功。
	 */
	@Test
	public void testStab() {
		Injector injector = createStubInjector();
		ProductA a = injector.getInstance(ProductA.class);
		// a.b に injection しているので、 b.add() が失敗しない。
		AbstractB abstractB = a.getB();
		int bi = abstractB.add(3);
		assertEquals(3, bi);

		// StubD を injection しているので d.add() が失敗しない。
		System.out.println("abstractB.class is " + abstractB.getClass());
		AbstractC abstractC = abstractB.getAbstractC();
		System.out.println("abstractC.class is " + abstractC.getClass());
		AbstractD abstractD = abstractC.getAbstractD();
		int di = abstractD.add(3);
		assertEquals(3, di);
	}

	/**
	 * テスト失敗。 Product* が全て完成したら成功。
	 */
	@Test
	public void testWithoutInjection() {
		ProductA a = new ProductA();
		// a.b が null なので、 java.lang.NullPointerException 発生する。
		int bi = a.getB().add(3);
		assertEquals(3, bi);
	}

}
