public class Bitonic {

    private int findPeak(Integer[] arr) {
        for(int i=0; i<arr.length-1 ; i++){
            if(arr[i]  > arr[i+1]){
                return i;
            }
        }
        return 0;
    }

    private int findPeak2(Integer[] arr) {
        int start = 0;
        int end = arr.length-1;
        while(start<end){
            int mid = (start +end)/2;
            if(arr[mid] < arr[mid+1]){
                start = mid + 1;
            }else{
                end = mid;
            }
        }
        return start;
    }

    private int binarySearch(Integer[] arr, int start, int end,int key,boolean increasing){
        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid] > key){
                if(increasing){
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if(arr[mid] < key){
                if(increasing){
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }else{
                return mid;
            }
        }
        return -1;
    }



    public int search(Integer[] array, int key) {
        int peak = findPeak(array);
        int index = binarySearch(array, 0, peak, key, true);
        if (index != -1) {
            return index;
        }
        return binarySearch(array, peak + 1, array.length - 1, key, false);
    }

    public int search2(Integer[] array, int key) {
        int peak = findPeak2(array);
        int index = binarySearch(array, 0, peak, key, true);
        if (index != -1) {
            return index;
        }
        return binarySearch(array, peak + 1, array.length - 1, key, false);
    }

    boolean contains(int[] a, int lo, int hi, int key) {

        if (lo > hi) return false;

        int mid = lo + (hi - lo)/2;

        if (a[mid] == key)
            return true;

        boolean increasing = mid < a.length-1 &&
                a[mid] < a[mid+1];

        if (increasing) {

            if (key > a[mid]) {
                return contains(a, mid+1, hi, key);
            } else {
                return contains(a, lo, mid-1, key)
                        || contains(a, mid+1, hi, key);
            }

        } else {

            if (key > a[mid]) {
                return contains(a, lo, mid-1, key);
            } else {
                return contains(a, lo, mid-1, key)
                        || contains(a, mid+1, hi, key);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 3, 8, 12, 42, 65, 34, 24, 4, 2};
        Bitonic bitonic = new Bitonic();
        int key = 2;
        int index = bitonic.search(arr, key);
        if (index != -1) {
            System.out.println("Element " + key + " found at index: " + index);
        } else {
            System.out.println("Element " + key + " not found.");
        }
    }
}
