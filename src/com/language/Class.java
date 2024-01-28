/////////////////////////////////////////////////
//                                             //
//       Author : Prem Chilumula               //
//                                             //
/////////////////////////////////////////////////
package com.language;
import java.util.List;
import java.util.Map;
public class Class implements Callable {
    final String name;
    private Class superclass;
    private final Map<String, Function> methods;

    public Class(String name, Class superclass, Map<String, Function> methods) {
        this.name = name;
        this.superclass = superclass;
        this.methods = methods;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Instance instance = new Instance(this);
        var initializer = findMethod("init");
        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }
        return instance;
    }

    @Override
    public int arity() {
        var initializer = findMethod("init");
        return initializer == null ? 0 : initializer.arity();
    }

    public Function findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }
        if (superclass != null) {
            return superclass.findMethod(name);
        }
        return null;
    }
}