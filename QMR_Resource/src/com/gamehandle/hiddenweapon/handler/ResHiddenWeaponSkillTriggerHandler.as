package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponSkillTriggerMessage;
	import net.Handler;

	public class ResHiddenWeaponSkillTriggerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponSkillTriggerMessage = ResHiddenWeaponSkillTriggerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}