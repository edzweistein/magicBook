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
 * <b>XspfExtension</b> is generated from xspf.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <element name="extension">
 *             <attribute name="application">
 *                 <data type="anyURI"/>
 *             </attribute>
 *
 *             <zeroOrMore>
 *                 <choice>
 *                     <element>
 *                         <anyName/>
 *                         <ref name="anything"/>
 *                     </element>
 *                     <text/>
 *                 </choice>
 *             </zeroOrMore>
 *         </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="extension"&gt;
 *             &lt;attribute name="application"&gt;
 *                 &lt;data type="anyURI"/&gt;
 *             &lt;/attribute&gt;
 *
 *             &lt;zeroOrMore&gt;
 *                 &lt;choice&gt;
 *                     &lt;element&gt;
 *                         &lt;anyName/&gt;
 *                         &lt;ref name="anything"/&gt;
 *                     &lt;/element&gt;
 *                     &lt;text/&gt;
 *                 &lt;/choice&gt;
 *             &lt;/zeroOrMore&gt;
 *         &lt;/element&gt;</pre>
 *
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class XspfExtension
    implements java.io.Serializable,
               Cloneable {
	
    // List<IXspfExtensionMixed>
    private java.util.List content_ = new java.util.ArrayList();
    private String application_;

    /**
     * Creates a <code>XspfExtension</code>.
     *
     */
    public XspfExtension() {
        application_ = "";
    }

    /**
     * Creates a <code>XspfExtension</code>.
     *
     * @param source
     */
    public XspfExtension(XspfExtension source) {
        setup(source);
    }

    /**
     * Creates a <code>XspfExtension</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public XspfExtension(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>XspfExtension</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public XspfExtension(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>XspfExtension</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public XspfExtension(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>XspfExtension</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfExtension(File file)
                  throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>XspfExtension</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfExtension(String uri)
                  throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>XspfExtension</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfExtension(URL url)
                  throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>XspfExtension</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfExtension(InputStream in)
                  throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>XspfExtension</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfExtension(InputSource is)
                  throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>XspfExtension</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfExtension(Reader reader)
                  throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>XspfExtension</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "extension")) {
            return (false);
        }
        RStack target = new RStack(element);
        if (!URelaxer.hasAttributeHungry(target, "application")) {
            return (false);
        }
        if (RString.isMatch(target)) {
            ;
        }
        while (true) {
            if (XspfExtensionAnyElement.isMatchHungry(target)) {
                ;
            } else {
                break;
            }
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>XspfExtension</code>.
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
     * is valid for the <code>XspfExtension</code>.
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
     * Gets the String property <b>application</b>.
     *
     * @return String
     */
    public String getApplication() {
        return (application_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getApplicationAsString() {
        return (URelaxer.getString(getApplication()));
    }

    /**
     * Gets the IXspfExtensionMixed property <b>content</b>.
     *
     * @return IXspfExtensionMixed[]
     */
    public IXspfExtensionMixed[] getContent() {
        IXspfExtensionMixed[] array = new IXspfExtensionMixed[content_.size()];
        return ((IXspfExtensionMixed[])content_.toArray(array));
    }

    /**
     * Gets the IXspfExtensionMixed property <b>content</b> by index.
     *
     * @param index
     * @return IXspfExtensionMixed
     */
    public IXspfExtensionMixed getContent(int index) {
        return ((IXspfExtensionMixed)content_.get(index));
    }

    /**
     * Sets the String property <b>application</b>.
     *
     * @param application
     */
    public void setApplication(String application) {
        this.application_ = application;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setApplicationByString(String string) {
        setApplication(URelaxer.getString(string));
    }

    /**
     * Sets a mixed content by <code>String</code>.
     *
     * @param value
     */
    public void setContent(String value) {
        setContent(new RString(value));
    }

    /**
     * Sets a mixed content by <code>org.w3c.dom.Node</code>.
     *
     * @param value
     */
    public void setContent(org.w3c.dom.Node value) {
        setContent(new RString(value));
    }

    /**
     * Sets the IXspfExtensionMixed property <b>content</b>.
     *
     * @param content
     */
    public void setContent(IXspfExtensionMixed[] content) {
        this.content_.clear();
        for (int i = 0; i < content.length; i++) {
            addContent(content[i]);
        }
    }

    /**
     * Sets the IXspfExtensionMixed property <b>content</b>.
     *
     * @param content
     */
    public void setContent(IXspfExtensionMixed content) {
        this.content_.clear();
        addContent(content);
    }

    /**
     * Sets the IXspfExtensionMixed property <b>content</b> by index.
     *
     * @param index
     * @param content
     */
    public void setContent(int index, IXspfExtensionMixed content) {
        this.content_.set(index, content);
    }

    /**
     * Sets a mixed content by <code>String</code>.
     *
     * @param value
     */
    public void setContentByString(String value) {
        setContent(new RString(value));
    }

    /**
     * Adds a mixed content by <code>String</code>.
     *
     * @param value
     */
    public void addContent(String value) {
        addContent(new RString(value));
    }

    /**
     * Adds a mixed content by <code>org.w3c.dom.Node</code>.
     *
     * @param value
     */
    public void addContent(org.w3c.dom.Node value) {
        addContent(new RString(value));
    }

    /**
     * Adds the IXspfExtensionMixed property <b>content</b>.
     *
     * @param content
     */
    public void addContent(IXspfExtensionMixed content) {
        this.content_.add(content);
    }

    /**
     * Adds the IXspfExtensionMixed property <b>content</b>.
     *
     * @param content
     */
    public void addContent(IXspfExtensionMixed[] content) {
        for (int i = 0; i < content.length; i++) {
            addContent(content[i]);
        }
    }

    /**
     * Adds the IXspfExtensionMixed property <b>content</b> by index.
     *
     * @param index
     * @param content
     */
    public void addContent(int index, IXspfExtensionMixed content) {
        this.content_.add(index, content);
    }

    /**
     * Adds a mixed content by <code>String</code>.
     *
     * @param value
     */
    public void addContentByString(String value) {
        addContent(new RString(value));
    }

    /**
     * Clear the IXspfExtensionMixed property <b>content</b>.
     *
     */
    public void clearContent() {
        this.content_.clear();
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new XspfExtension((XspfExtension)this));
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
        Element element = doc.createElement("extension");
        if (parent instanceof Document) {
            element.setAttribute("xmlns", "http://xspf.org/ns/0/");
        }
        int size;
        if (this.application_ != null) {
            URelaxer.setAttributePropertyByString(element, "application", this.application_);
        }
        size = this.content_.size();
        for (int i = 0; i < size; i++) {
            IXspfExtensionMixed value = (IXspfExtensionMixed)this.content_.get(i);
            value.makeElement(element);
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
        int size;
        buffer.append("<extension");
        buffer.append(" xmlns=\"http://xspf.org/ns/0/\"");
        if (application_ != null) {
            buffer.append(" application=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getApplication())));
            buffer.append("\"");
        }
        size = this.content_.size();
        for (int i = 0; i < size; i++) {
            IXspfExtensionMixed value = (IXspfExtensionMixed)this.content_.get(i);
            value.makeTextAttribute(buffer);
        }
        buffer.append(">");
        size = this.content_.size();
        for (int i = 0; i < size; i++) {
            IXspfExtensionMixed value = (IXspfExtensionMixed)this.content_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</extension>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    public void makeTextElement(Writer buffer)
                         throws IOException {
        int size;
        buffer.write("<extension");
        buffer.write(" xmlns=\"http://xspf.org/ns/0/\"");
        if (application_ != null) {
            buffer.write(" application=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getApplication())));
            buffer.write("\"");
        }
        size = this.content_.size();
        for (int i = 0; i < size; i++) {
            IXspfExtensionMixed value = (IXspfExtensionMixed)this.content_.get(i);
            value.makeTextAttribute(buffer);
        }
        buffer.write(">");
        size = this.content_.size();
        for (int i = 0; i < size; i++) {
            IXspfExtensionMixed value = (IXspfExtensionMixed)this.content_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</extension>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<extension");
        buffer.print(" xmlns=\"http://xspf.org/ns/0/\"");
        if (application_ != null) {
            buffer.print(" application=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getApplication())));
            buffer.print("\"");
        }
        size = this.content_.size();
        for (int i = 0; i < size; i++) {
            IXspfExtensionMixed value = (IXspfExtensionMixed)this.content_.get(i);
            value.makeTextAttribute(buffer);
        }
        buffer.print(">");
        size = this.content_.size();
        for (int i = 0; i < size; i++) {
            IXspfExtensionMixed value = (IXspfExtensionMixed)this.content_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</extension>");
    }

    /**
     * Remove the IXspfExtensionMixed property <b>content</b> by index.
     *
     * @param index
     */
    public void removeContent(int index) {
        this.content_.remove(index);
    }

    /**
     * Remove the IXspfExtensionMixed property <b>content</b> by object.
     *
     * @param content
     */
    public void removeContent(IXspfExtensionMixed content) {
        this.content_.remove(content);
    }

    /**
     * Initializes the <code>XspfExtension</code> by the XspfExtension <code>source</code>.
     *
     * @param source
     */
    public void setup(XspfExtension source) {
        int size;
        application_ = source.application_;
        this.content_.clear();
        size = source.content_.size();
        for (int i = 0; i < size; i++) {
            addContent((IXspfExtensionMixed)source.getContent(i).clone());
        }
    }

    /**
     * Initializes the <code>XspfExtension</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>XspfExtension</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>XspfExtension</code> by the Stack <code>stack</code>
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
     * Initializes the <code>XspfExtension</code> by the File <code>file</code>.
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
     * Initializes the <code>XspfExtension</code>
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
     * Initializes the <code>XspfExtension</code> by the URL <code>url</code>.
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
     * Initializes the <code>XspfExtension</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>XspfExtension</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>XspfExtension</code> by the Reader <code>reader</code>.
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
     * Gets number of the IXspfExtensionMixed property <b>content</b>.
     *
     * @return int
     */
    public int sizeContent() {
        return (content_.size());
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
        RStack stack = new RStack(element);
        try {
            application_ = URelaxer.getAttributePropertyAsString(element, "application");
        } catch (IllegalArgumentException e) {
            ;
        }
        this.content_.clear();
        
        while (true) {
            if (RString.isMatch(stack)) {
                addContent(new RString(stack));
            } else if (XspfExtensionAnyElement.isMatch(stack)) {
                addContent(new XspfExtensionAnyElement(stack));
            } else {
                break;
            }
        }
    }
}