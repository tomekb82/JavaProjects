package com.company.reports.factory;

import com.company.reports.*;
import com.company.reports.decorator.*;

public class ReportFactory extends AbstractReportFactory{

	public ReportInterface createReport(){
		return new ReportDecorator(new Report());
	}
}
