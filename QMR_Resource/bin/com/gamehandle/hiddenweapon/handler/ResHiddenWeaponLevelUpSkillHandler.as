package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponLevelUpSkillMessage;
	import net.Handler;

	public class ResHiddenWeaponLevelUpSkillHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponLevelUpSkillMessage = ResHiddenWeaponLevelUpSkillMessage(this.message);
			//TODO 添加消息处理
		}
	}
}