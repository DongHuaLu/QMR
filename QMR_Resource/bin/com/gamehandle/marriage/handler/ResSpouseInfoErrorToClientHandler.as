package com.game.marriage.handler{

	import com.game.marriage.message.ResSpouseInfoErrorToClientMessage;
	import net.Handler;

	public class ResSpouseInfoErrorToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSpouseInfoErrorToClientMessage = ResSpouseInfoErrorToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}