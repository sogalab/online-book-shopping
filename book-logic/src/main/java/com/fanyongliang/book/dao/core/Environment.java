package com.fanyongliang.book.dao.core;


public class Environment {

	public static OS getOS() {
		String os = System.getProperty("os.name").toLowerCase();
		if (OS.isUnix(os)) {
			return OS.Unix;
		}
		if (OS.isWindows(os)) {
			return OS.Windows;
		}
		if (OS.isMac(os)) {
			return OS.Mac;
		}

		throw new UnsupportedOperationException("Unsupported OS name: " + os);
	}

	public enum OS {
		Windows, Unix, Mac;

		static boolean isWindows(String os) {
			return (os.indexOf("win") >= 0);
		}

		static boolean isMac(String os) {
			return (os.indexOf("mac") >= 0);
		}

		static boolean isUnix(String os) {
			return (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os
					.indexOf("aix") > 0);
		}

		static boolean isSolaris(String os) {
			return (os.indexOf("sunos") >= 0);
		}
	}
}
