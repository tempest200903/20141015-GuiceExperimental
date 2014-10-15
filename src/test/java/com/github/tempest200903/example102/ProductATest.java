package com.github.tempest200903.example102;

import org.junit.Test;

public class ProductATest {

	/**
	 * テスト成功。
	 */
	@Test
	public void testStab() {
		AbstractA abstractA = new StubA();
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
