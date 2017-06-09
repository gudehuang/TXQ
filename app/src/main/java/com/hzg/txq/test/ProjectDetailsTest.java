package com.hzg.txq.test;

import com.google.gson.Gson;
import com.hzg.txq.bean.ProjectDetailBean;

/**
 * Created by hzg on 2017/6/7.
 */

public class ProjectDetailsTest {

    String json="{\n" +
            "  \"project\": {\n" +
            "    \"createTime\": 1473752750000,\n" +
            "    \"interviewNum\": 0,\n" +
            "    \"vcpeProject\": {\n" +
            "      \"downstreamCustomer\": \"\",\n" +
            "      \"projectRisk\": \"\",\n" +
            "      \"profitModel\": \"\",\n" +
            "      \"marketAnalysis\": \"\",\n" +
            "      \"upstreamSupplier\": \"\",\n" +
            "      \"mainBusiness\": \"\",\n" +
            "      \"shareholderList\": [\n" +
            "        \n" +
            "      ],\n" +
            "      \"developPlan\": \"\",\n" +
            "      \"growthCapability\": \"\",\n" +
            "      \"milestone\": \"\",\n" +
            "      \"financeAmount\": 0,\n" +
            "      \"competitorAnalysis\": \"\",\n" +
            "      \"industryOverview\": \"\",\n" +
            "      \"targetCustomer\": \"\",\n" +
            "      \"competitiveness\": \"\",\n" +
            "      \"marketStrategy\": \"\",\n" +
            "      \"projectValue\": 0,\n" +
            "      \"transferEquity\": 0,\n" +
            "      \"intentionAmount\": 0\n" +
            "    },\n" +
            "    \"cityName\": \"广州市\",\n" +
            "    \"projectManager\": \"\",\n" +
            "    \"projectType\": \"ANGEL\",\n" +
            "    \"provinceName\": \"广东省\",\n" +
            "    \"financeAmount\": 1900.0000,\n" +
            "    \"eisFollow\": false,\n" +
            "    \"projectState\": \"FINANCING\",\n" +
            "    \"projectLogo\": \"/static/upload/b1211fb7-1e69-47b1-b18f-4b70b0af2bb5.png\",\n" +
            "    \"projectManagerPhone\": \"\",\n" +
            "    \"followNum\": 1,\n" +
            "    \"investNum\": 0,\n" +
            "    \"tlsGroupId\": \"\",\n" +
            "    \"businessPlanUrl\": \"/static/upload/8463c0a4-dc96-400a-b400-6b9547311f30.pptx\",\n" +
            "    \"eisInvestor\": false,\n" +
            "    \"angelProject\": {\n" +
            "      \"financeAmount\": 1900.0000,\n" +
            "      \"competitorAnalysis\": \"自动建模0.6km2 /天  V.S. 人工建模0.013km2/天内业效率提高约50倍\\n\",\n" +
            "      \"targetCustomer\": \"给政府国土部门，各地测绘院、勘察院提供服务\\n产品销售给政府部门、事业单位，行业企业、军事部门\\n\",\n" +
            "      \"marketAnalysis\": \"近三年内，主要采取产品+服务模式，利用微型无人机倾斜摄影技术和无人机敏捷建模系统，巩固公司在低空倾斜摄影领域的优势，逐渐成为无人机系统在地理测绘、警用和军用领域的领导者。\\n扩大品牌影响力，发展系列产品，深入应用各个行业；\\n发挥红鹏在军内的优势，计划于2017年获得全部军工资质；\\n\",\n" +
            "      \"marketStrategy\": \"根据前瞻产业研究院发布的《2015-2020年中国地理信息产业发展前景与投资战略规划分析报告》数据显示，2009年以来我国地理信息产业产值每年保持将近25%以上增速。截至2013年底，我国地理信息产业年产值由2009年的931.9亿元提升至近2600亿元，企业数达2万多家，从业人员超过40万人。\\n国家发改委和国家测绘地信局2014年7月份印发的《国家地理信息产业发展规划（2014-2020）》，地理信息产业属于高新技术产业，现代服务业和战略性新兴产业，市场潜力巨大，发展前景广阔。规划指出，“十二五”以来，地理信息产业年增长率30%左右，未来将保持年均20%以上的增长速度，成为国民经济发展新的增长点。\\n随着地理信息的不断发展、新应用与新服务的不断产生、地理信息产业格局的逐渐形成，前瞻研究院预测，\\\"十三五\\\"期间，地理信息技数逐渐成熟，随着国民经济和社会需求拉动，产业必将做强、做大，年均增长率将保持20%以上，2020年产值将超过1万亿。\\n2013年，测绘地理信息行业产业转型升级全面加速，无人机、移动测量系统等高新技术装备得到广泛应用，新型多源遥感影像获取与数据处理、灾害应对与应急测绘、卫星导航与位置服务能力大幅提升，整个行业科技创新能力不断增强。\\n截至2013年末，测绘资质单位共有近500架低空无人驾驶摄影飞机、9000余套全数字摄影测量系统投入业务运行。\\n2013年，测绘地理信息行业产业转型升级全面加速，无人机、移动测量系统等高新技术装备得到广泛应用，新型多源遥感影像获取与数据处理、灾害应对与应急测绘、卫星导航与位置服务能力大幅提升，整个行业科技创新能力不断增强。\\n截至2013年末，测绘资质单位共有近500架低空无人驾驶摄影飞机、9000余套全数字摄影测量系统投入业务运行。\\n\\n\",\n" +
            "      \"mainBusiness\": \"红鹏于2002年在广州创建，拥有国家乙级航空摄影测绘资质和军工二级保密单位资质，专业从事测绘航空摄影技术的研发、生产与服务，是中国无人机倾斜摄影产品与服务的专业提供商。\\n\",\n" +
            "      \"shareholderList\": [\n" +
            "        {\n" +
            "          \"shareholderName\": \"北京红鹏天绘科技有限责任公司\",\n" +
            "          \"shareholderEquity\": 45.49\n" +
            "        },\n" +
            "        {\n" +
            "          \"shareholderName\": \"天津中科融知股权投资基金合伙企业\",\n" +
            "          \"shareholderEquity\": 24.81\n" +
            "        },\n" +
            "        {\n" +
            "          \"shareholderName\": \"天津彩航无人机科技合伙企业\",\n" +
            "          \"shareholderEquity\": 12.42\n" +
            "        },\n" +
            "        {\n" +
            "          \"shareholderName\": \"其他股东\",\n" +
            "          \"shareholderEquity\": 17.28\n" +
            "        }\n" +
            "      ],\n" +
            "      \"projectValue\": 190.0000,\n" +
            "      \"developPlan\": \"建立全国最大的真三维时空信息数据库，利用移动互联和虚拟现实等成熟技术，为政府和广大公众提供全信息地图和增强现实场景，成为国内外领先的真三维时空数据服务商。\\n\",\n" +
            "      \"transferEquity\": 10.00,\n" +
            "      \"intentionAmount\": 100\n" +
            "    },\n" +
            "    \"projectVideo\": \"\",\n" +
            "    \"company\": {\n" +
            "      \"projectIntroduce\": \"红鹏无人机，业内倾斜摄影龙头企业，和上市公司超图软件有多方面的业务合作，技术在国内领先，有军品资格。独家影像建模技术，效率远高同行多次参与国内重大救援救灾任务，企业估值合理，营收增长迅速，成长性确定。\",\n" +
            "      \"teamList\": [\n" +
            "        {\n" +
            "          \"teammateName\": \"徐鹏\",\n" +
            "          \"teammateIntroduce\": \"目前任红鹏直升机遥感科技有限公司董事长，是我国直升机航测技术的开创者和真三维技术的主要推动者，是中国低空多镜头航测建模专利的发明人，目前致力于全民应急素质提高工作，研发多套应急处置桌面演练系统。\",\n" +
            "          \"teammatePosition\": \"董事长\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    \"projectIcon\": \"/static/upload/5c57cc5a-ab1e-4e89-babc-4537578ee7e4.jpg\",\n" +
            "    \"transferEquity\": 10.00,\n" +
            "    \"projectUuid\": \"7edc6789-adac-429c-8bbb-022a77bc56ba\",\n" +
            "    \"projectName\": \"红鹏无人机\",\n" +
            "    \"intentionAmount\": 100\n" +
            "  },\n" +
            "  \"success\": true\n" +
            "}";

     public ProjectDetailBean.Project jsonToProject()
     {


         ProjectDetailBean bean=new Gson().fromJson(json,ProjectDetailBean.class);
        return   bean.getProject();
     }

}
