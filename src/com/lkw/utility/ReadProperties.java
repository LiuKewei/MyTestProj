package com.lkw.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

public class ReadProperties implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9083662427669691243L;

	private static class ReadPropertiesHolder {
		/**
		 * 
		 */
		static final ReadProperties INSTANCE = new ReadProperties();
	}

	public static ReadProperties getInstance() {
		return ReadPropertiesHolder.INSTANCE;
	}

	private ReadProperties() {
	}

	private Object readResolve() {
		return getInstance();
	}

	public Properties getProperty(String config) {
		String class_path = Thread.currentThread().getContextClassLoader()
				.getResource("").getPath();
		if (class_path.endsWith("classes/")) {
			class_path = class_path.substring(1, class_path.length()
					- "classes/".length());
		}
		Properties p = null;
		try {
			InputStream inputStream = null;
			inputStream = new FileInputStream(class_path + config);
			p = new Properties();
			p.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
}
