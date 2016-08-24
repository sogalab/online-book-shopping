package com.fanyongliang.book.dao.prop;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import com.fanyongliang.book.dao.core.Text;
/**
 * 处理properties属性文件的工具类
 */
public class PropertiesParser {

	private Properties props = null;

	/**
	 * 在构造函数中将properties值赋值给为props变量
	 * 
	 * @param properties
	 *            属性
	 */
	public PropertiesParser(Properties properties) {
		this.props = properties;
	}

	/**
	 * 在构造函数中通过文件路径读取属性文件并将内容赋值给props变量
	 * 
	 * @param propPath
	 *            属性文件路径
	 */
	public PropertiesParser(String... propPaths) {

		props = new Properties();

		if (propPaths == null || propPaths.length == 0) {
			return;
		}

		InputStream inputStream = null;

		for (String propPath : propPaths) {
			
			if (propPath == null || propPath.equals("")) {
				continue;
			}
			
			// 如果是绝对路径，通过FileInputStream输入流读取文件
			if (propPath.contains("/") || propPath.contains("\\")) {
				try {
					inputStream = new FileInputStream(propPath);
				} catch (FileNotFoundException e) {
					continue;
				}
			} else {
				inputStream = PropertiesParser.class.getClassLoader()
						.getResourceAsStream(propPath);
			}
			
			if (inputStream == null) {
				continue;
			}
			
			try {
				props.load(inputStream);
				inputStream.close();
			} catch (IOException e) {
				continue;
			}
		}

		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 得到属性文件对应的Properties类
	 * 
	 * @日期：2014年11月26日
	 * @Title: getUnderlyingProperties
	 * @return Properties
	 */
	public Properties getProperties() {
		return props;
	}

	/**
	 * 通过属性名properName得到对应的属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getStringProperty
	 * @param properName
	 *            属性名
	 * @return String 属性名properName对应的属性值
	 */
	public String getStringProperty(String properName) {
		return getStringProperty(properName, null);
	}

	/**
	 * 
	 * 通过属性名properName得到对应的属性值；如果属性值不存在，默认为用户输入defaultValue
	 * 
	 * @日期：2014年11月26日
	 * @Title: getStringProperty
	 * @param properName
	 *            属性名
	 * @param defaultValue
	 *            默认值
	 * @return String 属性名properName对应的属性值
	 */
	public String getStringProperty(String properName, String defaultValue) {
		String properValue = props.getProperty(properName, defaultValue);
		// 如果属性值为空或属性值为空格，返回默认值
		return Text.isNullOrWhitespace(properValue) ? defaultValue
				: properValue;
	}

	/**
	 * 得到boolean属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getBooleanProperty
	 * @param properName
	 *            属性名
	 * @return boolean True表示存在，False表示不存在
	 */
	public boolean getBooleanProperty(String properName) {
		return getBooleanProperty(properName, false);
	}

	/**
	 * 得到boolean属性值，并将其转化成bool类型，属性值不存在时返回默认值defaultBoolean
	 * 
	 * @日期：2014年11月26日
	 * @Title: getBooleanProperty
	 * @param properName
	 * @param defaultBoolean
	 * @return boolean
	 */
	public boolean getBooleanProperty(String properName, boolean defaultBoolean) {
		String properValue = getStringProperty(properName);
		// 该属性住不存在时，返回默认的bool值
		return (properValue == null) ? defaultBoolean : Boolean.valueOf(
				properValue).booleanValue();
	}

	/**
	 * 
	 * 通过属性名获取多个属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getStringArrayProperty
	 * @param propertName
	 *            属性名
	 * @return String[] 属性值数组
	 */
	public String[] getStringArrayProperty(String propertName) {
		return getStringArrayProperty(propertName, new String[0]);
	}

	/**
	 * 
	 * 通过属性名获取多个属性值；如果属性值不存在返回默认属性值数组
	 * 
	 * @日期：2014年11月26日
	 * @Title: getStringArrayProperty
	 * @param propertName
	 *            属性名
	 * @param defaultValueArr
	 *            默认属性值数组
	 * @return String[] 属性值数组
	 */
	public String[] getStringArrayProperty(String propertName,
			String[] defaultValueArr) {
		String properValues = getStringProperty(propertName);
		// 属性值不存在时，返回默认属性值数组
		if (properValues == null) {
			return defaultValueArr;
		}
		// 得到属性值字符串；多个属性值使用,分开，解析属性值字符串得到属性值数组
		StringTokenizer stok = new StringTokenizer(properValues, ",");
		ArrayList<String> properValueArr = new ArrayList<String>();
		try {
			while (stok.hasMoreTokens()) {
				properValueArr.add(stok.nextToken().trim());
			}
			return properValueArr.toArray(new String[properValueArr.size()]);
		} catch (Exception e) {
			return defaultValueArr;
		}
	}

	/**
	 * 
	 * 得到byte型属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getByteProperty
	 * @param properName
	 *            属性名
	 * @return 属性名对应的属性字节
	 * @throws NumberFormatException
	 *             byte
	 */
	public byte getByteProperty(String properName) {
		String propertValue = getStringProperty(properName);
		if (propertValue == null) {
			throw new RuntimeException(new NumberFormatException(properName
					+ " is null."));
		}
		try {
			return Byte.parseByte(propertValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 得到byte型属性值;如果属性值不存在，返回默认数值defaultByte
	 * 
	 * @日期：2014年11月26日
	 * @Title: getByteProperty
	 * @param properName
	 *            属性名
	 * @param defaultByte
	 *            属性名对应的字节
	 * @return
	 * @throws NumberFormatException
	 *             byte
	 */
	public byte getByteProperty(String properName, byte defaultByte)
			throws NumberFormatException {
		String properValue = getStringProperty(properName);
		// 属性值不存在时，返回默认字节
		if (properValue == null) {
			return defaultByte;
		}
		try {
			return Byte.parseByte(properValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 得到Char型属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getCharProperty
	 * @param properName
	 *            属性名
	 * @return char 属性值第一个字符
	 */
	public char getCharProperty(String properName) {
		return getCharProperty(properName, '\0');
	}

	/**
	 * 
	 * 得到byte型属性值;如果属性值不存在，返回默认字符defaultChar
	 * 
	 * @日期：2014年11月26日
	 * @Title: getCharProperty
	 * @param properName
	 *            属性名
	 * @param defaultChar
	 *            属性值默认字符
	 * @return char 属性值第一个字符
	 */
	public char getCharProperty(String properName, char defaultChar) {
		String properValue = getStringProperty(properName);
		return (properValue == null) ? defaultChar : properValue.charAt(0);
	}

	/**
	 * 
	 * 得到double型属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getDoubleProperty
	 * @param properName
	 * @return
	 * @throws NumberFormatException
	 *             double
	 */
	public double getDoubleProperty(String properName) {
		String properValue = getStringProperty(properName);
		// 属性值不存在时，抛出异常
		if (properValue == null) {
			throw new RuntimeException(new NumberFormatException(properName
					+ " is null."));
		}
		try {
			return Double.parseDouble(properValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 得到double型属性值;如果属性值不存在，返回默认数值defaultDouble
	 * 
	 * @日期：2014年11月26日
	 * @Title: getDoubleProperty
	 * @param properName
	 *            属性名
	 * @param defaultDouble
	 *            默认属性Double值
	 * @return
	 * @throws NumberFormatException
	 *             double
	 */
	public double getDoubleProperty(String properName, double defaultDouble) {
		String properValue = getStringProperty(properName);
		// 属性值不存在时，返回默认Double值
		if (properValue == null) {
			return defaultDouble;
		}
		try {
			// 将属性值转化成Double类型
			return Double.parseDouble(properValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 得到float型属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getFloatProperty
	 * @param properName
	 *            属性名
	 * @return 属性名对应的float值
	 * @throws NumberFormatException
	 *             float
	 */
	public float getFloatProperty(String properName) {
		String properValue = getStringProperty(properName);
		// 属性值不存在时，抛出异常
		if (properValue == null) {
			throw new RuntimeException(new NumberFormatException(properName
					+ " is null."));
		}
		try {
			return Float.parseFloat(properValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 得到float型属性值;如果属性值不存在，返回默认数值defaultFloat
	 * 
	 * @日期：2014年11月26日
	 * @Title: getFloatProperty
	 * @param properName
	 *            属性名
	 * @param defaultFloat
	 *            默认Float类型的属性值
	 * @return
	 * @throws NumberFormatException
	 *             float
	 */
	public float getFloatProperty(String properName, float defaultFloat) {
		String properValue = getStringProperty(properName);
		// 属性值不存在时，返回默认Float值
		if (properValue == null) {
			return defaultFloat;
		}
		try {
			return Float.parseFloat(properValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 得到int型属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getIntProperty
	 * @param properName
	 *            属性名
	 * @return 属性值对应的Int值
	 * @throws NumberFormatException
	 *             int
	 */
	public int getIntProperty(String properName) {
		String properValue = getStringProperty(properName);
		// 属性值不存在时，抛出异常
		if (properValue == null) {
			throw new RuntimeException(new NumberFormatException(properName
					+ " is null."));
		}
		try {
			return Integer.parseInt(properValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 得到int型属性值;如果属性值不存在，返回默认数值defaultInt
	 * 
	 * @日期：2014年11月26日
	 * @Title: getIntProperty
	 * @param properName
	 *            属性名
	 * @param defaultInt
	 *            默认Int值
	 * @return
	 * @throws NumberFormatException
	 *             int
	 */
	public int getIntProperty(String properName, int defaultInt)
			throws NumberFormatException {
		String properValue = getStringProperty(properName);
		// 属性值不存在时，返回默认int值
		if (properValue == null) {
			return defaultInt;
		}
		try {
			return Integer.parseInt(properValue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(new NumberFormatException(properName
					+ " is null."));
		}
	}

	/**
	 * 
	 * 根据属性名得到多个整型int属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getIntArrayProperty
	 * @param properName
	 *            属性名
	 * @return
	 * @throws NumberFormatException
	 *             int[]
	 */
	public int[] getIntArrayProperty(String properName) {
		return getIntArrayProperty(properName, new int[0]);
	}

	/**
	 * 
	 * 根据属性名得到多个整型int属性值；如果属性名不存在返回默认整型数组
	 * 
	 * @日期：2014年11月26日
	 * @Title: getIntArrayProperty
	 * @param properName
	 *            属性名
	 * @param defaultIntArr
	 *            默认整型数组
	 * @return
	 * @throws NumberFormatException
	 *             int[]
	 */
	public int[] getIntArrayProperty(String properName, int[] defaultIntArr) {
		String propervalues = getStringProperty(properName);
		// 属性值不存在时，返回默认整型数组defaultIntArr
		if (propervalues == null) {
			return defaultIntArr;
		}
		// 得到的属性值以，分割开得到字符串数组；然后将字符数组转化成整型数组
		StringTokenizer stok = new StringTokenizer(propervalues, ",");
		ArrayList<Integer> intArr = new ArrayList<Integer>();
		try {
			while (stok.hasMoreTokens()) {
				try {
					intArr.add(new Integer(stok.nextToken().trim()));
				} catch (NumberFormatException nfe) {
					throw new RuntimeException(nfe);
				}
			}
			int[] outInts = new int[intArr.size()];
			for (int i = 0; i < intArr.size(); i++) {
				outInts[i] = (intArr.get(i)).intValue();
			}
			return outInts;
		} catch (Exception e) {
			return defaultIntArr;
		}
	}

	/**
	 * 
	 * 得到long型属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getLongProperty
	 * @param properName
	 *            属性名
	 * @return
	 * @throws NumberFormatException
	 *             long
	 */
	public long getLongProperty(String properName) {
		String propervalue = getStringProperty(properName);
		// 属性值不存在时，抛出异常
		if (propervalue == null) {
			throw new RuntimeException(new NumberFormatException(properName
					+ " is null."));
		}
		try {
			return Long.parseLong(propervalue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 得到long型属性值;如果属性值不存在返回默认long类型值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getLongProperty
	 * @param properName
	 *            属性名
	 * @param defaultLong
	 *            默认long型属性值
	 * @return
	 * @throws NumberFormatException
	 *             long
	 */
	public long getLongProperty(String properName, long defaultLong) {
		String propervalue = getStringProperty(properName);
		// 属性值不存在返回默认属性值defaultLong
		if (propervalue == null) {
			return defaultLong;
		}
		try {
			return Long.parseLong(propervalue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 得到short型属性值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getShortProperty
	 * @param properName
	 *            属性名
	 * @return
	 * @throws NumberFormatException
	 *             short
	 */
	public short getShortProperty(String properName) {
		String propervalue = getStringProperty(properName);
		// 属性值不存在时，抛出异常
		if (propervalue == null) {
			throw new RuntimeException(new NumberFormatException(properName
					+ " is null."));
		}
		try {
			return Short.parseShort(propervalue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 得到short型属性值;如果属性值不存在返回默认short类型值
	 * 
	 * @日期：2014年11月26日
	 * @Title: getShortProperty
	 * @param properName
	 *            属性名
	 * @param defaultShort
	 *            默认short类型属性值
	 * @return
	 * @throws NumberFormatException
	 *             short
	 */
	public short getShortProperty(String properName, short defaultShort) {
		String propervalue = getStringProperty(properName);
		// 属性值不存在时，返回默认short属性值
		if (propervalue == null) {
			return defaultShort;
		}
		try {
			return Short.parseShort(propervalue);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException(nfe);
		}
	}

	/**
	 * 
	 * 以prefix开头，去掉属性名的前缀为prefix的属性名数组
	 * 
	 * @日期：2014年11月26日
	 * @Title: getPropertyGroups
	 * @param prefix
	 *            属性名前缀
	 * @return String[] 以prefix开头，去掉属性名的前缀为prefix的属性名数组
	 */
	public String[] getPropertyGroups(String prefix) {
		// 得到所有属性名
		Enumeration<?> keys = props.propertyNames();
		HashSet<String> groups = new HashSet<String>(10);
		// 前缀不是以。结束时,在前缀后加上.
		if (!prefix.endsWith(".")) {
			prefix += ".";
		}
		// 遍历所有属性
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			// 如果属性名不是以prefix值为前缀，则在属性名前加上前缀
			if (key.startsWith(prefix)) {
				String groupName = key.substring(prefix.length());
				groups.add(groupName);
			}
		}
		return groups.toArray(new String[groups.size()]);
	}

	/**
	 * 
	 * 得到所有前缀以prefix开始的属性
	 * 
	 * @日期：2014年11月26日
	 * @Title: getPropertyGroup
	 * @param prefix
	 *            前缀
	 * @return Properties
	 */
	public Properties getPropertyGroup(String prefix) {
		return getPropertyGroup(prefix, false, null);
	}

	/**
	 * 
	 * 得到所有以【属性名以prefix为前缀，根据stripPrefix值判断是否删除前缀】的属性
	 * 
	 * @日期：2014年11月26日
	 * @Title: getPropertyGroup
	 * @param prefix
	 *            属性名前缀
	 * @param stripPrefix
	 *            是否删除属性名的前缀prefix值
	 * @return Properties
	 */
	public Properties getPropertyGroup(String prefix, boolean stripPrefix) {
		return getPropertyGroup(prefix, stripPrefix, null);
	}

	/**
	 * 
	 * 得到所有
	 * 【属性名以prefix为前缀，判断是否符合excludedPrefixes数组中的前缀；没有则根据stripPrefix值判断是否删除前缀】的属性
	 * 
	 * @日期：2014年11月26日
	 * @Title: getPropertyGroup
	 * @param prefix
	 *            属性名前缀
	 * @param stripPrefix
	 *            是否删除属性名的前缀prefix值
	 * @param excludedPrefixes
	 *            排除的前缀数组
	 * @return Properties
	 */
	public Properties getPropertyGroup(String prefix, boolean stripPrefix,
			String[] excludedPrefixes) {
		Properties group = new Properties();

		// 前缀不是以。结束时,在前缀后加上.
		if (!prefix.endsWith(".")) {
			prefix += ".";
		}

		Enumeration<?> keys = props.propertyNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			// 属性名以prefix值为前缀
			if (key.startsWith(prefix)) {
				boolean exclude = false;
				// 判断属性名是否以excludedPrefixes数组中的字符串为前缀
				if (excludedPrefixes != null) {
					for (int i = 0; (i < excludedPrefixes.length)
							&& (exclude == false); i++) {
						exclude = key.startsWith(excludedPrefixes[i]);
					}
				}
				// 属性名没有以excludedPrefixes数组中的字符串为前缀
				if (exclude == false) {
					String value = getStringProperty(key, "");
					// 是否去掉属性名前缀
					if (stripPrefix) {
						// 去掉属性名前缀
						group.put(key.substring(prefix.length()), value);
					} else {
						group.put(key, value);
					}
				}
			}
		}

		return group;
	}

}
