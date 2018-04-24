package com.taotao.service;
/**
 * @author shijun_li
 * @date 2018年3月28日 下午8:37:28
 * 
 */

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {
	public TaotaoResult getItemParamByCid(long cid);
	public TaotaoResult insertItemParam(TbItemParam itemParam);
}
