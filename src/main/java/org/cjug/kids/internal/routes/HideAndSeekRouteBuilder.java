package org.cjug.kids.internal.routes;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.swing.JPanel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.cjug.kids.internal.model.GridPosition;
import org.cjug.kids.internal.model.Player;
import org.cjug.kids.internal.ui.Main;
import org.cjug.kids.internal.ui.PlayerPanel;

public class HideAndSeekRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		Main mainGrid = getContext().getRegistry().lookupByNameAndType("mainGui", Main.class);
		
		Map<String, Player> playerGridMap = new HashMap<>();
		
		onException(Exception.class)
		.handled(true)
		.onExceptionOccurred(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Exception e = exchange.getException();
				
				Map<String, String> result = new HashMap<>();
					
				result.put("error", e.getMessage());
					
				exchange.getIn().setBody(result);
			}
		});
		
		rest("/games/hide-and-seak")
		.post("check").type(GridPosition.class).to("direct:checkGrid")
		.post("register").type(Player.class).outType(Player.class).to("direct:registerPlayer")
		.post("unregister/{team}/{playerId}").to("direct:unregisterPlayer");
		
		from("direct:checkGrid")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String playerId = exchange.getIn().getHeader("playerId", String.class);
				
				if(!mainGrid.isPlayerActive(playerId))
				{
					throw new Exception("You are not active.");
				}
				
				GridPosition pos = exchange.getIn().getBody(GridPosition.class);
				
				Player foundPlayer = playerGridMap.get(pos.toString());
				if(foundPlayer != null)
				{
					mainGrid.getGameGridPanel().triggerGrid(pos.getRow(), pos.getCol());
					PlayerPanel playerPanel = mainGrid.getPlayerPanelMap().get(foundPlayer.getTeam());
					playerPanel.eliminatePlayer(foundPlayer);
				}
				else
				{
					mainGrid.getGameGridPanel().setGridColor(pos.getRow(), pos.getCol(), Color.BLUE);
				}
			}
		})
		.setBody(simple("null"));
		
		from("direct:registerPlayer")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				Player player = exchange.getIn().getBody(Player.class);
				if(playerGridMap.containsKey(player.getPos().toString()))
				{
					throw new Exception("A Player Already has that spot.");
				}
				String playerUUID = UUID.randomUUID().toString();
				player.setId(playerUUID);
				
				PlayerPanel playerPanel = mainGrid.getPlayerPanelMap().get(player.getTeam());
				playerPanel.registerPlayer(player);
				
				playerGridMap.put(player.getPos().toString(), player);
				
			}
		});
		
		from("direct:unregisterPlayer")
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String playerId = exchange.getIn().getHeader("playerId", String.class);
				String team = exchange.getIn().getHeader("team", String.class);
				
				PlayerPanel playerPanel = mainGrid.getPlayerPanelMap().get(team);
				playerPanel.unregisterPlayer(playerId);
				
			}
		})
		.setBody(simple("null"));

	}

}
