package com.game.map.handler{

	import com.game.map.message.ResRoundPetMessage;
	import net.Handler;

	public class ResRoundPetHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundPetMessage = ResRoundPetMessage(this.message);
			//TODO 添加消息处理
		}
	}
}