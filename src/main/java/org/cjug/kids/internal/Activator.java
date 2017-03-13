package org.cjug.kids.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.camel.CamelContext;
import org.apache.camel.core.osgi.OsgiDefaultCamelContext;
import org.apache.camel.core.osgi.OsgiServiceRegistry;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.impl.SimpleRegistry;
import org.cjug.kids.internal.routes.CoreRouteBuilder;
import org.cjug.kids.internal.routes.HideAndSeekRouteBuilder;
import org.cjug.kids.internal.ui.Main;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;

public class Activator implements BundleActivator {
	private Main mainGui;
	
	private CamelContext camelContext;
	
	public void start(BundleContext context) throws Exception {
		
		
		this.mainGui = new Main();
		this.mainGui.start();
		
		
		if(context != null)
		{
			this.camelContext = new OsgiDefaultCamelContext(context);
			Dictionary<String, Object> properties = new Hashtable<>();
			properties.put(Constants.SERVICE_PID, Main.class.getName());
			properties.put("name", "mainGui");
			context.registerService(Main.class, this.mainGui, properties);
		}
		else
		{
			SimpleRegistry registry = new SimpleRegistry();
			this.camelContext = new DefaultCamelContext(registry);
			
			registry.put("mainGui", this.mainGui);
		}
		
		this.camelContext.addRoutes(new CoreRouteBuilder());
		this.camelContext.addRoutes(new HideAndSeekRouteBuilder());
		
		this.camelContext.start();
		
	}
	
	public void stop(BundleContext context) throws Exception {
		if(this.mainGui != null)
		{
			this.mainGui.stop();
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		Activator activator = new Activator();
		activator.start(null);
	}
}
