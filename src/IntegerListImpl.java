import exception.InvalidIndexException;
import exception.ItemNotFoundException;
import exception.NullItemException;
import exception.NullParameterException;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private Integer[] storageArray;

    private int size;

    public IntegerListImpl() {
        storageArray = new Integer[10];
    }

    public IntegerListImpl(int arraySize) {
        this.storageArray = new Integer[arraySize];
    }

    @Override
    public Integer add(Integer item) {
        validateItem(item);
        if (size >= storageArray.length) {
            grow();
        }
        storageArray[size++] = item;
        return item;
    }

    private void grow() {
        this.storageArray = Arrays.copyOf(storageArray, (int) (storageArray.length + storageArray.length / 2 + 1));
    }

    @Override
    public Integer add(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        if (index == size) {
            storageArray[size++] = item;
            return item;
        }
        if (size >= storageArray.length) {
            grow();
        }

        System.arraycopy(storageArray, index, storageArray, index + 1, size - index);
        storageArray[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storageArray[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int indexOfItem = indexOf(item);
        if (indexOfItem == -1) {
            throw new ItemNotFoundException();
        }
        if (indexOfItem != storageArray.length - 1) {
            System.arraycopy(storageArray, indexOfItem + 1, storageArray, indexOfItem, size - indexOfItem);
        }
        size--;
        return item;
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);

        Integer itemToRemove = storageArray[index];
        if (index != storageArray.length - 1) {
            System.arraycopy(storageArray, index + 1, storageArray, index, size - index);
        }
        size--;
        return itemToRemove;
    }

    @Override
    public boolean contains(Integer item) {
        if (isEmpty()) {
            return false;
        }
        Integer[] storageCopy = toArray();
        quickSort(storageCopy, 0, size - 1);


        return binarySearch(storageCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (storageArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i > 0; i--) {
            if (storageArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storageArray[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullParameterException();
        }

        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        this.size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storageArray, size);
    }

    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    private void sortInsertion(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= tmp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = tmp;
        }
    }

    private boolean binarySearch(Integer[] arr, Integer element) {
        if (arr.length <= 0) {
            return false;
        }
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (element == arr[mid]) {
                return true;
            }

            if (element < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    private void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


}
