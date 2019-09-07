package nl.han.ica.icss.checker;

import java.util.HashMap;
import java.util.LinkedList;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.types.*;
/**
 * This chlass checks the variabletypes inside the AST tree
 */
public class Checker {

    private LinkedList<HashMap<String,ExpressionType>> variableTypes;

    public void check(AST ast) {
        variableTypes = new LinkedList<>();
        walkTree(ast.root);

    }

    /*
      Walks the tree, adds new hashmap to LinkedList when there's a new stylesheet/stylerule
    */
    private void walkTree(ASTNode node){
        if(node instanceof Stylesheet| node instanceof Stylerule){
            variableTypes.addFirst(new HashMap<>());
        } else if ( node instanceof VariableAssignment) {
            addVariable((VariableAssignment) node);
        }
        else if (node instanceof VariableReference){
            checkVariableReference((VariableReference)node);
        }
        else if(node instanceof Operation){
            checkOperationTypes((Operation)node);
        }
        else if(node instanceof Declaration){
            checkDeclarations((Declaration)node);
        }
        for(ASTNode child: node.getChildren()){
            walkTree(child);
        }
        if(node instanceof Stylesheet| node instanceof Stylerule){
            variableTypes.pop();
        }
    }
    /*
      Saves Variable
    */
    private void addVariable(VariableAssignment variableAssignment){
        variableTypes.getFirst().put(variableAssignment.name.name,getExpressionType(variableAssignment.expression));
    }
    /*
      Gets expressiontype of expression and checks for variablereferences
    */
    private ExpressionType getExpressionType(Expression expression) {
        if(expression instanceof ColorLiteral){
            return ExpressionType.COLOR;
        } else if (expression instanceof PercentageLiteral){
            return ExpressionType.PERCENTAGE;
        }else if (expression instanceof PixelLiteral){
            return ExpressionType.PIXEL;
        }else if (expression instanceof ScalarLiteral){
            return ExpressionType.SCALAR;
        }else if(expression instanceof VariableReference){
            return checkVariableReference((VariableReference) expression);
        }
        return ExpressionType.UNDEFINED;
    }
    /*
      Recusively checks the left and right nodes of an operation for errors.
    */
    private void checkOperationTypes(Operation operation) {
        ExpressionType rightOp=getExpressionType(operation.rhs);
        ExpressionType leftOp=getExpressionType(operation.lhs);
        if(operation.rhs instanceof Operation){
            checkOperationTypes((Operation) operation.rhs);
        } else if((leftOp==ExpressionType.PIXEL&&(rightOp!=ExpressionType.SCALAR))|(leftOp==ExpressionType.SCALAR&&rightOp!=ExpressionType.PIXEL) &&operation instanceof MultiplyOperation){
            operation.setError("The "+operation.getNodeLabel()+" operation  is invalid because you can only multiply with a scalar value");
        } else if((!leftOp.equals(rightOp)&&((operation instanceof AddOperation)|(operation instanceof SubtractOperation)))){
            operation.setError("The "+operation.getNodeLabel()+" operation is invalid because you can only add the same value types");
        } else if(((leftOp==ExpressionType.COLOR|rightOp==ExpressionType.COLOR)) && ((operation instanceof AddOperation)|(operation instanceof MultiplyOperation))){
            operation.setError("The "+operation.getNodeLabel()+" operation is invalid because you can't add or multiply colors");
        }
    }
    /*
      Checks declarations for incompatibility
    */
    private void checkDeclarations(Declaration declaration) {
        ExpressionType declaredType=getExpressionType(declaration.expression);

            if((declaration.property.name.equals("background-color")|declaration.property.name.equals("color"))&& ((declaredType.equals(ExpressionType.PERCENTAGE)|(declaredType.equals(ExpressionType.SCALAR))|((declaredType.equals(ExpressionType.PIXEL)))))){
                declaration.expression.setError("The declaration "+declaration.expression+" is invalid because only #XXXXXX can be used for colors");
            } else if((declaration.property.name.equals("width")|declaration.property.name.equals("height"))&& ((declaredType.equals(ExpressionType.COLOR)|(declaredType.equals(ExpressionType.SCALAR))))){
                declaration.expression.setError("The declaration "+declaration.expression+" is invalid because only Xpx and X% can be used with width or height");
            }
        }
    /*
     * Looks for ExpressionType of variablereference throws error when it doesn't exist.
     */
    private ExpressionType checkVariableReference(VariableReference variableReference){
        for(HashMap<String,ExpressionType> current:variableTypes){
            if(current.containsKey(variableReference.name)){
                return current.get(variableReference.name);
            }
        }
        variableReference.setError("The variable "+variableReference.name+" hasn't been assigned.");
        return ExpressionType.UNDEFINED;
    }


}
