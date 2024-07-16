package com.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

	public int STATUS_CODE_200= 200;
	public int STATUS_CODE_201= 201;
	public Properties prop;

	public Base() {
		prop = new Properties();
		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/api/config/config.properties");
			try {
				prop.load(fs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
