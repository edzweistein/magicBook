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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

/**
 * RStack
 *
 * @since   Mar.  8, 2000
 * @version Aug.  7, 2004
 * @author  ASAMI, Tomoharu (asami@relaxer.org)
 * @author Emil A. Lefkof III <info@melloware.com>
 */
public final class RStack {
    private Element element_;
    // Map<String, String>
    private HashMap pi_ = new HashMap();
    // Set<String>
    // private HashSet consumedAttrs_ = new HashSet();
    // Set<Attr>
    private HashSet consumedAttrNodes_ = null;
    // Set<Element>
    private HashSet consumedElementNodes_ = null;
    private int index_;
    // private Element lastPoppedElemnt_ = null;
    private Object[] children_;

    public RStack(Element element) {
        element_ = element;
        NodeList nodes = element.getChildNodes();
        List list = new ArrayList();
        _makeList(nodes, list);
        int size = list.size();
        children_ = new Object[size];
        children_ = list.toArray(children_);
        index_ = 0;
    }

    protected RStack() {
    }

    public Element getContextElement() {
        return (element_);
    }

    /*
     *  public boolean hasAttributeHungry(String name) {     if (isConsumedAttribute(name)) {         return (false);
     *  }     setCosumedAttribute(name);     return (URelaxer.hasAttribute(element_, name)); }
     */

    /*
     *  public boolean isConsumedAttribute(String name) {     return (consumedAttrs_.contains(name)); }
     *
     * public boolean isConsumedAttribute(String uri, String name) {     return (consumedAttrs_.contains(uri + "$" +
     * name)); }
     *
     * public void setConsumedAttribute(String name) {     if (isConsumedAttribute(name)) {         throw (new
     * InternalError());     }     consumedAttrs_.add(name); }
     *
     * public void setConsumedAttribute(String uri, String name) {     String fullname = uri + "$" + name;     if
     * (isConsumedAttribute(fullname)) {         throw (new InternalError());     }     consumedAttrs_.add(fullname); }
     */

    public Map getPIMap() {
        return ((Map)pi_.clone());
    }

    public boolean isConsumedAttribute(Attr attr) {
        if (consumedAttrNodes_ == null) {
            return (false);
        }
        return (consumedAttrNodes_.contains(attr));
    }

    public boolean isConsumedElement(Attr attr) {
        if (consumedElementNodes_ == null) {
            return (false);
        }
        return (consumedElementNodes_.contains(attr));
    }

    /*
     *  public Element getLastPoppedElement() {     return (lastPoppedElemnt_); }
     */

    public boolean isEmpty() {
        return (index_ == children_.length);
    }

    public boolean isEmptyElement() {
        if (index_ == children_.length) {
            return (true);
        }
        for (int i = index_; i < children_.length; i++) {
            if (children_[i] instanceof Element) {
                return (false);
            }
        }
        return (true);
    }

    public void addDirectAttributes(Map map) {
        NamedNodeMap attrs = element_.getAttributes();
        int size = attrs.getLength();
        for (int i = 0; i < size; i++) {
            Attr attr = (Attr)attrs.item(i);
            if (!isConsumedAttribute(attr)) {
                map.put(attr.getName(), attr.getValue());
            }
        }
    }

    public void consumeAttribute(Attr attr) {
        if (attr == null) {
            return;
        }
        if (consumedAttrNodes_ == null) {
            consumedAttrNodes_ = new HashSet();
        }
        consumedAttrNodes_.add(attr);
    }

    public void consumeElement(Attr attr) {
        if (consumedElementNodes_ == null) {
            consumedElementNodes_ = new HashSet();
        }
        consumedElementNodes_.add(attr);
    }

    public void eat(RStack stack) {
        element_ = stack.element_;
        children_ = stack.children_;
        consumedAttrNodes_ = stack.consumedAttrNodes_;
        consumedElementNodes_ = stack.consumedElementNodes_;
        pi_ = stack.pi_;
        index_ = stack.index_;
    }

    public RStack makeClone() {
        RStack newStack = new RStack();
        newStack.element_ = element_;
        newStack.children_ = (Object[])children_.clone();
        if (consumedAttrNodes_ != null) {
            newStack.consumedAttrNodes_ = (HashSet)consumedAttrNodes_.clone();
        }
        if (consumedElementNodes_ != null) {
            newStack.consumedElementNodes_ = (HashSet)consumedElementNodes_.clone();
        }
        newStack.pi_ = (HashMap)pi_.clone();
        newStack.index_ = index_;
        return (newStack);
    }

    public Object peek() {
        if (index_ == children_.length) {
            return (null);
        }
        return (children_[index_]);
    }

    public Element peekElement() {
        if (index_ == children_.length) {
            return (null);
        }
        for (int i = index_; i < children_.length; i++) {
            Object node = children_[i];
            if (node instanceof Element) {
                return ((Element)node);
            }
        }
        return (null);
    }

    public Element[] peekElements() {
        if (index_ == children_.length) {
            return (null);
        }
        List list = new ArrayList();
        for (int i = index_; i < children_.length; i++) {
            Object node = children_[i];
            if (node instanceof Element) {
                list.add(node);
            }
        }
        Element[] elements = new Element[list.size()];
        return ((Element[])list.toArray(elements));
    }

    public Object pop() {
        return (children_[index_++]);
    }

    public Element popElement() {
        if (index_ == children_.length) {
            return (null);
        }
        while (index_ < children_.length) {
            Object node = children_[index_++];
            if (node instanceof Element) {
                // lastPoppedElemnt_ = (Element)node;
                // return (lastPoppedElemnt_);
                return ((Element)node);
            }
        }
        return (null);
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        if (index_ < children_.length) {
            Object object = children_[index_];
            buffer.append(object);
            for (int i = index_ + 1; i < children_.length; i++) {
                buffer.append(",");
                object = children_[i];
                buffer.append(object);
            }
        }
        buffer.append("]");
        return (new String(buffer));
    }

    private void _makeList(NodeList nodes, List list) {
        int size = nodes.getLength();
        StringBuffer buffer = null;
        for (int i = 0; i < size; i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                if (buffer != null) {
                    list.add(new String(buffer));
                    buffer = null;
                }
                list.add(node);
            } else if (node instanceof Text) {
                if (buffer == null) {
                    buffer = new StringBuffer();
                }
                buffer.append(((Text)node).getData());
            } else if (node instanceof ProcessingInstruction) {
                ProcessingInstruction pi = (ProcessingInstruction)node;
                pi_.put(pi.getTarget(), pi.getData());
            } else if (node instanceof EntityReference) {
                _makeList(node.getChildNodes(), list);
            }
        }
        if (buffer != null) {
            list.add(new String(buffer));
        }
    }
}