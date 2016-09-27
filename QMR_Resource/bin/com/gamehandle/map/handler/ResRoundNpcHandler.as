package com.game.map.handler{

	import com.game.map.message.ResRoundNpcMessage;
	import net.Handler;

	public class ResRoundNpcHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundNpcMessage = ResRoundNpcMessage(this.message);
			//TODO 添加消息处理
		}
	}
}