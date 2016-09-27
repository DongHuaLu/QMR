package com.game.npc.handler{

	import com.game.npc.message.ResNpcActionMessage;
	import net.Handler;

	public class ResNpcActionHandler extends Handler {
	
		public override function action(): void{
			var msg: ResNpcActionMessage = ResNpcActionMessage(this.message);
			//TODO 添加消息处理
		}
	}
}