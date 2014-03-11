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
 * <b>XspfTrack</b> is generated from xspf.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <element name="track">
 *             <zeroOrMore>
 *                 <element name="location">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </zeroOrMore>
 *             <zeroOrMore>
 *                 <element name="identifier">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </zeroOrMore>
 *
 *             <optional>
 *                 <element name="title">
 *                     <text/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="creator">
 *                     <text/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="annotation">
 *                     <text/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="info">
 *                     <text/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="image">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="album">
 *                     <text/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="trackNum">
 *                     <data type="nonNegativeInteger"/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="duration">
 *                     <data type="nonNegativeInteger"/>
 *                 </element>
 *             </optional>
 *
 *             <zeroOrMore>
 *                 <ref name="link"/>
 *             </zeroOrMore>
 *             <zeroOrMore>
 *                 <ref name="meta"/>
 *             </zeroOrMore>
 *             <zeroOrMore>
 *                 <ref name="extension"/>
 *             </zeroOrMore>
 *         </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="track"&gt;
 *             &lt;zeroOrMore&gt;
 *                 &lt;element name="location"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/zeroOrMore&gt;
 *             &lt;zeroOrMore&gt;
 *                 &lt;element name="identifier"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/zeroOrMore&gt;
 *
 *             &lt;optional&gt;
 *                 &lt;element name="title"&gt;
 *                     &lt;text/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="creator"&gt;
 *                     &lt;text/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="annotation"&gt;
 *                     &lt;text/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="info"&gt;
 *                     &lt;text/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="image"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="album"&gt;
 *                     &lt;text/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="trackNum"&gt;
 *                     &lt;data type="nonNegativeInteger"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="duration"&gt;
 *                     &lt;data type="nonNegativeInteger"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *
 *             &lt;zeroOrMore&gt;
 *                 &lt;ref name="link"/&gt;
 *             &lt;/zeroOrMore&gt;
 *             &lt;zeroOrMore&gt;
 *                 &lt;ref name="meta"/&gt;
 *             &lt;/zeroOrMore&gt;
 *             &lt;zeroOrMore&gt;
 *                 &lt;ref name="extension"/&gt;
 *             &lt;/zeroOrMore&gt;
 *         &lt;/element&gt;</pre>
 *
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class XspfTrack
    implements java.io.Serializable,
               Cloneable {
	
    private java.math.BigInteger duration_;
    private java.math.BigInteger trackNum_;
    // List<XspfExtension>
    private java.util.List extension_ = new java.util.ArrayList();
    // List<String>
    private java.util.List identifier_ = new java.util.ArrayList();
    // List<XspfLink>
    private java.util.List link_ = new java.util.ArrayList();
    // List<String>
    private java.util.List location_ = new java.util.ArrayList();
    // List<XspfMeta>
    private java.util.List meta_ = new java.util.ArrayList();
    private String album_;
    private String annotation_;
    private String creator_;
    private String image_;
    private String info_;
    private String title_;

    /**
     * Creates a <code>XspfTrack</code>.
     *
     */
    public XspfTrack() {
    }

    /**
     * Creates a <code>XspfTrack</code>.
     *
     * @param source
     */
    public XspfTrack(XspfTrack source) {
        setup(source);
    }

    /**
     * Creates a <code>XspfTrack</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public XspfTrack(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>XspfTrack</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public XspfTrack(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>XspfTrack</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public XspfTrack(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>XspfTrack</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfTrack(File file)
              throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>XspfTrack</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfTrack(String uri)
              throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>XspfTrack</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfTrack(URL url)
              throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>XspfTrack</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfTrack(InputStream in)
              throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>XspfTrack</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfTrack(InputSource is)
              throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>XspfTrack</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfTrack(Reader reader)
              throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>XspfTrack</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
    	if (!URelaxer.isTargetElement(element, "track")) {
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
        while ((child = target.peekElement()) != null) {
            if (!URelaxer.isTargetElement(child, "identifier")) {
                break;
            }
            target.popElement();
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "title")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "creator")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "annotation")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "info")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "image")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "album")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "trackNum")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "duration")) {
                target.popElement();
            }
        }
        while (true) {
            if (!XspfLink.isMatchHungry(target)) {
                break;
            }
        }
        while (true) {
            if (!XspfMeta.isMatchHungry(target)) {
                break;
            }
        }
        while (true) {
            if (!XspfExtension.isMatchHungry(target)) {
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
     * is valid for the <code>XspfTrack</code>.
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
     * is valid for the <code>XspfTrack</code>.
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
     * Gets the String property <b>album</b>.
     *
     * @return String
     */
    public String getAlbum() {
        return (album_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getAlbumAsString() {
        return (URelaxer.getString(getAlbum()));
    }

    /**
     * Gets the String property <b>annotation</b>.
     *
     * @return String
     */
    public String getAnnotation() {
        return (annotation_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getAnnotationAsString() {
        return (URelaxer.getString(getAnnotation()));
    }

    /**
     * Gets the String property <b>creator</b>.
     *
     * @return String
     */
    public String getCreator() {
        return (creator_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getCreatorAsString() {
        return (URelaxer.getString(getCreator()));
    }

    /**
     * Gets the java.math.BigInteger property <b>duration</b>.
     *
     * @return java.math.BigInteger
     */
    public java.math.BigInteger getDuration() {
        return (duration_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getDurationAsString() {
        return (URelaxer.getString(getDuration()));
    }

    /**
     * Gets the XspfExtension property <b>extension</b>.
     *
     * @return XspfExtension[]
     */
    public XspfExtension[] getExtension() {
        XspfExtension[] array = new XspfExtension[extension_.size()];
        return ((XspfExtension[])extension_.toArray(array));
    }

    /**
     * Gets the XspfExtension property <b>extension</b> by index.
     *
     * @param index
     * @return XspfExtension
     */
    public XspfExtension getExtension(int index) {
        return ((XspfExtension)extension_.get(index));
    }

    /**
     * Gets the String property <b>identifier</b>.
     *
     * @return String[]
     */
    public String[] getIdentifier() {
        String[] array = new String[identifier_.size()];
        return ((String[])identifier_.toArray(array));
    }

    /**
     * Gets the String property <b>identifier</b> by index.
     *
     * @param index
     * @return String
     */
    public String getIdentifier(int index) {
        return ((String)identifier_.get(index));
    }

    /**
     * Gets the property value as String array.
     *
     * @return String[]
     */
    public String[] getIdentifierAsString() {
        int size = sizeIdentifier();
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = URelaxer.getString(getIdentifier(i));
        }
        return (array);
    }

    /**
     * Gets the property value by index as String.
     *
     * @param index
     * @return String
     */
    public String getIdentifierAsString(int index) {
        return (URelaxer.getString(getIdentifier(index)));
    }

    /**
     * Gets the String property <b>image</b>.
     *
     * @return String
     */
    public String getImage() {
        return (image_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getImageAsString() {
        return (URelaxer.getString(getImage()));
    }

    /**
     * Gets the String property <b>info</b>.
     *
     * @return String
     */
    public String getInfo() {
        return (info_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getInfoAsString() {
        return (URelaxer.getString(getInfo()));
    }

    /**
     * Gets the XspfLink property <b>link</b>.
     *
     * @return XspfLink[]
     */
    public XspfLink[] getLink() {
        XspfLink[] array = new XspfLink[link_.size()];
        return ((XspfLink[])link_.toArray(array));
    }

    /**
     * Gets the XspfLink property <b>link</b> by index.
     *
     * @param index
     * @return XspfLink
     */
    public XspfLink getLink(int index) {
        return ((XspfLink)link_.get(index));
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
     * Gets the XspfMeta property <b>meta</b>.
     *
     * @return XspfMeta[]
     */
    public XspfMeta[] getMeta() {
        XspfMeta[] array = new XspfMeta[meta_.size()];
        return ((XspfMeta[])meta_.toArray(array));
    }

    /**
     * Gets the XspfMeta property <b>meta</b> by index.
     *
     * @param index
     * @return XspfMeta
     */
    public XspfMeta getMeta(int index) {
        return ((XspfMeta)meta_.get(index));
    }

    /**
     * Gets the String property <b>title</b>.
     *
     * @return String
     */
    public String getTitle() {
        return (title_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getTitleAsString() {
        return (URelaxer.getString(getTitle()));
    }

    /**
     * Gets the java.math.BigInteger property <b>trackNum</b>.
     *
     * @return java.math.BigInteger
     */
    public java.math.BigInteger getTrackNum() {
        return (trackNum_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getTrackNumAsString() {
        return (URelaxer.getString(getTrackNum()));
    }

    /**
     * Sets the String property <b>album</b>.
     *
     * @param album
     */
    public void setAlbum(String album) {
        this.album_ = album;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setAlbumByString(String string) {
        setAlbum(string);
    }

    /**
     * Sets the String property <b>annotation</b>.
     *
     * @param annotation
     */
    public void setAnnotation(String annotation) {
        this.annotation_ = annotation;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setAnnotationByString(String string) {
        setAnnotation(string);
    }

    /**
     * Sets the String property <b>creator</b>.
     *
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator_ = creator;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setCreatorByString(String string) {
        setCreator(string);
    }

    /**
     * Sets the java.math.BigInteger property <b>duration</b>.
     *
     * @param duration
     */
    public void setDuration(java.math.BigInteger duration) {
        this.duration_ = duration;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setDurationByString(String string) {
        setDuration(new java.math.BigInteger(string));
    }

    /**
     * Sets the XspfExtension property <b>extension</b>.
     *
     * @param extension
     */
    public void setExtension(XspfExtension[] extension) {
        this.extension_.clear();
        for (int i = 0; i < extension.length; i++) {
            addExtension(extension[i]);
        }
    }

    /**
     * Sets the XspfExtension property <b>extension</b>.
     *
     * @param extension
     */
    public void setExtension(XspfExtension extension) {
        this.extension_.clear();
        addExtension(extension);
    }

    /**
     * Sets the XspfExtension property <b>extension</b> by index.
     *
     * @param index
     * @param extension
     */
    public void setExtension(int index, XspfExtension extension) {
        this.extension_.set(index, extension);
    }

    /**
     * Sets the String property <b>identifier</b>.
     *
     * @param identifier
     */
    public void setIdentifier(String[] identifier) {
        this.identifier_.clear();
        for (int i = 0; i < identifier.length; i++) {
            addIdentifier(identifier[i]);
        }
    }

    /**
     * Sets the String property <b>identifier</b>.
     *
     * @param identifier
     */
    public void setIdentifier(String identifier) {
        this.identifier_.clear();
        addIdentifier(identifier);
    }

    /**
     * Sets the String property <b>identifier</b> by index.
     *
     * @param index
     * @param identifier
     */
    public void setIdentifier(int index, String identifier) {
        this.identifier_.set(index, identifier);
    }

    /**
     * Sets the property value by String array.
     *
     * @param strings
     */
    public void setIdentifierByString(String[] strings) {
        if (strings.length > 0) {
            String string = strings[0];
            setIdentifier(URelaxer.getString(string));
            for (int i = 1; i < strings.length; i++) {
                string = strings[i];
                addIdentifier(string);
            }
        }
    }

    /**
     * Sets the property value by String via index.
     *
     * @param index
     * @param value
     */
    public void setIdentifierByString(int index, String value) {
        setIdentifier(index, URelaxer.getString(value));
    }

    /**
     * Sets the String property <b>image</b>.
     *
     * @param image
     */
    public void setImage(String image) {
        this.image_ = image;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setImageByString(String string) {
        setImage(URelaxer.getString(string));
    }

    /**
     * Sets the String property <b>info</b>.
     *
     * @param info
     */
    public void setInfo(String info) {
        this.info_ = info;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setInfoByString(String string) {
        setInfo(string);
    }

    /**
     * Sets the XspfLink property <b>link</b>.
     *
     * @param link
     */
    public void setLink(XspfLink[] link) {
        this.link_.clear();
        for (int i = 0; i < link.length; i++) {
            addLink(link[i]);
        }
    }

    /**
     * Sets the XspfLink property <b>link</b>.
     *
     * @param link
     */
    public void setLink(XspfLink link) {
        this.link_.clear();
        addLink(link);
    }

    /**
     * Sets the XspfLink property <b>link</b> by index.
     *
     * @param index
     * @param link
     */
    public void setLink(int index, XspfLink link) {
        this.link_.set(index, link);
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
     * Sets the XspfMeta property <b>meta</b>.
     *
     * @param meta
     */
    public void setMeta(XspfMeta[] meta) {
        this.meta_.clear();
        for (int i = 0; i < meta.length; i++) {
            addMeta(meta[i]);
        }
    }

    /**
     * Sets the XspfMeta property <b>meta</b>.
     *
     * @param meta
     */
    public void setMeta(XspfMeta meta) {
        this.meta_.clear();
        addMeta(meta);
    }

    /**
     * Sets the XspfMeta property <b>meta</b> by index.
     *
     * @param index
     * @param meta
     */
    public void setMeta(int index, XspfMeta meta) {
        this.meta_.set(index, meta);
    }

    /**
     * Sets the String property <b>title</b>.
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title_ = title;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setTitleByString(String string) {
        setTitle(string);
    }

    /**
     * Sets the java.math.BigInteger property <b>trackNum</b>.
     *
     * @param trackNum
     */
    public void setTrackNum(java.math.BigInteger trackNum) {
        this.trackNum_ = trackNum;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setTrackNumByString(String string) {
        setTrackNum(new java.math.BigInteger(string));
    }

    /**
     * Adds the XspfExtension property <b>extension</b>.
     *
     * @param extension
     */
    public void addExtension(XspfExtension extension) {
        this.extension_.add(extension);
    }

    /**
     * Adds the XspfExtension property <b>extension</b>.
     *
     * @param extension
     */
    public void addExtension(XspfExtension[] extension) {
        for (int i = 0; i < extension.length; i++) {
            addExtension(extension[i]);
        }
    }

    /**
     * Adds the XspfExtension property <b>extension</b> by index.
     *
     * @param index
     * @param extension
     */
    public void addExtension(int index, XspfExtension extension) {
        this.extension_.add(index, extension);
    }

    /**
     * Adds the String property <b>identifier</b>.
     *
     * @param identifier
     */
    public void addIdentifier(String identifier) {
        this.identifier_.add(identifier);
    }

    /**
     * Adds the String property <b>identifier</b>.
     *
     * @param identifier
     */
    public void addIdentifier(String[] identifier) {
        for (int i = 0; i < identifier.length; i++) {
            addIdentifier(identifier[i]);
        }
    }

    /**
     * Adds the String property <b>identifier</b> by index.
     *
     * @param index
     * @param identifier
     */
    public void addIdentifier(int index, String identifier) {
        this.identifier_.add(index, identifier);
    }

    /**
     * Adds the property value by String.
     *
     * @param string
     */
    public void addIdentifierByString(String string) {
        addIdentifier(string);
    }

    /**
     * Adds the XspfLink property <b>link</b>.
     *
     * @param link
     */
    public void addLink(XspfLink link) {
        this.link_.add(link);
    }

    /**
     * Adds the XspfLink property <b>link</b>.
     *
     * @param link
     */
    public void addLink(XspfLink[] link) {
        for (int i = 0; i < link.length; i++) {
            addLink(link[i]);
        }
    }

    /**
     * Adds the XspfLink property <b>link</b> by index.
     *
     * @param index
     * @param link
     */
    public void addLink(int index, XspfLink link) {
        this.link_.add(index, link);
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
     * Adds the XspfMeta property <b>meta</b>.
     *
     * @param meta
     */
    public void addMeta(XspfMeta meta) {
        this.meta_.add(meta);
    }

    /**
     * Adds the XspfMeta property <b>meta</b>.
     *
     * @param meta
     */
    public void addMeta(XspfMeta[] meta) {
        for (int i = 0; i < meta.length; i++) {
            addMeta(meta[i]);
        }
    }

    /**
     * Adds the XspfMeta property <b>meta</b> by index.
     *
     * @param index
     * @param meta
     */
    public void addMeta(int index, XspfMeta meta) {
        this.meta_.add(index, meta);
    }

    /**
     * Clear the XspfExtension property <b>extension</b>.
     *
     */
    public void clearExtension() {
        this.extension_.clear();
    }

    /**
     * Clear the String property <b>identifier</b>.
     *
     */
    public void clearIdentifier() {
        this.identifier_.clear();
    }

    /**
     * Clear the XspfLink property <b>link</b>.
     *
     */
    public void clearLink() {
        this.link_.clear();
    }

    /**
     * Clear the String property <b>location</b>.
     *
     */
    public void clearLocation() {
        this.location_.clear();
    }

    /**
     * Clear the XspfMeta property <b>meta</b>.
     *
     */
    public void clearMeta() {
        this.meta_.clear();
    }

    /**
     * @return Object
     */
    public Object clone() {
        return (new XspfTrack((XspfTrack)this));
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
        Element element = doc.createElement("track");
        if (parent instanceof Document) {
            element.setAttribute("xmlns", "http://xspf.org/ns/0/");
        }
        int size;
        URelaxer.setElementPropertyByStringList(element, "location", this.location_);
        URelaxer.setElementPropertyByStringList(element, "identifier", this.identifier_);
        if (this.title_ != null) {
            URelaxer.setElementPropertyByString(element, "title", this.title_);
        }
        if (this.creator_ != null) {
            URelaxer.setElementPropertyByString(element, "creator", this.creator_);
        }
        if (this.annotation_ != null) {
            URelaxer.setElementPropertyByString(element, "annotation", this.annotation_);
        }
        if (this.info_ != null) {
            URelaxer.setElementPropertyByString(element, "info", this.info_);
        }
        if (this.image_ != null) {
            URelaxer.setElementPropertyByString(element, "image", this.image_);
        }
        if (this.album_ != null) {
            URelaxer.setElementPropertyByString(element, "album", this.album_);
        }
        if (this.trackNum_ != null) {
            URelaxer.setElementPropertyByBigInteger(element, "trackNum", this.trackNum_);
        }
        if (this.duration_ != null) {
            URelaxer.setElementPropertyByBigInteger(element, "duration", this.duration_);
        }
        size = this.link_.size();
        for (int i = 0; i < size; i++) {
            XspfLink value = (XspfLink)this.link_.get(i);
            value.makeElement(element);
        }
        size = this.meta_.size();
        for (int i = 0; i < size; i++) {
            XspfMeta value = (XspfMeta)this.meta_.get(i);
            value.makeElement(element);
        }
        size = this.extension_.size();
        for (int i = 0; i < size; i++) {
            XspfExtension value = (XspfExtension)this.extension_.get(i);
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
        buffer.append("<track");
        buffer.append(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.append(">");
        size = sizeLocation();
        for (int i = 0; i < size; i++) {
            buffer.append("<location>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLocation(i))));
            buffer.append("</location>");
        }
        size = sizeIdentifier();
        for (int i = 0; i < size; i++) {
            buffer.append("<identifier>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getIdentifier(i))));
            buffer.append("</identifier>");
        }
        if (title_ != null) {
            buffer.append("<title>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getTitle())));
            buffer.append("</title>");
        }
        if (creator_ != null) {
            buffer.append("<creator>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getCreator())));
            buffer.append("</creator>");
        }
        if (annotation_ != null) {
            buffer.append("<annotation>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getAnnotation())));
            buffer.append("</annotation>");
        }
        if (info_ != null) {
            buffer.append("<info>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getInfo())));
            buffer.append("</info>");
        }
        if (image_ != null) {
            buffer.append("<image>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getImage())));
            buffer.append("</image>");
        }
        if (album_ != null) {
            buffer.append("<album>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getAlbum())));
            buffer.append("</album>");
        }
        if (trackNum_ != null) {
            buffer.append("<trackNum>");
            buffer.append(URelaxer.getString(getTrackNum()));
            buffer.append("</trackNum>");
        }
        if (duration_ != null) {
            buffer.append("<duration>");
            buffer.append(URelaxer.getString(getDuration()));
            buffer.append("</duration>");
        }
        size = this.link_.size();
        for (int i = 0; i < size; i++) {
            XspfLink value = (XspfLink)this.link_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.meta_.size();
        for (int i = 0; i < size; i++) {
            XspfMeta value = (XspfMeta)this.meta_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.extension_.size();
        for (int i = 0; i < size; i++) {
            XspfExtension value = (XspfExtension)this.extension_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.append("</track>");
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
        buffer.write("<track");
        buffer.write(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.write(">");
        size = sizeLocation();
        for (int i = 0; i < size; i++) {
            buffer.write("<location>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLocation(i))));
            buffer.write("</location>");
        }
        size = sizeIdentifier();
        for (int i = 0; i < size; i++) {
            buffer.write("<identifier>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getIdentifier(i))));
            buffer.write("</identifier>");
        }
        if (title_ != null) {
            buffer.write("<title>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getTitle())));
            buffer.write("</title>");
        }
        if (creator_ != null) {
            buffer.write("<creator>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getCreator())));
            buffer.write("</creator>");
        }
        if (annotation_ != null) {
            buffer.write("<annotation>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getAnnotation())));
            buffer.write("</annotation>");
        }
        if (info_ != null) {
            buffer.write("<info>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getInfo())));
            buffer.write("</info>");
        }
        if (image_ != null) {
            buffer.write("<image>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getImage())));
            buffer.write("</image>");
        }
        if (album_ != null) {
            buffer.write("<album>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getAlbum())));
            buffer.write("</album>");
        }
        if (trackNum_ != null) {
            buffer.write("<trackNum>");
            buffer.write(URelaxer.getString(getTrackNum()));
            buffer.write("</trackNum>");
        }
        if (duration_ != null) {
            buffer.write("<duration>");
            buffer.write(URelaxer.getString(getDuration()));
            buffer.write("</duration>");
        }
        size = this.link_.size();
        for (int i = 0; i < size; i++) {
            XspfLink value = (XspfLink)this.link_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.meta_.size();
        for (int i = 0; i < size; i++) {
            XspfMeta value = (XspfMeta)this.meta_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.extension_.size();
        for (int i = 0; i < size; i++) {
            XspfExtension value = (XspfExtension)this.extension_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.write("</track>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<track");
        buffer.print(" xmlns=\"http://xspf.org/ns/0/\"");
        buffer.print(">");
        size = sizeLocation();
        for (int i = 0; i < size; i++) {
            buffer.print("<location>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLocation(i))));
            buffer.print("</location>");
        }
        size = sizeIdentifier();
        for (int i = 0; i < size; i++) {
            buffer.print("<identifier>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getIdentifier(i))));
            buffer.print("</identifier>");
        }
        if (title_ != null) {
            buffer.print("<title>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getTitle())));
            buffer.print("</title>");
        }
        if (creator_ != null) {
            buffer.print("<creator>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getCreator())));
            buffer.print("</creator>");
        }
        if (annotation_ != null) {
            buffer.print("<annotation>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getAnnotation())));
            buffer.print("</annotation>");
        }
        if (info_ != null) {
            buffer.print("<info>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getInfo())));
            buffer.print("</info>");
        }
        if (image_ != null) {
            buffer.print("<image>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getImage())));
            buffer.print("</image>");
        }
        if (album_ != null) {
            buffer.print("<album>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getAlbum())));
            buffer.print("</album>");
        }
        if (trackNum_ != null) {
            buffer.print("<trackNum>");
            buffer.print(URelaxer.getString(getTrackNum()));
            buffer.print("</trackNum>");
        }
        if (duration_ != null) {
            buffer.print("<duration>");
            buffer.print(URelaxer.getString(getDuration()));
            buffer.print("</duration>");
        }
        size = this.link_.size();
        for (int i = 0; i < size; i++) {
            XspfLink value = (XspfLink)this.link_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.meta_.size();
        for (int i = 0; i < size; i++) {
            XspfMeta value = (XspfMeta)this.meta_.get(i);
            value.makeTextElement(buffer);
        }
        size = this.extension_.size();
        for (int i = 0; i < size; i++) {
            XspfExtension value = (XspfExtension)this.extension_.get(i);
            value.makeTextElement(buffer);
        }
        buffer.print("</track>");
    }

    /**
     * Remove the XspfExtension property <b>extension</b> by index.
     *
     * @param index
     */
    public void removeExtension(int index) {
        this.extension_.remove(index);
    }

    /**
     * Remove the XspfExtension property <b>extension</b> by object.
     *
     * @param extension
     */
    public void removeExtension(XspfExtension extension) {
        this.extension_.remove(extension);
    }

    /**
     * Remove the String property <b>identifier</b> by index.
     *
     * @param index
     */
    public void removeIdentifier(int index) {
        this.identifier_.remove(index);
    }

    /**
     * Remove the String property <b>identifier</b> by object.
     *
     * @param identifier
     */
    public void removeIdentifier(String identifier) {
        this.identifier_.remove(identifier);
    }

    /**
     * Remove the XspfLink property <b>link</b> by index.
     *
     * @param index
     */
    public void removeLink(int index) {
        this.link_.remove(index);
    }

    /**
     * Remove the XspfLink property <b>link</b> by object.
     *
     * @param link
     */
    public void removeLink(XspfLink link) {
        this.link_.remove(link);
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
     * Remove the XspfMeta property <b>meta</b> by index.
     *
     * @param index
     */
    public void removeMeta(int index) {
        this.meta_.remove(index);
    }

    /**
     * Remove the XspfMeta property <b>meta</b> by object.
     *
     * @param meta
     */
    public void removeMeta(XspfMeta meta) {
        this.meta_.remove(meta);
    }

    /**
     * Initializes the <code>XspfTrack</code> by the XspfTrack <code>source</code>.
     *
     * @param source
     */
    public void setup(XspfTrack source) {
        int size;
        setLocation(source.getLocation());
        setIdentifier(source.getIdentifier());
        title_ = source.title_;
        creator_ = source.creator_;
        annotation_ = source.annotation_;
        info_ = source.info_;
        image_ = source.image_;
        album_ = source.album_;
        trackNum_ = source.trackNum_;
        duration_ = source.duration_;
        this.link_.clear();
        size = source.link_.size();
        for (int i = 0; i < size; i++) {
            addLink((XspfLink)source.getLink(i).clone());
        }
        this.meta_.clear();
        size = source.meta_.size();
        for (int i = 0; i < size; i++) {
            addMeta((XspfMeta)source.getMeta(i).clone());
        }
        this.extension_.clear();
        size = source.extension_.size();
        for (int i = 0; i < size; i++) {
            addExtension((XspfExtension)source.getExtension(i).clone());
        }
    }

    /**
     * Initializes the <code>XspfTrack</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>XspfTrack</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>XspfTrack</code> by the Stack <code>stack</code>
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
     * Initializes the <code>XspfTrack</code> by the File <code>file</code>.
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
     * Initializes the <code>XspfTrack</code>
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
     * Initializes the <code>XspfTrack</code> by the URL <code>url</code>.
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
     * Initializes the <code>XspfTrack</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>XspfTrack</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>XspfTrack</code> by the Reader <code>reader</code>.
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
     * Gets number of the XspfExtension property <b>extension</b>.
     *
     * @return int
     */
    public int sizeExtension() {
        return (extension_.size());
    }

    /**
     * Gets number of the String property <b>identifier</b>.
     *
     * @return int
     */
    public int sizeIdentifier() {
        return (identifier_.size());
    }

    /**
     * Gets number of the XspfLink property <b>link</b>.
     *
     * @return int
     */
    public int sizeLink() {
        return (link_.size());
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
     * Gets number of the XspfMeta property <b>meta</b>.
     *
     * @return int
     */
    public int sizeMeta() {
        return (meta_.size());
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
        try {
            identifier_ = URelaxer.getElementPropertyAsStringListByStack(stack, "identifier");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            title_ = URelaxer.getElementPropertyAsStringByStack(stack, "title");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            creator_ = URelaxer.getElementPropertyAsStringByStack(stack, "creator");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            annotation_ = URelaxer.getElementPropertyAsStringByStack(stack, "annotation");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            info_ = URelaxer.getElementPropertyAsStringByStack(stack, "info");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            image_ = URelaxer.getElementPropertyAsStringByStack(stack, "image");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            album_ = URelaxer.getElementPropertyAsStringByStack(stack, "album");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            trackNum_ = URelaxer.getElementPropertyAsBigIntegerByStack(stack, "trackNum");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            duration_ = URelaxer.getElementPropertyAsBigIntegerByStack(stack, "duration");
        } catch (IllegalArgumentException e) {
            ;
        }
        link_.clear();
        while (true) {
            if (XspfLink.isMatch(stack)) {
                addLink(new XspfLink(stack));
            } else {
                break;
            }
        }
        meta_.clear();
        while (true) {
            if (XspfMeta.isMatch(stack)) {
                addMeta(new XspfMeta(stack));
            } else {
                break;
            }
        }
        extension_.clear();
        while (true) {
            if (XspfExtension.isMatch(stack)) {
                addExtension(new XspfExtension(stack));
            } else {
                break;
            }
        }
    }
}