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
 * <b>XspfPlaylistTrackList</b> is generated from xspf.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <element name="trackList">
 *                 <zeroOrMore>
 *                     <ref name="track"/>
 *                 </zeroOrMore>
 *             </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="trackList"&gt;
 *                 &lt;zeroOrMore&gt;
 *                     &lt;ref name="track"/&gt;
 *                 &lt;/zeroOrMore&gt;
 *             &lt;/element&gt;</pre>
 *
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class XspfPlaylistTrackList
    implements java.io.Serializable,
               Cloneable {
	
    // List<XspfTrack>
    private java.util.List track_ = new java.util.ArrayList();

    /**
     * Creates a <code>XspfPlaylistTrackList</code>.
     *
     */
    public XspfPlaylistTrackList() {
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code>.
     *
     * @param source
     */
    public XspfPlaylistTrackList(XspfPlaylistTrackList source) {
        setup(source);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public XspfPlaylistTrackList(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public XspfPlaylistTrackList(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public XspfPlaylistTrackList(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylistTrackList(File file)
                          throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylistTrackList(String uri)
                          throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylistTrackList(URL url)
                          throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylistTrackList(InputStream in)
                          throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylistTrackList(InputSource is)
                          throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>XspfPlaylistTrackList</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylistTrackList(Reader reader)
                          throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>XspfPlaylistTrackList</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
    	if (!URelaxer.isTargetElement(element, "trackList")) {
            return (false);
        }
        RStack target = new RStack(element);
        while (true) {
            if (!XspfTrack.isMatchHungry(target)) {
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
     * is valid for the <code>XspfPlaylistTrackList</code>.
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
     * is valid for the <code>XspfPlaylistTrackList</code>.
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
     * Gets the XspfTrack property <b>track</b>.
     *
     * @return XspfTrack[]
     */
    public XspfTrack[] getTrack() {
        XspfTrack[] array = new XspfTrack[track_.size()];
        return ((XspfTrack[])track_.toArray(array));
    }

    /**
     * Gets the XspfTrack property <b>track</b> by index.
     *
     * @param index
     * @return XspfTrack
     */
    public XspfTrack getTrack(int index) {
        return ((XspfTrack)track_.get(index));
    }

    /**
     * Sets the XspfTrack property <b>track</b>.
     *
     * @param track
     */
    public void setTrack(XspfTrack[] track) {
        this.track_.clear();
        for (int i = 0; i < track.length; i++) {
            addTrack(track[i]);
        }
    }

    /**
     * Sets the XspfTrack property <b>track</b>.
     *
     * @param track
     */
    public void setTrack(XspfTrack track) {
        this.track_.clear();
        addTrack(track);
    }

    /**
     * Sets the XspfTrack property <b>track</b> by index.
     *
     * @param index
     * @param track
     */
    public void setTrack(int index, XspfTrack track) {
        this.track_.set(index, track);
    }

    /**
     * Adds the XspfTrack property <b>track</b>.
     *
     * @param track
     */
    public void addTrack(XspfTrack track) {
        this.track_.add(track);
    }

    /**
     * Adds the XspfTrack property <b>track</b>.
     *
     * @param track
     */
    public void addTrack(XspfTrack[] track) {
        for (int i = 0; i < track.length; i++) {
            addTrack(track[i]);
        }
    }

    /**
     * Adds the XspfTrack property <b>track</b> by index.
     *
     * @param index
     * @param track
     */
    public void addTrack(int index, XspfTrack track) {
        this.track_.add(index, track);
    }

    /**
     * Clear the XspfTrack property <b>track</b>.
     *
     */
    public void clearTrack() {
        this.track_.clear();
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new XspfPlaylistTrackList((XspfPlaylistTrackList)this));
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
        Element element = doc.createElement("trackList");
        if (parent instanceof Document) {
            element.setAttribute("xmlns", "http://xspf.org/ns/0/");
        }
        int size;
        size = this.track_.size();
        for (int i = 0; i < size; i++) {
            XspfTrack value = (XspfTrack)this.track_.get(i);
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
        buffer.append("<trackList");
        buffer.append(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.append(">");
        size = this.track_.size();
        for (int i = 0; i < size; i++) {
            XspfTrack value = (XspfTrack)this.track_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</trackList>");
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
        buffer.write("<trackList");
        buffer.write(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.write(">");
        size = this.track_.size();
        for (int i = 0; i < size; i++) {
            XspfTrack value = (XspfTrack)this.track_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</trackList>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<trackList");
        buffer.print(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.print(">");
        size = this.track_.size();
        for (int i = 0; i < size; i++) {
            XspfTrack value = (XspfTrack)this.track_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</trackList>");
    }

    /**
     * Remove the XspfTrack property <b>track</b> by index.
     *
     * @param index
     */
    public void removeTrack(int index) {
        this.track_.remove(index);
    }

    /**
     * Remove the XspfTrack property <b>track</b> by object.
     *
     * @param track
     */
    public void removeTrack(XspfTrack track) {
        this.track_.remove(track);
    }

    /**
     * Initializes the <code>XspfPlaylistTrackList</code> by the XspfPlaylistTrackList <code>source</code>.
     *
     * @param source
     */
    public void setup(XspfPlaylistTrackList source) {
        int size;
        this.track_.clear();
        size = source.track_.size();
        for (int i = 0; i < size; i++) {
            addTrack((XspfTrack)source.getTrack(i).clone());
        }
    }

    /**
     * Initializes the <code>XspfPlaylistTrackList</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>XspfPlaylistTrackList</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>XspfPlaylistTrackList</code> by the Stack <code>stack</code>
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
     * Initializes the <code>XspfPlaylistTrackList</code> by the File <code>file</code>.
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
     * Initializes the <code>XspfPlaylistTrackList</code>
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
     * Initializes the <code>XspfPlaylistTrackList</code> by the URL <code>url</code>.
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
     * Initializes the <code>XspfPlaylistTrackList</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>XspfPlaylistTrackList</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>XspfPlaylistTrackList</code> by the Reader <code>reader</code>.
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
     * Gets number of the XspfTrack property <b>track</b>.
     *
     * @return int
     */
    public int sizeTrack() {
        return (track_.size());
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
        track_.clear();
        while (true) {
            if (XspfTrack.isMatch(stack)) {
                addTrack(new XspfTrack(stack));
            } else {
                break;
            }
        }
    }
}