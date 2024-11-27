package pe.edu.nh.composer;

import org.zkoss.chart.Charts;
import org.zkoss.chart.Legend;
import org.zkoss.chart.Responsive;
import org.zkoss.chart.Series;
import org.zkoss.chart.plotOptions.SeriesPlotOptions;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import pe.edu.nh.model.LineBasicData;

public class LineBasicComposer extends SelectorComposer<Window> {

    @Wire
    Charts chart;

    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        // Enable series-label features for this demo
        Executions.getCurrent().setAttribute("charts.modules.seriesLabel", "true");

        chart.getTitle().setAlign("left");
        chart.setSubtitle("By Job Category. Source: <a href=\"https://irecusa.org/programs/solar-jobs-census/\" target=\"_blank\">IREC</a>.");
        chart.getSubtitle().setAlign("left");

        int[] index = new int[] {0};
        LineBasicData.getData().forEach((k, v) -> {
            Series series = chart.getSeries(index[0]++);
            series.setName(k);
            series.setData(v);
        });

        chart.getXAxis().getAccessibility().setRangeDescription("Range: 2010 to 2022");

        chart.getYAxis().setTitle("Number of Employees");

        Legend legend = chart.getLegend();
        legend.setLayout("vertical");
        legend.setAlign("right");
        legend.setVerticalAlign("middle");

        SeriesPlotOptions series = chart.getPlotOptions().getSeries();
        series.getLabel().setConnectorAllowed(false);
        series.setPointStart(2010);

        Responsive responsive = chart.getResponsive();
        Responsive.Rule rule = new Responsive.Rule();
        rule.getCondition().setMaxWidth(500);
        Legend legend1 = rule.getChartOptions().getLegend();
        legend1.setLayout("horizontal");
        legend1.setAlign("center");
        legend1.setVerticalAlign("bottom");
        responsive.addRule(rule);
    }
}