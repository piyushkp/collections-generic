// GenericsNote: Converted.
/*
 *  Copyright 1999-2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.commons.collections15.iterators;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * As the wrapped Iterator is traversed, ListIteratorWrapper
 * builds a LinkedList of its values, permitting all required
 * operations of ListIterator.
 *
 * @author Morgan Delagrange
 * @author Matt Hall, John Watkinson, Stephen Colebourne
 * @version $Revision: 1.1 $ $Date: 2005/10/11 17:05:24 $
 * @since Commons Collections 2.1
 */
public class ListIteratorWrapper <E> implements ListIterator<E> {

    /**
     * Holds value of property "iterator"
     */
    private final Iterator<E> iterator;
    private final LinkedList<E> list = new LinkedList<E>();

    // position of this iterator
    private int currentIndex = 0;
    // position of the wrapped iterator
    // this Iterator should only be used to populate the list
    private int wrappedIteratorIndex = 0;

    private static final String UNSUPPORTED_OPERATION_MESSAGE = "ListIteratorWrapper does not support optional operations of ListIterator.";

    // Constructor
    //-------------------------------------------------------------------------

    /**
     * Constructs a new <code>ListIteratorWrapper</code> that will wrap
     * the given iterator.
     *
     * @param iterator the iterator to wrap
     * @throws NullPointerException if the iterator is null
     */
    public ListIteratorWrapper(Iterator<E> iterator) {
        super();
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        this.iterator = iterator;
    }

    // ListIterator interface
    //-------------------------------------------------------------------------

    /**
     * Throws {@link UnsupportedOperationException}.
     *
     * @param o ignored
     * @throws UnsupportedOperationException always
     */
    public void add(E o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }


    /**
     * Returns true if there are more elements in the iterator.
     *
     * @return true if there are more elements
     */
    public boolean hasNext() {
        if (currentIndex == wrappedIteratorIndex) {
            return iterator.hasNext();
        }

        return true;
    }

    /**
     * Returns true if there are previous elements in the iterator.
     *
     * @return true if there are previous elements
     */
    public boolean hasPrevious() {
        if (currentIndex == 0) {
            return false;
        }

        return true;
    }

    /**
     * Returns the next element from the iterator.
     *
     * @return the next element from the iterator
     * @throws NoSuchElementException if there are no more elements
     */
    public E next() throws NoSuchElementException {
        if (currentIndex < wrappedIteratorIndex) {
            ++currentIndex;
            return list.get(currentIndex - 1);
        }

        E retval = iterator.next();
        list.add(retval);
        ++currentIndex;
        ++wrappedIteratorIndex;
        return retval;
    }

    /**
     * Returns in the index of the next element.
     *
     * @return the index of the next element
     */
    public int nextIndex() {
        return currentIndex;
    }

    /**
     * Returns the the previous element.
     *
     * @return the previous element
     * @throws NoSuchElementException if there are no previous elements
     */
    public E previous() throws NoSuchElementException {
        if (currentIndex == 0) {
            throw new NoSuchElementException();
        }

        --currentIndex;
        return list.get(currentIndex);
    }

    /**
     * Returns the index of the previous element.
     *
     * @return the index of the previous element
     */
    public int previousIndex() {
        return currentIndex - 1;
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException always
     */
    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    /**
     * Throws {@link UnsupportedOperationException}.
     *
     * @param o ignored
     * @throws UnsupportedOperationException always
     */
    public void set(E o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

}

