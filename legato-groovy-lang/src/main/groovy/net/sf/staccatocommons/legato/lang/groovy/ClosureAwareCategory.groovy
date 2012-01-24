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
package net.sf.staccatocommons.legato.lang.groovy

import net.sf.staccatocommons.defs.Applicable
import net.sf.staccatocommons.defs.Applicable2
import net.sf.staccatocommons.defs.Evaluable
import net.sf.staccatocommons.defs.Executable
import net.sf.staccatocommons.defs.Thunk


/**
 * @author flbulgarelli
 * 
 */
@Category(Object)
class ClosureAwareCategory {



  def methodMissing(String name, args) {
    def missingMethod = {
      new MissingMethodException(name, this.class, args)
    }
    def convertClosure = { closure, targetType ->
      switch(targetType) {
        case Executable:
          return { GBlock.from it }
        case Evaluable:
          return { GPredicate.from it }
        case Thunk:
          return { GThunk.from it }
        case Applicable2:
          return { GFunction2.from it}
        case Applicable:
          return { GFunction.from it }
        default:
          return null
      }
    }
    def closure = args.find()
    if(args.size() != 1 || !(closure instanceof Closure))
      throw missingMethod()

    def targetType = this.respondsTo(name).find {
      it.parameterTypes.size() == 1
    }?.parameterTypes?.find()
    if(!targetType)
      throw missingMethod()

    def conversion = convertClosure(closure, targetType)
    if(!conversion)
      throw missingMethod()

    this.class.metaClass."$name" << { Closure c ->
      this.invokeMethod(name, conversion(c))
    }

    invokeMethod(name, args)
  }
}
