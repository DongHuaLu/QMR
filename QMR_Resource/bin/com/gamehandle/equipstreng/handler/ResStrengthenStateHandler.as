package com.game.equipstreng.handler{

	import com.game.equipstreng.message.ResStrengthenStateMessage;
	import net.Handler;

	public class ResStrengthenStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStrengthenStateMessage = ResStrengthenStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}