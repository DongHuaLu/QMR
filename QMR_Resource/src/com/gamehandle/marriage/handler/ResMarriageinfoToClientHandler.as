package com.game.marriage.handler{

	import com.game.marriage.message.ResMarriageinfoToClientMessage;
	import net.Handler;

	public class ResMarriageinfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMarriageinfoToClientMessage = ResMarriageinfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}