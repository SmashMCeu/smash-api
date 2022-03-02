package eu.smashmc.api.proxysystem;

import net.md_5.bungee.api.connection.ProxiedPlayer;

class ProxyServiceFallback implements ProxyService {

	@Override
	public boolean hasStreamerModeEnabled(ProxiedPlayer player) {
		return false;
	}

}
