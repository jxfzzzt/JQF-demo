package com.jxfzzzt;

import com.pholser.junit.quickcheck.From;
import edu.berkeley.cs.jqf.fuzz.Fuzz;
import edu.berkeley.cs.jqf.fuzz.JQF;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

@Slf4j
@RunWith(JQF.class)
public class JQFTest {

    @Fuzz
    public void testCopy(Map<String, Integer> map, String key) {
        assumeTrue(map.containsKey(key));
        // Create new trie with input `map`
        Trie trie = new PatriciaTrie(map);
        // The key should exist in the trie as well
        assertTrue(trie.containsKey(key));
    }

    @Fuzz
    public void testPrefixMap(HashMap<String, Integer> map, String prefix) {
        assumeTrue(prefix.length() > 0);
        // Create new trie with input `map`
        PatriciaTrie trie = new PatriciaTrie(map);
        // Get sub-map whose keys start with `prefix`
        Map prefixMap = trie.prefixMap(prefix);
        // Ensure that it contains all keys that start with `prefix`
        for (String key : map.keySet()) {
            if (key.startsWith(prefix)) {
                assertTrue(prefixMap.containsKey(key));
            }
        }
    }

    @Fuzz
    public void testCopyAscii(Map<@From(AsciiStringGenerator.class) String, Integer> map,
                              @From(AsciiStringGenerator.class) String key) {
        assumeTrue(map.containsKey(key));
        // Create new trie with input `map`
        Trie trie = new PatriciaTrie(map);
        // The key should exist in the trie as well
        assertTrue(trie.containsKey(key));
    }
}
