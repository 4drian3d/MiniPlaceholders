package me.dreamerzero.miniplaceholders;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.dreamerzero.miniplaceholders.api.Expansion;
import me.dreamerzero.miniplaceholders.api.MiniPlaceholders;
import me.dreamerzero.miniplaceholders.api.utils.TagsUtils;

public class ThrowableTest {
    @Test
    @DisplayName("Nullable Exception")
    void throwOnNull(){
        assertThrows(NullPointerException.class, () -> Expansion.builder(null));

        Expansion.Builder expansionTest = assertDoesNotThrow(() -> Expansion.builder("throwable"));

        assertThrows(NullPointerException.class, () -> expansionTest.audiencePlaceholder(null, (aud, queue, ctx) -> TagsUtils.EMPTY_TAG));
        assertThrows(NullPointerException.class, () -> expansionTest.globalPlaceholder("test", null));

        Expansion.builder("testThrows")
            .audiencePlaceholder("test", (aud, queue, ctx) -> TagsUtils.EMPTY_TAG)
            .relationalPlaceholder("test2", (aud, aud2, queue, ctx) -> TagsUtils.EMPTY_TAG)
            .build()
            .register();

        assertThrows(NullPointerException.class, () -> MiniPlaceholders.getAudiencePlaceholders(null));
        assertThrows(NullPointerException.class, () -> MiniPlaceholders.getRelationalPlaceholders(null, null));
    }

    @Test
    @DisplayName("Empty Exception")
    void throwOnBlankString(){
        Expansion.Builder builder = assertDoesNotThrow(() -> Expansion.builder("hello"));

        assertThrows(IllegalStateException.class, () -> builder.audiencePlaceholder("", (aud, queue, ctx) -> TagsUtils.EMPTY_TAG));
        assertThrows(IllegalStateException.class, () -> builder.globalPlaceholder("     ", (queue, ctx) -> TagsUtils.EMPTY_TAG));

        assertDoesNotThrow(() -> builder.globalPlaceholder("hehe", (queue, ctx) -> TagsUtils.EMPTY_TAG));
    }
}