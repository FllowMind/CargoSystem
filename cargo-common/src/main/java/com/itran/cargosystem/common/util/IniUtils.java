package com.itran.cargosystem.common.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.ini4j.Config;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

public class IniUtils {
	
	public static Ini getIni(String path) {		
		Ini ini = new Ini();
		try {
			InputStream is = IniUtils.class.getClassLoader().getResourceAsStream(path);
			BufferedReader fr = new BufferedReader(new InputStreamReader(is));
			Config cfg = new Config();
			cfg.setMultiSection(true);
			ini.setConfig(cfg);
			ini.load(fr);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件没找到");
		} catch (InvalidFileFormatException e) {
			throw new RuntimeException("文件格式错误");
		} catch (IOException e) {
			throw new RuntimeException("文件读写错误");
		}
		return ini;
	}
	
	public static void main(String[] args) {
		getIni("/dispatch.ini");
	}
}
