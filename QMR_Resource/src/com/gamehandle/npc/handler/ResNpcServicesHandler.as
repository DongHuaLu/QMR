package com.game.npc.handler{

	import com.game.npc.message.ResNpcServicesMessage;
	import net.Handler;

	public class ResNpcServicesHandler extends Handler {
	
		public override function action(): void{
			var msg: ResNpcServicesMessage = ResNpcServicesMessage(this.message);
			//TODO 添加消息处理
		}
	}
}