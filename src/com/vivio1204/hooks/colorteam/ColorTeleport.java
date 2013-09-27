package com.vivio1204.hooks.colorteam;

import org.bukkit.plugin.java.JavaPlugin;

import com.github.ucchyocean.ct.ColorTeaming;

public class ColorTeleport extends JavaPlugin{

	@Override
	public void onEnable(){
		
		// ColorTeamingをロードする。
		// NOTE: plugin.ymlのdependに、ColorTeamingを追加しているので、変数ctはnullにはなりえない。
		ColorTeaming ct = (ColorTeaming)getServer().getPluginManager().getPlugin("ColorTeaming");
		String ctversion = ct.getDescription().getVersion();
		if ( !isUpperVersion(ctversion, "2.2.0") ) {
			getLogger().warning("ColorTeaming のバージョンが古いため、ColorTeleport が使用できません。");
			getLogger().warning("ColorTeaming v2.2.0 以降のバージョンをご利用ください。");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		// イベントを登録する
		getServer().getPluginManager().registerEvents(new ColorListener(this), this);
		
		// コマンド実行クラスを登録する
		getCommand("colorteleport").setExecutor(new ColorCommand(ct));
	}

	/**
	 * 指定されたバージョンが、基準より新しいバージョンかどうかを確認する
	 * @param version 確認するバージョン
	 * @param border 基準のバージョン
	 * @return 基準より確認対象の方が新しいバージョンかどうか<br/>
	 * ただし、無効なバージョン番号（数値でないなど）が指定された場合はfalseに、
	 * 2つのバージョンが完全一致した場合はtrueになる。
	 */
	private static boolean isUpperVersion(String version, String border) {

		String[] versionArray = version.split("\\.");
		int[] versionNumbers = new int[versionArray.length];
		for ( int i=0; i<versionArray.length; i++ ) {
			if ( !versionArray[i].matches("[0-9]+") )
				return false;
			versionNumbers[i] = Integer.parseInt(versionArray[i]);
		}

		String[] borderArray = border.split("\\.");
		int[] borderNumbers = new int[borderArray.length];
		for ( int i=0; i<borderArray.length; i++ ) {
			if ( !borderArray[i].matches("[0-9]+") )
				return false;
			borderNumbers[i] = Integer.parseInt(borderArray[i]);
		}

		int index = 0;
		while ( (versionNumbers.length > index) && (borderNumbers.length > index) ) {
			if ( versionNumbers[index] > borderNumbers[index] ) {
				return true;
			} else if ( versionNumbers[index] < borderNumbers[index] ) {
				return false;
			}
			index++;
		}
		if ( borderNumbers.length == index ) {
			return true;
		} else {
			return false;
		}
	}
}