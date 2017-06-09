package com.hzg.txq.bean;

import java.io.Serializable;
import java.util.List;

public class ProjectListBean {
    
    private  Project project;
    private  boolean success;

	public boolean isSuccess() {
		return success;
	}

	private void setProject(Project project) {
         this.project = project;
     }
    public Project getProject() {
         return project;
     }

    private void setSuccess(boolean success) {
         this.success = success;
     }
     private boolean getSuccess() {
         return success;
     }
	public class Project{
		public ProjectPage getPageable() {
			return pageable;
		}

		public List<ProjectInfo> getProjectList() {
			return projectList;
		}

		private ProjectPage  pageable;
		private List<ProjectInfo> projectList;
	}
	public class   ProjectInfo implements Serializable{
		//关注人数
		private int followNum;
		//项目类型
		private  String projectType;
		//项目宗旨
		private  String projectIntroduceBrief;
		//所属行业类别列表
		private  List<String> industryNameList;
		//融资总额
		private  double financeAmount;
		//浏览数
		private  int readNum;
		//项目状态
		private  String projectState;
		//项目图片
		private  String projectIcon;
		//出让股权
		private double transferEquity;
		//项目uuid
		private  String projectUuid;
		//项目名称
		private  String projectName;
		//意向金额
		private  double intentionAmount;
		//项目团队
		private  String projectTeamIntroduce;
		public int getFollowNum() {
			return followNum;
		}

		public String getProjectType() {
			return projectType;
		}

		public String getProjectIntroduceBrief() {
			return projectIntroduceBrief;
		}

		public List<String> getIndustryNameList() {
			return industryNameList;
		}

		public double getFinanceAmount() {
			return financeAmount;
		}

		public int getReadNum() {
			return readNum;
		}

		public String getProjectState() {
			return projectState;
		}

		public String getProjectIcon() {
			return projectIcon;
		}

		public double getTransferEquity() {
			return transferEquity;
		}

		public String getProjectUuid() {
			return projectUuid;
		}

		public String getProjectName() {
			return projectName;
		}

		public double getIntentionAmount() {
			return intentionAmount;
		}

		public String getProjectTeamIntroduce() {
			return projectTeamIntroduce;
		}



	
	}
	public class ProjectPage
	{
	   //项目总数
	    private  int totalElements;
	    //项目总页数

		public int getTotalElements() {
			return totalElements;
		}

		public int getTotalPages() {
			return totalPages;
		}

		private  int totalPages;
	     private int getTotalelements() {
	         return totalElements;
	     }
	     private int getTotalpages() {
	         return totalPages;
	     }
	}
}

