import exception.*;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private String[] storageArray;

    private int size;

    public StringListImpl() {
        storageArray = new String[10];
    }

    public StringListImpl(int arraySize) {
        this.storageArray = new String[arraySize];
    }

    @Override
    public String add(String item) {
        if (size >= storageArray.length) {
            storageArray = Arrays.copyOf(storageArray, (int) (storageArray.length * 0.5));
        }
        storageArray[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateIndex(index);
        validateItem(item);
        if (index == size) {
            storageArray[size++] = item;
            return item;
        }

        System.arraycopy(storageArray, index, storageArray, index + 1, size - index);
        storageArray[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        storageArray[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
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
    public String remove(int index) {
        validateIndex(index);

        String itemToRemove = storageArray[index];
        if (index != storageArray.length - 1) {
            System.arraycopy(storageArray, index + 1, storageArray, index, size - index);
        }
        size--;
        return itemToRemove;
    }

    @Override
    public boolean contains(String item) {
        int indexOf = indexOf(item);

        return indexOf >= 0;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (storageArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i > 0; i--) {
            if (storageArray[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return storageArray[index];
    }

    @Override
    public boolean equals(StringList otherList) {
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
    public String[] toArray() {
        return Arrays.copyOf(storageArray, size);
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void validateSize() {
        if (size == storageArray.length) {
            throw new StorageIsFullException();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }
}
