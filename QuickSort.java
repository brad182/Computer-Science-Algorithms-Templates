import java.util.*;

public class QuickSort {

    static int[] numbers;

    public static void quicksort(int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = numbers[right];

        int i = left;
        int j = right - 1;

        while (true) {
            while (numbers[i] < pivot && i < right) { // keep moving i forward until there is a number greater than or equal to the pivot
                i++;
            }

            while (numbers[j] > pivot && j > 0) { // keep moving j backwards until there is a number less than or equal to the pivot
                j--;
            }

            if(i >= j){
                break;
            }

            int ithNumber = numbers[i];
            int jthNumber = numbers[j];

            numbers[i] = jthNumber;
            numbers[j] = ithNumber;

            i++;
            j--;
        }

        int ithNumber = numbers[i];
        int rthNumber = numbers[right];

        numbers[i] = rthNumber;
        numbers[right] = ithNumber;

        for(int x = 0; x < numbers.length; x++){
            if(x == numbers.length - 1){
                System.out.println(numbers[x]);
                break;
            }
            System.out.print(numbers[x] + " ");
        }

        quicksort(left, i - 1);
        quicksort(i + 1, right);

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        numbers = new int[n];

        for(int x = 0; x < n; x++){
            numbers[x] = scanner.nextInt();
        }

        quicksort(0, n - 1);
    }
}
