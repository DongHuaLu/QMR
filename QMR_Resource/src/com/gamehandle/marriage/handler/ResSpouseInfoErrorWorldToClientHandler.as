package com.game.marriage.handler{

	import com.game.marriage.message.ResSpouseInfoErrorWorldToClientMessage;
	import net.Handler;

	public class ResSpouseInfoErrorWorldToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSpouseInfoErrorWorldToClientMessage = ResSpouseInfoErrorWorldToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}