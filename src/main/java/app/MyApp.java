package app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("rest")
public class MyApp extends ResourceConfig {
	
	
	public MyApp() {
		packages("com.cursos.profi.ws");
	}
}
