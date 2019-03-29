package com.qsmx.zww.servcie;

import com.qsmx.zww.po.PoetryInfo;

/**
 * Created by zww on 2019-03-25.
 */
public interface PoetryService {
    //偷取古诗
    Integer insertStealPoetry();

    //偷网名
    Integer insertNetName();
}
