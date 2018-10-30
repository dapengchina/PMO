package com.pmo.dashboard.dao;

import java.math.BigDecimal;

import com.pmo.dashboard.entity.Currencys;
import com.pmo.dashboard.entity.WorkHour;

public interface CurrencysMapper {
	Currencys queryCurrency(Currencys currencys);
}