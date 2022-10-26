package com.epam.mjc;

import com.sun.jdi.connect.Connector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        // private void log(String value)
        List<String> delimeters = List.of(" ", ",", "(", ")");

        StringSplitter stringSplitter = new StringSplitter();
        List<String> strings = stringSplitter.splitByDelimiters(signatureString, delimeters);
        String access = null;
        String returnType = null;
        String methodName = null;
        if(strings.get(0).equalsIgnoreCase("private") || strings.get(0).equalsIgnoreCase("public") || strings.get(0).equalsIgnoreCase("protected")){
            access = strings.get(0);
            returnType = strings.get(1);
            methodName = strings.get(2);

            strings.remove(0);
            strings.remove(0);
            strings.remove(0);
            System.out.println(strings.toString());
        } else {
            returnType = strings.get(0);
            methodName = strings.get(1);

            strings.remove(0);
            strings.remove(0);

        }

        List<MethodSignature.Argument> arguments = new ArrayList<>();
        for (int i = 0; i < strings.size(); i=i+2) {
            MethodSignature.Argument argument = new MethodSignature.Argument(strings.get(i), strings.get(i+1));
            arguments.add(argument);
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(access);
        methodSignature.setReturnType(returnType);
return methodSignature;

    }


}
