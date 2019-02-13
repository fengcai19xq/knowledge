package com.sky.knowledge.module.framework.shared.service;

import com.sky.knowledge.module.framework.shared.entity.SyncDataRequest;
import com.sky.knowledge.module.framework.shared.entity.SyncDataResponse;

public interface ISyncDataRemoting extends IHessianService{

    SyncDataResponse processSyncData(SyncDataRequest request) ;

}
