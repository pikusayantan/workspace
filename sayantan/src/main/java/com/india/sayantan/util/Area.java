package com.india.sayantan.util;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.india.sayantan.model.Points;

@Component
public class Area {
	
	
	private Points point;
	

	public Points getPoint() {
		return point;
	}

	/*@Required
	@Autowired//(required=true)
	@Qualifier(value="length2")*/
	@Resource(name="length2")
	public void setPoint(Points point) {
		this.point = point;
	}
	
	public void getArea(){
		System.out.println("Area is: "+point.getPointA()*point.getPointB());
	}
}
