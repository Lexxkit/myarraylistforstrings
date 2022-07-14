import exception.InvalidIndexException;
import exception.ItemNotFoundException;
import exception.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {
    private IntegerList out;

    @BeforeEach
    void setUp() {
        out = new IntegerListImpl(IntegerListTestConstants.INITIAL_ARRAY_SIZE);
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(IntegerListTestConstants.EMPTY_ARRAY_SIZE, out.size());
    }

    @Test
    void shouldAddItemToListAndReturnItem() {
        Integer result = out.add(IntegerListTestConstants.ONE);

        assertEquals(1, out.size());
        assertEquals(IntegerListTestConstants.ONE, result);
    }

    @Test
    void shouldThrowNullItemExceptionWhenAddNull() {
        assertThrows(NullItemException.class, () -> out.add(IntegerListTestConstants.NULL_INTEGER));
    }

    @Test
    void shouldExtendListWhenAddItemToFullInitialList() {
        out.add(IntegerListTestConstants.ONE);
        out.add(IntegerListTestConstants.ONE);
        int result = out.size();

        assertEquals(2, result);
    }

    @Test
    void shouldThrowNullItemExceptionWhenAddNullItemWithRealIndex() {
        assertThrows(NullItemException.class, () -> out.add(IntegerListTestConstants.INDEX_ZERO, IntegerListTestConstants.NULL_INTEGER));
    }

    @Test
    void shouldThrowInvalidIndexExceptionWhenAddItemWithInvalidIndex() {
        assertThrows(InvalidIndexException.class, () -> out.add(IntegerListTestConstants.INVALID_INDEX, IntegerListTestConstants.ONE));
    }

    @Test
    void shouldAddItemToSpecifiedIndexAndReturnItem() {
        out.add(IntegerListTestConstants.ONE);
        Integer result = out.add(IntegerListTestConstants.INDEX_ZERO, IntegerListTestConstants.TWO);
        Integer resultGetByIndex = out.get(IntegerListTestConstants.INDEX_ZERO);

        assertEquals(2, out.size());
        assertEquals(IntegerListTestConstants.TWO, result);
        assertEquals(IntegerListTestConstants.TWO, resultGetByIndex);

    }

    @Test
    void shouldSetItemToSpecifiedIndexAndReturnItem() {
        out.add(IntegerListTestConstants.ONE);
        Integer result = out.set(IntegerListTestConstants.INDEX_ZERO, IntegerListTestConstants.TWO);

        assertEquals(IntegerListTestConstants.INITIAL_ARRAY_SIZE, out.size());
        assertEquals(IntegerListTestConstants.TWO, result);
    }

    @Test
    void shouldThrowInvalidIndexExceptionWhenSetItemWithInvalidIndex() {
        assertThrows(InvalidIndexException.class, () -> out.set(IntegerListTestConstants.INVALID_INDEX, IntegerListTestConstants.ONE));
    }

    @Test
    void shouldThrowNullItemExceptionWhenSetNullItemWithRealIndex() {
        assertThrows(NullItemException.class, () -> out.set(IntegerListTestConstants.INDEX_ZERO, IntegerListTestConstants.NULL_INTEGER));
    }

    @Test
    void shouldRemoveItemShrinkListAndReturnRemovedItem() {
        out.add(IntegerListTestConstants.ONE);
        Integer result = out.remove(IntegerListTestConstants.ONE);

        assertEquals(0, out.size());
        assertEquals(IntegerListTestConstants.ONE, result);
    }

    @Test
    void shouldThrowNullItemExceptionWhenRemoveNullItem() {
        assertThrows(NullItemException.class, () -> out.remove(IntegerListTestConstants.NULL_INTEGER));
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenRemoveNonExistentItem() {
        assertThrows(ItemNotFoundException.class, () -> out.remove(IntegerListTestConstants.ONE));
    }

    @Test
    void shouldRemoveItemByIndexShrinkListAndReturnRemovedItem() {
        out.add(IntegerListTestConstants.ONE);
        Integer result = out.remove(IntegerListTestConstants.INDEX_ZERO);

        assertEquals(0, out.size());
        assertEquals(IntegerListTestConstants.ONE, result);
    }

    @Test
    void shouldThrowInvalidIndexExceptionWhenRemoveItemByInvalidIndex() {
        assertThrows(InvalidIndexException.class, () -> out.remove(IntegerListTestConstants.INVALID_INDEX));
    }

    @Test
    void shouldReturnTrueWhenListContainsItem() {
        out.add(IntegerListTestConstants.ONE);
        boolean result = out.contains(IntegerListTestConstants.ONE);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenListNOTContainsItem() {
        boolean result = out.contains(IntegerListTestConstants.ONE);

        assertFalse(result);
    }

    @Test
    void shouldReturnMinusOneWhenSearchForIndexOfItemNotInList() {
        int result = out.indexOf(IntegerListTestConstants.ONE);

        assertEquals(-1, result);
    }

    @Test
    void shouldReturnIndexWhenSearchForIndexOfItemInList() {
        out.add(IntegerListTestConstants.ONE);
        int result = out.indexOf(IntegerListTestConstants.ONE);

        assertEquals(IntegerListTestConstants.INDEX_ZERO, result);
    }

    @Test
    void shouldReturnMinusOneWhenSearchForLastIndexOfItemNotInList() {
        int result = out.lastIndexOf(IntegerListTestConstants.ONE);

        assertEquals(-1, result);
    }

    @Test
    void shouldReturnIndexWhenSearchForLastIndexOfItemInList() {
        out.add(IntegerListTestConstants.ONE);
        out.add(IntegerListTestConstants.TWO);
        int result = out.lastIndexOf(IntegerListTestConstants.TWO);

        assertEquals(IntegerListTestConstants.INDEX_ONE, result);
    }

    @Test
    void shouldReturnTrueForEmptyList() {
        boolean result = out.isEmpty();
        assertTrue(result);
    }

    @Test
    void shouldSetListSizeToZeroWhenClearList() {
        out.add(IntegerListTestConstants.ONE);
        out.clear();
        assertEquals(0, out.size());
    }

}