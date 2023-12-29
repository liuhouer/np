package cn.northpark.utils;

/**
 * @author zhangyang
 * @date 2023年07月25日 09:39:19
 * 自定义逻辑表达式计算：支持多逻辑符，不带括号
 */
public class ExpressComputeUtil {
    /**
     * 支持单一逻辑符号的函数
     * @param expression
     * @param value
     * @return
     */
    public static boolean evaluateExpressionSingle(String expression, Double value) {
        // Splitting the expression into parts
        String[] parts = expression.split("&|\\|");

        // Evaluating each part
        boolean result = evaluatePart(parts[0], value);
        for (int i = 1; i < parts.length; i++) {
            // If the part is preceded by an '&', perform an 'and' operation
            if (expression.contains("&") && expression.indexOf("&") < expression.indexOf(parts[i])) {
                result = result && evaluatePart(parts[i], value);
            }
            // If the part is preceded by a '|', perform an 'or' operation
            else if (expression.contains("|") && expression.indexOf("|") < expression.indexOf(parts[i])) {
                result = result || evaluatePart(parts[i], value);
            }
        }
        return result;
    }

    /**
     * 支持多个逻辑符号的函数
     * @param expression
     * @param value
     * @return
     */
    public static boolean evaluateExpression(String expression, Double value) {
        // Splitting the expression into parts
        String[] parts = expression.split("(?=[&|])|(?<=[&|])");

        // Evaluating each part
        boolean result = evaluatePart(parts[0], value);
        for (int i = 1; i < parts.length; i += 2) {
            // If the part is an '&', perform an 'and' operation
            if (parts[i].equals("&")) {
                result = result && evaluatePart(parts[i + 1], value);
            }
            // If the part is a '|', perform an 'or' operation
            else if (parts[i].equals("|")) {
                result = result || evaluatePart(parts[i + 1], value);
            }
        }
        return result;
    }

    public static boolean evaluatePart(String part, Double value) {
        // Check if the part contains a '>'
        if (part.contains(">")) {
            // If the '>' is followed by an '=', check if the value is greater than or equal to the number
            if (part.contains("=")) {
                return value >= Double.parseDouble(part.substring(2));
            }
            // If the '>' is not followed by an '=', check if the value is greater than the number
            else {
                return value > Double.parseDouble(part.substring(1));
            }
        }
        // Check if the part contains a '<'
        else if (part.contains("<")) {
            // If the '<' is followed by an '=', check if the value is less than or equal to the number
            if (part.contains("=")) {
                return value <= Double.parseDouble(part.substring(2));
            }
            // If the '<' is not followed by an '=', check if the value is less than the number
            else {
                return value < Double.parseDouble(part.substring(1));
            }
        }
        else if(part.startsWith("=")){
            return value == Double.parseDouble(part.substring(1));
        }
        return false;
    }


    public static void main(String[] args) {
        boolean a = evaluateExpression(">10&<20|>100", 115d);
        System.err.println("a: " + a);
        boolean b = evaluateExpression(">10&<20|=30", 30d);
        System.err.println("b: " + b);
        boolean c = evaluateExpression("<10|>20", 15d);
        System.err.println("c: " + c);
        boolean d = evaluateExpression("<10|>20", 60d);
        System.err.println("d: " + d);
        boolean e = evaluateExpression(">10", 15d);
        System.err.println("e: " + e);
        boolean f = evaluateExpression(">10", 5d);
        System.err.println("f: " + f);
        boolean g = evaluateExpression("<=10", 5d);
        System.err.println("g: " + g);
        boolean h = evaluateExpression("<=10", 15d);
        System.err.println("h: " + h);
    }

}