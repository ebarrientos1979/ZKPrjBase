package pe.edu.nh.composer;

import java.util.Arrays;
import java.util.List;

import org.zkoss.chart.Charts;
import org.zkoss.chart.Point;
import org.zkoss.chart.Series;
import org.zkoss.chart.plotOptions.DataLabels;
import org.zkoss.chart.plotOptions.DataLabelsFilter;
import org.zkoss.chart.plotOptions.SeriesPlotOptions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import pe.edu.nh.dto.ClienteDTO;
import pe.edu.nh.model.LineBasicData;
import pe.edu.nh.rest.RestCliente;

import org.zkoss.chart.Legend;
import org.zkoss.chart.Responsive;
import org.zkoss.zk.ui.Executions;


public class PieBasicComposer extends SelectorComposer<Window> {

    @Wire
    Charts chart;
   
    public void doAfterCompose(Window comp) throws Exception {
        super.doAfterCompose(comp);
        
        RestCliente rc = new RestCliente();
        
        List<ClienteDTO> lc = rc.ListarClientes();
               

        chart.getTooltip().setValueSuffix("%");

        chart.setSubtitle("Distribucion de Compras");

        SeriesPlotOptions plotOptions = chart.getPlotOptions().getSeries();
        plotOptions.setAllowPointSelect(true);
        plotOptions.setCursor("pointer");

        DataLabels[] dataLabels = new DataLabels[] {new DataLabels(), new DataLabels()};
        dataLabels[0].setEnabled(true);
        dataLabels[0].setDistance(20);
        dataLabels[1].setEnabled(true);
        dataLabels[1].setDistance(-40);
        dataLabels[1].setFormat("{point.percentage:.1f}%");
        dataLabels[1].setStyle("fontSize: '1.2em'; textOutline: 'none'; opacity: 0.7");
        DataLabelsFilter filter = dataLabels[1].getFilter();
        filter.setOperator(">");
        filter.setProperty("percentage");
        filter.setValue(10);
        plotOptions.setDataLabels(Arrays.asList(dataLabels));

        Series series = chart.getSeries();
        series.setName("Porcentajes");
        series.setColorByPoint(true);        
        
        for(ClienteDTO c : lc) {
        	series.addPoint(c.getNombre(), c.getClienteId());
        }
                
       
    }
}