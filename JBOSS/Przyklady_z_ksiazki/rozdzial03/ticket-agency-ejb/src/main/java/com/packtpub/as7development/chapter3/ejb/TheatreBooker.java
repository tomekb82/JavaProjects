package com.packtpub.as7development.chapter3.ejb;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;

import com.packtpub.as7development.chapter3.exception.NotEnoughMoneyException;
import com.packtpub.as7development.chapter3.exception.SeatBookedException;

public interface TheatreBooker {
   public String bookSeat(int seatId) throws SeatBookedException,NotEnoughMoneyException;
   @Asynchronous
	public Future<String> bookSeatAsync(int seatId);
}