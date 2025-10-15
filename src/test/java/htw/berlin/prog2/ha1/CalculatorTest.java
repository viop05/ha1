package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after multiplying two positive numbers")
    void testPositiveMultiplication() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(7);
        calc.pressEqualsKey();

        String expected = "49";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should repeat last operation when pressing equals multiple times")
    void testRepeatEqualsOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("3");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();
        calc.pressEqualsKey();

        String expected = "11";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    //Gibt 13, anstatt 11 wieder
    // Im Code Calculator Zeile 122 steht: case "+" -> latestValue + Double.parseDouble(screen); und das bedeutet, dass der aktuelle wert auf dem Schirm immer mit der Zahl vor dem Operator rechnet, was falsch ist.
    // 5 + 3 = 8; wenn man wieder auf = drückt, wird NICHT 8 + 3 = 11 gerechnet, sondern 8 + 5 = 13

    @Test
    @DisplayName("should repeat last operation when pressing equals multiple times")
    void testPercentAfterOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(1);
        calc.pressDigitKey(0);
        calc.pressUnaryOperationKey("%");
        calc.pressEqualsKey();

        String expected = "220";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    //Der Test macht 200.1 anstatt 220 (200 + 10%) daraus
    //Der Fehler liegt im Code Calculator Zeile 79: case "%" -> Double.parseDouble(screen) / 100; das macht jedoch die aktuelle Zahl/100
    // 10% = 0.1; 200 + 0.1= 200.1

}

