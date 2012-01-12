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

package net.sf.staccatocommons.groovy.control.monad
/*
 Copyright (c) 2011, The Staccato-Commons Team
 This program is free software you can redistribute it and/or modify
 it under the terms of the GNU Lesser General License as published by
 the Free Software Foundation version 3 of the License.
 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Lesser General License for more details.
 */
import static net.sf.staccatocommons.control.monad.Monads.*
import static org.junit.Assert.*

import java.util.concurrent.Executors

import org.junit.Test

/**
 * @author flbulgarelli
 * 
 */
class GMonadTest {


  /***/
  @Test
  void testname()  {
    final col = []
    GMonads//
      .cons(4)
      .map { it + 5 }
      .map { it + 1 }
      .filter { it > 2 }
      .each { col << it }
      .each { print it }
      .run()

    assert 1 ==  col.size()
  }

  /***/
  @Test
  void testAsync()  {
    GMonads //
      .cons(4)
      .each(logThread)
      .map { it + 1 }
      .fork(Executors.newSingleThreadExecutor())
      .each(logThread)
      .run()
    Thread.sleep(1000)
  }

  /***/
  @Test
  void testIterable()  {
    GMonads //
      .from(4, 5, 6, 9)
      .map { it + 1 }
      .filter { it > 6 }
      .each { print it }
      .run()

  }

  def logThread = { println(Thread.currentThread()) }

}
