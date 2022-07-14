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
            storageArray = Arrays.copyOf(storageArray, (int) (storageArray.length + storageArray.length * 0.5 + 1));
        }
        storageArray[size++] = item;
        return item;
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
            storageArray = Arrays.copyOf(storageArray, (int) (storageArray.length + storageArray.length * 0.5 + 1));
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
        int indexOf = indexOf(item);

        return indexOf >= 0;
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


}
