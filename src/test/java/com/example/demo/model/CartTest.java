package com.example.demo.model;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.example.demo.entity.ExtendedItem;
import com.example.demo.entity.Item;

class CartTest {

	/** テスト対象クラス：system under test */
	private Cart sut; 
	
	@BeforeEach
	void setUp() throws Exception {
		sut = new Cart();
	}

	@Nested
	@DisplayName("Cart#getTotalPrice(List<Item>メソッドのテストクラス")
	class GetTotalPriceTest {
		@ParameterizedTest
		@MethodSource("provideTestParams")
		void testGetTotalPrice(List<ExtendedItem> targets, Integer expected) {
			// setup
			for (Item target : targets) {
				sut.add(target);
			}
			// execute
			Integer actual = sut.getTotalPrice();
			// verify
			assertThat(actual, is(expected));
		}
		
		private static Stream<Arguments> provideTestParams() {
			// setup
			List<ExtendedItem> targetList = null;
			List<List<ExtendedItem>> targets = new ArrayList<>();
			List<Integer> expected = new ArrayList<>();
			
			// Test-21 カートに商品が入っていないときの商品金額の総合計は0である
			targetList = new ArrayList<>();
			targets.add(targetList);
			expected.add(0);
			
			// Test-22  カートに商品が入っているときの商品の金額の総合計を計算する
			targetList = new ArrayList<>();
			targetList.add(new ExtendedItem(1, 1, "Javaの基本本", 2500, 1));
			targetList.add(new ExtendedItem(5, 2, "Th Racer", 1000, 1));
			targetList.add(new ExtendedItem(4, 2, "なつかしいアニメシリーズ", 2000, 1));
			targetList.add(new ExtendedItem(1, 1, "Javaの基本本", 2500, 1));
			targets.add(targetList);
			expected.add(8000);
			
			// テスト用パラメータを返却
			return Stream.of(
					  Arguments.of(targets.get(0), expected.get(0))
					, Arguments.of(targets.get(1), expected.get(1))
					);
		}
	}
	
	@Nested
	@DisplayName("Cart#add(Item)メソッドのテストクラス")
	class AddTest {
		@ParameterizedTest
		@MethodSource("provideTestParams")
		@DisplayName("Test-11：カートに商品を追加できる")
		void testAdd(List<ExtendedItem> targetList, List<ExtendedItem> expectedList) {
			// execute
			for (Item target : targetList) {
				sut.add(target);
			}
			//  verify
			List<Item> actualList = sut.getItems();
			if (actualList.size() > 0) {
				for (int i = 0; i < expectedList.size(); i++) {
					String actual =  actualList.get(i).toString();
					String expected = expectedList.get(i).toString();
					assertThat(actual, is(expected));
				}
			} else {
				fail("まだ実装されていません");
			}
		}
		
		private static Stream<Arguments> provideTestParams() {
			// setup
			List<ExtendedItem> targetList = null;
			List<List<ExtendedItem>> targets = new ArrayList<>();
			List<ExtendedItem> expectedList = null;
			List<List<ExtendedItem>> expected = new ArrayList<>();
			
			// Test-11 カート内に商品がない状態亜で商品を追加できる
			targetList = new ArrayList<>();
			targetList.add(new ExtendedItem(1, 1, "Javaの基本", 2500));
			targets.add(targetList);
			expectedList = new ArrayList<>();
			expectedList.add(new ExtendedItem(1, 1, "Javaの基本", 2500));
			expected.add(expectedList);
			
			// Test-12 カート内に商品がある場合新しい商品を追加できる
			targetList = new ArrayList<>();
			targetList.add(new ExtendedItem(1, 1, "Javaの基本", 2500));
			targetList.add(new ExtendedItem(5, 2, "The Racer", 1000));
			targetList.add(new ExtendedItem(4, 2, "なつかしのアニメシリーズ", 2000));
			targets.add(targetList);
			expectedList = new ArrayList<>();
			expectedList.add(new ExtendedItem(1, 1, "Javaの基本", 2500));
			expectedList.add(new ExtendedItem(5, 2, "The Racer", 1000));
			expectedList.add(new ExtendedItem(4, 2, "なつかしのアニメシリーズ", 2000));
			expected.add(expectedList);
			
			// Test-13 カート内に商品がある場合すでにある商品をさらに追加することができる
			targetList = new ArrayList<>();
			targetList.add(new ExtendedItem(1, 1, "Javaの基本", 2500, 1));
			targetList.add(new ExtendedItem(5, 2, "The Racer", 1000, 1));
			targetList.add(new ExtendedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			targetList.add(new ExtendedItem(1, 1, "Javaの基本", 2500, 1));
			targets.add(targetList);
			expectedList = new ArrayList<>();
			expectedList.add(new ExtendedItem(1, 1, "Javaの基本", 2500, 2));
			expectedList.add(new ExtendedItem(5, 2, "The Racer", 1000, 1));
			expectedList.add(new ExtendedItem(4, 2, "なつかしのアニメシリーズ", 2000, 1));
			expected.add(expectedList);
			
			
			// テスト用パラメータの返却
			return Stream.of(
					  Arguments.of(targets.get(0), expected.get(0))
					, Arguments.of(targets.get(1), expected.get(1))
					, Arguments.of(targets.get(2), expected.get(2))
					);
		}
	}
	
	@Nested
	class InstanceTest {
		@Test
		@DisplayName("Test01：インスタンス化できる")
		void test01() {
			assertThat(sut, is(instanceOf(Cart.class)));
			assertThat(sut.getItems(), is(instanceOf(ArrayList.class)));
		}
	}

}
