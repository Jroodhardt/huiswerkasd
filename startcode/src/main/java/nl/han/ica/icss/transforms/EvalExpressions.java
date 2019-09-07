package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.HashMap;
import java.util.LinkedList;

public class EvalExpressions implements Transform {

    private LinkedList<HashMap<String, Literal>> variableValues;

    public EvalExpressions() {
        variableValues = new LinkedList<>();
    }

    @Override
    public void apply(AST ast) {
        variableValues = new LinkedList<>();
        evaluateExpressions(ast.root);
        }
    /*
     Walks the tree, adds new hashmap to LinkedList when there's a new stylesheet/stylerule. Also checks for Declarations.
    */
    private void evaluateExpressions(ASTNode node){
        if(node instanceof Stylesheet| node instanceof Stylerule){
            variableValues.push(new HashMap<>());
        }  else if ( node instanceof VariableAssignment) {
            addVariable((VariableAssignment) node);
        }
        else if ( node instanceof Declaration) {
            addDeclaration((Declaration)node);
        }
        for(ASTNode child: node.getChildren()){
            evaluateExpressions(child);
        }
        if(node instanceof Stylesheet| node instanceof Stylerule){
            variableValues.pop();
        }
    }

    private void addDeclaration(Declaration declaration) {
            declaration.expression=getLiteral(declaration.expression);
    }

    private void addVariable(VariableAssignment variableAssignment) {
        variableAssignment.expression = getLiteral(variableAssignment.expression);
        variableValues.getFirst().put(variableAssignment.name.name, getLiteral(variableAssignment.expression));
    }
    /*
    Gets Literals from operation and calculates them
     */
    private Literal calculateOperation(Operation operation) {
        Literal leftType = getLiteral(operation.lhs);
        Literal rightType = getLiteral(operation.rhs);
        return calculator(leftType,rightType,operation);
    }

    private int getValue(Expression expression) {
        if (expression instanceof PercentageLiteral) {
            return ((PercentageLiteral) expression).value;
        } else if (expression instanceof ScalarLiteral) {
            return ((ScalarLiteral) expression).value;
        }else if (expression instanceof PixelLiteral) {
            return ((PixelLiteral) expression).value;
        }
        return 0;
    }
/*
Calculates operation and makes it a Literal
 */
    private Literal calculator(Literal left, Literal right,Operation operation) {
        if (operation instanceof MultiplyOperation) {
            return createLiteral(left,getValue(left) * getValue(right));
        } else if (operation instanceof AddOperation) {
            return createLiteral(left,getValue(left) + getValue(right));
        } else if (operation instanceof SubtractOperation){
            return createLiteral(left,getValue(left) - getValue(right));
        }
        else return null;
    }

    private Literal getLiteral(Expression expression){
        if(expression instanceof ColorLiteral || expression instanceof PercentageLiteral || expression instanceof ScalarLiteral || expression instanceof PixelLiteral) {
            return (Literal) expression;
        } else if(expression instanceof Operation) {
            return calculateOperation((Operation) expression);
        } else if(expression instanceof VariableReference) {
            return makeVariableLiteral((VariableReference) expression);
        }
        return null;
    }
    private Literal makeVariableLiteral(VariableReference variableReference) {
        for(HashMap<String,Literal> current : variableValues) {
            if(current.containsKey(variableReference.name))
                return current.get(variableReference.name);
        }
        return null;
    }

  /*
  Creates typed Literal out of Literal and value
   */
    private Literal createLiteral(Literal literal, int value) {
        if(literal instanceof PercentageLiteral) {
            return new PercentageLiteral(value);
        } else if(literal instanceof ScalarLiteral) {
            return new ScalarLiteral(value);
        } else if(literal instanceof PixelLiteral) {
            return new PixelLiteral(value);
        }
        return null;

    }
}
