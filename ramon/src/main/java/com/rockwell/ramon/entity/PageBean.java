package com.rockwell.ramon.entity;

import java.util.List;

/**
 * TODO Please enter the description of this type. This is mandatory!
 * <p>
 * @author Brian, 2018年10月27日 上午1:00:45
 */
public class PageBean
{

	private List recordList;

	private int pageCount;

	private Object ortherSearchBean;

	public List getRecordList()
	{
		return recordList;
	}

	public void setRecordList(List recordList)
	{
		this.recordList = recordList;
	}

	public int getPageCount()
	{
		return pageCount;
	}

	public void setPageCount(int pageCount)
	{
		this.pageCount = pageCount;
	}

	public Object getOrtherSearchBean()
	{
		return ortherSearchBean;
	}

	public void setOrtherSearchBean(Object ortherSearchBean)
	{
		this.ortherSearchBean = ortherSearchBean;
	}

}
