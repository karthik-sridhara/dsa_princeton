import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ThreeSum {

    Integer [] arr;
    public ThreeSum(int[] arr) {
        this.arr = new Integer[arr.length];
        for(int i = 0; i < arr.length; i++){
            this.arr[i] = arr[i];
        }
    }

    public static void printArray(int[] arr){
        for (int t : arr) {
            System.out.print(t + " ");
        }
        System.out.println();
    }

    public static void printAnswer(List<String> answer){
        for(String s : answer){
            System.out.println(s);
        }
    }

    public void usingBinarySearch(){
        ArrayList<String> answer = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){

            for(int j = i + 1; j < arr.length; j++){

                int sum = (arr[i] + arr[j]) * -1;
                int low = j+1;
                int high = arr.length - 1;

                while(low <= high){
                    int mid = (low + high) / 2;
                    if(sum == arr[mid]){
                        answer.add(String.format("%d, %d, %d", arr[i], arr[j], arr[mid]));
                        break;
                    } else if(sum < arr[mid]){
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        }
        printAnswer(answer);
    }

    public void usingTwoPointers(){
        ArrayList<String> answer = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            int low = i + 1;
            int high = arr.length - 1;
            while(low < high){
                int sum =  arr[i] + arr[low] + arr[high];
                if(sum < 0)
                    low++;
                else if(sum > 0)
                    high--;
                else{
                    answer.add(String.format("%d, %d, %d", arr[i], arr[low], arr[high]));

                    if(low+1 < high && arr[low]==arr[low+1]){
                        low++;
                    }else if(high-1 > low && arr[high]==arr[high-1]){
                        high--;
                    }else {
                        low++;
                        high--;
                    }
                }

            }
        }
        printAnswer(answer);
    }

    public static int[] createArray(int n){
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            int value = (int) (Math.random() * 100);
            int multiplier = Math.random() < 0.5 ? 1 : -1;
            arr[i] = value * multiplier;
        }
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = createArray(n);
        printArray(arr);

        ThreeSum threeSum1 = new ThreeSum(arr);
        StopWatch stopWatch1 = new StopWatch();
        threeSum1.usingBinarySearch();
        System.out.printf("Elapsed time: %.3f seconds%n", stopWatch1.elapsedTime());

        ThreeSum threeSum2 = new ThreeSum(arr);
        StopWatch stopWatch2 = new StopWatch();
        threeSum2.usingTwoPointers();
        System.out.printf("Elapsed time: %.3f seconds%n", stopWatch2.elapsedTime());

    }
}
