/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/10/31 21:25</create-date>
 *
 * <copyright file="UnicodeState.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package org.ahocorasick.trie;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author hankcs
 */
public class UnicodeState extends State
{
    /**
     * goto 表，也称转移函数。根据字符串的下一个字符转移到下一个状态
     */
    private Map<Character, State> success = new TreeMap<Character, State>();

    public UnicodeState()
    {
        super();
    }

    public UnicodeState(int depth)
    {
        super(depth);
    }

    /**
     * 转移到下一个状态
     * @param character 希望按此字符转移
     * @param ignoreRootState 是否忽略根节点，如果是根节点自己调用则应该是true，否则为false
     * @return 转移结果
     */
    private State nextState(Character character, boolean ignoreRootState)
    {
        State nextState = this.success.get(character);
        if (!ignoreRootState && nextState == null && this.rootState != null)
        {
            nextState = this.rootState;
        }
        return nextState;
    }

    @Override
    public State nextState(Character character)
    {
        return nextState(character, false);
    }

    @Override
    public State nextStateIgnoreRootState(Character character)
    {
        return nextState(character, true);
    }

    @Override
    public State addState(Character character)
    {
        State nextState = nextStateIgnoreRootState(character);
        if (nextState == null)
        {
            nextState = new UnicodeState(this.depth + 1);
            this.success.put(character, nextState);
        }
        return nextState;
    }

    @Override
    public Collection<State> getStates()
    {
        return this.success.values();
    }

    @Override
    public Collection<Character> getTransitions()
    {
        return this.success.keySet();
    }
}
