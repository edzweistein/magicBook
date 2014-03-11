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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * <b>IXspfExtensionMixedChoice</b> is generated from xspf.rng by Relaxer.
 * A concrete class of the interface is XspfExtensionAnyElement.
 * <p>
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public interface IXspfExtensionMixedChoice
    extends IXspfExtensionMixed {

    /**
     * @return IXspfAnythingMixed[]
     */
    IXspfAnythingMixed[] getContent();

    /**
     * @param index
     * @return IXspfAnythingMixed
     */
    IXspfAnythingMixed getContent(int index);

    /**
     * @param value
     */
    void setContent(String value);

    /**
     * @param value
     */
    void setContent(org.w3c.dom.Node value);

    /**
     * @param content
     */
    void setContent(IXspfAnythingMixed[] content);

    /**
     * @param content
     */
    void setContent(IXspfAnythingMixed content);

    /**
     * @param index
     * @param content
     */
    void setContent(int index, IXspfAnythingMixed content);

    /**
     * @param value
     */
    void setContentByString(String value);

    /**
     * @param value
     */
    void addContent(String value);

    /**
     * @param value
     */
    void addContent(org.w3c.dom.Node value);

    /**
     * @param content
     */
    void addContent(IXspfAnythingMixed content);

    /**
     * @param content
     */
    void addContent(IXspfAnythingMixed[] content);

    /**
     * @param index
     * @param content
     */
    void addContent(int index, IXspfAnythingMixed content);

    /**
     * @param value
     */
    void addContentByString(String value);

    /**
     */
    void clearContent();

    /**
     * @exception ParserConfigurationException
     * @return Document
     */
    Document makeDocument()
                   throws ParserConfigurationException;

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    void makeTextAttribute(StringBuffer buffer);

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    void makeTextAttribute(Writer buffer)
                    throws IOException;

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    void makeTextAttribute(PrintWriter buffer);

    /**
     * @return String
     */
    String makeTextDocument();

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    void makeTextElement(StringBuffer buffer);

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     * @exception IOException
     */
    void makeTextElement(Writer buffer)
                  throws IOException;

    /**
     * Makes an XML text representation.
     *
     * @param buffer
     */
    void makeTextElement(PrintWriter buffer);

    /**
     * @param index
     */
    void removeContent(int index);

    /**
     * @param content
     */
    void removeContent(IXspfAnythingMixed content);

    /**
     * @param source
     */
    void setup(XspfExtensionAnyElement source);

    /**
     * @param doc
     */
    void setup(Document doc);

    /**
     * @param element
     */
    void setup(Element element);

    /**
     * @param file
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    void setup(File file)
        throws IOException, SAXException, ParserConfigurationException;

    /**
     * @param uri
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    void setup(String uri)
        throws IOException, SAXException, ParserConfigurationException;

    /**
     * @param url
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    void setup(URL url)
        throws IOException, SAXException, ParserConfigurationException;

    /**
     * @param in
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    void setup(InputStream in)
        throws IOException, SAXException, ParserConfigurationException;

    /**
     * @param is
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    void setup(InputSource is)
        throws IOException, SAXException, ParserConfigurationException;

    /**
     * @param reader
     * @exception IOException
     * @exception SAXException
     * @exception ParserConfigurationException
     */
    void setup(Reader reader)
        throws IOException, SAXException, ParserConfigurationException;

    /**
     * @return int
     */
    int sizeContent();
}