package org.cjug.kids.internal.routes;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import org.cjug.kids.internal.model.GridPosition;
import org.cjug.kids.internal.model.Player;
import org.cjug.kids.internal.ui.Main;
import org.cjug.kids.internal.ui.PlayerPanel;

public class CoreRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		Main mainGrid = getContext().getRegistry().lookupByNameAndType("mainGui", Main.class);
		
		restConfiguration("netty4-http").host("localhost").port(8890).bindingMode(RestBindingMode.json);
		
		rest("/test").post().to("direct:triggerGrid");
		
		rest("/register").post().to("direct:registerPlayer");
		
		rest("/unregister").post().to("direct:unregisterPlayer");
		
		from("direct:triggerGrid")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Map body = exchange.getIn().getBody(Map.class);
				mainGrid.getGameGridPanel().triggerGrid((String)body.get("col"), (Integer)body.get("row"));
			}
		})
		.setBody(simple("null"));
		
		from("direct:registerPlayer")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Map body = exchange.getIn().getBody(Map.class);
				Map playerMap = (Map)body.get("player");
				Map playerPosMap = (Map)playerMap.get("pos");
				
				GridPosition playerPos = new GridPosition((String)playerPosMap.get("row"), (Integer)playerPosMap.get("col"));
				Player player = new Player((String)playerMap.get("name"), playerPos);
				
				PlayerPanel playerPanel = mainGrid.getPlayerPanelMap().get((String) body.get("team"));
				playerPanel.registerPlayer(player);
				
			}
		})
		.setBody(simple("null"));
		
		from("direct:unregisterPlayer")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Map body = exchange.getIn().getBody(Map.class);
				Map playerMap = (Map)body.get("player");
				Map playerPosMap = (Map)playerMap.get("pos");
				
				GridPosition playerPos = new GridPosition((String)playerPosMap.get("row"), (Integer)playerPosMap.get("col"));
				Player player = new Player((String)playerMap.get("name"), playerPos);
				
				PlayerPanel playerPanel = mainGrid.getPlayerPanelMap().get((String) body.get("team"));
				playerPanel.unregisterPlayer(player);
				
			}
		})
		.setBody(simple("null"));

	}

}
