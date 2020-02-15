package com.common.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CollectionUtil {

	@SuppressWarnings("unchecked")
	public static synchronized List subList(List list, int index, int length) {
		LinkedList result = new LinkedList();
		if (list.size() < index || list.size() < index + length)
			return result;
		return list.subList(index, index + length);
	}

	@SuppressWarnings("unchecked")
	public static List listToken(List sourceList, Object token){
		List result = new LinkedList();
		int tokenIndex = 0;
		for(tokenIndex = 0; tokenIndex <= sourceList.size() -1; tokenIndex++){
			Integer val = (Integer)sourceList.get(tokenIndex);
			if (val.equals(token)){
				break;
			}else{
				
			}
		}
		for(Iterator<Integer> it = sourceList.iterator(); it.hasNext(); ){
			Integer val = it.next();
			if (val.equals(token)){
				break;
			}else{
				result.add(val);
			}
		}
		if (!result.isEmpty())
			for(int i = 1; i<= tokenIndex; i++){
				sourceList.remove(0);
			}
		if (!sourceList.isEmpty())
			sourceList.remove(0);
		return result;
	}

    /**
     * 整型List转换成字符串，列表中的每个元素都依次存入字符串里
     * @param list
     * @return
     */
	public synchronized static String integerListToString(List<Integer> list){
		StringBuilder sb = new StringBuilder();
		for(Iterator<Integer> it = list.iterator(); it.hasNext();){
			Integer val = it.next();
			sb.append((char)val.intValue());
		}
		return sb.toString();
	}

	public static interface TrimCallback{
		public boolean canRemoveHeader(Object value);
		public boolean canRemoveFooter(Object value);
	}

	/**
	 * 剔除List首尾的无效元素
	 * @param list 需要剔除无效元素的List
	 * @param trimCallback 回调方法
	 */
	@SuppressWarnings("unchecked")
	public synchronized static void trimList(List list, TrimCallback trimCallback){
		int index = 0;
		for(index = 0; index<= list.size() -1; index++){
			Object o = list.get(index);
			if (!trimCallback.canRemoveHeader(o))
				break;
		}
		for(int i = 0; i<= index -1; i++){
			list.remove(0);
		}

		for(index = list.size() -1; index >= 0; index--){
			Object o = list.get(index);
			if (!trimCallback.canRemoveFooter(o))
				break;
		}
		int count = list.size() -index -1;
		for(int i = 0; i<= count -1; i++){
			list.remove(list.size()-1);
		}
	}

	/**
	 * 统计列表中某对象的出现次数
	 * @param list
	 * @param element
	 * @return
	 */
	public synchronized static int getListElementCount(List list, Object element){
		int result = 0;
		for(Iterator it = list.iterator(); it.hasNext();){
			Object o = it.next();
			if ( (element == null && o == null) || (element != null && element.equals(o))){
				result ++;
			}
		}
		return result;
	}
}
