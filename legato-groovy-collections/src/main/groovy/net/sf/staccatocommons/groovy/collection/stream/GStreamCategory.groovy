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

import net.sf.staccatocommons.collections.stream.Stream
import net.sf.staccatocommons.legato.lang.groovy.GBlock
import net.sf.staccatocommons.legato.lang.groovy.GFunction
import net.sf.staccatocommons.legato.lang.groovy.GPredicate

/**
 * @author flbulgarelli
 * 
 */
@Category(Stream)
class GStreamCategory {

  Stream map(Closure closure) {
    map GFunction.from(closure)
  }

  Stream filter(Closure closure) {
    filter GPredicate.from(closure)
  }

  Stream incorporate(Closure closure) {
    incorporate GFunction.from(closure)
  }

  Stream each(Closure closure) {
    each GBlock.from(closure)
  }

  Stream all(Closure closure) {
    all GBlock.from(closure)
  }

  Stream any(Closure closure) {
    all GBlock.from(closure)
  }

  void forEach(Closure closure) {
    forEach GBlock.from(closure)
  }


  Stream flatMap(Closure closure) {
    flatMap GFunction.from(closure)
  }

  //GDK integration

  Stream collect(Closure closure) {
    map closure
  }

  Stream findAll(Closure closure) {
    filter closure
  }

  Stream every(Closure closure) {
    all closure
  }

  def asType(Class clazz) {
    if(clazz == Set)
      toSet()
    else if(clazz == Collection || clazz == List )
      toList()
    else
      throw new ClassCastException("Can not convert Stream to $clazz")
  }
}
