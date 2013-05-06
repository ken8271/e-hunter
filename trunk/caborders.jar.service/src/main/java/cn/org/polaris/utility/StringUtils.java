package cn.org.polaris.utility;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static final String EMPTY_STRING = "";

	public static final String COMMA = ",";
	
	public static final String DOT = ".";
	
	public static final String SPACE = " ";

	public static String toString(Object obj) {
		if (null == obj) {
			return EMPTY_STRING;
		} else {
			return obj.toString();
		}
	}

	public static boolean isEmpty(String s) {
		return (s == null || (s.trim()).equals(EMPTY_STRING));
	}

	public static boolean isEmpty(String[] s) {
		return (s == null || s.length == 0);
	}

	public static String trim(String s) {
		if (s == null) {
			return EMPTY_STRING;
		} else {
			return s.trim();
		}
	}

	public static String[] tokenize(String str, String delim) {
		String[] strs = null;
		StringTokenizer tokens = null;

		if (null != str) {
			if (null != delim) {
				tokens = new StringTokenizer(str, delim);
			} else {
				tokens = new StringTokenizer(str);
			}

			strs = new String[tokens.countTokens()];

			for (int i = 0; i < strs.length && tokens.hasMoreTokens(); i++) {
				strs[i] = tokens.nextToken();
			}
		}
		return strs;
	}

	public static boolean isAllDigit(String s) {
		if (s == null || s.equals(EMPTY_STRING)) {
			return false;
		} else {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if (!Character.isDigit(c)) {
					return false;
				}
			}
			return true;
		}
	}

	public static boolean isAllAlphabet(String s) {

		if (s == null || s.equals(EMPTY_STRING)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^[a-zA-Z]*");

		Matcher matcher = pattern.matcher(s);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isAllAlphanumeric(String s) {

		if (s == null || s.equals(EMPTY_STRING)) {
			return false;
		}

		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]*");

		Matcher matcher = pattern.matcher(s);

		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isMatched(String input, String regex) {
		if (isEmpty(input)) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		return m.find();
	}

	public static String addLeadingZero(String s, int padding) {
		return addLeadingCharacter(s, '0', padding);
	}

	public static String addLeadingCharacter(String s, char c, int padding) {
		if (s != null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < padding; i++) {
				sb.append(c);
			}
			sb.append(s);
			return sb.toString();
		} else {
			return null;
		}
	}

	public static String concat(String[] tokens, String delim) {
		return concat(tokens, EMPTY_STRING, EMPTY_STRING, delim);
	}

	public static String concat(String[] tokens, String tokenPrefix,
			String tokenSuffix, String delim) {
		StringBuffer s = new StringBuffer();
		if (tokens != null) {
			if (tokenPrefix == null) {
				tokenPrefix = EMPTY_STRING;
			}
			if (tokenSuffix == null) {
				tokenSuffix = EMPTY_STRING;
			}
			if (delim == null) {
				delim = EMPTY_STRING;
			}
			for (int i = 0; i < tokens.length; i++) {
				s.append(tokenPrefix).append(tokens[i]).append(tokenSuffix);
				if (i + 1 < tokens.length) {
					s.append(delim);
				}
			}
		}
		return s.toString();
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
