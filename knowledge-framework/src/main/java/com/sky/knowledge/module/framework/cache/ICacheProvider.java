package com.sky.knowledge.module.framework.cache;

import java.util.Date;

public interface ICacheProvider<K, V> {
    Date getLastModifyTime();
}
