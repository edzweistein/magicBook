/**
 * JSpiff
 * -----------------
 * Copyright 2005-2006 Emil A. Lefkof III
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

import java.io.InputStream;
import java.math.BigInteger;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;

import junit.framework.TestCase;

/**
 * Test case to test JAXB playlists.
 * <p>
 * Copyright (c) 2006
 * Melloware, Inc. <http://www.melloware.com>
 * @author Emil A. Lefkof III <elefkof@ksmpartners.com>
 * @version 4.0
 */
public class XspfJAXBTest
    extends TestCase {

    /**
     * Constructor for XspfJAXBTest.
     * @param arg0
     */
    public XspfJAXBTest(String arg0) {
        super(arg0);
    }

    /**
     * Tests an invalid playlist file
     */
    public void testInvalidPlaylistFile() {
        try {
            // create the file
            final InputStream stream = getClass().getResourceAsStream("/playlist-invalid.xml");

            JAXBContext jc = JAXBContext.newInstance("com.melloware.jspiff.jaxb");

            Unmarshaller u = jc.createUnmarshaller();
            JAXBElement element = (JAXBElement)u.unmarshal(stream);
            PlaylistType playlist = (PlaylistType)element.getValue();
            System.out.println(playlist.getTitle());
        } catch (PropertyException ex) {
            fail(ex.getMessage());
        } catch (JAXBException ex) {
            // don't fail,should get a JAXBExcption
            ;

        }
    }

    /**
     * Tests the creation of an XspfPlaylist object.
     */
    public void testPlaylist() {
        try {
            final ObjectFactory factory = new ObjectFactory();
            final PlaylistType playlist = factory.createPlaylistType();
            playlist.setTitle("Melloware XSPF Playlist");
            playlist.setCreator("Melloware User");
            playlist.setDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(2006, 9, 27, 6, 1,
                                                                                                      1, 1, 1));
            playlist.setInfo("http://melloware.com/");
            playlist.setVersion("1");
            playlist.setImage("http://melloware.com/images/header.jpg");
            playlist.setIdentifier(Integer.toString(super.hashCode()));
            playlist.setLicense("http://www.apache.org/licenses/LICENSE-2.0.txt");

            final JAXBElement element = factory.createPlaylist(playlist);
            // make an xml document out of it
            final JAXBContext jc = JAXBContext.newInstance("com.melloware.jspiff.jaxb");
            final Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(element, System.out);
            assertNotNull(element);
        } catch (PropertyException ex) {
            fail(ex.getMessage());
        } catch (DatatypeConfigurationException ex) {
            fail(ex.getMessage());
        } catch (JAXBException ex) {
            fail(ex.getMessage());
        }

    }

    /**
     * Tests the creation of an XspfTrack objects and appending them to a
     * playlist.
     */
    public void testTracks() {
        try {
            final ObjectFactory factory = new ObjectFactory();
            final PlaylistType playlist = factory.createPlaylistType();
            playlist.setTitle("Melloware XSPF Playlist");
            playlist.setCreator("Melloware User");
            playlist.setDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(2006, 9, 27, 6, 1,
                                                                                                      1, 1, 1));
            playlist.setInfo("http://melloware.com/");
            playlist.setVersion("1");
            playlist.setImage("http://melloware.com/images/header.jpg");
            playlist.setIdentifier(Integer.toString(super.hashCode()));
            playlist.setLicense("http://www.apache.org/licenses/LICENSE-2.0.txt");

            // create track list first
            final TrackListType tracks = factory.createTrackListType();

            // now create track 1 and add to list
            TrackType track = factory.createTrackType();
            track.getIdentifier().add("135c3af5-526f-4d87-9757-1b404b51e31d");
            track.getLocation().add("C:\\music\\01 - She Talks To Angels.mp3");
            track.setCreator("Black Crowes");
            track.setAlbum("Shake Your Moneymaker");
            track.setTitle("She Talks To Angels");
            track.setAnnotation("This is a classic song");
            track.setTrackNum(BigInteger.valueOf(1));
            track.setDuration(BigInteger.valueOf(314));
            tracks.getTrack().add(track);

            // now create track 2 and add to list
            track = factory.createTrackType();
            track.getIdentifier().add("e113c56f-c4d4-4bfb-b9f0-6f90a172b5a9");
            track.getLocation().add("C:\\music\\02 - Come Together.mp3");
            track.setCreator("Beatles");
            track.setAlbum("Abbey Road");
            track.setTitle("Come Together");
            track.setAnnotation("Love the Beatles");
            track.setTrackNum(BigInteger.valueOf(2));
            track.setDuration(BigInteger.valueOf(226));
            tracks.getTrack().add(track);

            // add track to playlist
            playlist.setTrackList(tracks);

            final JAXBElement element = factory.createPlaylist(playlist);
            // make an xml document out of it
            final JAXBContext jc = JAXBContext.newInstance("com.melloware.jspiff.jaxb");
            final Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(element, System.out);
            assertNotNull(element);
        } catch (PropertyException ex) {
            fail(ex.getMessage());
        } catch (DatatypeConfigurationException ex) {
            fail(ex.getMessage());
        } catch (JAXBException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Tests a valid playlist file version 0.
     */
    public void testValidPlaylistFileVersion0() {
        try {
            // create the file
            final InputStream stream = getClass().getResourceAsStream("/playlist-valid-ver0.xml");

            final JAXBContext jc = JAXBContext.newInstance("com.melloware.jspiff.jaxb");
            final Unmarshaller u = jc.createUnmarshaller();
            final JAXBElement element = (JAXBElement)u.unmarshal(stream);
            final PlaylistType playlist = (PlaylistType)element.getValue();

            assertEquals(playlist.getTitle(), "XSPlF it up!");

            for (Iterator iter = playlist.getTrackList().getTrack().iterator(); iter.hasNext();) {
                TrackType track = (TrackType)iter.next();
                assertNotNull(track.getTitle());
                assertEquals(track.getMeta().size(), 1);
            }

            assertEquals(playlist.getAttribution().getLocation().size(), 1);
            assertEquals(playlist.getTrackList().getTrack().size(), 3);

            // make an xml document out of it
            final Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(element, System.out);
        } catch (PropertyException ex) {
            fail(ex.getMessage());
        } catch (JAXBException ex) {
            fail(ex.getMessage());
        }
    }

    /**
    * Tests a valid playlist file version 1.
    */
    public void testValidPlaylistFileVersion1() {
        try {
            // create the file
            final InputStream stream = getClass().getResourceAsStream("/playlist-valid-ver1.xml");

            JAXBContext jc = JAXBContext.newInstance("com.melloware.jspiff.jaxb");
            Unmarshaller u = jc.createUnmarshaller();
            JAXBElement element = (JAXBElement)u.unmarshal(stream);
            PlaylistType playlist = (PlaylistType)element.getValue();

            assertEquals(playlist.getTitle(), "Track Test Playlist");

            for (Iterator iter = playlist.getTrackList().getTrack().iterator(); iter.hasNext();) {
                TrackType track = (TrackType)iter.next();
                assertNotNull(track.getTitle());
            }

            assertEquals(playlist.getTrackList().getTrack().size(), 2);

            // make an xml document out of it
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(element, System.out);
        } catch (PropertyException ex) {
            fail(ex.getMessage());
        } catch (JAXBException ex) {
            fail(ex.getMessage());
        }
    }

    /*
     * @see TestCase#setUp()
     */
    protected void setUp()
                  throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    protected void tearDown()
                     throws Exception {
        super.tearDown();
    }

}