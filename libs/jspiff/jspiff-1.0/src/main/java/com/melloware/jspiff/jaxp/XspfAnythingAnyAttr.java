/**
 * JSpiff
 * -----------------
 * Copyright (c) 2005-2006 Emil A. Lefkof III
 *
 * I always give it my best shot to make a program useful and solid, but
 * remeber that there is absolutely no warranty for using this program as
 * stated in the following terms:
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.melloware.jspiff.jaxp;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * <b>XspfAnythingAnyAttr</b> is generated from xspf.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <attribute>
 *                     <anyName/>
 *                 </attribute>-->
 * <!-- for javadoc -->
 * <pre> &lt;attribute&gt;
 *                     &lt;anyName/&gt;
 *                 &lt;/attribute&gt;</pre>
 *
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class XspfAnythingAnyAttr
    implements java.io.Serializable,
               Cloneable,
               IXspfAnythingMixed,
               IXspfAnythingMixedChoice {
	
    private static RNameClass $nameClass$ = new RNameClass("<anyName></anyName>");
    private String $localName$ = "attribute";
    private String content_;

    /**
     * Creates a <code>XspfAnythingAnyAttr</code>.
     *
     */
    public XspfAnythingAnyAttr() {
    }

    /**
     * Creates a <code>XspfAnythingAnyAttr</code>.
     *
     * @param source
     */
    public XspfAnythingAnyAttr(XspfAnythingAnyAttr source) {
        setup(source);
    }

    /**
     * Creates a <code>XspfAnythingAnyAttr</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public XspfAnythingAnyAttr(RStack stack) {
        setup(stack);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>XspfAnythingAnyAttr</code>.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatch(RStack stack) {
        return (isMatchHungry(stack.makeClone()));
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>XspfAnythingAnyAttr</code>.
     * This method consumes the stack contents during matching operation.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatchHungry(RStack stack) {
        RStack target = stack;
        boolean $match$ = false;
        if (!$nameClass$.isMatchAttributeHungry(target)) {
            return (false);
        }
        $match$ = true;
        return ($match$);
    }

    /**
     * Gets the String property <b>content</b>.
     *
     * @return String
     */
    public String getContent() {
        return (content_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getContentAsString() {
        return (URelaxer.getString(getContent()));
    }

    /**
     * Sets the String property <b>content</b>.
     *
     * @param content
     */
    public void setContent(String content) {
        this.content_ = content;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setContentByString(String string) {
        setContent(string);
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new XspfAnythingAnyAttr((XspfAnythingAnyAttr)this));
    }

    /**
     * Creates a DOM representation of the object.
     * Result is appended to the Node <code>parent</code>.
     *
     * @param parent
     */
    public void makeElement(Node parent) {
        Element element = (Element)parent;
        element.setAttribute($localName$, URelaxer.getString(getContent()));
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(StringBuffer buffer) {
        buffer.append(" ");
        buffer.append($localName$);
        buffer.append("=\"");
        if (content_ != null) {
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getContent())));
        }
        buffer.append("\"");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextAttribute(Writer buffer)
                           throws IOException {
        buffer.write(" ");
        buffer.write($localName$);
        buffer.write("=\"");
        if (content_ != null) {
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getContent())));
        }
        buffer.write("\"");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(PrintWriter buffer) {
        buffer.print(" ");
        buffer.print($localName$);
        buffer.print("=\"");
        if (content_ != null) {
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getContent())));
        }
        buffer.print("\"");
    }

    /**
     * Makes an XML text representation.
     *
     * @return String
     */
    public String makeTextDocument() {
        StringBuffer buffer = new StringBuffer();
        if (content_ != null) {
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getContent())));
        }
        return (new String(buffer));
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(StringBuffer buffer) {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer)
                         throws IOException {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
    }

    /**
     * Initializes the <code>XspfAnythingAnyAttr</code> by the XspfAnythingAnyAttr <code>source</code>.
     *
     * @param source
     */
    public void setup(XspfAnythingAnyAttr source) {
        content_ = source.content_;
    }

    /**
     * Initializes the <code>XspfAnythingAnyAttr</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public void setup(RStack stack) {
        String[] result = $nameClass$.getAttributeHungry(stack);
        $localName$ = result[0];
        String value = result[1];
        setContent(value.toString());
    }

    /**
     * Returns a String representation of this object.
     * While this method informs as XML format representaion,
     *  it's purpose is just information, not making
     * a rigid XML documentation.
     *
     * @return String
     */
    public String toString() {
        try {
            return (makeTextDocument());
        } catch (Exception e) {
            return (super.toString());
        }
    }
}