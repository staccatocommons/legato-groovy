/**
 *  Copyright (c) 2011, The Staccato-Commons Team
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

package net.sf.staccatocommons.defs.groovy
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
import static org.junit.Assert.*

import java.util.concurrent.Executors

import net.sf.staccatocommons.control.monad.Monad
import net.sf.staccatocommons.control.monad.Monads
import net.sf.staccatocommons.defs.reduction.Accumulator
import net.sf.staccatocommons.lang.Compare
import net.sf.staccatocommons.lang.Option

import org.codehaus.groovy.tools.shell.IO
import org.junit.BeforeClass
import org.junit.Test

/**
 * @author flbulgarelli
 * 
 */
// @RunWith(Theories.class)
class MonadTest {

  @BeforeClass
  static void setup() {
    Monad.mixin(GMonadCategory)
  }

  /***/
  @Test
  void testname()  {
    final col = []
    Monads//
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
  void testKleisli()  {
    Monads //
      .from(4, 5, 6)
      .bind(map(add(5)) //
      .then(map(add(1)))
      .then(map(add(90)))
      .then(filter(Compare.greaterThan(2)))
      .then(each(IO.printlnSysout())))
      .run()
  }

  /***/
  @Test
  void testAsync()  {
    Monads //
      .cons(4)
      .map(logThread)
      .map { it + 1 }
      .fork(Executors.newSingleThreadExecutor())
      .map(logThread)
      .run()
    Thread.sleep(1000)
  }

  /***/
  @Test
  void testIterable()  {
    Monads //
      .from(4, 5, 6, 9)
      .map { it + 1 }
      .filter { it > 6 }
      .each { print it }
      .run()

  }

  def logThread = { println(Thread.currentThread()); it }

  /***/
  @Test
  void testSkipAfterEffect()  {
    Accumulator accum = Reductions.sum().newAccumulator()
    Monads.from(10, 20, 3).each(accumulate(accum)).skip(20).run()
    assertEquals(33, accum.value())
  }

  /***/
  @Test
  void testSkipBeforeEffect()  {
    Accumulator accum = Reductions.sum().newAccumulator()
    Monads.from(10, 20, 3).skip(20).each(accumulate(accum)).run()
    assertEquals(13, accum.value())
  }

  /***/
  @Test
  void testAppendBeforeEffect()  {
    Accumulator accum = Reductions.sum().newAccumulator()
    Monads.from(10, 3).append(Monads.from(10)).each(accumulate(accum)).run()
    assertEquals(23, accum.value())
  }

  /***/
  @Test
  void fromOption()  {
    Accumulator accum = Reductions
      .append()
      .newAccumulator()

    Monads.from(Option.some("hello")).clone(Strings.length()).each(accumulate(accum)).run()
    assertEquals(5, (int) Maps.from(accum.value()).get("hello"))

  }

  /***/
  // TODO move to reductions
  def accumulate(final Accumulator accumularor) {
    block { accumularor.accumulate(it) }
  }
}
