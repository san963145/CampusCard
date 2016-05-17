package com.sys.card.util;

import java.util.HashMap;
import java.util.List;

import com.sys.card.bean.CostTime;
import com.sys.card.bean.StudentCostRecord;


public class ChartGenerator {
	
	public static String generatorMap(String id,String title,String description,List list)
	{
		Integer min=999;
		Integer max=0;		
		for(int i=0;i<list.size();i++)
		{
			Object[] obj=(Object[]) list.get(i);
			if(obj[1]==null)
			{
				obj[1]="0";
				list.set(i, obj);
			}			
		}
		/*for(int i=0;i<list.size();i++)
		{
			Object[] obj=(Object[]) list.get(i);
			System.out.println(obj[0]+" "+obj[1]);			
		}*/
		for(int i=0;i<list.size();i++)
		{
			Object[] obj=(Object[]) list.get(i);
			String aveCost=obj[1].toString();
			double d=Double.parseDouble(aveCost);
			int t=(int) d;
			if(t>max)
			{
				max=t;
			}
			if(t<min&&t>0)
			{
				min=t;
			}
		}
		max++;
		if(id.equals("map1"))
		{
			min=0;
		}
		StringBuilder st=new StringBuilder();
		String content="var dom = document.getElementById(\""+id+"\");var myChart = echarts.init(dom);option = null;"+
				       "option = {"+
				       "title: {text: '"+title+"',left: 'center'},tooltip: {trigger: 'item'},visualMap: {min: "+min.toString()+",max: "+max.toString()+",left: 'left',top: 'bottom',text: ['高','低'],calculable: true},"+
				       "toolbox: {show: true,orient: 'vertical',left: 'right',top: 'center',feature: {dataView: {readOnly: false},restore: {},saveAsImage: {}}},"+
				       "series: [{name: '"+description+"',type: 'map',mapType: 'china',roam: false,label: {normal: {show: false},emphasis: {show: true}},"+
				       "data:[";
				       
		st.append(content);
		for(int i=0;i<list.size();i++)
		{
			Object[] obj=(Object[]) list.get(i);
			String provinceName=obj[0].toString();
			String aveCost=obj[1].toString();
			st.append("{name: '"+provinceName+
					  "',value: "+aveCost+
					  " },");
		}
		//st.append("{name: '香港',value: 0 },{name: '澳门',value: 0},{name: '台湾',value: 0 },");
		st.append("]}]};;");
		st.append("if (option && typeof option === \"object\") {myChart.setOption(option, true);}");
		return st.toString();
	}
	public static String generatorBreakfastLine(String id,List<StudentCostRecord> list)
	{
		
		StringBuilder st=new StringBuilder();
		String content="var dom = document.getElementById(\""+id+"\");var myChart = echarts.init(dom);option = null;"+
				       "var data1 = [";				       
		st.append(content);
		for(int i=0;i<list.size();i++)
		{
			StudentCostRecord s=list.get(i);
			st.append("{name:'"+s.getLogicDate()+"',value:["+(i+1)+","+s.getCost()+"]},");                
		}
		st.append("];");
		st.append("var data2 = [");
		for(int i=0;i<list.size();i++)
		{
			StudentCostRecord s=list.get(i);
			st.append("{name:'"+s.getLogicDate()+"',value:["+(i+1)+","+s.getAverage()+"]},");                
		}
		st.append("];");
		st.append("option = {title: {text: '早餐消费历史'},tooltip: {trigger: 'axis',formatter: function (params) {params = params[0];return  params.name+ ' : ' + params.value[1];},axisPointer: {animation: false}},xAxis: {type: 'value',min:'0',max:'"+(list.size()+1)+"',splitLine: {show: false}},");
		st.append("yAxis: {type: 'value',min: '0',max: '15',splitLine: {show: false}},series: [{name: '个人早餐消费',type: 'line',showSymbol: false,hoverAnimation: false,data: data1},{name: '学院年级平均',type: 'line',showSymbol: false,hoverAnimation: false,data: data2}]};");
		st.append("myChart.setOption(option, true);");
		return st.toString();
	}
	public static String generatorLunchLine(String id,List<StudentCostRecord> list)
	{
		
		StringBuilder st=new StringBuilder();
		String content="var domLunch = document.getElementById(\""+id+"\");var myChartLunch = echarts.init(domLunch);optionLunch = null;"+
				       "var dataLunch1 = [";				       
		st.append(content);
		for(int i=0;i<list.size();i++)
		{
			StudentCostRecord s=list.get(i);
			st.append("{name:'"+s.getLogicDate()+"',value:["+(i+1)+","+s.getCost()+"]},");                
		}
		st.append("];");
		st.append("var dataLunch2 = [");
		for(int i=0;i<list.size();i++)
		{
			StudentCostRecord s=list.get(i);
			st.append("{name:'"+s.getLogicDate()+"',value:["+(i+1)+","+s.getAverage()+"]},");                
		}
		st.append("];");
		st.append("optionLunch = {title: {text: '午餐消费历史'},tooltip: {trigger: 'axis',formatter: function (params) {params = params[0];return   params.name+ ' : ' + params.value[1];},axisPointer: {animation: false}},xAxis: {type: 'value',min:'0',max:'"+(list.size()+1)+"',splitLine: {show: false}},");
		st.append("yAxis: {type: 'value',min: '0',max: '30',splitLine: {show: false}},series: [{name: '个人午餐消费',type: 'line',showSymbol: false,hoverAnimation: false,data: dataLunch1},{name: '学院年级平均',type: 'line',showSymbol: false,hoverAnimation: false,data: dataLunch2}]};");
		st.append("myChartLunch.setOption(optionLunch, true);");
		return st.toString();
	}
	public static String generatorSupperLine(String id,List<StudentCostRecord> list)
	{
		
		StringBuilder st=new StringBuilder();
		String content="var domSupper = document.getElementById(\""+id+"\");var myChartSupper = echarts.init(domSupper);optionSupper = null;"+
				       "var dataSupper1 = [";				       
		st.append(content);
		for(int i=0;i<list.size();i++)
		{
			StudentCostRecord s=list.get(i);
			st.append("{name:'"+s.getLogicDate()+"',value:["+(i+1)+","+s.getCost()+"]},");                
		}
		st.append("];");
		st.append("var dataSupper2 = [");
		for(int i=0;i<list.size();i++)
		{
			StudentCostRecord s=list.get(i);
			st.append("{name:'"+s.getLogicDate()+"',value:["+(i+1)+","+s.getAverage()+"]},");                
		}
		st.append("];");
		st.append("optionSupper = {title: {text: '晚餐消费历史'},tooltip: {trigger: 'axis',formatter: function (params) {params = params[0];return   params.name+ ' : ' + params.value[1];},axisPointer: {animation: false}},xAxis: {type: 'value',min:'0',max:'"+(list.size()+1)+"',splitLine: {show: false}},");
		st.append("yAxis: {type: 'value',min: '0',max: '30',splitLine: {show: false}},series: [{name: '个人晚餐消费',type: 'line',showSymbol: false,hoverAnimation: false,data: dataSupper1},{name: '学院年级平均',type: 'line',showSymbol: false,hoverAnimation: false,data: dataSupper2}]};");
		st.append("myChartSupper.setOption(optionSupper, true);");
		return st.toString();
	}
	public static String generatorMonths(List<String> months)
	{
		String s="";
		for(int i=0;i<months.size();i++)
		{
			String t=months.get(i).substring(2);
			s+=("'"+t+"',");
		}
		return s;
	}
	public static String generatorHeatMap(List<CostTime> list,List<String> months)
	{
		HashMap<String,HashMap<String,Integer>>map=new HashMap<String,HashMap<String,Integer>>();
		for(int i=0;i<list.size();i++)
		{
			CostTime c=list.get(i);
			String month=c.getMonths();
			if(map.containsKey(month))
			{
				HashMap<String,Integer> temp=map.get(month);
				temp.put(c.getHours(), c.getCount());
				map.put(month, temp);
			}
			else
			{
				HashMap<String,Integer> temp=new HashMap<String,Integer>();
				temp.put(c.getHours(), c.getCount());
				map.put(month, temp);
			}
		}
		int n=months.size();
		int data[][]=new int[n][24];
		for(int i=0;i<n;i++)
		{
			String month=months.get(i);
			if(map.containsKey(month))
			{
				HashMap<String,Integer> temp=map.get(month);
				for(int j=0;j<24;j++)
				{
					String hour="";
					if(j<10)
					{
						hour="0"+j;
					}
					else
					{
						hour=new Integer(j).toString();
					}
					if(temp.containsKey(hour))
					{
						data[i][j]=temp.get(hour);
					}
					else
					{
						data[i][j]=0;
					}
				}
			}
			else
			{
				for(int j=0;j<24;j++)
				{
					data[i][j]=0;
				}
			}			
		}
		
		StringBuilder st=new StringBuilder();
		for(int i=0;i<n;i++)
			for(int j=0;j<24;j++)
			{
				st.append("[");
				st.append(i+","+j+","+data[i][j]);
				st.append("],");
			}				
		return st.toString();
	}
	
}
