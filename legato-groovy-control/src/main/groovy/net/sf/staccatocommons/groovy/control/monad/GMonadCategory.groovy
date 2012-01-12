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
package net.sf.staccatocommons.groovy.control.monad

import groovy.lang.Closure
import net.sf.staccatocommons.control.monad.Monad
import net.sf.staccatocommons.legato.lang.groovy.GBlock
import net.sf.staccatocommons.legato.lang.groovy.GFunction
import net.sf.staccatocommons.legato.lang.groovy.GPredicate

/**
 * @author flbulgarelli
 * 
 */
@Category(Monad)
class GMonadCategory {

  Monad bind(Closure closure) {
    bind GFunction.from(closure)
  }

  Monad map(Closure closure) {
    map GFunction.from(closure)
  }

  /* Filtering */

  Monad filter(Closure closure) {
    filter GPredicate.from(closure)
  }

  Monad incorporate(Closure closure) {
    incorporate GFunction.from(closure)
  }

  Monad each(Closure closure) {
    each GBlock.from(closure)
  }

  void forEach(Closure closure) {
    forEach GBlock.from(closure)
  }


  Monad flatMap(Closure closure) {
    flatMap GFunction.from(closure)
  }

  //TODO clone & branch
}
