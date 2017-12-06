package com.annet.test;

import org.junit.Test;

import com.toy.model.Employee;
import com.toy.utils.PagingUtils;

public class TestPage {

	@Test
	public void test01() {
		PagingUtils<Employee> page = new PagingUtils<Employee>(1, 5, 123, null);
		System.out.println(
				"开始页:"+page.getStartPage() + 
				"\n结束页:" + page.getEndPage() + 
				"\n当前页:" + page.getPaging() + 
				"\n每页条数:" + page.getContentSize()+ 
				"\n总条数:" + page.getContentCount() + 
				"\n显示页数:" + page.getPageNumber() + 
				"\n总页数:" + page.getPageCount());
		System.out.println("--------------------------------------------------");
		PagingUtils<Employee> page2 = new PagingUtils<Employee>(3, 5, 123, null);
		System.out.println(
				"开始页:"+page2.getStartPage() + 
				"\n结束页:" + page2.getEndPage() + 
				"\n当前页:" + page2.getPaging() + 
				"\n每页条数:" + page2.getContentSize()+ 
				"\n总条数:" + page2.getContentCount() + 
				"\n显示页数:" + page2.getPageNumber() + 
				"\n总页数:" + page2.getPageCount());
		System.out.println("--------------------------------------------------");
		PagingUtils<Employee> page3 = new PagingUtils<Employee>(4, 5, 123, null);
		System.out.println(
				"开始页:"+page3.getStartPage() + 
				"\n结束页:" + page3.getEndPage() + 
				"\n当前页:" + page3.getPaging() + 
				"\n每页条数:" + page3.getContentSize()+ 
				"\n总条数:" + page3.getContentCount() + 
				"\n显示页数:" + page3.getPageNumber() + 
				"\n总页数:" + page3.getPageCount());
		System.out.println("--------------------------------------------------");
		PagingUtils<Employee> page4 = new PagingUtils<Employee>(9, 5, 123, null);
		System.out.println(
				"开始页:"+page4.getStartPage() + 
				"\n结束页:" + page4.getEndPage() + 
				"\n当前页:" + page4.getPaging() + 
				"\n每页条数:" + page4.getContentSize()+ 
				"\n总条数:" + page4.getContentCount() + 
				"\n显示页数:" + page4.getPageNumber() + 
				"\n总页数:" + page4.getPageCount());
	}

}
