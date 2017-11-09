package com.x.core.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("javadoc")
public abstract class NewBaseViewAdapter extends BaseAdapter {

	protected final String TAG = this.getClass().getSimpleName();
	private final ArrayList<NewAdapterItem> mData = new ArrayList<>();

	/** key=clsName , value=layoutID列表 */
	private HashMap<String, Set<Integer>> mCls_LayoutIdSet_Map = new LinkedHashMap<>();

	/** key=clsName+"_"+layoutID , value=viewType */
	private HashMap<String, Integer> mCls_LayoutId_Map = new HashMap<>();

	private Context context = null;
	private LayoutInflater mInflater = null;
	private final ConcurrentHashMap<String, NewIViewHolder> viewMap = new ConcurrentHashMap<>();

	//=============================重写begin============================

	/** (1)初始化数据类型对应的layoutID */
	protected abstract void onInitViewType();

	/** (2)创建ViewHolder，可根据不同的数据类型返回不同的Holder */
	protected abstract NewBaseViewHolder onCreateViewHolder(View view, Context act, int posIndex, Object data);

	/** (3)销毁时调用 */
	protected abstract void onDestroy();

	/** (4-1)当同一种数据类型对应了多种显示样式的时候，此方法就必须要重写 */
	protected int choseLayoutIdFromList(Object data,Set<Integer> set){
		Iterator<Integer> iter = set.iterator();
		return iter.next();
	}

	/** (4-2)当同一种数据类型对应了多种显示样式的时候，此方法就必须要重写 */
	protected int choseLayoutIdFromList(NewAdapterItem data,Set<Integer> set,int position){
		return 0;
	}

	/** (5)根据索引获取对应的LayoutID,不建议重写 */
	protected final int getLayoutIdByPosition(int position) {
		NewAdapterItem item = getItem(position);
		Object data = item.getData();
		final String className = data.getClass().getName();
		String parentClassName = data.getClass().getSuperclass().getName();
		String clsArr[] = new String[]{className,parentClassName};
		int selectedLayoutId = 0;
		for (int i = 0; i< clsArr.length;i++){
			String clsName = clsArr[i];
			Set<Integer> set = null;
			if(mCls_LayoutIdSet_Map.containsKey(clsName)){
				set =  mCls_LayoutIdSet_Map.get(className);
			}
			if(set != null && set.size() > 0){
				if(set.size() > 1){
					selectedLayoutId = choseLayoutIdFromList(item,set,position);
					if(selectedLayoutId == 0) {
						selectedLayoutId = choseLayoutIdFromList(data, set);
					}
				}else{
					Iterator<Integer> iter = set.iterator();
					selectedLayoutId = iter.next();
				}
				break;
			}
		}
		if(selectedLayoutId == 0){
			throw new RuntimeException("Not found suitable layout ID");
		}
		return selectedLayoutId;
	}


	/** (6)根据positoin获得数据实体，然后根据数据实体获取对应的viewType,不建议重写 */
	@Override
	public final int getItemViewType(int position) {
		int tc = getViewTypeCount();
		if(tc == 1){
			return 0;
		}
		NewAdapterItem item = getItem(position);
		//String key = item.getData().getClass().getName();
		int layoutId = getLayoutIdByPosition(position);
		String indexKey = getIndexKey(item.getData().getClass(),layoutId);
		return mCls_LayoutId_Map.get(indexKey);
	}

	/** (7)返回总共有多少种样式 ,不建议重写*/
	@Override
	public final int getViewTypeCount() {
		return Math.max(mCls_LayoutId_Map.size(),1);
	}

	//=============================重写end============================
	public NewBaseViewAdapter(Context act) {
		super();
		this.context = act;
		mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		onInitViewType();
	}
	
	public final LayoutInflater getLayoutInflater(){
		if(mInflater == null){
			mInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		return mInflater;
	}

	public final void addViewType(Class<?> c, int layoutId) {
		String key = c.getName();
		Set<Integer> set = null;
		if(mCls_LayoutIdSet_Map.containsKey(key)){
			set = mCls_LayoutIdSet_Map.get(key);
		}
		if(set == null){
			set = new TreeSet<>();
			mCls_LayoutIdSet_Map.put(key, set);
		}
		if(set.size() > 0 && set.contains(layoutId)){
			return;
		}
		set.add(layoutId);
		String indexKey = getIndexKey(c,layoutId);
		if(!mCls_LayoutId_Map.containsKey(indexKey)) {
			mCls_LayoutId_Map.put(indexKey, mCls_LayoutId_Map.size());
		}
	}

	private String getIndexKey(Class<?> c,int layoutId){
		String key = c.getName();
		return key+"_"+layoutId;
	}

	@Override
	public final int getCount() {
		return mData.size();
	}

	@Override
	public final NewAdapterItem getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public final long getItemId(int arg0) {
		return arg0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        NewBaseViewHolder holder ;
		NewAdapterItem item = getItem(position);
		if (convertView == null) {
			convertView = inflateView(position, null, parent);
			if(convertView == null){
				return null;
			}
			holder = createViewHolder(convertView,position);
			viewMap.put(holder.toString(), holder);
		} else {
			holder = (NewBaseViewHolder) convertView.getTag();
			holder.setPosIndex(position);
			holder.onLayout();
		}
        convertView.setTag(holder);
		holder.setItem(item);
		return convertView;
	}
	
	private NewAdapterItem convertDataToAdapterItem(Object data, Object state){
		if (data == null)
			return null;
		return new NewAdapterItem(data, state);
	}

	public final NewAdapterItem addItem(Object data, Object state) {
		return addItem(mData.size(),data,state);
	}
	
	public final NewAdapterItem addItem(int index, Object data, Object state) {
		NewAdapterItem adapterItem = convertDataToAdapterItem(data,state);
		if(adapterItem != null)
			mData.add(index,adapterItem);
		return adapterItem;
	}
	
	public final NewAdapterItem addItem(NewAdapterItem ai) {
		mData.add(ai);
		return ai;
	}
	
	public final NewAdapterItem addItem(Object data, Object state, NewOnAdapterItemStateChangeListener listener) {
		NewAdapterItem item = addItem(data, state);
		if (item != null)
			item.setOnAdapterItemStateChangeListener(listener);
		return item;
	}

	public final NewAdapterItem addItem(int index, Object data, Object state, NewOnAdapterItemStateChangeListener listener) {
		NewAdapterItem item = addItem(index, data, state);
		if(item != null)
			item.setOnAdapterItemStateChangeListener(listener);
		return item;
	}
	
	public final List<NewAdapterItem> addItems(List<Object> datas, List<Object> states){
		if(datas == null || datas.size() == 0){
			return null;
		}
		List<NewAdapterItem> items = new ArrayList<>();
		for (int i = 0; i < datas.size() ; i++){
			Object st = states != null && states.size() > i ? states.get(i) : null;
			NewAdapterItem item = addItem(datas.get(i),st);
			if(item != null){
				items.add(item);
			}
		}
		return items;
	}

	public final void delItem(NewAdapterItem item) {
		if (item == null || mData == null || mData.size() == 0)
			return;
		mData.remove(item);
	}

	public final NewAdapterItem delItem(int position) {
		if(mData == null)
			return null;
		if (position >= mData.size())
			return null;
		NewAdapterItem item = mData.remove(position);
		return item;
	}

	public final void clearItems() {
		if(mData != null) {
			mData.clear();
		}
	}

	private NewBaseViewHolder createViewHolder(View view, int posIndex) {
		Object data = getItem(posIndex).getData();
		NewBaseViewHolder holder = onCreateViewHolder(view, this.context,posIndex,data);
        holder.setPosIndex(posIndex);
		holder.setDataType(data.getClass());
		holder.initViews();
		return holder;
	}

	private View inflateView(int position, View convertView, ViewGroup parent) {
		int resource = getLayoutIdByPosition(position);
		View v = convertView;
		if (convertView == null) {
			v = mInflater.inflate(resource, parent, false);
		}
		return v;
	}

	protected final Context getMyContext() {
		return this.context;
	}

	/**
	 * 在Activity销毁的时候，必须要手动调用
	 */
	public final void destroy() {
		try {
			onDestroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (NewIViewHolder h : viewMap.values()) {
			h.destroy();
		}
		viewMap.clear();
		mCls_LayoutIdSet_Map.clear();
		mCls_LayoutId_Map.clear();
	}

	public final ArrayList<NewAdapterItem> getItems() {
		ArrayList<NewAdapterItem> items = new ArrayList<>();
		items.addAll(mData);
		return items;
	}

	@Deprecated
	public static void printStatck(){
		   Throwable ex = new Throwable();
	        StackTraceElement[] es = ex.getStackTrace();
	        if (es != null) {
	            for (int i = 0; i < es.length; i++) {
	                StackTraceElement item = es[i];
	                String gg = item.getClassName()+"->"+item.getMethodName()+" line:"+item.getLineNumber();
	                System.out.println(gg);
	            }
	        }
	}
	private Map<String,String> extraMap = null;
	public final void putParam(String key,String value){
		if(extraMap == null){
			extraMap = new HashMap<>();
		}
		extraMap.put(key,value);
	}
	public final String getParam(String key){
		if(extraMap == null || extraMap.size() == 0){
			return null;
		}
		return extraMap.get(key);
	}
}
