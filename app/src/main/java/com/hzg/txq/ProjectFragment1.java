//package com.hzg.txq;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import android.app.Activity;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.FastJsonRequest;
//import com.google.gson.annotations.JsonAdapter;
//import com.ibstart.adapter.ProjectAdapter;
//import com.ibstart.adapter.ProjectAdapter1;
//import com.ibstart.adapter.ScreedAdapter2;
//import com.ibstart.adapter.ScreedDiQuAdapter;
//import com.ibstart.adapter.ScreedStateAdapter;
//import com.ibstart.adapter.ScreedTypeAdapter;
//import com.ibstart.app.AccountCache;
//import com.ibstart.app.MyApp;
//import com.ibstart.app.R;
//import com.ibstart.app.account.LoginActivity;
//import com.ibstart.app.base.BaseFragment;
//import com.ibstart.app.main.project.ProjectDetailsActivity;
//import com.ibstart.app.main.project.ProjectDetailsActivity2;
//import com.ibstart.entity.Hangye;
//import com.ibstart.entity.Hangye.Industries;
//import com.ibstart.entity.HotCity;
//import com.ibstart.entity.HotCity.HotCities;
//import com.ibstart.entity.ProjectListBean;
//import com.ibstart.entity.ProjectListBean.Project;
//import com.ibstart.entity.ProjectListBean.ProjectInfo;
//import com.ibstart.entity.Projects;
//import com.ibstart.entity.ProjectsEntity;
//import com.ibstart.network.BaseErrorListener;
//import com.ibstart.network.BaseFastJsonRequest;
//import com.ibstart.network.BaseListener;
//import com.ibstart.utils.Config;
//import com.ibstart.utils.GlobalValue;
//import com.ibstart.utils.L;
//import com.ibstart.utils.ScreenUtils;
//import com.ibstart.utils.SharePrfUtil;
//import com.ibstart.utils.T;
//import com.ibstart.view.pulltorefresh.PullToRefreshLayout;
//import com.ibstart.view.pulltorefresh.PullToRefreshLayout.OnRefreshListener;
//import com.ibstart.view.pulltorefresh.PullableListView;
//
///**
// * 项目
// *
// *
// */
//public class ProjectFragment1 extends BaseFragment implements OnClickListener, OnItemClickListener, OnRefreshListener {
////
//	private List<ProjectListBean.ProjectInfo> dataList = new ArrayList<>();
//	private PullToRefreshLayout refresh_view;
//	private PullableListView listview;
//	private ProjectAdapter1 adapter;
//
//	private TextView hangye_txt, city_txt, type_txt, state_txt;
//	private int page = 1;
//	private int totalCount = -1;
//
//	private Intent intent;
//	private Projects projectspage;
//	private String hangyegtxt = "", diqugtxt = "", typetxt = "", statetxt = "";
//	private ListView grid_hangye;// , grid_city, grid_type, grid_state;
//
//	private Hangye hangye;
//	private HotCity hotcity;
//	private ScreedTypeAdapter screedtypeadapter;
//	private ScreedStateAdapter screedstateadapter;
//	private ScreedAdapter2 screedadapter;
//	private ScreedDiQuAdapter screeddiquadapter;
//	private RelativeLayout body;
//	private int select;
//	private IntentFilter filter;
//	private BroadcastReceiver receiver;
//
//	private TextView tv_result;
//
//	private static final int PAGE_SIZE = 10;
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		this.filter = new IntentFilter();
//		filter.addAction(GlobalValue.ACTION_LOGIN);
//		filter.addAction(GlobalValue.ACTION_LOGOUT);
//		this.receiver = new BroadcastReceiver() {
//
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				String action = intent.getAction();
//				if (GlobalValue.ACTION_LOGIN.equals(action)) {
//					getData(false, PullToRefreshLayout.REFRESH_TYPE_NONE, page, false);
//				} else if (GlobalValue.ACTION_LOGOUT.equals(action)) {// 这个代码应该用不上
//					for (int i = 0; i < dataList.size(); i++) {
//
//					}
//					adapter.notifyDataSetChanged();
//				}
//			}
//		};
//		MyApp.getInstance().getLocalBroadcastManager().registerReceiver(receiver, filter);
//		return super.onCreateView(inflater, container, savedInstanceState);
//	}
//
//	@Override
//	public void onDestroyView() {
//		super.onDestroyView();
//		MyApp.getInstance().getLocalBroadcastManager().unregisterReceiver(receiver);
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//
//		case R.id.hangye_txt:
//			hideAllScreen();
//			if (select == 1) {
//				// 关闭
//				// body.setVisibility(View.GONE);
//				hidBody();
//				select = 0;
//			} else {
//				// 打开
//				if (screedadapter == null) {
//					hangyedata(true, true);
//				} else {
//
//					grid_hangye.setAdapter(screedadapter);
//					setScreenTextView(hangye_txt, true);
//					// body.setVisibility(View.VISIBLE);
//					showBody(screedadapter.getCount());
//					select = 1;
//				}
//			}
//			break;
//		case R.id.body:
//			hidBody();
//			// body.setVisibility(View.GONE);
//			select = 0;
//			break;
//
//		case R.id.city_txt:
//			hideAllScreen();
//			if (select == 2) {
//				// 关闭
//				// body.setVisibility(View.GONE);
//				hidBody();
//				select = 0;
//			} else {
//				// 打开
//				if (screeddiquadapter != null) {
//					setScreenTextView(city_txt, true);
//					grid_hangye.setAdapter(screeddiquadapter);
//					// body.setVisibility(View.VISIBLE);
//					showBody(screeddiquadapter.getCount());
//					select = 2;
//				} else {
//					gethostcity(true, true);
//				}
//			}
//			break;
//		case R.id.type_txt:
//			hideAllScreen();
//
//			if (select == 3) {
//				// 关闭
//				// body.setVisibility(View.GONE);
//				hidBody();
//				select = 0;
//			} else {
//				// 打开
//				grid_hangye.setAdapter(screedtypeadapter);
//				// body.setVisibility(View.VISIBLE);
//				showBody(screedtypeadapter.getCount());
//				select = 3;
//				setScreenTextView(type_txt, true);
//			}
//			break;
//		case R.id.state_txt:
//
//			hideAllScreen();
//			if (select == 4) {
//				// 关闭
//				// body.setVisibility(View.GONE);
//
//				hidBody();
//				select = 0;
//			} else {
//				// 打开
//
//				grid_hangye.setAdapter(screedstateadapter);
//				// body.setVisibility(View.VISIBLE);
//				setScreenTextView(state_txt, true);
//				showBody(screedstateadapter.getCount());
//				select = 4;
//			}
//			break;
//		// case R.id.confirm:
//		//
//		// body.setVisibility(View.GONE);
//		// select = 0;
//		// page = 1;
//		// getData(true, PullToRefreshLayout.REFRESH_TYPE_NONE, 1, true);
//		// break;
//		// case R.id.reset:
//		//
//		// body.setVisibility(View.GONE);
//		// select = 0;
//		// page = 1;
//		// reset();
//		// getData(true, PullToRefreshLayout.REFRESH_TYPE_NONE, 1, false);
//		// break;
//		case R.id.tv_result:
//			getData(true, PullToRefreshLayout.REFRESH_TYPE_NONE, page, false);
//			break;
//		}
//	}
//
//	private void hidBody() {
//		hideAllScreen();
//		body.setVisibility(View.GONE);
//	}
//
//	private void hideAllScreen() {
//		setScreenTextView(city_txt, false);
//		setScreenTextView(hangye_txt, false);
//		setScreenTextView(state_txt, false);
//		setScreenTextView(type_txt, false);
//	}
//
//	private void setScreenTextView(TextView tv, boolean show) {
//		if (show) {
//			setTextViewDrawableRight(tv, R.drawable.screen_open);
//			tv.setTextColor(getResources().getColor(R.color.detault));
//		} else {
//			setTextViewDrawableRight(tv, R.drawable.screen_default);
//			tv.setTextColor(getResources().getColor(R.color.text2));
//		}
//	}
//
//	private void setTextViewDrawableRight(TextView tv, int resId) {
//		Drawable d = getResources().getDrawable(resId);
//		d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
//		tv.setCompoundDrawables(null, null, d, null);
//	}
//
//	private void showBody(int count) {
//		View listItem = grid_hangye.getAdapter().getView(0, null, grid_hangye);
//		if (listItem != null) {
//			listItem.measure(0, 0);
//			int listItemHeight = listItem.getMeasuredHeight();
//			int dp480 = ScreenUtils.convertDpToPx(mContext, 300);
//			if (dp480 > count * listItemHeight) {
//				grid_hangye.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//						RelativeLayout.LayoutParams.WRAP_CONTENT));
//			} else {
//				grid_hangye.setLayoutParams(
//						new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, dp480));
//			}
//		}
//		body.setVisibility(View.VISIBLE);
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> pview, View view, int p, long id) {
//		//
//		switch (pview.getId()) {
//
//		case R.id.grid_hangye:
//
//			if (select == 1) {
//
//				screedadapter.selectedPosition = p;
//				screedadapter.notifyDataSetChanged();
//				hangyegtxt = hangye.industries.get(p).industryId;
//				hangye_txt.setText(hangye.industries.get(p).industryName);
//				if (p == 0) {
//					hangyegtxt = "";
//				}
//			} else if (select == 2) {
//				screeddiquadapter.selectedPosition = p;
//				screeddiquadapter.notifyDataSetChanged();
//				diqugtxt = hotcity.hotCities.get(p).id;
//				city_txt.setText(hotcity.hotCities.get(p).name);
//				if (p == 0) {
//					diqugtxt = "";
//				}
//
//			} else if (select == 3) {
//				screedtypeadapter.selectedPosition = p;
//				screedtypeadapter.notifyDataSetChanged();
//				switch (p) {
//				case 0:
//					typetxt = "";
//					type_txt.setText("全部分类");
//					break;
//				case 1:
//					typetxt = "ANGEL";
//					type_txt.setText("天使项目");
//					break;
//				case 2:
//					typetxt = "VCPE";
//					type_txt.setText("VCPE项目");
//					break;
//				case 3:
//					typetxt = "PLC";
//					type_txt.setText("上市公司项目");
//					break;
//				// case 4:
//				// typetxt = "PFUND";
//				// type_txt.setText("私募基金项目");
//				// break;
//				default:
//					break;
//				}
//			} else if (select == 4) {
//				screedstateadapter.selectedPosition = p;
//				screedstateadapter.notifyDataSetChanged();
//				switch (p) {
//				case 0:
//					statetxt = "";
//					state_txt.setText("全部状态");
//					break;
//				case 1:
//					statetxt = "FINANCING";
//					state_txt.setText("融资中");
//					break;
//				case 2:
//					statetxt = "FINISH";
//					state_txt.setText("完成");
//					break;
//				default:
//					break;
//				}
//			}
//			hidBody();
//			select = 0;
//			page = 1;
//			getData(true, PullToRefreshLayout.REFRESH_TYPE_NONE, page, true);
//			break;
//
//		default:
//			break;
//		}
//
//	}
//
//	private void getData(final boolean dialogSHow, final int pullType, final int page, final boolean isFromFilter) {
//		if (dialogSHow) {
//			loadingDialog.show();
//		}
//
//		String url = Config.URL_GetVCProjectList + "?type=" + typetxt + "&state=" + statetxt + "&city=" + diqugtxt
//				+ "&industry=" + hangyegtxt + "&page=" + page + "&pageSize=" + PAGE_SIZE;
//		//登陆接口没更新，在浏览器获取access_token使用
//        String urlNew=Config.URL_GetVCProjectList_New+"?access_token="+"d4ef5890-10bb-4adc-8cf4-c1de0c98cbaf"
//				             +"&pageSize="+"10"
//        		             +"&pageIndex="+page;
//        BaseListener listenerNew=new BaseListener()
//        {
//        	@Override
//        	public void onResponse(JSONObject response) {
//        		// TODO Auto-generated method stub
//        		super.onResponse(response);
//				L.i(response.toJSONString());
//				if (dialogSHow) {
//					loadingDialog.hide();
//				}
//
//				if(response.getString("errorCode") != null){
//					if(response.getString("errorCode").equals("004") ){
//						Intent login = new Intent(getActivity(),LoginActivity.class);
//						startActivity(login);
//					}
//				}
//
//				boolean success = response.getBooleanValue("success");
//				afterFresh(success, pullType);
//				if (success) {
//					tv_result.setVisibility(View.GONE);
//					ProjectListBean dataBean=JSON.parseObject(response.toJSONString(),ProjectListBean.class);
//					totalCount = dataBean.project.pageable.totalElements;
//					JSONArray arr = response.getJSONArray("projects");
//				    List<ProjectInfo> temp =dataBean.project.projectList;
//
//					if (temp.size() == 0) {
//						if (page == 1) {// 说明没有数据，显示textview
//							if (isFromFilter) {
//								tv_result.setVisibility(View.VISIBLE);
//								tv_result.setText("暂无相关条件对应的项目，点击重新加载");
//							} else {
//								tv_result.setVisibility(View.VISIBLE);
//								tv_result.setText("暂无相关数据，点击重新加载");
//							}
//						} else {
//							T.showShort(mContext, "没有更多数据了");
//						}
//					} else {
//						ProjectFragment1.this.page = (page + 1);
//						if (page == 1) {
//							dataList.clear();
//							dataList.addAll(temp);
//							adapter.reLoad(temp);
//						} else {
//							dataList.addAll(temp);
//							adapter.addAll(temp);
//						}
//					}
//
//				} else {
//					T.showShort(mContext, "获取数据失败，请稍后再试");
//					if (page == 1) {
//						if (isFromFilter) {
//							T.showShort(mContext, "加载失败");
//							tv_result.setVisibility(View.VISIBLE);
//							tv_result.setText("加载失败，点击重新加载");
//						} else {
//							if (dataList.size() > 0) {
//								T.showShort(mContext, "加载失败");
//								tv_result.setVisibility(View.GONE);
//							} else {
//								tv_result.setVisibility(View.VISIBLE);
//								tv_result.setText("加载失败，点击重新加载");
//							}
//						}
//					} else {
//						tv_result.setVisibility(View.GONE);
//					}
//				}
//
//        	}
//        };
//
//		BaseErrorListener error = new BaseErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				super.onErrorResponse(error);
//				afterFresh(false, pullType);
//				if (dialogSHow) {
//					loadingDialog.hide();
//				}
//				if (page == 1) {
//					if (isFromFilter) {
//						T.showShort(mContext, "加载失败");
//						tv_result.setVisibility(View.VISIBLE);
//						tv_result.setText("加载失败，点击重新加载");
//					} else {
//						if (dataList.size() > 0) {
//							T.showShort(mContext, "加载失败");
//							tv_result.setVisibility(View.GONE);
//						} else {
//							tv_result.setVisibility(View.VISIBLE);
//							tv_result.setText("加载失败，点击重新加载");
//						}
//
//					}
//				} else {
//					tv_result.setVisibility(View.GONE);
//				}
//
//			}
//		};
//
//		BaseFastJsonRequest request = new BaseFastJsonRequest(Request.Method.GET, urlNew, listenerNew, error) {
////			@Override
////			public Map<String, String> getHeaders() throws AuthFailureError {
////				super.getHeaders();
//////				Map<String, String> map = new HashMap<String, String>();
//////				map.put("token", SharePrfUtil.getInstance().getApptoken());
//////				map.put("name", SharePrfUtil.getInstance().getUsername());
//////				map.put("Cookie", AccountCache.getCookie());
////				return map;
////			}
//		};
//		MyApp.getInstance().addNewRequest(request, TAG, 2);
//
//	}
//
//	private void afterFresh(boolean success, int pullType) {
//		if (pullType == PullToRefreshLayout.REFRESH_TYPE_TOP) {
//			if (success) {
//				refresh_view.refreshFinish(PullToRefreshLayout.SUCCEED);
//			} else {
//				refresh_view.refreshFinish(PullToRefreshLayout.FAIL);
//
//			}
//		} else if (pullType == PullToRefreshLayout.REFRESH_TYPE_BUTTOM) {
//			if (success) {
//				refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//			} else {
//				refresh_view.loadmoreFinish(PullToRefreshLayout.FAIL);
//
//			}
//		}
//	}
//
//	private void gethostcity(final boolean isDialogShow, final boolean showPanel) {
//		if (isDialogShow) {
//			loadingDialog.show();
//		}
//
//		String url = Config.URL + "/app/hotCities";
//
//		Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				if (isDialogShow) {
//					loadingDialog.hide();
//				}
//				hotcity = JSON.parseObject(response.toJSONString(), HotCity.class);
//				if (hotcity.success) {
//					HotCities model = new HotCities();
//					model.id = "0";
//					model.name = "全部城市";
//					hotcity.hotCities.add(0, model);
//					screeddiquadapter = new ScreedDiQuAdapter(mContext, hotcity);
//					// grid_city.setAdapter(screeddiquadapter);
//					if (showPanel) {
//						grid_hangye.setAdapter(screeddiquadapter);
//						showBody(screeddiquadapter.getCount());
//						// body.setVisibility(View.VISIBLE);
//						select = 2;
//						setScreenTextView(city_txt, true);
//					}
//				} else {
//					if (showPanel) {
//						T.showShort(mContext, "加载失败，请稍后再试");
//					}
//				}
//			}
//		};
//		Response.ErrorListener error = new Response.ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				if (isDialogShow) {
//					loadingDialog.hide();
//				}
//				if (showPanel) {
//					T.showShort(mContext, "加载失败，请稍后再试");
//				}
//			}
//		};
//
//		FastJsonRequest request = new FastJsonRequest(Request.Method.GET, url, listener, error) {
//			@Override
//			public Map<String, String> getHeaders() throws AuthFailureError {
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("token", SharePrfUtil.getInstance().getApptoken());
//				map.put("name", SharePrfUtil.getInstance().getUsername());
//				map.put("Cookie", AccountCache.getCookie());
//				return map;
//			}
//		};
//		MyApp.getInstance().addNewRequest(request, TAG, 2);
//
//	}
//
//	private void hangyedata(final boolean isDialogShow, final boolean showPanel) {
//		if (isDialogShow) {
//			loadingDialog.show();
//		}
//
//		String url = Config.URL_GetHangYeList;
//
//		BaseListener listener = new BaseListener() {
//
//			@Override
//			public void onResponse(JSONObject response) {
//				super.onResponse(response);
//				if (isDialogShow) {
//					loadingDialog.hide();
//				}
//				hangye = JSON.parseObject(response.toJSONString(), Hangye.class);
//				if (hangye.success) {
//					if (hangye.industries != null) {
//						Industries model = new Industries();
//						model.industryId = "0";
//						model.industryName = "全部行业";
//						hangye.industries.add(0, model);
//
//						screedadapter = new ScreedAdapter2(mContext, hangye);
//						if (showPanel) {
//							grid_hangye.setAdapter(screedadapter);
//							body.setVisibility(View.VISIBLE);
//							select = 1;
//						}
//					}
//				} else {
//					if (showPanel) {
//						T.showShort(mContext, "加载失败，请稍后再试");
//					}
//				}
//
//			}
//		};
//		BaseErrorListener error = new BaseErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError error) {
//				super.onErrorResponse(error);
//				if (isDialogShow) {
//					loadingDialog.hide();
//				}
//				if (showPanel) {
//					T.showShort(mContext, "加载失败，请稍后再试");
//				}
//			}
//		};
//
//		BaseFastJsonRequest request = new BaseFastJsonRequest(Request.Method.POST, url, listener, error) {
//			@Override
//			public Map<String, String> getHeaders() throws AuthFailureError {
//				super.getHeaders();
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("token", SharePrfUtil.getInstance().getApptoken());
//				map.put("name", SharePrfUtil.getInstance().getUsername());
//				map.put("Cookie", AccountCache.getCookie());
//				return map;
//			}
//		};
//		MyApp.getInstance().addNewRequest(request, TAG, 2);
//
//	}
//
//	private void reset() {
//		//
//		hangyegtxt = "";
//		diqugtxt = "";
//		typetxt = "";
//		statetxt = "";
//		hangye_txt.setText("全部行业");
//		city_txt.setText("全部城市");
//		type_txt.setText("全部类型");
//		state_txt.setText("全部状态");
//		screedadapter.selectedPosition = -1;
//		// screedadapter.notifyDataSetChanged();
//		screeddiquadapter.selectedPosition = -1;
//		// screeddiquadapter.notifyDataSetChanged();
//		screedtypeadapter.selectedPosition = -1;
//		// screedtypeadapter.notifyDataSetChanged();
//		screedtypeadapter.selectedPosition = -1;
//		// screedtypeadapter.notifyDataSetChanged();
//	}
//
//	@Override
//	protected void initData() {
//		getData(true, PullToRefreshLayout.REFRESH_TYPE_NONE, 1, false);
//		gethostcity(false, false);
//		hangyedata(false, false);
//	}
//
//	@Override
//	protected void initViews(View v) {
//		body = (RelativeLayout) v.findViewById(R.id.body);
//		hangye_txt = (TextView) v.findViewById(R.id.hangye_txt);
//		city_txt = (TextView) v.findViewById(R.id.city_txt);
//		type_txt = (TextView) v.findViewById(R.id.type_txt);
//		state_txt = (TextView) v.findViewById(R.id.state_txt);
//		grid_hangye = (ListView) v.findViewById(R.id.grid_hangye);
//		listview = (PullableListView) v.findViewById(R.id.listview);
//		refresh_view = (PullToRefreshLayout) v.findViewById(R.id.refresh_view);
//
//		screedtypeadapter = new ScreedTypeAdapter(mContext);
//		screedstateadapter = new ScreedStateAdapter(mContext);
//
//		adapter = new ProjectAdapter1(mContext, dataList);
//
//		listview.setAdapter(adapter);
//		this.tv_result = (TextView) v.findViewById(R.id.tv_result);
//		tv_result.setVisibility(View.VISIBLE);
//	}
//
//	@Override
//	protected void addListeners(View v) {
//		body.setOnClickListener(this);
//		hangye_txt.setOnClickListener(this);
//		city_txt.setOnClickListener(this);
//		type_txt.setOnClickListener(this);
//		state_txt.setOnClickListener(this);
//		grid_hangye.setOnItemClickListener(this);
//		refresh_view.setOnRefreshListener(this);
//		tv_result.setOnClickListener(this);
//		listview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
//
//				if (AccountCache.isCookieExpires()) {
//					T.showShort(mContext, "请先登录");
//					// TODO startActivityforresutl
//					Intent i = new Intent(getActivity(), LoginActivity.class);
//					i.putExtra("entity", dataList.get(position));
//					i.putExtra("from", "from_prj_list");
//					startActivityForResult(i, 7756);
//				} else {
//					// VCPEPrjEntity prom = dataList.get(position).project;
//					ProjectInfo entity = dataList.get(position);
//					String projectId = entity.projectUuid;
//
//					if ("ANGEL".equals(entity.projectType) || "VCPE".equals(entity.projectType)) {
//						intent = new Intent(getActivity(), ProjectDetailsActivity.class);
//						intent.putExtra("id", projectId);
//						startActivity(intent);
//					} else {
//						intent = new Intent(getActivity(), ProjectDetailsActivity2.class);
//						intent.putExtra("id", projectId);
//						startActivity(intent);
//					}
//				}
//			}
//		});
//
//	}
//
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		if (resultCode == Activity.RESULT_OK) {
//			switch (requestCode) {
//			case 7756:
//				if (data != null) {
//					ProjectsEntity entity = (ProjectsEntity) data.getSerializableExtra("entity");
//					if (entity != null) {
//
//						long projectId = entity.id;
//
//						if ("ANGEL".equals(entity.type) || "VCPE".equals(entity.type)) {
//							Intent intent = new Intent(getActivity(), ProjectDetailsActivity.class);
//							intent.putExtra("id", projectId);
//							startActivity(intent);
//						} else {
//							Intent intent = new Intent(getActivity(), ProjectDetailsActivity2.class);
//							intent.putExtra("id", projectId);
//							startActivity(intent);
//						}
//					}
//
//				}
//				break;
//
//			default:
//				break;
//			}
//		} else {
//			switch (requestCode) {
//			case 7756:
//				T.showShort(mContext, "请先登录");
//				break;
//
//			default:
//				break;
//			}
//		}
//	}
//
//	@Override
//	protected View setContent(LayoutInflater inflater, ViewGroup container) {
//		return inflater.inflate(R.layout.fragment_project, container, false);
//	}
//
//	@Override
//	public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
//		getData(false, PullToRefreshLayout.REFRESH_TYPE_TOP, 1, false);
//	}
//
//	@Override
//	public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
//		if (totalCount != -1 && dataList != null && dataList.size() >= totalCount) {
//
//			refresh_view.postDelayed(new Runnable() {
//
//				@Override
//				public void run() {
//					T.showShort(mContext, "没有更多数据了");
//					refresh_view.loadmoreFinish(PullToRefreshLayout.SUCCEED);
//				}
//			}, 50);
//		} else {
//			getData(false, PullToRefreshLayout.REFRESH_TYPE_BUTTOM, page, false);
//		}
//	}
//
//}