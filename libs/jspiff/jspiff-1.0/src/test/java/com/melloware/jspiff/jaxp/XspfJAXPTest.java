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

package com.melloware.jspiff.jaxp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import junit.framework.TestCase;

/**
 * Test case to verify creating of XspfPlaylists and adding Tracks to those
 * playlists.  This test if for the JAXP code.
 * <p>
 * Copyright (c) 2006
 * Melloware, Inc. <http://www.melloware.com>
 * @author Emil A. Lefkof III <info@melloware.com>
 * @version 4.0
 */
public class XspfJAXPTest
    extends TestCase {

    /**
     * Constructor for XspfJAXPTest.
     * @param arg0
     */
    public XspfJAXPTest(String arg0) {
        super(arg0);
    }

    /**
     * Tests an invalid playlist file.
     */
    public void testInvalidPlaylistFile() {
        // create the file
        try {
            final InputStream stream = getClass().getResourceAsStream("/playlist-invalid.xml");
            final XspfPlaylist playlist = new XspfPlaylist(stream);
            final String xml = playlist.makeTextDocument();
            System.out.println(xml);
            assertNotNull(xml);
            assertNotSame(playlist.getTitle(), "XSPlF it up!");
            assertNull(playlist.getPlaylistTrackList());
        } catch (IOException ex) {
            fail(ex.getMessage());
        } catch (SAXException ex) {
            fail(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Tests the creation of an XspfPlaylist object.
     */
    public void testPlaylist() {
        final XspfPlaylist playlist = new XspfPlaylist();
        playlist.setTitle("Melloware XSPF Playlist");
        playlist.setCreator("Melloware User");
        playlist.setDate(new Timestamp(System.currentTimeMillis()));
        playlist.setInfo("http://melloware.com/");
        playlist.setVersion("1");
        playlist.setImage("http://melloware.com/images/header.jpg");
        playlist.setIdentifier(Integer.toString(super.hashCode()));
        playlist.setLicense("http://www.apache.org/licenses/LICENSE-2.0.txt");

        // make an xml document out of it
        final String xml = playlist.makeTextDocument();
        System.out.println(xml);
        assertNotNull(xml);

    }

    /**
     * Tests the creation of an XspfTrack objects and appending them to a
     * playlist.
     */
    public void testTracks() {
        final XspfPlaylist playlist = new XspfPlaylist();
        playlist.setTitle("Track Test Playlist");
        playlist.setCreator("Melloware User");
        playlist.setDate(new Timestamp(System.currentTimeMillis()));
        playlist.setInfo("http://melloware.com/");
        playlist.setVersion("1");
        playlist.setImage("http://melloware.com/images/header.jpg");
        playlist.setIdentifier(Integer.toString(super.hashCode()));
        playlist.setLicense("http://www.apache.org/licenses/LICENSE-2.0.txt");

        // create track list first
        final XspfPlaylistTrackList tracks = new XspfPlaylistTrackList();

        // now create track 1 and add to list
        XspfTrack track = new XspfTrack();
        track.setIdentifier("135c3af5-526f-4d87-9757-1b404b51e31d");
        track.setLocation("C:\\music\\01 - She Talks To Angels.mp3");
        track.setCreator("Black Crowes");
        track.setAlbum("Shake Your Moneymaker");
        track.setTitle("She Talks To Angels");
        track.setAnnotation("This is a classic song");
        track.setTrackNumByString("01");
        track.setDurationByString("314");
        tracks.addTrack(track);

        // now create track 2 and add to list
        track = new XspfTrack();
        track.setIdentifier("e113c56f-c4d4-4bfb-b9f0-6f90a172b5a9");
        track.setLocation("C:\\music\\02 - Come Together.mp3");
        track.setCreator("Beatles");
        track.setAlbum("Abbey Road");
        track.setTitle("Come Together");
        track.setAnnotation("Love the Beatles");
        track.setTrackNumByString("02");
        track.setDurationByString("226");
        tracks.addTrack(track);

        // add track to playlist
        playlist.setPlaylistTrackList(tracks);

        // make an xml document out of it
        final String xml = playlist.makeTextDocument();
        System.out.println(xml);
        assertNotNull(xml);
    }

    /**
     * Tests a valid playlist file.
     */
    public void testValidPlaylistFileVersion0() {
        // create the file
        try {
            final InputStream stream = getClass().getResourceAsStream("/playlist-valid-ver0.xml");

            final XspfPlaylist playlist = new XspfPlaylist(stream);
            assertEquals(playlist.getTitle(), "XSPlF it up!");

            final XspfTrack[] array = playlist.getPlaylistTrackList().getTrack();
            for (int i = 0; i < array.length; i++) {
                XspfTrack element = (XspfTrack)array[i];
                assertNotNull(element.makeTextDocument());

                assertEquals(element.sizeMeta(), 1);
            }
            assertEquals(playlist.getAttribution_Location().length, 1);
            assertEquals(playlist.getPlaylistTrackList().sizeTrack(), 3);

            // make an xml document out of it
            final String xml = playlist.makeTextDocument();
            System.out.println(xml);
            assertNotNull(xml);
        } catch (IOException ex) {
            fail(ex.getMessage());
        } catch (SAXException ex) {
            fail(ex.getMessage());
        } catch (ParserConfigurationException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Tests a valid playlist file.
     */
    public void testValidPlaylistFileVersion1() {
        // create the file
        try {
            final InputStream stream = getClass().getResourceAsStream("/playlist-valid-ver1.xml");

            final XspfPlaylist playlist = new XspfPlaylist(stream);
            assertEquals(playlist.getTitle(), "Track Test Playlist");

            final XspfTrack[] array = playlist.getPlaylistTrackList().getTrack();
            for (int i = 0; i < array.length; i++) {
                XspfTrack element = (XspfTrack)array[i];
                assertNotNull(element.makeTextDocument());

            }
            assertEquals(playlist.getPlaylistTrackList().sizeTrack(), 2);

            // make an xml document out of it
            final String xml = playlist.makeTextDocument();
            System.out.println(xml);
            assertNotNull(xml);
        } catch (IOException ex) {
            fail(ex.getMessage());
        } catch (SAXException ex) {
            fail(ex.getMessage());
        } catch (ParserConfigurationException ex) {
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