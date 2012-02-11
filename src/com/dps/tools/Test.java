package com.dps.tools;

import java.util.Comparator;

import org.nutz.dao.Dao;

public class Test {

	Dao dao;

	public void printObjectType() {

	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		// Integer tmp = Integer.valueOf("20120210113812828");
		// System.out.println(tmp);

		// System.out.println(Integer.MAX_VALUE);
		// System.out.println("20120210113812828".length());

	}
}

class comp implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		return 0;
	}

}