## jac-core
> 基于Java实现AhoCorasick自动机框架

[![lang](https://img.shields.io/badge/lang-java-brightgreen.svg)]()
[![ide](https://img.shields.io/badge/ide-IntelliJ%20IDEA-brightgreen.svg)]()
[![maven](https://img.shields.io/badge/maven-3.6.0-brightgreen.svg)]()

### Examples

**1.General match method**
```java
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
```
**Result**
```
0:4=Hello
13:17=Hello
19:22=java
```

**2.Chinese match method**
```java
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
```
**Result**
```
0:1=基于
8:18=AhoCorasick
19:21=自动机
```

**3.Match by `TrieConfig`**
```java
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
```
**Result**
```
0:4=hello
13:17=hello
19:22=java
```

----------------------------------------------

- [Blog<sup>csdn</sup>](https://qwhai.blog.csdn.net/)
- 《[深入理解Aho-Corasick自动机算法](https://qwhai.blog.csdn.net/article/details/49335051)》
- [Github](https://github.com/qwhaib)
