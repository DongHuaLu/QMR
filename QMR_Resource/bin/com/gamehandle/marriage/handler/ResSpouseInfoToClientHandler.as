package com.game.marriage.handler{

	import com.game.marriage.message.ResSpouseInfoToClientMessage;
	import net.Handler;

	public class ResSpouseInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSpouseInfoToClientMessage = ResSpouseInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}