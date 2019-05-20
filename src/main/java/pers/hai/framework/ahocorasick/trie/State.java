package pers.hai.framework.ahocorasick.trie;

import java.util.*;

/**
 * <p>
 * 一个状态有如下几个功能
 * </p>
 * <p/>
 * <ul>
 * <li>success; 成功转移到另一个状态</li>
 * <li>failure; 不可顺着字符串跳转的话，则跳转到一个浅一点的节点</li>
 * <li>emits; 命中一个模式串</li>
 * </ul>
 * <p/>
 * <p>
 * 根节点稍有不同，根节点没有 failure 功能，它的“failure”指的是按照字符串路径转移到下一个状态。其他节点则都有failure状态。
 * </p>
 *
 * @author Robert Bor
 */
public abstract class State {

    /**
     * 模式串的长度，也是这个状态的深度
     */
    protected final int depth;

    /**
     * 只用于根节点来表示自己（当没有匹配到任何模式串的时候）
     */
    protected final State rootState;

    /**
     * fail 函数，如果没有匹配到，则跳转到此状态。
     */
    private State failure = null;

    /**
     * 只要这个状态可达，则记录模式串
     */
    private Set<String> emits = null;

    /**
     * 构造深度为0的节点
     */
    public State()
    {
        this(0);
    }

    /**
     * 构造深度为depth的节点
     * @param depth
     */
    public State(int depth)
    {
        this.depth = depth;
        this.rootState = depth == 0 ? this : null;
    }

    /**
     * 获取节点深度
     * @return
     */
    public int getDepth()
    {
        return this.depth;
    }

    /**
     * 添加一个匹配到的模式串（这个状态对应着这个模式串)
     * @param keyword
     */
    public void addEmit(String keyword)
    {
        if (this.emits == null)
        {
            this.emits = new TreeSet<String>();
        }
        this.emits.add(keyword);
    }

    /**
     * 添加一些匹配到的模式串
     * @param emits
     */
    public void addEmit(Collection<String> emits)
    {
        for (String emit : emits)
        {
            addEmit(emit);
        }
    }

    /**
     * 获取这个节点代表的模式串（们）
     * @return
     */
    public Collection<String> emit()
    {
        return this.emits == null ? Collections.<String>emptyList() : this.emits;
    }

    /**
     * 获取failure状态
     * @return
     */
    public State failure()
    {
        return this.failure;
    }

    /**
     * 设置failure状态
     * @param failState
     */
    public void setFailure(State failState)
    {
        this.failure = failState;
    }

    /**
     * 转移到下一个状态（基于success转移）
     * @param character 希望按此字符转移
     * @return 转移结果
     */
    public abstract State nextState(Character character);

    /**
     * 转移到下一个状态，忽略根节点
     * @param character
     * @return
     */
    public abstract State nextStateIgnoreRootState(Character character);

    /**
     * 添加一个状态到success函数
     * @param character
     * @return
     */
    public abstract State addState(Character character);

    /**
     * 获取success状态
     * @return
     */
    public abstract Collection<State> getStates();

    /**
     * 获取要转移到下一个状态的可能char
     * @return
     */
    public abstract Collection<Character> getTransitions();
}
