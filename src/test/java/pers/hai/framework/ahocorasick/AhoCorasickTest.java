package pers.hai.framework.ahocorasick;

import pers.hai.framework.ahocorasick.trie.Emit;
import pers.hai.framework.ahocorasick.trie.Trie;
import pers.hai.framework.ahocorasick.trie.TrieConfig;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description TODO
 * @Author: Q-WHai
 * @Date: Created in 17:12 2019/05/06
 */
public class AhoCorasickTest {

    private final Logger logger = Logger.getLogger(AhoCorasickTest.class);

    @Test
    public void test1() {
        String text = "Hello world, Hello java.";
        Set<String> words = new HashSet<>(){{
            add("Hello");
            add("World");
            add("java");
        }};

        Trie trie = new Trie();
        for (String word : words) {
            trie.addKeyword(word);
        }

        Collection<Emit> emits = trie.parseText(text);
        for (Emit emit : emits) {
            logger.info(emit);
        }
    }

    @Test
    public void test2() {
        String text = "基于Java实现AhoCorasick自动机框架";
        Set<String> words = new HashSet<>(){{
            add("基于");
            add("AhoCorasick");
            add("自动机");
        }};

        Trie trie = new Trie(false);
        for (String word : words) {
            trie.addKeyword(word);
        }

        Collection<Emit> emits = trie.parseText(text);
        for (Emit emit : emits) {
            logger.info(emit);
        }
    }

    @Test
    public void test3() {
        String text = "Hello world, Hello java.";
        Set<String> words = new HashSet<>(){{
            add("hello");
            add("World");
            add("java");
        }};

        TrieConfig config = new TrieConfig();
        config.setAllowOverlaps(false);
        config.setOnlyWholeWords(true);
        config.setCaseInsensitive(true);

        Trie trie = new Trie(config);
        for (String word : words) {
            trie.addKeyword(word);
        }

        Collection<Emit> emits = trie.parseText(text);
        for (Emit emit : emits) {
            logger.info(emit);
        }
    }

    @Test
    public void test4() {
        String text = "基于Java实现AhoCorasick自动机框架";
        Set<String> words = new HashSet<>(){{
            add("基于");
            add("ahocorasick");
            add("自动机");
            add("java");
        }};

        TrieConfig config = new TrieConfig();
        config.setAllowOverlaps(false);
        config.setOnlyWholeWords(true);
        config.setCaseInsensitive(true);

        Trie trie = new Trie(config, false);
        for (String word : words) {
            trie.addKeyword(word);
        }

        Collection<Emit> emits = trie.parseText(text);
        for (Emit emit : emits) {
            logger.info(emit);
        }
    }
}
