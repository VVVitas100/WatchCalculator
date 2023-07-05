package ru.vvvitas.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WearCalculatorUnitTest {
    String indicator = "0";

    @Test
    public void CheckingFunctionalityButtons() {

        // Проверка работоспособности кнопок и индикатора (кроме кнопок операций, будут проверены позднее)

        for (int i = 9; i >= 0; i--) indicator = Calculator.calc(i + "", indicator);
        assertEquals("Проверка корректности ввода цифр кнопками от 9 до 0 в порядке убывания", "9876543210", indicator);
        assertEquals("Проверка корректности ввода кнопкой [/-/]", "-9876543210", indicator = Calculator.calc("/-/", indicator));
        assertEquals("Проверка корректности работы функции [<-]", "-987654321", indicator = Calculator.calc("<-", indicator));
        indicator = Calculator.calc("0", indicator);
        assertEquals("Проверка корректности ввода кнопкой [.]", "-9876543210.", indicator = Calculator.calc(".", indicator));
        assertEquals("Проверка корректности работы функции [С]", "0", indicator = Calculator.calc("C", indicator));
    }

    @Test
    public void CheckingCorrectnessAdditionOperation() {


        // Проверка корректности операции сложения

        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода первого аргумента", "-1111111", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc("+", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("8", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "-8888888", indicator = Calculator.calc("/-/", indicator));
        assertEquals("Проверка корректности операции сложения отрицательных аргументов", "-9999999.0", indicator = Calculator.calc("=", indicator));

        indicator = Calculator.calc("C", indicator);
        indicator = Calculator.calc(".", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc(i + "", indicator);
        assertEquals("Проверка корректности ввода первого аргумента", "0.1234567", indicator);
        indicator = Calculator.calc("+", indicator);
        indicator = Calculator.calc("1", indicator);
        indicator = Calculator.calc(".", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc(i + "", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "-1.1234567", indicator = Calculator.calc("/-/", indicator));
        assertEquals("Проверка корректности операции сложения аргументов с разным знаком", "-1.0", indicator = Calculator.calc("=", indicator));

        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода первого аргумента без предварительного сброса предыдущего результата", "1111111111", indicator);
        indicator = Calculator.calc("+", indicator);
        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("8", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "8888888888", indicator);
        assertEquals("Проверка корректности операции сложения положительных аргументов", "1.0000001E10", indicator = Calculator.calc("=", indicator));
    }

    @Test
    public void CheckingCorrectnessSubtractionOperation() {

        // Проверка корректности операции вычитания

        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода первого аргумента", "1111111", indicator);
        indicator = Calculator.calc("-", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("9", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "9999999", indicator);
        assertEquals("Проверка корректности операции вычитания положительных аргументов большего из меньшего", "-8888888.0", indicator = Calculator.calc("=", indicator));

        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода первого аргумента без предварительного сброса предыдущего результата", "-1111111", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc("-", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "1111111", indicator);
        assertEquals("Проверка корректности операции вычитания положительного аргумента из отрицательного", "-2222222.0", indicator = Calculator.calc("=", indicator));

        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода первого аргумента без предварительного сброса предыдущего результата", "-1111111", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc("-", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("2", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "-2222222", indicator = Calculator.calc("/-/", indicator));
        assertEquals("Проверка корректности операции вычитания отрицательного аргумента из отрицательного", "1111111.0", indicator = Calculator.calc("=", indicator));
    }

    @Test
    public void CheckingCorrectnessMultiplicationOperation() {

        // Проверка корректности операции умножения

        indicator = Calculator.calc("2", indicator);
        indicator = Calculator.calc("0", indicator);
        assertEquals("Проверка корректности ввода первого аргумента", "-20", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc("X", indicator);
        indicator = Calculator.calc(".", indicator);
        indicator = Calculator.calc("2", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "0.2", indicator);
        assertEquals("Проверка корректности операции умножения аргументов разных знаков", "-4.0", indicator = Calculator.calc("=", indicator));

        indicator = Calculator.calc(".", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("0", indicator);
        indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода первого аргумента без предварительного сброса предыдущего результата", "-0.00000001", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc("X", indicator);
        indicator = Calculator.calc(".", indicator);
        for (int i = 1; i <= 7; i++) indicator = Calculator.calc("0", indicator);
        indicator = Calculator.calc("2", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "-0.00000002", indicator = Calculator.calc("/-/", indicator));
        assertEquals("Проверка корректности операции умножения отрицательных аргументов", "2.0E-16", indicator = Calculator.calc("=", indicator));
    }

    @Test
    public void CheckingCorrectnessDivisionOperation() {

        // Проверка корректности операции деления

        indicator = Calculator.calc("2", indicator);
        indicator = Calculator.calc("0", indicator);
        assertEquals("Проверка корректности ввода первого аргумента", "-20", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc(":", indicator);
        indicator = Calculator.calc(".", indicator);
        indicator = Calculator.calc("2", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "0.2", indicator);
        assertEquals("Проверка корректности операции деления аргументов разных знаков", "-100.0", indicator = Calculator.calc("=", indicator));

        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода первого аргумента без предварительного сброса предыдущего результата", "-1111111111", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc(":", indicator);
        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("2", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "-2222222222", indicator = Calculator.calc("/-/", indicator));
        assertEquals("Проверка корректности операции деления отрицательного аргумента на отрицательный", "0.5", indicator = Calculator.calc("=", indicator));
    }

    @Test
    public void CheckingCorrectnessPercentageCalculation() {
        // Проверка корректности операции вычисления процента

        indicator = Calculator.calc("1", indicator);
        indicator = Calculator.calc("0", indicator);
        indicator = Calculator.calc("0", indicator);
        indicator = Calculator.calc("0", indicator);
        assertEquals("Проверка корректности ввода первого аргумента", "1000", indicator);
        indicator = Calculator.calc("%", indicator);
        indicator = Calculator.calc(".", indicator);
        indicator = Calculator.calc("1", indicator);
        assertEquals("Проверка корректности ввода второго аргумента", "0.1", indicator);
        assertEquals("Проверка корректности операции вычисления процента", "1.0", indicator = Calculator.calc("=", indicator));
    }

    @Test
    public void overflowErrorNegativeTest() {

        // Попытка вызвать ошибку переполнения

        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("9", indicator);
        indicator = Calculator.calc("X", indicator);
        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("9", indicator);
        indicator = Calculator.calc("=", indicator);
        indicator = Calculator.calc("X", indicator);
        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("9", indicator);
        indicator = Calculator.calc("=", indicator);
        indicator = Calculator.calc("X", indicator);
        for (int i = 1; i <= 10; i++) indicator = Calculator.calc("9", indicator);
        assertEquals("Проверка результата Infinity при переполнении ", "Infinity", indicator = Calculator.calc("=", indicator));
    }

    @Test
    public void divisionByNullNegativeTest() {

        // Проверка реакции деления на 0

        indicator = Calculator.calc("1", indicator);
        indicator = Calculator.calc("/-/", indicator);
        indicator = Calculator.calc(":", indicator);
        assertEquals("Проверка реакции деления отличного от 0 аргумента на 0 ", "-Infinity", indicator = Calculator.calc("=", indicator));

        assertEquals("Проверка изменения знака значения бесконечности", "Infinity", indicator = Calculator.calc("/-/", indicator));
        indicator = Calculator.calc(":", indicator);
        assertEquals("Проверка реакции деления значения бесконечности на 0 ", "Infinity", indicator = Calculator.calc("=", indicator));

        indicator = Calculator.calc(".", indicator);
        assertEquals("Проверка сброса предыдущего результата при нажатии [.]", "0.", indicator = Calculator.calc("=", indicator));
        indicator = Calculator.calc(":", indicator);
        assertEquals("Проверка реакции деления 0 на 0 ", "NaN", indicator = Calculator.calc("=", indicator));
    }

    @Test
    public void incorrectArgumentNegativeTest() {
        indicator = "NaN";
        assertEquals("Проверка реакции на удаление последнего символа значения NaN", "Na", indicator = Calculator.calc("<-", indicator));
        assertEquals("Проверка реакции на попытку совершения действий над некорректным аргументом", "Ё", indicator = Calculator.calc("%", indicator));
    }

    @Test
    public void  deleteSingleCharacterTest() {
        assertEquals("Проверка реакции на попытку удаления единственного символа на индикаторе","0", indicator = Calculator.calc("<-", indicator));
    }

}