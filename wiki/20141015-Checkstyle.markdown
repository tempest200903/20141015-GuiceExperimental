= 20141015-Guice
[2014-10-15 20:59] >>> f:/goat-pc-data/mydropbox/Dropbox/trunksync/notes/20141008-Checkstyle.markdown
 #+LAST_UPDATED: 2014-10-15

# 20141015-Guice #

## 目的 ##

- [2014-10-15 水 15:38] 要件
  - プロダクトコード class A, B, C, D がある。
  - A が内部で B を new する。
  - B が内部で C を new する。
  - C が内部で D を new する。
  - ATest を書いている。 D が改修中で動作しないのでスタブで置き換えたい。
  - inner class で実現することは可能だが、 new B, new C, new D するメソッ
    ドを全て override することになり、かなり面倒。
  - Guice を使えば、 new D する箇所だけをスタブに交換すればよい。

## 参考情報

- http://kenmaz.hatenadiary.jp/entry/20100320/1269098801
  - Guiceを使ってみた - kenmazの日記
- http://d.hatena.ne.jp/nodchip/20130126/1359161946
  - 心地良すぎるDependency Injectionライブラリ Guice - nodchipの日記
- http://qiita.com/opengl-8080/items/6fb69cd2493e149cac3a
  - Java - Google Guice 使い方メモ - Qiita
- http://www.ibm.com/developerworks/jp/java/library/j-guice.html
  - Guice による依存性注入

## Eclipse プロジェクト ##

- mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DgroupId=com.github.tempest200903 -DartifactId=20141015-GuiceExperimental -DinteractiveMode=false
- F:\goat-pc-data\ecworkspace\20141015-Guice
- git clone git@github.com:tempest200903/20141015-GuiceExperimental.git

## 考察 ##

- Stub テスト実行時は ProductA, StubB, StubC, StubD で実行したいなら、 package example101 のとおりでよい。
- 実際にやりたいのは  ProductA, ProductB, ProductC, StubD 。これはどうやって実現するのか？
- どこでも自由自在に inject できるわけではない。基本的には Injector.getInstance() で生成したインスタンスを経由する必要がある。
- package example102 は inject を一切使わないで実現する方法。
    - StubA から StubB を new する。
    - StubB から StubC を new する。
    - StubC から StubD を new する。
    - StubD で add() を override する。これをテストに用いる。
- スタブの種類がそれぞれ1個だけで増減しないならこれで十分。
- スタブの種類が増減するならこれでは不十分。
- StubA.createAbstractB() { return new StubB(); } ここが固定なのが問題。
- ここの new StubB() を inject すれば、 StubB の種類を増やせる。
- 同様に、 StubB.createAbstractC() { return new StubC(); } のところを inject すれば StubC の種類を増やせる。
- StubB の injector に StubC の injector を事前に渡しておく必要がある。
- StubD1, StubD2 2種類を使い分けたい。 StubC, StubC, StubA の種類は増やさずに、 StubD の種類を増やしたい。
- example102.StubC.createAbstractD() に new StubD1() と書いていたが、このままでは StubD の種類を増やせない。
- example103.StubC.createAbstractD() では inject した AbstractDFactory に委譲する。
- StubC.abstractDFactory に inject するためには、 StubC を new の代わりに Injector.getInstance() で生成する。
- example102.StubB.createAbstractC() では new StubC していた。
- example103.StubB.createAbstractC() では Injector.getInstance() で StubC を生成する。
- example102.StubA.createAbstractB() では new StubB していた。
- example103.StubA.createAbstractB() では Injector.getInstance() で StubB を生成する。
- example103.ProductATest.createAbstractA() では new StubA する代わりに inject する。
- なお、 inject するより先に inject 対象フィールドを読み書きしてはいけない。すると NullPointerException 発生。
- 失敗。

    ```
    > java.lang.NullPointerException
    > 	at com.github.tempest200903.example103.StubB.createAbstractC(StubB.java:12)
    > 	at com.github.tempest200903.example103.ProductB.getAbstractC(ProductB.java:14)
    > 	at com.github.tempest200903.example103.ProductATest.testStab1(ProductATest.java:36)
    ```

- StubB を生成したのは誰か？ -> StubBFactory
- StubBFactory は StubB を new している。 injector.getInstance していない。
- StubBFactory が injector.getInstance するにはどうすればいいのか？
  StubBFactory に直接 injector を書いてよいのなら、それで解決。
  問題は、 ProductATest から StubBFactory を制御することができないこと。なぜなら、 StubBFactory はこうなっている。

    ```
    > private Injector createStubInjector() {
    > 		Injector injector = Guice.createInjector(new AbstractModule() {
    > 			@Override
    > 			protected void configure() {
    > 				bind(AbstractB.class).to(StubB.class);
    > 				bind(AbstractBFactory.class).to(StubBFactory.class);
    > 				bind(AbstractCFactory.class).to(StubCFactory.class);
    > 			}
    > 		});
    > 		return injector;
    > 	}
    ```
- 結局、 StubD の種類を増やすためには StubC, StubB の種類を増やすしかないのか？
- StubA までなら inject できるが、 StubB を inject する方法が分からない。


## wiki ##

- copy F:\goat-pc-data\mydropbox\Dropbox\trunksync\notes\20141015-Checkstyle.markdown F:\goat-pc-data\ecworkspace\20141015-GuiceExperimental\wiki\20141015-Checkstyle.markdown
- https://github.com/tempest200903/20141015-GuiceExperimental/blob/master/wiki/20141015-Checkstyle.markdown
