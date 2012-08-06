package com.pccw.ehunter.helper;

public abstract class AbstractIDGenerator implements IDGenerator {
	private String prefix;
	private String key;
	private int padding;

	public AbstractIDGenerator(String prefix, String key) {
		super();
		this.prefix = prefix;
		this.key = key;
	}

	public AbstractIDGenerator(String prefix, String key, int padding) {
		super();
		this.prefix = prefix;
		this.key = key;
		this.padding = padding;
	}

}
