import exception.InvalidIndexException;
import exception.ItemNotFoundException;
import exception.NullItemException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {
    private StringList out;

    @BeforeEach
    void setUp() {
        out = new StringListImpl(StringListTestConstants.INITIAL_ARRAY_SIZE);
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        assertEquals(StringListTestConstants.EMPTY_ARRAY_SIZE, out.size());
    }

    @Test
    void shouldAddItemToListAndReturnItem() {
        String result = out.add(StringListTestConstants.TEST_STRING);

        assertEquals(1, out.size());
        assertEquals(StringListTestConstants.TEST_STRING, result);
    }

    @Test
    void shouldThrowNullItemExceptionWhenAddNull() {
        assertThrows(NullItemException.class, () -> out.add(StringListTestConstants.NULL_STRING));
    }

    @Test
    void shouldExtendListWhenAddItemToFullInitialList() {
        out.add(StringListTestConstants.TEST_STRING);
        out.add(StringListTestConstants.TEST_STRING);
        int result = out.size();

        assertEquals(2, result);
    }

    @Test
    void shouldThrowNullItemExceptionWhenAddNullItemWithRealIndex() {
        assertThrows(NullItemException.class, () -> out.add(StringListTestConstants.INDEX_ZERO, StringListTestConstants.NULL_STRING));
    }

    @Test
    void shouldThrowInvalidIndexExceptionWhenAddItemWithInvalidIndex() {
        assertThrows(InvalidIndexException.class, () -> out.add(StringListTestConstants.INVALID_INDEX, StringListTestConstants.TEST_STRING));
    }

    @Test
    void shouldAddItemToSpecifiedIndexAndReturnItem() {
        out.add(StringListTestConstants.TEST_STRING);
        String result = out.add(StringListTestConstants.INDEX_ZERO, StringListTestConstants.TEST_STRING_2);
        String resultGetByIndex = out.get(StringListTestConstants.INDEX_ZERO);

        assertEquals(2, out.size());
        assertEquals(StringListTestConstants.TEST_STRING_2, result);
        assertEquals(StringListTestConstants.TEST_STRING_2, resultGetByIndex);

    }

    @Test
    void shouldSetItemToSpecifiedIndexAndReturnItem() {
        out.add(StringListTestConstants.TEST_STRING);
        String result = out.set(StringListTestConstants.INDEX_ZERO, StringListTestConstants.TEST_STRING_2);

        assertEquals(StringListTestConstants.INITIAL_ARRAY_SIZE, out.size());
        assertEquals(StringListTestConstants.TEST_STRING_2, result);
    }

    @Test
    void shouldThrowInvalidIndexExceptionWhenSetItemWithInvalidIndex() {
        assertThrows(InvalidIndexException.class, () -> out.set(StringListTestConstants.INVALID_INDEX, StringListTestConstants.TEST_STRING));
    }

    @Test
    void shouldThrowNullItemExceptionWhenSetNullItemWithRealIndex() {
        assertThrows(NullItemException.class, () -> out.set(StringListTestConstants.INDEX_ZERO, StringListTestConstants.NULL_STRING));
    }

    @Test
    void shouldRemoveItemShrinkListAndReturnRemovedItem() {
        out.add(StringListTestConstants.TEST_STRING);
        String result = out.remove(StringListTestConstants.TEST_STRING);

        assertEquals(0, out.size());
        assertEquals(StringListTestConstants.TEST_STRING, result);
    }

    @Test
    void shouldThrowNullItemExceptionWhenRemoveNullItem() {
        assertThrows(NullItemException.class, () -> out.remove(StringListTestConstants.NULL_STRING));
    }

    @Test
    void shouldThrowItemNotFoundExceptionWhenRemoveNonExistentItem() {
        assertThrows(ItemNotFoundException.class, () -> out.remove(StringListTestConstants.TEST_STRING));
    }

}