package com.hzg.txq;

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
		private  int financeAmount;
		//浏览数
		private  int readNum;
		//项目状态
		private  String projectState;
		//项目图片
		private  String projectIcon;
		//出让股权
		private  int transfereQuity;
		//项目uuid
		private  String projectUuid;
		//项目名称
		private  String projectName;
		//意向金额
		private  int intentionAmount;
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

		public int getFinanceAmount() {
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

		public int getTransfereQuity() {
			return transfereQuity;
		}

		public String getProjectUuid() {
			return projectUuid;
		}

		public String getProjectName() {
			return projectName;
		}

		public int getIntentionAmount() {
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
	    private  int totalPages;
	     private int getTotalelements() {
	         return totalElements;
	     }
	     private int getTotalpages() {
	         return totalPages;
	     }
	}
}

