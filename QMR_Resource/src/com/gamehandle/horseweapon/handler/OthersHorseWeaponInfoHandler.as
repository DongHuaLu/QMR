package com.game.horseweapon.handler{

	import com.game.horseweapon.message.OthersHorseWeaponInfoMessage;
	import net.Handler;

	public class OthersHorseWeaponInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: OthersHorseWeaponInfoMessage = OthersHorseWeaponInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}