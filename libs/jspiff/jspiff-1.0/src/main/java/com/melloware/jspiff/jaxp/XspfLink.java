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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <b>XspfLink</b> is generated from xspf.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <element name="link">
 *             <attribute name="rel">
 *                 <data type="anyURI"/>
 *             </attribute>
 *
 *             <data type="anyURI"/>
 *         </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="link"&gt;
 *             &lt;attribute name="rel"&gt;
 *                 &lt;data type="anyURI"/&gt;
 *             &lt;/attribute&gt;
 *
 *             &lt;data type="anyURI"/&gt;
 *         &lt;/element&gt;</pre>
 *
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class XspfLink
    implements java.io.Serializable,
               Cloneable {
	
    private String content_;
    private String rel_;

    /**
     * Creates a <code>XspfLink</code>.
     *
     */
    public XspfLink() {
        rel_ = "";
    }

    /**
     * Creates a <code>XspfLink</code>.
     *
     * @param source
     */
    public XspfLink(XspfLink source) {
        setup(source);
    }

    /**
     * Creates a <code>XspfLink</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public XspfLink(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>XspfLink</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public XspfLink(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>XspfLink</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public XspfLink(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>XspfLink</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfLink(File file)
             throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>XspfLink</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfLink(String uri)
             throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>XspfLink</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfLink(URL url)
             throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>XspfLink</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfLink(InputStream in)
             throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>XspfLink</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfLink(InputSource is)
             throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>XspfLink</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfLink(Reader reader)
             throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>XspfLink</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "link")) {
            return (false);
        }
        RStack target = new RStack(element);
        if (!URelaxer.hasAttributeHungry(target, "rel")) {
            return (false);
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>XspfLink</code>.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatch(RStack stack) {
        Element element = stack.peekElement();
        if (element == null) {
            return (false);
        }
        return (isMatch(element));
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>XspfLink</code>.
     * This method consumes the stack contents during matching operation.
     * This mehtod is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     * @return boolean
     */
    public static boolean isMatchHungry(RStack stack) {
        Element element = stack.peekElement();
        if (element == null) {
            return (false);
        }
        if (isMatch(element)) {
            stack.popElement();
            return (true);
        } else {
            return (false);
        }
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
     * Gets the String property <b>rel</b>.
     *
     * @return String
     */
    public String getRel() {
        return (rel_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getRelAsString() {
        return (URelaxer.getString(getRel()));
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
        setContent(URelaxer.getString(string));
    }

    /**
     * Sets the String property <b>rel</b>.
     *
     * @param rel
     */
    public void setRel(String rel) {
        this.rel_ = rel;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setRelByString(String string) {
        setRel(URelaxer.getString(string));
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new XspfLink((XspfLink)this));
    }

    /**
     * Creates a DOM document representation of the object.
     *
     * @exception ParserConfigurationException
     * @return Document
     */
    public Document makeDocument()
                          throws ParserConfigurationException {
        Document doc = UJAXP.makeDocument();
        makeElement(doc);
        return (doc);
    }

    /**
     * Creates a DOM representation of the object.
     * Result is appended to the Node <code>parent</code>.
     *
     * @param parent
     */
    public void makeElement(Node parent) {
        Document doc;
        if (parent instanceof Document) {
            doc = (Document)parent;
        } else {
            doc = parent.getOwnerDocument();
        }
        Element element = doc.createElement("link");
        if (parent instanceof Document) {
            element.setAttribute("xmlns", "http://xspf.org/ns/0/");
        }
        URelaxer.setElementPropertyByString(element, this.content_);
        if (this.rel_ != null) {
            URelaxer.setAttributePropertyByString(element, "rel", this.rel_);
        }
        parent.appendChild(element);
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
     * @return String
     */
    public String makeTextDocument() {
        StringBuffer buffer = new StringBuffer();
        makeTextElement(buffer);
        return (new String(buffer));
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(StringBuffer buffer) {
        buffer.append("<link");
        buffer.append(" xmlns=\"http://xspf.org/ns/0/\"");
        if (rel_ != null) {
            buffer.append(" rel=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getRel())));
            buffer.append("\"");
        }
        buffer.append(">");
        if (content_ != null) {
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getContent())));
        }
        buffer.append("</link>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer)
                         throws IOException {
        buffer.write("<link");
        buffer.write(" xmlns=\"http://xspf.org/ns/0/\"");
        if (rel_ != null) {
            buffer.write(" rel=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getRel())));
            buffer.write("\"");
        }
        buffer.write(">");
        if (content_ != null) {
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getContent())));
        }
        buffer.write("</link>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        buffer.print("<link");
        buffer.print(" xmlns=\"http://xspf.org/ns/0/\"");
        if (rel_ != null) {
            buffer.print(" rel=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getRel())));
            buffer.print("\"");
        }
        buffer.print(">");
        if (content_ != null) {
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getContent())));
        }
        buffer.print("</link>");
    }

    /**
     * Initializes the <code>XspfLink</code> by the XspfLink <code>source</code>.
     *
     * @param source
     */
    public void setup(XspfLink source) {
        content_ = source.content_;
        rel_ = source.rel_;
    }

    /**
     * Initializes the <code>XspfLink</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>XspfLink</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>XspfLink</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public void setup(RStack stack) {
        init(stack.popElement());
    }

    /**
     * Initializes the <code>XspfLink</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(File file)
               throws IOException, SAXException, ParserConfigurationException {
        setup(file.toURL());
    }

    /**
     * Initializes the <code>XspfLink</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(String uri)
               throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(uri, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>XspfLink</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(URL url)
               throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(url, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>XspfLink</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputStream in)
               throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(in, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>XspfLink</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(InputSource is)
               throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(is, UJAXP.FLAG_NONE));
    }

    /**
     * Initializes the <code>XspfLink</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public void setup(Reader reader)
               throws IOException, SAXException, ParserConfigurationException {
        setup(UJAXP.getDocument(reader, UJAXP.FLAG_NONE));
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

    /**
     * @param element
     */
    private void init(Element element) {
        try {
            content_ = URelaxer.getElementPropertyAsString(element);
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            rel_ = URelaxer.getAttributePropertyAsString(element, "rel");
        } catch (IllegalArgumentException e) {
            ;
        }
    }
}