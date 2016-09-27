package com.game.signwage.handler{

	import com.game.signwage.message.ResSignWagetoWageInfoMessage;
	import net.Handler;

	public class ResSignWagetoWageInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSignWagetoWageInfoMessage = ResSignWagetoWageInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}