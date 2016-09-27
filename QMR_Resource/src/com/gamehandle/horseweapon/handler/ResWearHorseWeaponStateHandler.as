package com.game.horseweapon.handler{

	import com.game.horseweapon.message.ResWearHorseWeaponStateMessage;
	import net.Handler;

	public class ResWearHorseWeaponStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWearHorseWeaponStateMessage = ResWearHorseWeaponStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}