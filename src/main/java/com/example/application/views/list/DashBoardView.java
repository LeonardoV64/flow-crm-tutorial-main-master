package com.example.application.views.list;

import javax.annotation.security.PermitAll;

import com.example.application.data.service.CrmService;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItem;
import com.vaadin.flow.component.charts.model.PlotOptionsSeries;
import com.vaadin.flow.component.charts.model.PointOptions;
import com.vaadin.flow.component.charts.model.style.ChartStyle;
import com.vaadin.flow.component.charts.model.style.GradientColor;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "dashboard", layout = MainLayout.class)
@PageTitle("DashBoard")
@PermitAll
public class DashBoardView extends VerticalLayout{
	private CrmService service;
	
	
	public DashBoardView(CrmService service) {
		this.service = service;
		addClassName("dashboard-view");
		GradientColor color = GradientColor.createLinear(0,0,0,0);
		color.addColorStop(0, new SolidColor("#000000"));
		setDefaultHorizontalComponentAlignment(Alignment.CENTER);
		add(new H2("Contas Bancárias"), getContaStats(), getContasSaldoChart());
	}
	
	private Component getContaStats() {
		Span stats = new Span("Saldo Total " + service.somaSaldo());
		stats.addClassNames("text-xl", "mt-m");
		return stats;
	}



	private Component getContasSaldoChart() {
		Chart chart = new Chart(ChartType.PIE);
		
		//ChartStyle chtStyle = new ChartStyle();
		
		//chtStyle.setBackgroundColor(new SolidColor("#000000"));
		
		
		DataSeries dataSeries = new DataSeries();
		service.buscaTodasContas(null).forEach(conta->{
			dataSeries.add(new DataSeriesItem(conta.getConta(), conta.getSaldo()));
			});
		chart.getConfiguration().setSeries(dataSeries);
		
		return chart;
	}
	


	

}
