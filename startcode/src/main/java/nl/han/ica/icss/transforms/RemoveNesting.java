package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.*;

import java.util.LinkedList;
import java.util.Objects;

public class RemoveNesting implements Transform {


    private LinkedList<Stylerule> ruleParents;
    private LinkedList<Stylerule> styleRules;
    @Override
    public void apply(AST ast) {
        ruleParents = new LinkedList<>();
        styleRules = new LinkedList<>();
        removeNesting(ast.root);
        for(Stylerule stylerule : styleRules) {
            if(!ast.root.getChildren().contains(stylerule))
                ast.root.addChild(stylerule);
        }
    }

/*
Gets nested style rules and moves selectors.
 */
    private void removeNesting(ASTNode node) {
        if (node instanceof Stylerule) {
            Stylerule stylerule = (Stylerule) node;
            if (ruleParents.size() > 0) {
                ruleParents.peek().removeChild(node);
                stylerule.selectors.addAll(0, Objects.requireNonNull(ruleParents.peek()).selectors);
            }
            ruleParents.push(stylerule);
            styleRules.add(stylerule);
        }
        for (ASTNode child : node.getChildren()) {
            removeNesting(child);
        }
        if (node instanceof Stylerule) {
            ruleParents.pop();
        }
    }
}
