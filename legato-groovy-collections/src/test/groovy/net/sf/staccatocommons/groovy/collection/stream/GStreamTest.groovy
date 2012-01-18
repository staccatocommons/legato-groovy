/**
 *  Copyright (c) 2012, The StaccatoCommons Team
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation; version 3 of the License.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 */

/*
 Copyright (c) 2012, The Staccato-Commons Team
 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU Lesser General Public License as published by
 the Free Software Foundation; version 3 of the License.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General Public License for more details.
 */
package net.sf.staccatocommons.groovy.collection.stream

import net.sf.staccatocommons.collections.stream.Streams

import org.junit.Test

/**
 * @author flbulgarelli
 * 
 */
class GStreamTest {

  static {
    GStream.enableGlobally()
  }

  @Test void testStreamToList() {
    assert [4, 5, 9].toStream() as List == [4, 5, 9]
  }

  @Test void testListToStream() {
    assert [4, 5, 9].toStream().equiv([4, 5, 9])
  }

  @Test void testTake() {
    assert [4, 5, 9, 0, 10].toStream().collect { 0 / it }.take(3).toList() == [0, 0, 0]
  }

  @Test void testMap() {
    Streams.from([4, 5, 6]).map { it + 1 }.println()
  }

  @Test void testFilter() {
    Streams.from([4, 5, 6]).filter { it > 4 }.println()
  }
}
