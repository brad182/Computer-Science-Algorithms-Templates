import java.util.*;

public class MergeSort {

    static int[] numbers;

    public static void mergesort (int left, int right) {
        if(left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergesort(left, mid);
        mergesort(mid + 1, right);

        int i = left;
        int j = mid + 1;

        int k = 0;

        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right){
            if (numbers[i] < numbers[j]) {
                temp[k] = numbers[i];
                i++;
            }
            else {
                temp[k] = numbers[j];
                j++;
            }
            k++;
        }

        /* the next two for loops are to fill up temp with the rest of the array that has not been completely added yet
           if one array is already fully put into temp, the for loop will simply not execute
         */

        for (int x = i; x <= mid; x++) {
            temp[k] = numbers[x];
            k++;
        }

        for (int x = j; x <= right; x++) {
            temp[k] = numbers[x];
            k++;
        }

        int count = 0;
        for(int x = left; x <= right; x++){
            numbers[x] = temp[count];
            count++;
        }

        for(int x = 0; x < numbers.length; x++){
            if(x == numbers.length - 1){
                System.out.println(numbers[x]);
                break;
            }
            System.out.print(numbers[x] + " ");
        }
    }

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        numbers = new int[n];

        for (int x = 0; x < n; x++) {
            numbers[x] = scanner.nextInt();
        }

        mergesort(0, n - 1);
    }
}
