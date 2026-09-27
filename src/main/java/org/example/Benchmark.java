package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {

    // Чтобы результат поиска использовался
    static volatile boolean result;

    // Генерация случайной матрицы
    public static int[][] createMatrix(int size, Random random) {

        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int up = 0;
                int left = 0;

                if (i > 0) {
                    up = matrix[i - 1][j];
                }

                if (j > 0) {
                    left = matrix[i][j - 1];
                }

                matrix[i][j] = Math.max(up, left) + 2 + random.nextInt(10) * 2;
            }
        }

        return matrix;
    }

    // Выбираем число, которого нет в матрице
    public static int createNumber(int[][] matrix, Random random) {

        int min = matrix[0][0];
        int max = matrix[matrix.length - 1][matrix.length - 1];

        // Все числа в матрице четные,
        // поэтому любое нечетное число между min и max
        // точно отсутствует
        int number;

        do {
            number = min + 1 + random.nextInt(max - min);
        } while (number % 2 == 0);

        return number;
    }

    // Медиана
    public static long median(long[] times) {

        Arrays.sort(times);

        return times[times.length / 2];
    }

    // Замер первого алгоритма
    public static long measurePrpereb(int[][] matrix, int number) {

        long start = System.nanoTime();

        result = Main.prpereb(matrix, number);

        long finish = System.nanoTime();

        return finish - start;
    }

    // Замер второго алгоритма
    public static long measureBinpoisk(int[][] matrix, int number) {

        long start = System.nanoTime();

        result = Main.binpoisk(matrix, number);

        long finish = System.nanoTime();

        return finish - start;
    }

    // Замер третьего алгоритма
    public static long measurePoicPy(int[][] matrix, int number) {

        long start = System.nanoTime();

        result = Main.poicPy(matrix, number);

        long finish = System.nanoTime();

        return finish - start;
    }

    public static void main(String[] args) throws IOException {

        int[] sizes = {
                64,
                128,
                256,
                512,
                1024,
                2048
        };

        int warmup = 10;
        int repeats = 9;

        Random random = new Random();

        FileWriter file = new FileWriter("benchmark.csv");

        file.write("size,prpereb,binpoisk,poicPy\n");

        System.out.println("Размер | Перебор | Бинарный | Правый верхний");
        System.out.println("-----------------------------------------------");

        for (int size : sizes) {

            // Создаем данные до начала измерения
            int[][] matrix = createMatrix(size, random);

            // Случайное число, которого нет в матрице
            int number = createNumber(matrix, random);

            // Прогрев
            for (int i = 0; i < warmup; i++) {

                result = Main.prpereb(matrix, number);
                result = Main.binpoisk(matrix, number);
                result = Main.poicPy(matrix, number);
            }

            long[] timesPrpereb = new long[repeats];
            long[] timesBinpoisk = new long[repeats];
            long[] timesPoicPy = new long[repeats];

            // Основные измерения
            for (int i = 0; i < repeats; i++) {

                timesPrpereb[i] =
                        measurePrpereb(matrix, number);

                timesBinpoisk[i] =
                        measureBinpoisk(matrix, number);

                timesPoicPy[i] =
                        measurePoicPy(matrix, number);
            }

            // Медианы
            long medianPrpereb = median(timesPrpereb);
            long medianBinpoisk = median(timesBinpoisk);
            long medianPoicPy = median(timesPoicPy);

            // Вывод в консоль
            System.out.println(
                    size + " | " +
                            medianPrpereb + " | " +
                            medianBinpoisk + " | " +
                            medianPoicPy
            );

            // Запись в CSV
            file.write(
                    size + "," +
                            medianPrpereb + "," +
                            medianBinpoisk + "," +
                            medianPoicPy + "\n"
            );
        }

        file.close();

        System.out.println();
        System.out.println("Результаты сохранены в benchmark.csv");
    }
}