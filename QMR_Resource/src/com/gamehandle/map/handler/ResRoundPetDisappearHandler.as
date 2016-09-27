package com.game.map.handler{

	import com.game.map.message.ResRoundPetDisappearMessage;
	import net.Handler;

	public class ResRoundPetDisappearHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundPetDisappearMessage = ResRoundPetDisappearMessage(this.message);
			//TODO 添加消息处理
		}
	}
}