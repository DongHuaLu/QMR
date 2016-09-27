package com.game.hiddenweapon.handler{

	import com.game.hiddenweapon.message.ResHiddenWeaponSkillRestrictionMessage;
	import net.Handler;

	public class ResHiddenWeaponSkillRestrictionHandler extends Handler {
	
		public override function action(): void{
			var msg: ResHiddenWeaponSkillRestrictionMessage = ResHiddenWeaponSkillRestrictionMessage(this.message);
			//TODO 添加消息处理
		}
	}
}