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

    @Test
    void calculate1() throws Exception {
        Expression expression=new Expression("(1+2+3*2)*(1+1)+5*2");
        Assertions.assertEquals(28, expression.calculate());
    }
    @Test
    void calculate2() throws Exception {
        Expression expression=new Expression("2*3*4*5+1");
        Assertions.assertEquals(121, expression.calculate());
    }
    @Test
    void calculate3() throws Exception {
        Expression expression=new Expression("1+2+3*2+4");
        Assertions.assertEquals(13, expression.calculate());
    }
    @Test
    void calculate4() throws Exception {
        Expression expression=new Expression("2*(3+1)");
        Assertions.assertEquals(8, expression.calculate());
    }

    @Test
    void calculate5() throws Exception {
        Expression expression=new Expression("2*((1+2)*3*(2+3)+1)");
        Assertions.assertEquals(92, expression.calculate());
    }

}
