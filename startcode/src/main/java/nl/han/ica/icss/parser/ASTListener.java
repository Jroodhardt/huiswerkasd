package nl.han.ica.icss.parser;

import java.util.Stack;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the ANTLR Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

    private AST ast;

    private Stack<ASTNode> currentContainer;

    public ASTListener() {
        ast = new AST();
        currentContainer = new Stack<>();
    }
    public AST getAST() {
        return ast;
    }

    /*
        Adds new stylesheet to the Stack
    */
    @Override
    public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
        currentContainer.push(new Stylesheet());
    }
    /*
        Removes stylesheet and adds to tree
    */
    @Override public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
        ast.root = (Stylesheet)currentContainer.pop();
    }

    @Override
    public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        VariableAssignment variableAssignment = new VariableAssignment();
        currentContainer.push(variableAssignment);
    }

    @Override
    public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        ASTNode node = currentContainer.pop();
        currentContainer.peek().addChild(node);
    }

    @Override
    public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
        VariableReference variableReference = new VariableReference(ctx.getText());
        ASTNode current = currentContainer.peek();
        if(current instanceof VariableAssignment) {
            ((VariableAssignment)current).name = variableReference;
        } else if (current instanceof Literal) {
            current.addChild(variableReference);
        } else if (current instanceof Declaration) {
            ((Declaration)current).expression = variableReference;
        } else if(current instanceof Operation) {
            current.addChild(variableReference);
        }
    }

    @Override
    public void enterLiteral(ICSSParser.LiteralContext ctx) {
        Literal literal = createLiteral(ctx);
        ASTNode current = currentContainer.peek();
        if(current instanceof VariableAssignment) {
            ((VariableAssignment)current).expression = literal;
        } else if(current instanceof Declaration) {
            ((Declaration)current).expression = literal;
        } else if(current instanceof Operation) {
            current.addChild(literal);
        }
    }

    @Override
    public void enterStylerule(ICSSParser.StyleruleContext ctx) {
        currentContainer.push(new Stylerule());
    }

    @Override
    public void exitStylerule(ICSSParser.StyleruleContext ctx) {
        ASTNode stylerule = currentContainer.pop();
        currentContainer.peek().addChild(stylerule);
    }
    /*
        Adds selector to parent
    */
    @Override
    public void enterSelector(ICSSParser.SelectorContext ctx) {
        if(ctx.CLASS_IDENT() != null) {
            (currentContainer.peek()).addChild(new ClassSelector(ctx.getText()));
        } else if(ctx.ID_IDENT() != null) {
            (currentContainer.peek()).addChild(new IdSelector(ctx.getText()));
        } else if(ctx.LOWER_IDENT() != null) {
            (currentContainer.peek()).addChild(new TagSelector(ctx.getText()));
        }
    }

    @Override
    public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
        currentContainer.push(new Declaration());
    }

    @Override
    public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
        ASTNode declaration = currentContainer.pop();
        currentContainer.peek().addChild(declaration);
    }

    @Override
    public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
        ((Declaration)currentContainer.peek()).property = new PropertyName(ctx.getText());
    }
    /*
        Checks type of operation and adds to stack
    */
    @Override
    public void enterOperation(ICSSParser.OperationContext ctx) {
        if(ctx.MUL() != null) {
            currentContainer.push(new MultiplyOperation());
        } else if(ctx.PLUS() != null) {
            currentContainer.push(new AddOperation());
        } else if(ctx.MIN() != null) {
            currentContainer.push(new SubtractOperation());
        }
    }
    /*
        Adds opertion to parent
    */
    @Override
    public void exitOperation(ICSSParser.OperationContext ctx) {
       ASTNode operations= currentContainer.pop();
       currentContainer.peek().addChild(operations);
    }

    /*
        Checks LiteralType and creates Literal
    */
    private Literal createLiteral(ICSSParser.LiteralContext ctx) {
        if(ctx.COLOR() != null) {
            return new ColorLiteral(ctx.getText());
        } else if(ctx.PERCENTAGE() != null) {
            return new PercentageLiteral(ctx.getText());
        } else if(ctx.PIXELSIZE() != null) {
            return new PixelLiteral(ctx.getText());
        } else if(ctx.SCALAR() != null) {
            return new ScalarLiteral(ctx.getText());
        }
        return null;
    }
}