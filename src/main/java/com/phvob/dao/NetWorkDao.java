package com.phvob.dao;

import java.util.List;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-26 18:36
 */

public interface NetWorkDao {
    public List<String> queryAllNetWork();
    public List<String> queryHostByNetWork(String network);
}
