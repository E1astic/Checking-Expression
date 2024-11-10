import org.example.Expression;
import org.example.IncorrectExpressionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExpressionTest {

    // ТЕСТЫ МЕТОДА checkBrackets()
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

    // ТЕСТЫ МЕТОДА calculate()
    @Test
    void calculate1() throws IncorrectExpressionException {
        Expression expression=new Expression("(1+2777+331*2)*([14+1]+556*22)");
        Assertions.assertEquals(42129680, expression.calculate());
    }
    @Test
    void calculate2() throws IncorrectExpressionException {
        Expression expression=new Expression("2*3*4*5+1");
        Assertions.assertEquals(121, expression.calculate());
    }
    @Test
    void calculate3() throws IncorrectExpressionException {
        Expression expression=new Expression("111+2222+333*2+56");
        Assertions.assertEquals(3055, expression.calculate());
    }
    @Test
    void calculate4() throws IncorrectExpressionException {
        Expression expression=new Expression("12*(10+25)");
        Assertions.assertEquals(420, expression.calculate());
    }

    @Test
    void calculate5() throws IncorrectExpressionException {
        Expression expression=new Expression("3*[(14+2)*31*(201+4)+22]");
        Assertions.assertEquals(305106, expression.calculate());
    }

    // ТЕСТЫ ПРОВЕРКИ ВЫБРАСЫВАЕМЫХ ИСКЛЮЧЕНИЙ
    @Test
    void exception1(){
        Expression expression=new Expression("1+*(2+3)");
        Assertions.assertThrows(IncorrectExpressionException.class, () -> expression.calculate());
    }
    @Test
    void exception2(){
        Expression expression=new Expression("1*(2+3-)");
        Assertions.assertThrows(IncorrectExpressionException.class, () -> expression.calculate());
    }
    @Test
    void exception3(){
        Expression expression=new Expression("1*(*2+3)");
        Assertions.assertThrows(IncorrectExpressionException.class, () -> expression.calculate());
    }
    @Test
    void exception4(){
        Expression expression=new Expression("1*@(2 3)");
        Assertions.assertThrows(IncorrectExpressionException.class, () -> expression.calculate());
    }
    @Test
    void exception5(){
        Expression expression=new Expression("[1*(2+3)");
        Assertions.assertThrows(IncorrectExpressionException.class, () -> expression.calculate());
    }
    @Test
    void exception6(){
        Expression expression=new Expression("+[1*(2+3)");
        Assertions.assertThrows(IncorrectExpressionException.class, () -> expression.calculate());
    }

}
