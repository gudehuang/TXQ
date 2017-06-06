//package com.hzg.txq;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.ibstart.app.R;
//import com.ibstart.app.account.LoginActivity;
//import com.ibstart.entity.ProjectListBean;
//import com.ibstart.entity.ProjectListBean.ProjectInfo;
//import com.ibstart.entity.ProjectsEntity;
//import com.ibstart.utils.Config;
//import com.ibstart.utils.L;
//import com.ibstart.utils.ScreenUtils;
//import com.ibstart.utils.StringUtil;
//import com.ibstart.utils.UILUtil;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.text.SpannableStringBuilder;
//import android.text.style.ForegroundColorSpan;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//public class ProjectAdapter1 extends BaseAdapter {
//	private Context context;
//	private LayoutInflater mInflater;
//	// private String type;
//	private List<ProjectListBean.ProjectInfo> dataList;
//	private int detaultColor;
//
//	public ProjectAdapter1(Context context, List<ProjectInfo> dataList) {
//		this.context = context;
//		this.dataList = new ArrayList<>();
//		if (dataList != null) {
//			this.dataList.addAll(dataList);
//		}
//		detaultColor = context.getResources().getColor(R.color.detault);
//		mInflater = LayoutInflater.from(context);
//	}
//
//	public void addAll(List<ProjectInfo> temp) {
//		if (temp == null) {
//			return;
//		}
//		dataList.addAll(temp);
//		notifyDataSetChanged();
//	}
//
//	public void reLoad(List<ProjectInfo> temp) {
//		dataList.clear();
//		if (temp != null) {
//			dataList.addAll(temp);
//		}
//		notifyDataSetChanged();
//	}
//
//	@Override
//	public int getCount() {
//		return dataList.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		return position;
//	}
//
//	@Override
//	public long getItemId(int position) {
//		return position;
//	}
//
//	@Override
//	public int getItemViewType(int position) {
//		String type = dataList.get(position).projectType;
//		if (type.equals("ANGEL") || type.equals("VCPE")) {
//			return 0;
//		} else {
//			return 1;
//		}
//
//	}
//
//	@Override
//	public int getViewTypeCount() {
//		return 2;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parent) {
//		ProjectInfo entity = dataList.get(position);
//		L.i("entity="+entity);
//		String type = entity.projectType;
//		ViewHolder1 vh1 = null;
//		ViewHolder2 vh2 = null;
//		if (convertView == null) {
//
//			if (type.equals("ANGEL") || type.equals("VCPE")) {
//				convertView = mInflater.inflate(R.layout.item_project_one, null);
//				vh1 = new ViewHolder1(convertView);
//
//				convertView.setTag(vh1);
//			} else {
//				convertView = mInflater.inflate(R.layout.item_project_two, null);
//				vh2 = new ViewHolder2();
//				vh2.img = (ImageView) convertView.findViewById(R.id.img);
//				vh2.briefIntroduction = (TextView) convertView.findViewById(R.id.briefIntroduction);
//				vh2.name = (TextView) convertView.findViewById(R.id.name);
//				convertView.setTag(vh2);
//			}
//		} else {
//			if (type.equals("ANGEL") || type.equals("VCPE")) {
//				vh1 = (ViewHolder1) convertView.getTag();
//			} else {
//				vh2 = (ViewHolder2) convertView.getTag();
//			}
//		}
//
//		if (type.equals("ANGEL") || type.equals("VCPE")) {
//			String typeStr = null;
//			if ("ANGEL".equals(type)) {
//				typeStr = "天使";
//			} else if ("VCPE".equals(type)) {
//				typeStr = "VC/PE";
//			}
//			L.i("type=" + typeStr);
//
//			vh1.tv_team.setText("团队： "+entity.projectTeamIntroduce);
//			vh1.name.setText(new SpannableStringBuilder(entity.projectName)
//					.append(StringUtil.getColorSp("(" + typeStr + ")", Color.rgb(0x9c, 0x9c, 0x9c))));
//
//			L.i("name=" + vh1.name.getText());
//
//			UILUtil.LoadImg(Config.IMGURL + entity.projectIcon, vh1.img2, R.drawable.pic, true);
//
//			vh1.tv_follow.setText(StringUtil.getColorSp(""+entity.followNum, detaultColor)
//					.append(StringUtil.getColorSp(" 关注", Color.rgb(0xc5, 0xc5, 0xc5))));
//			vh1.tv_view.setText(StringUtil.getColorSp(""+entity.readNum, detaultColor)
//					.append(StringUtil.getColorSp(" 浏览", Color.rgb(0xc5, 0xc5, 0xc5))));
//			if(entity.financeAmount==0){
//				vh1.progressBar1.setVisibility(View.VISIBLE);
//				vh1.tv_total.setText(new SpannableStringBuilder("融资总额：\n")
//						.append(StringUtil.getColorSp("暂未公开", detaultColor)));
//				vh1.tv_equity.setText("出让股权：\n" + "-");
//				vh1.tv_purpose.setText(new SpannableStringBuilder("意向金额：\n")
//						.append(StringUtil.getColorSp("-", detaultColor)));
//
//			}else{
//
//				vh1.progressBar1.setVisibility(View.VISIBLE);
//				vh1.tv_total.setText(new SpannableStringBuilder("融资总额：\n")
//						.append(StringUtil.getColorSp(entity.financeAmount + "万", detaultColor)));
//				vh1.tv_equity.setText("出让股权：\n" + entity.transfereQuity + "%");
//				vh1.tv_purpose.setText(new SpannableStringBuilder("意向金额：\n")
//						.append(StringUtil.getColorSp(entity.intentionAmount + "万", detaultColor)));
//			}
//			vh1.tv_desc.setText(entity.projectIntroduceBrief);
//
//			vh1.progressBar1.setMax((int) entity.financeAmount);
//			vh1.progressBar1.setProgress((int) entity.intentionAmount);
//
//			if (entity.financeAmount == 0 || entity.intentionAmount == 0) {
//				vh1.progresstxt.setText("0%");
//			} else {
////				String result = String.format("%.2f", Math.round((100 * ((entity.financeAmount / entity.financeAmount)))));
//
//				vh1.progresstxt.setText( Math.round((100 * ((entity.intentionAmount / entity.financeAmount)))) + "%");
//
//			}
//
//			if (entity.industryNameList != null &&entity.industryNameList.size() != 0) {
//				vh1.ll_tag.setVisibility(View.VISIBLE);
//				if (entity.industryNameList.size() <= 3) {
//					for (int i = 0; i <entity.industryNameList.size(); i++) {
//						if (i == 0) {
//							vh1.tv1.setText(entity.industryNameList.get(0));
//							vh1.tv1.setVisibility(View.VISIBLE);
//
//						} else if (i == 1) {
//							vh1.tv2.setText(entity.industryNameList.get(1));
//							vh1.tv2.setVisibility(View.VISIBLE);
//						} else if (i == 2) {
//							vh1.tv3.setText(entity.industryNameList.get(2));
//							vh1.tv3.setVisibility(View.GONE);
//						}
//					}
//				} else {
//					vh1.tv1.setText(entity.industryNameList.get(0));
//					vh1.tv2.setText(entity.industryNameList.get(1));
//					vh1.tv3.setText(entity.industryNameList.get(2));
//					vh1.tv1.setVisibility(View.VISIBLE);
//					vh1.tv2.setVisibility(View.VISIBLE);
//					vh1.tv3.setVisibility(View.GONE);
//				}
//
//			}
//
//			if (entity.financeAmount == 0 || entity.financeAmount == 0) {
//				vh1.iv_tag.setImageResource(R.drawable.item_vcpe_yure);
//				vh1.progressBar1.setVisibility(View.GONE);
//				vh1.progresstxt.setVisibility(View.GONE);
//			} else if(entity.projectState.equals("FINANCING")){
//				vh1.iv_tag.setImageResource(R.drawable.item_vcpe_rongzi);
//				vh1.progressBar1.setVisibility(View.VISIBLE);
//				vh1.progresstxt.setVisibility(View.VISIBLE);
//
//				int x = (int) (100 * (((double) entity.intentionAmount / (double) entity.financeAmount)));
//			}else if(entity.projectState.equals("FINISH")){
//				vh1.iv_tag.setImageResource(R.drawable.item_vcpe_done);
//				vh1.progressBar1.setVisibility(View.VISIBLE);
//				vh1.progresstxt.setVisibility(View.VISIBLE);
//			}
//		} else {
//			UILUtil.LoadImg(Config.IMGURL + entity.projectIcon, vh2.img, R.drawable.pic, true,
//					ScreenUtils.convertDpToPx(context, 4));
//			vh2.briefIntroduction.setText(entity.projectIntroduceBrief);
//			vh2.name.setText(entity.projectName);
//
//		}
//		/**
//		 * 这里是没有权限时跳转登陆界面，判断条件不是现在这个
//		 */
//		if (entity.financeAmount == -1) {
//			Intent intent = new Intent(context, LoginActivity.class);
//			context.startActivity(intent);
//		}
//
//		return convertView;
//
//	}
//
//	class ViewHolder1 {
//		ImageView img2;
//		ImageView iv_tag;
//		TextView tv_desc;
//		TextView tv_total;
//		TextView tv_equity;
//		TextView tv_purpose;
//		TextView name;
//		ProgressBar progressBar1;
//		TextView progresstxt;
//
//		TextView tv1, tv2, tv3;
//		TextView tv_team;
//		TextView tv_follow;
//		TextView tv_view;
//		LinearLayout ll_tag;
//
//		public ViewHolder1(View convertView) {
//			this.img2 = (ImageView) convertView.findViewById(R.id.img2);
//			this.tv_total = (TextView) convertView.findViewById(R.id.tv_total);
//			this.tv_equity = (TextView) convertView.findViewById(R.id.tv_equity);
//			this.tv_purpose = (TextView) convertView.findViewById(R.id.tv_purpose);
//			this.name = (TextView) convertView.findViewById(R.id.name);
//			this.progressBar1 = (ProgressBar) convertView.findViewById(R.id.progressBar1);
//			this.iv_tag = (ImageView) convertView.findViewById(R.id.iv_tag);
//			this.tv_desc = (TextView) convertView.findViewById(R.id.tv_desc);
//			this.tv1 = (TextView) convertView.findViewById(R.id.tv1);
//			this.tv2 = (TextView) convertView.findViewById(R.id.tv2);
//			this.tv3 = (TextView) convertView.findViewById(R.id.tv3);
//			this.tv_team = (TextView) convertView.findViewById(R.id.tv_team);
//			this.tv_follow = (TextView) convertView.findViewById(R.id.tv_follow);
//			this.tv_view = (TextView) convertView.findViewById(R.id.tv_view);
//			this.ll_tag = (LinearLayout) convertView.findViewById(R.id.ll_tag);
//			this.progresstxt = (TextView) convertView.findViewById(R.id.progresstxt);
//		}
//
//	}
//
//	class ViewHolder2 {
//		ImageView img;
//		TextView briefIntroduction;
//		TextView name;
//	}
//}
