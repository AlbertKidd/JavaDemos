package com.kidd.demos.typesafe.config;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 类似LinkedHashMap，读写数据时保持顺序
 * 在使用Config进行对象转换时不会丢失顺序信息
 * @author Kidd
 */
public class SortedConfigMap<V> {

    private HashMap<String, V> map = new HashMap<>();

    private LinkedHashSet<String> keys = new LinkedHashSet<>();

    /**
     * 获取键值对的数量
     * @return
     */
    public int size() {
        return keys.size();
    }

    /**
     * 判断Map是否为空
     * @return
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 判断是否存在指定的键
     * @param key
     * @return
     */
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    /**
     * 判断是否存在指定的值
     * @param value
     * @return
     */
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    /**
     * 根据键获取对应的值
     * @param key
     * @return
     */
    public V get(Object key) {
        return map.get(key);
    }

    /**
     * 存储键值对
     * @param key
     * @param value
     * @return
     */
    public V put(String key, V value) {
        keys.add(key);
        return map.put(key, value);
    }

    /**
     * 根据键删除键值对
     * @param key
     * @return
     */
    public V remove(String key) {
        keys.remove(key);
        return map.remove(key);
    }

    /**
     * 批量存储键值对
     * @param map
     */
    public void putAll(Map<String, ? extends V> map) {
        keys.addAll(map.keySet());
        this.map.putAll(map);
    }

    /**
     * 清空所有的键值对
     */
    public void clear(){
        keys.clear();
        values().clear();
    }

    /**
     * 获取所有的键
     * @return
     */
    public Set<String> keySet(){
        return keys;
    }

    /**
     * 获取当前对象对应的LinkedHashMap
     * @return
     */
    public LinkedHashMap<String, V> getSortedMap() {
        LinkedHashMap<String, V> linkedHashMap = new LinkedHashMap<>();
        for (String key : keys) {
            linkedHashMap.put(key, map.get(key));
        }
        return linkedHashMap;
    }

    /**
     * 获取所有的值
     * @return
     */
    public Collection<V> values() {
        return getSortedMap().values();
    }

    /**
     * 获取所有的键值对
     * @return
     */
    public Set<Map.Entry<String, V>> entrySet() {
        return getSortedMap().entrySet();
    }

}
