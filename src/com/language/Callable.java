/////////////////////////////////////////////////
//                                             //
//       Author : Prem Chilumula               //
//                                             //
/////////////////////////////////////////////////
package com.language;

import java.util.List;

interface Callable {
    Object call(Interpreter interpreter, List<Object> arguments);

    int arity();
}