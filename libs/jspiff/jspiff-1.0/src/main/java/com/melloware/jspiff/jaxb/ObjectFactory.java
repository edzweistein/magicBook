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


package com.melloware.jspiff.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.melloware.jspiff.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Playlist_QNAME = new QName("http://xspf.org/ns/0/", "playlist");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.melloware.jspiff.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MetaType }
     * 
     */
    public MetaType createMetaType() {
        return new MetaType();
    }

    /**
     * Create an instance of {@link PlaylistType }
     * 
     */
    public PlaylistType createPlaylistType() {
        return new PlaylistType();
    }

    /**
     * Create an instance of {@link TrackType }
     * 
     */
    public TrackType createTrackType() {
        return new TrackType();
    }

    /**
     * Create an instance of {@link AttributionType }
     * 
     */
    public AttributionType createAttributionType() {
        return new AttributionType();
    }

    /**
     * Create an instance of {@link LinkType }
     * 
     */
    public LinkType createLinkType() {
        return new LinkType();
    }

    /**
     * Create an instance of {@link TrackListType }
     * 
     */
    public TrackListType createTrackListType() {
        return new TrackListType();
    }

    /**
     * Create an instance of {@link ExtensionType }
     * 
     */
    public ExtensionType createExtensionType() {
        return new ExtensionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlaylistType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://xspf.org/ns/0/", name = "playlist")
    public JAXBElement<PlaylistType> createPlaylist(PlaylistType value) {
        return new JAXBElement<PlaylistType>(_Playlist_QNAME, PlaylistType.class, null, value);
    }

}
