import org.example.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionTest {
    @Test
    void checkBrackets_true(){
        Expression expression=new Expression("(2+1)*((1+1)*[(1+1)*(2+2)]+5)");
        Assertions.assertEquals(true, expression.checkBrackets());
    }

    @Test
    void checkBrackets_false(){
        Expression expression=new Expression("(2+1)*((1+1)*[(1+1)*(2+2))]+5)");
        Assertions.assertEquals(false, expression.checkBrackets());
    }

}
