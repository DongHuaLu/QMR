package com.game.equipstreng.handler{

	import com.game.equipstreng.message.ResStrengItemToClientMessage;
	import net.Handler;

	public class ResStrengItemToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStrengItemToClientMessage = ResStrengItemToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}