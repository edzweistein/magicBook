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

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * <b>RString</b> is a text container class which is used for mixed.
 * <p>
 * @version xspf.rng 1.0 (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class RString
    implements java.io.Serializable,
               Cloneable,
               IXspfExtensionMixed,
               IXspfAnythingMixed {
	
    private boolean cdata_;
    private Object value_;

    /**
     * Creates a <code>RString</code>.
     *
     */
    public RString() {
    }

    /**
     * Creates a <code>RString</code> by the String <code>text</code>.
     *
     * @param text
     */
    public RString(String text) {
        value_ = text;
    }

    /**
     * Creates a <code>RString</code> by the DOM node <code>node</code>.
     *
     * @param node
     */
    public RString(org.w3c.dom.Node node) {
        value_ = node;
    }

    /**
     * Creates a <code>RString</code> by the Object <code>object</code>.
     *
     * @param object
     */
    public RString(Object object) {
        value_ = object;
    }

    /**
     * Creates a <code>RString</code> by the Rstring <code>source</code>.
     *
     * @param source
     */
    public RString(RString source) {
        this(source.getContent());
    }

    /**
     * Creates a <code>RString</code> by the Stack <code>stack</code>.
     * This constructor is supposed to be used internallyby the Relaxer system.
     *
     * @param stack
     */
    public RString(RStack stack) {
        setup(stack);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>RString</code>.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatch(RStack stack) {
        return (stack.peek() instanceof String);
    }

    /**
     * Gets the content.
     *
     * @return Object
     */
    public Object getContent() {
        return (value_);
    }

    /**
     * Gets the text content as String.
     *
     * @return String
     */
    public String getContentAsString() {
        if (value_ == null) {
            ;
            return (null);
        } else if (value_ instanceof org.w3c.dom.Node) {
            return (URelaxer.node2String4Data((Node)value_));
        } else {
            return (value_.toString());
        }
    }

    /**
     * Gets the DOM node.
     *
     * @return org.w3c.dom.Node
     */
    public org.w3c.dom.Node getNode() {
        if (value_ instanceof org.w3c.dom.Node) {
            return ((org.w3c.dom.Node)value_);
        } else {
            return (null);
        }
    }

    /**
     * Gets the object.
     *
     * @return Object
     */
    public Object getObject() {
        if ((value_ instanceof String) || (value_ instanceof org.w3c.dom.Node)) {
            return (null);
        } else {
            return (value_);
        }
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getText() {
        if (value_ instanceof String) {
            return ((String)value_);
        } else {
            return (null);
        }
    }

    /**
     * Sets wheter cdata or not.
     *
     * @param cdata
     */
    public void setCdata(boolean cdata) {
        cdata_ = cdata;
    }

    /**
     * Sets the content.
     *
     * @param value
     */
    public void setContent(Object value) {
        value_ = value;
    }

    /**
     * Sets the DOM node.
     *
     * @param node
     */
    public void setNode(org.w3c.dom.Node node) {
        value_ = node;
    }

    /**
     * Sets the DOM node.
     *
     * @param object
     */
    public void setObject(Object object) {
        value_ = object;
    }

    /**
     * Sets the text.
     *
     * @param text
     */
    public void setText(String text) {
        value_ = text;
    }

    /**
     * Checks whether cdata or not.
     *
     * @return boolean
     */
    public boolean isCdata() {
        return (cdata_);
    }

    /**
     * Clones the String.
     *
     * @return Object
     */
    public Object clone() {
        return (new RString(this));
    }

    /**
     * Creates a DOM representation of the object.
     * Result is appended to the Node <code>parent</code>.
     *
     * @param node
     */
    public void makeElement(Node node) {
        Document doc = node.getOwnerDocument();
        if (value_ instanceof org.w3c.dom.Node) {
            node.appendChild(doc.importNode((Node)value_, true));
        } else if (value_ != null) {
            if (cdata_) {
                node.appendChild(doc.createCDATASection(value_.toString()));
            } else {
                node.appendChild(doc.createTextNode(value_.toString()));
            }
        }
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(StringBuffer buffer) {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextAttribute(Writer buffer)
                           throws IOException {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextAttribute(PrintWriter buffer) {
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(StringBuffer buffer) {
        if (value_ instanceof org.w3c.dom.Node) {
            buffer.append(URelaxer.node2String4Data((Node)value_));
        } else {
            if (cdata_) {
                buffer.append("<![CDATA[");
                buffer.append(value_.toString());
                buffer.append("]]>");
            } else {
                buffer.append(URelaxer.escapeCharData(value_.toString()));
            }
        }
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer)
                         throws IOException {
        if (value_ instanceof org.w3c.dom.Node) {
            buffer.write(URelaxer.node2String4Data((Node)value_));
        } else {
            if (cdata_) {
                buffer.write("<![CDATA[");
                buffer.write(value_.toString());
                buffer.write("]]>");
            } else {
                buffer.write(URelaxer.escapeCharData(value_.toString()));
            }
        }
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        if (value_ instanceof org.w3c.dom.Node) {
            buffer.print(URelaxer.node2String4Data((Node)value_));
        } else {
            if (cdata_) {
                buffer.print("<![CDATA[");
                buffer.print(value_.toString());
                buffer.print("]]>");
            } else {
                buffer.print(URelaxer.escapeCharData(value_.toString()));
            }
        }
    }

    /**
     * Initializes the <code>RString</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internallyby the Relaxer system.
     *
     * @param stack
     */
    public void setup(RStack stack) {
        value_ = stack.pop().toString();
    }

    /**
     * Gets the String.
     *
     * @return String
     */
    public String toString() {
        return (getContentAsString());
    }
}