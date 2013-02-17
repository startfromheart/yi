/**
 * 
 */
package com.nianway.core;

import java.util.Enumeration;
import java.util.Properties;

import junit.framework.TestCase;

/**
 * @author zhizhang.zhou
 * 
 */
public class HelperTest extends TestCase {

	public void testGetProperty() {

		Properties properties = System.getProperties();

		Enumeration<Object> propertiesEnumeration = properties.keys();

		while (propertiesEnumeration.hasMoreElements()) {
			Object key = propertiesEnumeration.nextElement();
			Object value = properties.get(key);

			System.out.println();
			System.out.print("\t");
			System.out.print("key:" + key);
			System.out.print("\t");
			System.out.print("value:" + value);
		}

	}

}
