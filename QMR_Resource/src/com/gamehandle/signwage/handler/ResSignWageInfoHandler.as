package com.game.signwage.handler{

	import com.game.signwage.message.ResSignWageInfoMessage;
	import net.Handler;

	public class ResSignWageInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSignWageInfoMessage = ResSignWageInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}