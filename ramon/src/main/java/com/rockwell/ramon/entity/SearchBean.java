package com.rockwell.ramon.entity;

/**
 * TODO Please enter the description of this type. This is mandatory!
 * <p>
 * @author Brian, 2018年10月27日 上午1:00:55
 */
public class SearchBean
{

	private Integer pageNum;// 页数

	private Integer numPerPage;// 每页条数

	private String key; // 查询字段

	private String value;// 查询值

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Integer getPageNum()
	{
		if (pageNum == null)
		{
			pageNum = 1;
		}
		return pageNum;
	}

	public void setPageNum(Integer pageNum)
	{
		this.pageNum = pageNum;
	}

	public Integer getNumPerPage()
	{
		if (numPerPage == null)
		{
			numPerPage = 20;
		}
		return numPerPage;
	}

	public void setNumPerPage(Integer numPerPage)
	{
		this.numPerPage = numPerPage;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

}
