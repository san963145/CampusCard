package com.sys.card.util;

import java.util.List;

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

}
