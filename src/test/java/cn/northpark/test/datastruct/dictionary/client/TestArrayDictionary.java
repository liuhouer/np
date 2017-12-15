package cn.northpark.test.datastruct.dictionary.client;

import cn.northpark.test.datastruct.dictionary.ArrayDictionary;

public class TestArrayDictionary {
	public static void main(String[] args) {
		ArrayDictionary<Integer, String> arrayDict = new ArrayDictionary<Integer, String>();
		arrayDict.add(1, "1");
		arrayDict.add(2, "2");
	}
}
