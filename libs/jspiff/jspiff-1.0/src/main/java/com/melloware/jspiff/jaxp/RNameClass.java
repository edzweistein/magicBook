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
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

/**
 * RNameClass
 *
 * @since   May. 19, 2002
 * @version May. 17, 2003
 * @author  ASAMI, Tomoharu (asami@relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public final class RNameClass {
    private Node pattern_;

    public RNameClass(String config) {
        try {
            Document doc = UJAXP.getDocument(new StringReader(config));
            pattern_ = _makePattern(doc.getDocumentElement());
        } catch (IOException e) {
            throw (new IllegalArgumentException());
        } catch (SAXException e) {
            throw (new IllegalArgumentException());
        } catch (ParserConfigurationException e) {
            throw (new IllegalArgumentException());
        }
    }

    public String[] getAttributeHungry(RStack stack) {
        Element element = stack.getContextElement();
        NamedNodeMap attrs = element.getAttributes();
        int size = attrs.getLength();
        for (int i = 0; i < size; i++) {
            Attr attr = (Attr)attrs.item(i);
            if (stack.isConsumedAttribute(attr)) {
                continue;
            }
            String attrName = attr.getName();
            if (pattern_.eval("", attrName)) {
                stack.consumeAttribute(attr);
                String[] result = new String[2];
                result[0] = attrName;
                result[1] = element.getAttribute(attrName);
                return (result);
            }
        }
        return (null);
    }

    /*
     *  public boolean isMatchAttribute(Element element, String name) {     return (pattern_.eval("", name)); }
     */

    public boolean isMatchAttributeHungry(RStack stack) {
        Element element = stack.getContextElement();
        NamedNodeMap attrs = element.getAttributes();
        int size = attrs.getLength();
        for (int i = 0; i < size; i++) {
            Attr attr = (Attr)attrs.item(i);
            String attrName = attr.getName();
            if (stack.isConsumedAttribute(attr)) {
                continue;
            }
            if (pattern_.eval("", attrName)) {
                stack.consumeAttribute(attr);
                return (true);
            }
        }
        return (false);
    }

    public boolean isMatchElement(Element element) {
        return (pattern_.eval("", element.getTagName()));
    }

    private Node _makePattern(Element element) {
        String tagName = element.getTagName();
        Node node;
        if ("anyName".equals(tagName)) {
            node = new AnyName();
        } else if ("nsName".equals(tagName)) {
            node = new NsName(element.getAttribute("ns"));
        } else if ("name".equals(tagName)) {
            String ns = element.getAttribute("ns");
            String name = URelaxer.getElementPropertyAsString(element);
            node = new Name(ns, name);
            return (node);
        } else if ("choice".equals(tagName)) {
            node = new Choice();
        } else if ("except".equals(tagName)) {
            node = new Except();
        } else {
            throw (new IllegalArgumentException());
        }
        Element[] children = URelaxer.getElements(element);
        for (int i = 0; i < children.length; i++) {
            node.addChild(_makePattern(children[i]));
        }
        return (node);
    }

    public static class AnyName
        extends Node {
    }

    public static class Choice
        extends Node {
        public boolean eval(String ns, String local) {
            int size = children_.size();
            for (int i = 0; i < size; i++) {
                Node node = (Node)children_.get(i);
                if (node.eval(ns, local)) {
                    return (true);
                }
            }
            return (false);
        }
    }

    public static class Except
        extends Node {
        public boolean eval(String ns, String local) {
            return (!_evalChildren(ns, local));
        }
    }

    public static class Name
        extends Node {
        private String name_;
        private String ns_;

        public Name(String ns, String name) {
            ns_ = ns;
            name_ = name;
        }

        protected boolean _evalBody(String ns, String local) {
            return (ns_.equals(ns) && name_.equals(local));
        }
    }

    public static class Node {
        protected List children_ = new ArrayList();

        public void addChild(Node child) {
            children_.add(child);
        }

        public boolean eval(String ns, String local) {
            if (!_evalBody(ns, local)) {
                return (false);
            }
            return (_evalChildren(ns, local));
        }

        protected boolean _evalBody(String ns, String local) {
            return (true);
        }

        protected boolean _evalChildren(String ns, String local) {
            int size = children_.size();
            for (int i = 0; i < size; i++) {
                Node node = (Node)children_.get(i);
                if (!node.eval(ns, local)) {
                    return (false);
                }
            }
            return (true);
        }
    }

    public static class NsName
        extends Node {
        private String ns_;

        public NsName(String ns) {
            ns_ = ns;
        }

        protected boolean _evalBody(String ns, String local) {
            return (ns_.equals(ns));
        }
    }
}