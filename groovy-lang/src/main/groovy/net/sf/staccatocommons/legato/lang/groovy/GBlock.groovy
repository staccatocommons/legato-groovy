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

package net.sf.staccatocommons.legato.lang.groovy
import net.sf.staccatocommons.defs.Executable

/**
 * @author flbulgarelli
 * 
 */
class GBlock {

  static Executable from(Closure closure) {
    new Executable() {
        void exec(arg0) {
          closure(arg0)
        }
      }
  }
}
