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

/**
 * @author flbulgarelli
 * 
 */
class GStreamConvertionCategory {

  static def toStream(Collection self) {
    GStreams.from(self)
  }

  static def toStream(Iterable self) {
    GStreams.from(self)
  }

  static def toStream(Object self) {
    GStreams.from(self.iterator())
  }

  static def toStream(Iterator self) {
    GStreams.from(self)
  }

  static def toStream(List self) {
    GStreams.from(self)
  }
}
