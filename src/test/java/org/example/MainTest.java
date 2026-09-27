package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // Обычная матрица, отсортированная
    // по строкам и по столбцам
    int[][] matrica = {
            {1, 4, 7, 10},
            {2, 5, 8, 12},
            {3, 6, 9, 15}
    };


    // Проверяем обычный полный перебор
    @Test
    public void testPrpereb() {

        // Числа, которые есть в матрице
        assertTrue(Main.prpereb(matrica, 8));
        assertTrue(Main.prpereb(matrica, 1));
        assertTrue(Main.prpereb(matrica, 15));

        // Числа, которых нет в матрице
        assertFalse(Main.prpereb(matrica, 11));
        assertFalse(Main.prpereb(matrica, 100));
    }


    // Проверяем бинарный поиск
    @Test
    public void testBinpoisk() {

        // Числа, которые есть в матрице
        assertTrue(Main.binpoisk(matrica, 8));
        assertTrue(Main.binpoisk(matrica, 1));
        assertTrue(Main.binpoisk(matrica, 15));

        // Числа, которых нет в матрице
        assertFalse(Main.binpoisk(matrica, 11));
        assertFalse(Main.binpoisk(matrica, 100));
    }


    // Проверяем поиск от правого верхнего угла
    @Test
    public void testPoicPy() {

        // Числа, которые есть в матрице
        assertTrue(Main.poicPy(matrica, 8));
        assertTrue(Main.poicPy(matrica, 1));
        assertTrue(Main.poicPy(matrica, 15));

        // Числа, которых нет в матрице
        assertFalse(Main.poicPy(matrica, 11));
        assertFalse(Main.poicPy(matrica, 100));
    }


    // Проверяем пустую матрицу
    @Test
    public void testEmptyMatrix() {

        int[][] empty = {};

        // Все методы должны вернуть false
        assertFalse(Main.prpereb(empty, 5));
        assertFalse(Main.binpoisk(empty, 5));
        assertFalse(Main.poicPy(empty, 5));
    }


    // Проверяем матрицу из одного элемента
    @Test
    public void testOneElement() {

        int[][] one = {
                {5}
        };

        // Поиск существующего элемента
        assertTrue(Main.prpereb(one, 5));
        assertTrue(Main.binpoisk(one, 5));
        assertTrue(Main.poicPy(one, 5));

        // Поиск отсутствующего элемента
        assertFalse(Main.prpereb(one, 3));
        assertFalse(Main.binpoisk(one, 3));
        assertFalse(Main.poicPy(one, 3));
    }


    // Проверяем матрицу с повторяющимися элементами
    @Test
    public void testDuplicates() {

        int[][] duplicates = {
                {1, 2, 2, 5},
                {2, 3, 4, 6},
                {2, 4, 5, 8}
        };

        // Число 2 встречается несколько раз
        assertTrue(Main.prpereb(duplicates, 2));
        assertTrue(Main.binpoisk(duplicates, 2));
        assertTrue(Main.poicPy(duplicates, 2));

        // Числа 7 нет в матрице
        assertFalse(Main.prpereb(duplicates, 7));
        assertFalse(Main.binpoisk(duplicates, 7));
        assertFalse(Main.poicPy(duplicates, 7));
    }


    // Проверяем, что все три алгоритма
    // дают одинаковый результат
    @Test
    public void testAllSearchesGiveSameResult() {

        int[] numbers = {
                1, 4, 6, 8, 10, 12, 15, 20
        };

        for (int chislo : numbers) {

            boolean result1 = Main.prpereb(matrica, chislo);
            boolean result2 = Main.binpoisk(matrica, chislo);
            boolean result3 = Main.poicPy(matrica, chislo);

            // Результат первого алгоритма
            // должен совпадать со вторым
            assertEquals(result1, result2);

            // И с третьим
            assertEquals(result1, result3);
        }
    }
}