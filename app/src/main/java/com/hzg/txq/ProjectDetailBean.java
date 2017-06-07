package com.hzg.txq;

import java.io.Serializable;
import java.util.List;

public class ProjectDetailBean {

	public Project project;
	public boolean success;

	public Project getProject() {
		return project;
	}

	public boolean isSuccess() {
		return success;
	}

	public static class VcpeProject {
		public int projectValue;//项目估值
		public int financeAmount;//融资金额
		public int transferEquity;//出让股权
		public int intentionAmount;//意向金额

		public String downstreamCustomer;//下游客户
		public String projectRisk;//项目风险提示
		public String profitModel;//盈利模式
		public String marketAnalysis;//市场分析
		public String upstreamSupplier;//上游供应商
		public String mainBusiness;//主营客户
		public List<ShareholderList> shareholderList;//股东列表
		public String developPlan;//发展计划
		public String growthCapability;//持续增长能力
		public String milestone;//发展历程
		public String competitorAnalysis;//竞争对手分析
		public String industryOverview;//行业概况
		public String targetCustomer;//目标客户
		public String competitiveness;//核心竞争力
		public String marketStrategy;//推广策略

		public String getDownstreamCustomer() {
			return downstreamCustomer;
		}

		public String getProjectRisk() {
			return projectRisk;
		}

		public String getProfitModel() {
			return profitModel;
		}

		public String getMarketAnalysis() {
			return marketAnalysis;
		}

		public String getUpstreamSupplier() {
			return upstreamSupplier;
		}

		public String getMainBusiness() {
			return mainBusiness;
		}

		public List<ShareholderList> getShareholderList() {
			return shareholderList;
		}

		public String getDevelopPlan() {
			return developPlan;
		}

		public String getGrowthCapability() {
			return growthCapability;
		}

		public String getMilestone() {
			return milestone;
		}

		public int getFinanceAmount() {
			return financeAmount;
		}

		public String getCompetitorAnalysis() {
			return competitorAnalysis;
		}

		public String getIndustryOverview() {
			return industryOverview;
		}

		public String getTargetCustomer() {
			return targetCustomer;
		}

		public String getCompetitiveness() {
			return competitiveness;
		}

		public String getMarketStrategy() {
			return marketStrategy;
		}

		public int getProjectValue() {
			return projectValue;
		}

		public int getTransferEquity() {
			return transferEquity;
		}

		public int getIntentionAmount() {
			return intentionAmount;
		}


	}
	public static class AngelProject {

		public int financeAmount;  //融资金额
		public String competitorAnalysis;//竞争对手分析
		public String targetCustomer; //目标客户
		public String marketAnalysis; //市场分析

		public String marketStrategy;//推广策略
		public String mainBusiness;//主营业务
		public List<ShareholderList> shareholderList; //股东列表
		public int projectValue; //估值

		public String developPlan;//发展计划
		public int transferEquity;//出让股权
		public int intentionAmount;//意向金额
		public int getFinanceAmount() {
			return financeAmount;
		}

		public String getCompetitorAnalysis() {
			return competitorAnalysis;
		}

		public String getTargetCustomer() {
			return targetCustomer;
		}

		public String getMarketAnalysis() {
			return marketAnalysis;
		}

		public String getMarketStrategy() {
			return marketStrategy;
		}

		public String getMainBusiness() {
			return mainBusiness;
		}

		public List<ShareholderList> getShareholderList() {
			return shareholderList;
		}

		public int getProjectValue() {
			return projectValue;
		}

		public String getDevelopPlan() {
			return developPlan;
		}

		public int getTransferEquity() {
			return transferEquity;
		}

		public int getIntentionAmount() {
			return intentionAmount;
		}


	}
	public static class ShareholderList {
		public String shareholderName; //股东名称
		public double shareholderEquity;//持股比例
		public String getShareholderName() {
			return shareholderName;
		}

		public void setShareholderName(String shareholderName) {
			this.shareholderName = shareholderName;
		}

		public double getShareholderEquity() {
			return shareholderEquity;
		}

		public void setShareholderEquity(double shareholderEquity) {
			this.shareholderEquity = shareholderEquity;
		}


	}
	public static class TeamList {

		public String teammateName;//成员名称
		public String teammateIntroduce;//成员介绍
		public String teammatePosition;//成员职位
		public String getTeammateName() {
			return teammateName;
		}

		public void setTeammateName(String teammateName) {
			this.teammateName = teammateName;
		}

		public String getTeammateIntroduce() {
			return teammateIntroduce;
		}

		public void setTeammateIntroduce(String teammateIntroduce) {
			this.teammateIntroduce = teammateIntroduce;
		}

		public String getTeammatePosition() {
			return teammatePosition;
		}

		public void setTeammatePosition(String teammatePosition) {
			this.teammatePosition = teammatePosition;
		}


	}
	public  static class Company {
		public String projectIntroduce;//项目介绍
		public List<TeamList> teamList;//团队列表
		public String getProjectIntroduce() {
			return projectIntroduce;
		}

		public void setProjectIntroduce(String projectIntroduce) {
			this.projectIntroduce = projectIntroduce;
		}

		public List<TeamList> getTeamList() {
			return teamList;
		}

		public void setTeamList(List<TeamList> teamList) {
			this.teamList = teamList;
		}


	}

	public  static class Project  implements Serializable{

		public long createTime;//上线时间
		public String projectVideo;//项目视频
		public Company company;//公司介绍
		public String projectIcon;//项目图片
		public String projectUuid;//项目Uuid
		public String projectName;//项目名称
		public String projectType;//项目类型
		public String projectState;//项目状态
		public String projectLogo;//项目Logo
		public String provinceName;//省份
		public String cityName;//城市
		public String projectManager;//项目经理,是投资人才有数据返回
		public String projectManagerPhone;//项目经理电话,是投资人才有数据返回
		public String tlsGroupId;//腾讯云群组id
		public String businessPlanUrl;//商业计划书

		public int financeAmount;//融资金额
		public int intentionAmount;//意向金额
		public int transferEquity;//出让股权
		public int interviewNum;//约谈人数
		public int followNum;//关注人数
		public int investNum;//投资人数

		public VcpeProject vcpeProject;//VCPE项目详情
		public AngelProject angelProject;//天使项目详情


		public boolean eisFollow;//是否关注
		public boolean eisInvestor;//是否投资人


		public void setCreateTime(int createTime) {
			this.createTime = createTime;
		}
		public long getCreateTime() {
			return createTime;
		}

		public void setInterviewNum(int interviewNum) {
			this.interviewNum = interviewNum;
		}
		public int getInterviewNum() {
			return interviewNum;
		}

		public void setVcpeProject(VcpeProject vcpeProject) {
			this.vcpeProject = vcpeProject;
		}
		public VcpeProject getVcpeProject() {
			return vcpeProject;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getCityName() {
			return cityName;
		}

		public void setProjectManager(String projectManager) {
			this.projectManager = projectManager;
		}
		public String getProjectManager() {
			return projectManager;
		}

		public void setProjectType(String projectType) {
			this.projectType = projectType;
		}
		public String getProjectType() {
			return projectType;
		}

		public void setProvinceName(String provinceName) {
			this.provinceName = provinceName;
		}
		public String getProvinceName() {
			return provinceName;
		}

		public void setFinanceAmount(int financeAmount) {
			this.financeAmount = financeAmount;
		}
		public int getFinanceAmount() {
			return financeAmount;
		}

		public void setEisFollow(boolean eisFollow) {
			this.eisFollow = eisFollow;
		}
		public boolean getEisFollow() {
			return eisFollow;
		}

		public void setProjectState(String projectState) {
			this.projectState = projectState;
		}
		public String getProjectState() {
			return projectState;
		}

		public void setProjectLogo(String projectLogo) {
			this.projectLogo = projectLogo;
		}
		public String getProjectLogo() {
			return projectLogo;
		}

		public void setProjectManagerPhone(String projectManagerPhone) {
			this.projectManagerPhone = projectManagerPhone;
		}
		public String getProjectManagerPhone() {
			return projectManagerPhone;
		}

		public void setFollowNum(int followNum) {
			this.followNum = followNum;
		}
		public int getFollowNum() {
			return followNum;
		}

		public void setInvestNum(int investNum) {
			this.investNum = investNum;
		}
		public int getInvestNum() {
			return investNum;
		}

		public void setTlsGroupId(String tlsGroupId) {
			this.tlsGroupId = tlsGroupId;
		}
		public String getTlsGroupId() {
			return tlsGroupId;
		}

		public void setBusinessPlanUrl(String businessPlanUrl) {
			this.businessPlanUrl = businessPlanUrl;
		}
		public String getBusinessPlanUrl() {
			return businessPlanUrl;
		}

		public void setEisInvestor(boolean eisInvestor) {
			this.eisInvestor = eisInvestor;
		}
		public boolean getEisInvestor() {
			return eisInvestor;
		}

		public void setAngelProject(AngelProject angelProject) {
			this.angelProject = angelProject;
		}
		public AngelProject getAngelProject() {
			return angelProject;
		}

		public void setProjectVideo(String projectVideo) {
			this.projectVideo = projectVideo;
		}
		public String getProjectVideo() {
			return projectVideo;
		}

		public void setCompany(Company company) {
			this.company = company;
		}
		public Company getCompany() {
			return company;
		}

		public void setProjectIcon(String projectIcon) {
			this.projectIcon = projectIcon;
		}
		public String getProjectIcon() {
			return projectIcon;
		}

		public void setTransferEquity(int transferEquity) {
			this.transferEquity = transferEquity;
		}
		public int getTransferEquity() {
			return transferEquity;
		}

		public void setProjectUuid(String projectUuid) {
			this.projectUuid = projectUuid;
		}
		public String getProjectUuid() {
			return projectUuid;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		public String getProjectName() {
			return projectName;
		}

		public void setIntentionAmount(int intentionAmount) {
			this.intentionAmount = intentionAmount;
		}
		public int getIntentionAmount() {
			return intentionAmount;
		}

	}
}
