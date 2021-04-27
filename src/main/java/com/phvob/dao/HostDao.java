package com.phvob.dao;

import java.net.InetAddress;
import java.util.List;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-25 17:37
 */

public interface HostDao {
    //查询所有主机
    public List<String> queryAllHost();

}
