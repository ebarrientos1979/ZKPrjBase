package pe.edu.nh;

import org.modelmapper.ModelMapper;

public class ModelMapperConfig {
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public static void copyProperties( Object source, Object destination) {
		modelMapper.map(source, destination);
	}
	
	public static <D> D map(Object source, Class<D> destinationType) {
		
		return modelMapper.map(source, destinationType);
	}
}
