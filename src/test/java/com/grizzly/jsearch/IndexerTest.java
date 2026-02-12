package com.grizzly.jsearch;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

class IndexerTest {

    @Test
    void shouldStoreWordPositionsCorrectly() {
        Indexer indexer = new Indexer();

        indexer.add("java", "doc1", List.of(1, 5));
        indexer.add("java", "doc2", List.of(3));
        indexer.add("coding", "doc1", List.of(7));

        Map<String, Map<String, List<Integer>>> map = indexer.getIndexMap();

        assertTrue(map.containsKey("java"));
        assertTrue(map.get("java").containsKey("doc1"));
        assertTrue(map.get("java").containsKey("doc2"));

        assertEquals(List.of(1, 5), map.get("java").get("doc1"));
        assertEquals(List.of(3), map.get("java").get("doc2"));

        assertEquals(List.of(7), map.get("coding").get("doc1"));
    }
}
