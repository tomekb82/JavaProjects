package com.packtpub.as7development.chapter10.ejb;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.packtpub.as7development.chapter10.model.Seat;


@Stateless
@Remote(TheatreInfo.class) 
public class  TheatreInfoBean implements TheatreInfo  {
	@EJB TheatreBox box;

	@Override
	public String printSeatList() {
		ArrayList<Seat> seats= box.getSeatList();
		StringBuffer sb = new StringBuffer();
		for (Seat seat: seats) {
			sb.append(seat.toString());
			sb.append("\n");
		}
		return sb.toString();


	}


}
