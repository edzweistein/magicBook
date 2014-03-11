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
 * <b>XspfAttribution</b> is generated from xspf.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <element name="attribution">
 *             <zeroOrMore>
 *                 <element name="location">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </zeroOrMore>
 *         </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="attribution"&gt;
 *             &lt;zeroOrMore&gt;
 *                 &lt;element name="location"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/zeroOrMore&gt;
 *         &lt;/element&gt;</pre>
 *
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class XspfAttribution
    implements java.io.Serializable,
               Cloneable {
    // List<String>
    private java.util.List location_ = new java.util.ArrayList();

    /**
     * Creates a <code>XspfAttribution</code>.
     *
     */
    public XspfAttribution() {
    }

    /**
     * Creates a <code>XspfAttribution</code>.
     *
     * @param source
     */
    public XspfAttribution(XspfAttribution source) {
        setup(source);
    }

    /**
     * Creates a <code>XspfAttribution</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public XspfAttribution(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>XspfAttribution</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public XspfAttribution(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>XspfAttribution</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public XspfAttribution(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>XspfAttribution</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfAttribution(File file)
                    throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>XspfAttribution</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfAttribution(String uri)
                    throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>XspfAttribution</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfAttribution(URL url)
                    throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>XspfAttribution</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfAttribution(InputStream in)
                    throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>XspfAttribution</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfAttribution(InputSource is)
                    throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>XspfAttribution</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfAttribution(Reader reader)
                    throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>XspfAttribution</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "attribution")) {
            return (false);
        }
        RStack target = new RStack(element);
        Element child;
        while ((child = target.peekElement()) != null) {
            if (!URelaxer.isTargetElement(child, "location")) {
                break;
            }
            target.popElement();
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>XspfAttribution</code>.
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
     * is valid for the <code>XspfAttribution</code>.
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
     * Gets the String property <b>location</b>.
     *
     * @return String[]
     */
    public String[] getLocation() {
        String[] array = new String[location_.size()];
        return ((String[])location_.toArray(array));
    }

    /**
     * Gets the String property <b>location</b> by index.
     *
     * @param index
     * @return String
     */
    public String getLocation(int index) {
        return ((String)location_.get(index));
    }

    /**
     * Gets the property value as String array.
     *
     * @return String[]
     */
    public String[] getLocationAsString() {
        int size = sizeLocation();
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = URelaxer.getString(getLocation(i));
        }
        return (array);
    }

    /**
     * Gets the property value by index as String.
     *
     * @param index
     * @return String
     */
    public String getLocationAsString(int index) {
        return (URelaxer.getString(getLocation(index)));
    }

    /**
     * Sets the String property <b>location</b>.
     *
     * @param location
     */
    public void setLocation(String[] location) {
        this.location_.clear();
        for (int i = 0; i < location.length; i++) {
            addLocation(location[i]);
        }
    }

    /**
     * Sets the String property <b>location</b>.
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location_.clear();
        addLocation(location);
    }

    /**
     * Sets the String property <b>location</b> by index.
     *
     * @param index
     * @param location
     */
    public void setLocation(int index, String location) {
        this.location_.set(index, location);
    }

    /**
     * Sets the property value by String array.
     *
     * @param strings
     */
    public void setLocationByString(String[] strings) {
        if (strings.length > 0) {
            String string = strings[0];
            setLocation(URelaxer.getString(string));
            for (int i = 1; i < strings.length; i++) {
                string = strings[i];
                addLocation(string);
            }
        }
    }

    /**
     * Sets the property value by String via index.
     *
     * @param index
     * @param value
     */
    public void setLocationByString(int index, String value) {
        setLocation(index, URelaxer.getString(value));
    }

    /**
     * Adds the String property <b>location</b>.
     *
     * @param location
     */
    public void addLocation(String location) {
        this.location_.add(location);
    }

    /**
     * Adds the String property <b>location</b>.
     *
     * @param location
     */
    public void addLocation(String[] location) {
        for (int i = 0; i < location.length; i++) {
            addLocation(location[i]);
        }
    }

    /**
     * Adds the String property <b>location</b> by index.
     *
     * @param index
     * @param location
     */
    public void addLocation(int index, String location) {
        this.location_.add(index, location);
    }

    /**
     * Adds the property value by String.
     *
     * @param string
     */
    public void addLocationByString(String string) {
        addLocation(string);
    }

    /**
     * Clear the String property <b>location</b>.
     *
     */
    public void clearLocation() {
        this.location_.clear();
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new XspfAttribution((XspfAttribution)this));
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
        Element element = doc.createElement("attribution");
        if (parent instanceof Document) {
            element.setAttribute("xmlns", "http://xspf.org/ns/0/");
        }
        URelaxer.setElementPropertyByStringList(element, "location", this.location_);
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
        buffer.append("<attribution");
        buffer.append(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.append(">");
        size = sizeLocation();
        for (int i = 0; i < size; i++) {
            buffer.append("<location>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLocation(i))));
            buffer.append("</location>");
        }
        buffer.append("</attribution>");
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
        buffer.write("<attribution");
        buffer.write(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.write(">");
        size = sizeLocation();
        for (int i = 0; i < size; i++) {
            buffer.write("<location>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLocation(i))));
            buffer.write("</location>");
        }
        buffer.write("</attribution>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<attribution");
        buffer.print(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.print(">");
        size = sizeLocation();
        for (int i = 0; i < size; i++) {
            buffer.print("<location>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLocation(i))));
            buffer.print("</location>");
        }
        buffer.print("</attribution>");
    }

    /**
     * Remove the String property <b>location</b> by index.
     *
     * @param index
     */
    public void removeLocation(int index) {
        this.location_.remove(index);
    }

    /**
     * Remove the String property <b>location</b> by object.
     *
     * @param location
     */
    public void removeLocation(String location) {
        this.location_.remove(location);
    }

    /**
     * Initializes the <code>XspfAttribution</code> by the XspfAttribution <code>source</code>.
     *
     * @param source
     */
    public void setup(XspfAttribution source) {
        setLocation(source.getLocation());
    }

    /**
     * Initializes the <code>XspfAttribution</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>XspfAttribution</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>XspfAttribution</code> by the Stack <code>stack</code>
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
     * Initializes the <code>XspfAttribution</code> by the File <code>file</code>.
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
     * Initializes the <code>XspfAttribution</code>
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
     * Initializes the <code>XspfAttribution</code> by the URL <code>url</code>.
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
     * Initializes the <code>XspfAttribution</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>XspfAttribution</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>XspfAttribution</code> by the Reader <code>reader</code>.
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
     * Gets number of the String property <b>location</b>.
     *
     * @return int
     */
    public int sizeLocation() {
        return (location_.size());
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
            location_ = URelaxer.getElementPropertyAsStringListByStack(stack, "location");
        } catch (IllegalArgumentException e) {
            ;
        }
    }
}