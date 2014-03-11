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
 * <b>XspfPlaylist</b> is generated from xspf.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <element name="playlist">
 *             <attribute name="version">
 *                 <data type="string">
 *                     <param name="pattern">\d+</param>
 *                 </data>
 *             </attribute>
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
 *                 <element name="location">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="identifier">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="image">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="date">
 *                     <data type="dateTime"/>
 *                 </element>
 *             </optional>
 *             <optional>
 *                 <element name="license">
 *                     <data type="anyURI"/>
 *                 </element>
 *             </optional>
 *
 *             <optional>
 *                 <ref name="attribution"/>
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
 *
 *             <element name="trackList">
 *                 <zeroOrMore>
 *                     <ref name="track"/>
 *                 </zeroOrMore>
 *             </element>
 *         </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="playlist"&gt;
 *             &lt;attribute name="version"&gt;
 *                 &lt;data type="string"&gt;
 *                     &lt;param name="pattern"&gt;\d+&lt;/param&gt;
 *                 &lt;/data&gt;
 *             &lt;/attribute&gt;
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
 *                 &lt;element name="location"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="identifier"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="image"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="date"&gt;
 *                     &lt;data type="dateTime"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *             &lt;optional&gt;
 *                 &lt;element name="license"&gt;
 *                     &lt;data type="anyURI"/&gt;
 *                 &lt;/element&gt;
 *             &lt;/optional&gt;
 *
 *             &lt;optional&gt;
 *                 &lt;ref name="attribution"/&gt;
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
 *
 *             &lt;element name="trackList"&gt;
 *                 &lt;zeroOrMore&gt;
 *                     &lt;ref name="track"/&gt;
 *                 &lt;/zeroOrMore&gt;
 *             &lt;/element&gt;
 *         &lt;/element&gt;</pre>
 *
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public class XspfPlaylist
    implements java.io.Serializable,
               Cloneable {
	
    // List<XspfExtension>
    private java.util.List extension_ = new java.util.ArrayList();
    // List<XspfLink>
    private java.util.List link_ = new java.util.ArrayList();
    // List<XspfMeta>
    private java.util.List meta_ = new java.util.ArrayList();
    private String annotation_;
    private String creator_;
    private String identifier_;
    private String image_;
    private String info_;
    private String license_;
    private String location_;
    private String title_;
    private String version_;
    private java.sql.Timestamp date_;
    private XspfAttribution attribution_;
    private XspfPlaylistTrackList playlistTrackList_;

    /**
     * Creates a <code>XspfPlaylist</code>.
     *
     */
    public XspfPlaylist() {
        version_ = "";
    }

    /**
     * Creates a <code>XspfPlaylist</code>.
     *
     * @param source
     */
    public XspfPlaylist(XspfPlaylist source) {
        setup(source);
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the Stack <code>stack</code>
     * that contains Elements.
     * This constructor is supposed to be used internally
     * by the Relaxer system.
     *
     * @param stack
     */
    public XspfPlaylist(RStack stack) {
        setup(stack);
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public XspfPlaylist(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public XspfPlaylist(Element element) {
        setup(element);
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the File <code>file</code>.
     *
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylist(File file)
                 throws IOException, SAXException, ParserConfigurationException {
        setup(file);
    }

    /**
     * Creates a <code>XspfPlaylist</code>
     * by the String representation of URI <code>uri</code>.
     *
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylist(String uri)
                 throws IOException, SAXException, ParserConfigurationException {
        setup(uri);
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the URL <code>url</code>.
     *
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylist(URL url)
                 throws IOException, SAXException, ParserConfigurationException {
        setup(url);
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the InputStream <code>in</code>.
     *
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylist(InputStream in)
                 throws IOException, SAXException, ParserConfigurationException {
        setup(in);
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the InputSource <code>is</code>.
     *
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylist(InputSource is)
                 throws IOException, SAXException, ParserConfigurationException {
        setup(is);
    }

    /**
     * Creates a <code>XspfPlaylist</code> by the Reader <code>reader</code>.
     *
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    public XspfPlaylist(Reader reader)
                 throws IOException, SAXException, ParserConfigurationException {
        setup(reader);
    }

    /**
     * Tests if a Element <code>element</code> is valid
     * for the <code>XspfPlaylist</code>.
     *
     * @param element
     * @return boolean
     */
    public static boolean isMatch(Element element) {
        if (!URelaxer.isTargetElement(element, "playlist")) {
            return (false);
        }
        RStack target = new RStack(element);
        Element child;
        if (!URelaxer.hasAttributeHungry(target, "version")) {
            return (false);
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
            if (URelaxer.isTargetElement(child, "location")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "identifier")) {
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
            if (URelaxer.isTargetElement(child, "date")) {
                target.popElement();
            }
        }
        child = target.peekElement();
        if (child != null) {
            if (URelaxer.isTargetElement(child, "license")) {
                target.popElement();
            }
        }
        if (XspfAttribution.isMatchHungry(target)) {
            ;
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
        if (!XspfPlaylistTrackList.isMatchHungry(target)) {
            return (false);
        }
        if (!target.isEmptyElement()) {
            return (false);
        }
        return (true);
    }

    /**
     * Tests if elements contained in a Stack <code>stack</code>
     * is valid for the <code>XspfPlaylist</code>.
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
     * is valid for the <code>XspfPlaylist</code>.
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
     * Gets the XspfAttribution property <b>attribution</b>.
     *
     * @return XspfAttribution
     */
    public XspfAttribution getAttribution() {
        return (attribution_);
    }

    /**
     * Gets attribute.
     *
     * @return String[]
     */
    public String[] getAttribution_Location() {
        if (attribution_ == null) {
            return (null);
        }
        return (attribution_.getLocation());
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
     * Gets the java.sql.Timestamp property <b>date</b>.
     *
     * @return java.sql.Timestamp
     */
    public java.sql.Timestamp getDate() {
        return (date_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getDateAsString() {
        return (URelaxer.getString(getDate()));
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
     * @return String
     */
    public String getIdentifier() {
        return (identifier_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getIdentifierAsString() {
        return (URelaxer.getString(getIdentifier()));
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
     * Gets the String property <b>license</b>.
     *
     * @return String
     */
    public String getLicense() {
        return (license_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getLicenseAsString() {
        return (URelaxer.getString(getLicense()));
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
     * @return String
     */
    public String getLocation() {
        return (location_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getLocationAsString() {
        return (URelaxer.getString(getLocation()));
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
     * Gets the XspfPlaylistTrackList property <b>playlistTrackList</b>.
     *
     * @return XspfPlaylistTrackList
     */
    public XspfPlaylistTrackList getPlaylistTrackList() {
        return (playlistTrackList_);
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
     * Gets the String property <b>version</b>.
     *
     * @return String
     */
    public String getVersion() {
        return (version_);
    }

    /**
     * Gets the property value as String.
     *
     * @return String
     */
    public String getVersionAsString() {
        return (URelaxer.getString(getVersion()));
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
     * Sets the XspfAttribution property <b>attribution</b>.
     *
     * @param attribution
     */
    public void setAttribution(XspfAttribution attribution) {
        this.attribution_ = attribution;
    }

    /**
     * Gets attribute.
     *
     * @param location
     */
    public void setAttribution_Location(String[] location) {
        if (attribution_ == null) {
            attribution_ = new XspfAttribution();
        }
        attribution_.setLocation(location);
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
     * Sets the java.sql.Timestamp property <b>date</b>.
     *
     * @param date
     */
    public void setDate(java.sql.Timestamp date) {
        this.date_ = date;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setDateByString(String string) {
        setDate(URelaxer.getSQLTimestamp(string));
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
    public void setIdentifier(String identifier) {
        this.identifier_ = identifier;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setIdentifierByString(String string) {
        setIdentifier(URelaxer.getString(string));
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
     * Sets the String property <b>license</b>.
     *
     * @param license
     */
    public void setLicense(String license) {
        this.license_ = license;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setLicenseByString(String string) {
        setLicense(URelaxer.getString(string));
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
    public void setLocation(String location) {
        this.location_ = location;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setLocationByString(String string) {
        setLocation(URelaxer.getString(string));
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
     * Sets the XspfPlaylistTrackList property <b>playlistTrackList</b>.
     *
     * @param playlistTrackList
     */
    public void setPlaylistTrackList(XspfPlaylistTrackList playlistTrackList) {
        this.playlistTrackList_ = playlistTrackList;
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
     * Sets the String property <b>version</b>.
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version_ = version;
    }

    /**
     * Sets the property value by String.
     *
     * @param string
     */
    public void setVersionByString(String string) {
        setVersion(string);
    }

    /**
     * Adds attribute.
     *
     * @param location
     */
    public void addAttribution_Location(String location) {
        if (attribution_ == null) {
            attribution_ = new XspfAttribution();
        }
        attribution_.addLocation(location);
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
     * Clear the XspfLink property <b>link</b>.
     *
     */
    public void clearLink() {
        this.link_.clear();
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
        return (new XspfPlaylist((XspfPlaylist)this));
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
        Element element = doc.createElement("playlist");
        if (parent instanceof Document) {
            element.setAttribute("xmlns", "http://xspf.org/ns/0/");
        }
        int size;
        if (this.version_ != null) {
            URelaxer.setAttributePropertyByString(element, "version", this.version_);
        }
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
        if (this.location_ != null) {
            URelaxer.setElementPropertyByString(element, "location", this.location_);
        }
        if (this.identifier_ != null) {
            URelaxer.setElementPropertyByString(element, "identifier", this.identifier_);
        }
        if (this.image_ != null) {
            URelaxer.setElementPropertyByString(element, "image", this.image_);
        }
        if (this.date_ != null) {
            URelaxer.setElementPropertyBySQLTimestamp(element, "date", this.date_);
        }
        if (this.license_ != null) {
            URelaxer.setElementPropertyByString(element, "license", this.license_);
        }
        if (this.attribution_ != null) {
            this.attribution_.makeElement(element);
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
        if (this.playlistTrackList_ != null) {
            this.playlistTrackList_.makeElement(element);
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
        buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buffer.append("<playlist");
        buffer.append(" xmlns=\"http://xspf.org/ns/0/\"");
        if (version_ != null) {
            buffer.append(" version=\"");
            buffer.append(URelaxer.escapeAttrQuot(URelaxer.getString(getVersion())));
            buffer.append("\"");
        }
        buffer.append(">");
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
        if (location_ != null) {
            buffer.append("<location>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLocation())));
            buffer.append("</location>");
        }
        if (identifier_ != null) {
            buffer.append("<identifier>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getIdentifier())));
            buffer.append("</identifier>");
        }
        if (image_ != null) {
            buffer.append("<image>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getImage())));
            buffer.append("</image>");
        }
        if (date_ != null) {
            buffer.append("<date>");
            buffer.append(URelaxer.getString(getDate()));
            buffer.append("</date>");
        }
        if (license_ != null) {
            buffer.append("<license>");
            buffer.append(URelaxer.escapeCharData(URelaxer.getString(getLicense())));
            buffer.append("</license>");
        }
        if (attribution_ != null) {
            attribution_.makeTextElement(buffer);
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
        if (playlistTrackList_ != null) {
            playlistTrackList_.makeTextElement(buffer);
        }
        buffer.append("</playlist>");
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
        buffer.write("<playlist");
        buffer.write(" xmlns=\"http://xspf.org/ns/0/\"");
        if (version_ != null) {
            buffer.write(" version=\"");
            buffer.write(URelaxer.escapeAttrQuot(URelaxer.getString(getVersion())));
            buffer.write("\"");
        }
        buffer.write(">");
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
        if (location_ != null) {
            buffer.write("<location>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLocation())));
            buffer.write("</location>");
        }
        if (identifier_ != null) {
            buffer.write("<identifier>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getIdentifier())));
            buffer.write("</identifier>");
        }
        if (image_ != null) {
            buffer.write("<image>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getImage())));
            buffer.write("</image>");
        }
        if (date_ != null) {
            buffer.write("<date>");
            buffer.write(URelaxer.getString(getDate()));
            buffer.write("</date>");
        }
        if (license_ != null) {
            buffer.write("<license>");
            buffer.write(URelaxer.escapeCharData(URelaxer.getString(getLicense())));
            buffer.write("</license>");
        }
        if (attribution_ != null) {
            attribution_.makeTextElement(buffer);
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
        if (playlistTrackList_ != null) {
            playlistTrackList_.makeTextElement(buffer);
        }
        buffer.write("</playlist>");
    }

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    public void makeTextElement(PrintWriter buffer) {
        int size;
        buffer.print("<playlist");
        buffer.print(" xmlns=\"http://xspf.org/ns/0/\"");
        if (version_ != null) {
            buffer.print(" version=\"");
            buffer.print(URelaxer.escapeAttrQuot(URelaxer.getString(getVersion())));
            buffer.print("\"");
        }
        buffer.print(">");
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
        if (location_ != null) {
            buffer.print("<location>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLocation())));
            buffer.print("</location>");
        }
        if (identifier_ != null) {
            buffer.print("<identifier>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getIdentifier())));
            buffer.print("</identifier>");
        }
        if (image_ != null) {
            buffer.print("<image>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getImage())));
            buffer.print("</image>");
        }
        if (date_ != null) {
            buffer.print("<date>");
            buffer.print(URelaxer.getString(getDate()));
            buffer.print("</date>");
        }
        if (license_ != null) {
            buffer.print("<license>");
            buffer.print(URelaxer.escapeCharData(URelaxer.getString(getLicense())));
            buffer.print("</license>");
        }
        if (attribution_ != null) {
            attribution_.makeTextElement(buffer);
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
        if (playlistTrackList_ != null) {
            playlistTrackList_.makeTextElement(buffer);
        }
        buffer.print("</playlist>");
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
     * Initializes the <code>XspfPlaylist</code> by the XspfPlaylist <code>source</code>.
     *
     * @param source
     */
    public void setup(XspfPlaylist source) {
        int size;
        version_ = source.version_;
        title_ = source.title_;
        creator_ = source.creator_;
        annotation_ = source.annotation_;
        info_ = source.info_;
        location_ = source.location_;
        identifier_ = source.identifier_;
        image_ = source.image_;
        date_ = source.date_;
        license_ = source.license_;
        if (source.attribution_ != null) {
            setAttribution((XspfAttribution)source.getAttribution().clone());
        }
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
        if (source.playlistTrackList_ != null) {
            setPlaylistTrackList((XspfPlaylistTrackList)source.getPlaylistTrackList().clone());
        }
    }

    /**
     * Initializes the <code>XspfPlaylist</code> by the Document <code>doc</code>.
     *
     * @param doc
     */
    public void setup(Document doc) {
        setup(doc.getDocumentElement());
    }

    /**
     * Initializes the <code>XspfPlaylist</code> by the Element <code>element</code>.
     *
     * @param element
     */
    public void setup(Element element) {
        init(element);
    }

    /**
     * Initializes the <code>XspfPlaylist</code> by the Stack <code>stack</code>
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
     * Initializes the <code>XspfPlaylist</code> by the File <code>file</code>.
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
     * Initializes the <code>XspfPlaylist</code>
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
     * Initializes the <code>XspfPlaylist</code> by the URL <code>url</code>.
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
     * Initializes the <code>XspfPlaylist</code> by the InputStream <code>in</code>.
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
     * Initializes the <code>XspfPlaylist</code> by the InputSource <code>is</code>.
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
     * Initializes the <code>XspfPlaylist</code> by the Reader <code>reader</code>.
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
     * Gets number of the XspfLink property <b>link</b>.
     *
     * @return int
     */
    public int sizeLink() {
        return (link_.size());
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
            version_ = URelaxer.getAttributePropertyAsString(element, "version");
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
            location_ = URelaxer.getElementPropertyAsStringByStack(stack, "location");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            identifier_ = URelaxer.getElementPropertyAsStringByStack(stack, "identifier");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            image_ = URelaxer.getElementPropertyAsStringByStack(stack, "image");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            date_ = URelaxer.getElementPropertyAsSQLTimestampByStack(stack, "date");
        } catch (IllegalArgumentException e) {
            ;
        }
        try {
            license_ = URelaxer.getElementPropertyAsStringByStack(stack, "license");
        } catch (IllegalArgumentException e) {
            ;
        }
        if (XspfAttribution.isMatch(stack)) {
            setAttribution(new XspfAttribution(stack));
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
        if (XspfPlaylistTrackList.isMatch(stack)) {
            setPlaylistTrackList(new XspfPlaylistTrackList(stack));
        }
    }
}