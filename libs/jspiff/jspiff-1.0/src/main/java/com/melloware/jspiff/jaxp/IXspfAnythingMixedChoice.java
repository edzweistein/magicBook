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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * <b>IXspfAnythingMixedChoice</b> is generated from xspf.rng by Relaxer.
 * Concrete classes of the interface are XspfAnythingAnyAttr and 
 * XspfAnythingAnyElement.
 * <p>
 * @version xspf.rng (Wed Sep 27 17:36:25 EDT 2006)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public interface IXspfAnythingMixedChoice
    extends IXspfAnythingMixed {

    /**
     * @param value
     */
    void setContent(String value);

    /**
     * @param value
     */
    void setContentByString(String value);

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
}