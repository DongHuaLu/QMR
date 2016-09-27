package com.game.marriage.handler{

	import com.game.marriage.message.ResTeaseSpouseToClientMessage;
	import net.Handler;

	public class ResTeaseSpouseToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTeaseSpouseToClientMessage = ResTeaseSpouseToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}