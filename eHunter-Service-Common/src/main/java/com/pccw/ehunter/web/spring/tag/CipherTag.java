package com.pccw.ehunter.web.spring.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


/**
 * Tag to cipher data without using Spring MVC.
 * This is
 * <p>
 * Being "hdiv" the identifier used to reference HDIV tag library, the format of the
 * tag will be as follows:<br />
 * <code>&lt;hdiv:cipher action="a1" parameter="p1" value="v1" /&gt;</code>.
 * </p>
 * <p>
 * The result will be an encoded value that only HDIV can interpretate.
 * </p>
 * 
 * @author Beck Lu
 * @since HDIV 2.0.3
 */
public class CipherTag extends TagSupport {

	/**
	 * Universal version identifier. Deserialization uses this number to ensure that
	 * a loaded class corresponds exactly to a serialized object.
	 */
	private static final long serialVersionUID = -5223047708438702403L;

	/**
	 * Sets the action <code>action</code> defined in the tag.
	 * 
	 * @param action Action
	 */
	public void setAction(String action) {
		this.setValue("action", action);
	}

	/**
	 * Sets the parameter <code>parameter</code> defined in the tag.
	 * 
	 * @param parameter Parameter
	 */
	public void setParameter(String parameter) {
		this.setValue("parameter", parameter);
	}

	/**
	 * Sets the value <code>value</code> defined in the tag.
	 * 
	 * @param value Value
	 */
	public void setValue(String value) {
		this.setValue("value", value);
	}

	/**
	 * Process the start of this tag.
	 * 
	 * @throws JspException If the attributes passed to the tag are incorrect, an
	 *             exception will be thrown.
	 * @see javax.servlet.jsp.tagext.TagSupport.doStartTag#int ()
	 */
	public int doStartTag() throws JspException {

		String value = (String) this.getValue("value");

		String cipheredValue = value;


		try {
			JspWriter out = this.pageContext.getOut();
			out.print(cipheredValue);
		} catch (IOException e) {
			throw new JspException("Error:" + e.getMessage());
		}

		return SKIP_BODY;
	}

}
