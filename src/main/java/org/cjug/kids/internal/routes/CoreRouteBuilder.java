package org.cjug.kids.internal.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import org.cjug.kids.internal.ui.Main;

public class CoreRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		Main mainGrid = getContext().getRegistry().lookupByNameAndType("mainGui", Main.class);
		
		restConfiguration("netty4-http").host("0.0.0.0").port(8890).bindingMode(RestBindingMode.json)
		.enableCORS(true);
		
		interceptFrom("rest:/games/{gameName}/*").to("direct:checkGameName");
		
		from("direct:checkGameName")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String gameName = exchange.getIn().getHeader("gameName", String.class);
				
				if(!gameName.equals(mainGrid.getGameName()))
				{
					throw new Exception("This game is not Active");
				}
				
			}
		});
		
		

	}

}
