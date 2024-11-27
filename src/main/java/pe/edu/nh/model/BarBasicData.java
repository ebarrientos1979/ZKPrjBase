package pe.edu.nh.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.zkoss.chart.model.CategoryModel;
import org.zkoss.chart.model.DefaultCategoryModel;

import pe.edu.nh.dto.ClienteDTO;
import pe.edu.nh.rest.RestCliente;



public class BarBasicData {
	 
    private static CategoryModel model;
    static {
    	model = new DefaultCategoryModel();
    	RestCliente rc = new RestCliente();
    	try {
			List<ClienteDTO> lc = rc.ListarClientes();
			for(ClienteDTO c : lc) {
				System.out.println(c.getNombre());
				model.setValue("Cliente", c.getNombre(), c.getClienteId());
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static CategoryModel getCategoryModel() {
        return model;
    }
}