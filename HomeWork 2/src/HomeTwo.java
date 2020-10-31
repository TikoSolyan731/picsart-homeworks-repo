public class HomeTwo {
    public static void main(String[] args) {
        // Task 1. Create an array and fill it with 2 number.
        int[] number2 = {2, 2, 2, 2, 2, 2, 2};

        // Task 2. Create an array and fill it with numbers from 1:1000.
        int[] numbersThousand = new int[1000];

        for (int i = 1; i < numbersThousand.length; i++)
            numbersThousand[i] = i;

        for (int i = 1; i < numbersThousand.length; i++)
            System.out.print(i % 20 == 0 ? numbersThousand[i] + "\n" : numbersThousand[i] + " ");

        System.out.println();
        System.out.println();

        // Task 3. Create an array and fill it with odd numbers from -20:20.

        int[] oddNumbers = new int[20];

        for (int i = 0, j = -19; j <= 19; i++, j += 2)
            oddNumbers[i] = j;

        for (int i = 0; i < oddNumbers.length; i++)
            System.out.print(oddNumbers[i] + " ");

        System.out.println();
        System.out.println();

        // Task 4. Create an array and fill it. Print all elements which can be divided by 5.

        int[] array = {2, 3, 5, 10, 6, 7, 15};
        for (int i = 0; i < array.length; i++)
            if (array[i] % 5 == 0)
                System.out.print(array[i] + " ");

        System.out.println();
        System.out.println();

        // Task 5. Create an array and fill it. Print all elements which are between 24.12 and 467.23.

        double[] array2 = {21.5, 48.24, 4586.2154, -48.5, 4.2, 784.4458, 64.2};
        for (int i = 0; i < array2.length; i++)
            if (array2[i] > 24.12 && array2[i] < 467.23)
                System.out.print(array2[i] + " ");

        System.out.println();
        System.out.println();

        // Task 6. Create an array and fill it. Print count of elements which can be divided by 2.

        int[] array3 = {2, 7, 5, 8, 6, 4, 48, 78, 53, 1};
        int countOfEvens = 0;

        for (int i = 0; i < array3.length; i++)
            if (array3[i] % 2 == 0)
                countOfEvens++;

        System.out.println("Count of Even Numbers: " + countOfEvens);

        System.out.println();

        // Task 7. Given an integer, 0< N < 21 , print its first 10 multiples.
        //Each multiple  N x i (where 0<i<11) should be printed on a new line in the form: N x i = result.

        int n = 9;

        for (int i = 0; i < 11; i++) {
            System.out.println(n + " X " + i + " = " + (n * i));
        }
    }
}
