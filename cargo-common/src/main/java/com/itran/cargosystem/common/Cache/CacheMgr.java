package com.itran.cargosystem.common.Cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 缓存管理类
 * 
 * @author Administrator
 * 
 */
public class CacheMgr {

	private static Map cacheMap = new HashMap();
	private static Map cacheConfMap = new HashMap();

	private static CacheMgr cm = null;

	// 构造方法
	private CacheMgr() {
	}

	public static CacheMgr getInstance() {
		if (cm == null) {
			cm = new CacheMgr();
			/*Thread t = new ClearCache();
			t.start();*/
		}
		return cm;
	}

	/**
	 * 增加缓存
	 * 
	 * @param key
	 * @param value
	 * @param ccm
	 *            缓存对象
	 * @return
	 */
	public boolean addCache(Object key, Object value/*, CacheConfModel ccm*/) {
		boolean flag = false;
		try {
			cacheMap.put(key, value);
			/*cacheConfMap.put(key, ccm);*/
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 获取缓存实体
	 */
	public Object getValue(String key) {
		Object ob = cacheMap.get(key);
		if (ob != null) {
			return ob;
		} else {
			return null;
		}
	}

	/**
	 * 获取缓存数据的数量
	 * 
	 * @return
	 */
	public int getSize() {
		return cacheMap.size();
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public boolean removeCache(Object key) {
		boolean flag = false;
		try {
			cacheMap.remove(key);
			cacheConfMap.remove(key);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 清空缓存
	 * 
	 * @param key
	 * @return
	 */
	public boolean clearCache() {
		boolean flag = false;
		try {
			cacheMap.clear();
			cacheConfMap.clear();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	
	/**
	 * 清除缓存的类 继承Thread线程类
	 *//*
	private static class ClearCache extends Thread {
		public void run() {
			while (true) {
				Set tempSet = new HashSet();
				Set set = cacheConfMap.keySet();
				Iterator it = set.iterator();
				while (it.hasNext()) {
					Object key = it.next();
					CacheConfModel ccm = (CacheConfModel) cacheConfMap.get(key);
					// 比较是否需要清除
					if (!ccm.isForever()) {
						if ((new Date().getTime() - ccm.getBeginTime()) >= ccm.getDurableTime() * 60 * 1000) {
							// 可以清除，先记录下来
							tempSet.add(key);
						}
					}
				}
				// 真正清除
				Iterator tempIt = tempSet.iterator();
				while (tempIt.hasNext()) {
					Object key = tempIt.next();
					cacheMap.remove(key);
					cacheConfMap.remove(key);

				}
				System.out.println("now thread================>" + cacheMap.size());
				// 休息
				try {
					Thread.sleep(60 * 1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}*/

}